package com.letscode.api.starwars.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.letscode.api.starwars.domains.Location;
import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.repository.RebelRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RebelServices {

  private final RebelRepository repository;

  public Long add(Rebel rebel) {
    return repository.save(rebel).getId();
  }

  public Page<Rebel> getAll(Pageable page) {
    return repository.findAll(page);
  }

  public Optional<Rebel> getRebel(Long id) {
    return repository.findById(id);
  }

  public void updateLocation(Long id, Location location) {
    getRebel(id)
        .map(rebel -> rebel.withLocation(location))
        .map(repository::save)
        .orElseThrow();
  }

  public void reportAsTraitor(Long id) {
    getRebel(id)
        .map(rebel -> rebel.withReportCounter(rebel.getReportCounter()+1))
        .map(repository::save)
        .orElseThrow();
  }
}
