package com.example.urlShortener.repositories;

import com.example.urlShortener.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    Optional<Url> findByShortCode(String shortCode);
    Optional<Url> findByUrl(String url);
}
