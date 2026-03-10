package com.example.urlShortener.controllers;

import com.example.urlShortener.dto.UrlDTO;
import com.example.urlShortener.dto.UrlDTOWithoutStats;
import com.example.urlShortener.models.Url;
import com.example.urlShortener.services.UrlService;
import com.example.urlShortener.util.UrlNotCreatedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shorten")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlDTOWithoutStats> createShortUrl(@RequestBody @Valid UrlDTO urlDTO,
                                                             BindingResult bindingResult){
        getAllFieldErrors(bindingResult);
        return new ResponseEntity<>(urlService.createUrl(urlDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlDTOWithoutStats> updateUrl(@RequestBody @Valid UrlDTO urlDTO,
                                         BindingResult bindingResult,
                                         @PathVariable("shortCode") String shortCode){
        getAllFieldErrors(bindingResult);
        return new ResponseEntity<>(urlService.updateUrl(urlDTO, shortCode), HttpStatus.OK);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<HttpStatus> deleteUrl(@PathVariable String shortCode){
        urlService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlDTOWithoutStats> retrieveUrl(@PathVariable String shortCode){
//        Url url = urlService.retrieveUrl(shortCode);
        return new ResponseEntity<>(urlService.retrieveUrl(shortCode), HttpStatus.OK);
//        return "redirect:" + url.getUrl();
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<Url> getUrlStats(@PathVariable String shortCode){
        return new ResponseEntity<>(urlService.retrieveUrlStats(shortCode), HttpStatus.OK);
    }

    private void getAllFieldErrors(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError fieldError : fieldErrors){
                errorMsg.append(fieldError.getField())
                        .append(" - ").append(fieldError.getDefaultMessage())
                        .append(";");
            }
            throw new UrlNotCreatedException(errorMsg.toString());
        }
    }
}
