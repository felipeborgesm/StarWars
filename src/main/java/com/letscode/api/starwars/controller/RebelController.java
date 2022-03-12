package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rebels")
public class RebelController {

  private final RebelRepository rebelRepository;

  @GetMapping
  public ResponseEntity<?> listRebels(Pageable page){

    return ResponseEntity.ok(rebelRepository.findAll(page));

  }

  @PostMapping
  public ResponseEntity<?> addRebel(@RequestBody Rebel rebel, UriComponentsBuilder builder) throws MalformedURLException {
    rebelRepository.save(rebel);
    return ResponseEntity.created(builder.replacePath("/api/rebels/{id}").buildAndExpand(0).toUri()).build();
  }
}
