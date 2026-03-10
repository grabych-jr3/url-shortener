package com.example.urlShortener.services;

import com.example.urlShortener.dto.UrlDTO;
import com.example.urlShortener.dto.UrlDTOWithoutStats;
import com.example.urlShortener.models.Url;
import com.example.urlShortener.repositories.UrlRepository;
import com.example.urlShortener.util.UrlNotFoundException;
import com.example.urlShortener.util.encoding.Base62Encoding;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional(readOnly = true)
public class UrlService {

    private final UrlRepository urlRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UrlService(UrlRepository urlRepository, ModelMapper modelMapper) {
        this.urlRepository = urlRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UrlDTOWithoutStats createUrl(UrlDTO urlDTO){
        Url url = new Url();
        url.setUrl(urlDTO.getUrl());
        url = urlRepository.save(url);
        url.setShortCode(Base62Encoding.encode(url.getId()));
        urlRepository.save(enrichUrl(url));
        return convertTOUrlUrlDTOWithoutStats(url);
    }

    @Transactional
    public UrlDTOWithoutStats updateUrl(UrlDTO urlDTO, String shortCode){
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Url with short code: " + shortCode + " not found"));

        url.setUrl(urlDTO.getUrl());
        url.setUpdatedAt(Instant.now());
        return convertTOUrlUrlDTOWithoutStats(url);
    }

    @Transactional
    public void deleteUrl(String shortCode){
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Url with short code: " + shortCode + " not found"));
        urlRepository.delete(url);
    }

    @Transactional
    public UrlDTOWithoutStats retrieveUrl(String shortCode){
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Url with short code: " + shortCode + " not found"));
        url.setAccessCount(url.getAccessCount() + 1);
        return convertTOUrlUrlDTOWithoutStats(url);
    }

    public Url retrieveUrlStats(String shortCode){
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Url with short code: " + shortCode + " not found"));
        return url;
    }

    private UrlDTOWithoutStats convertTOUrlUrlDTOWithoutStats(Url url){
        return modelMapper.map(url, UrlDTOWithoutStats.class);
    }

    private Url enrichUrl(Url url){
        url.setCreatedAt(Instant.now());
        url.setUpdatedAt(Instant.now());
        return url;
    }
}
