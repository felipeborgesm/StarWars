package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.domains.Location;
import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.services.RebelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rebels")
public class RebelsController {

  private final RebelServices rebelServices;

  @GetMapping
  public ResponseEntity<?> listRebels(Pageable page) {
    return ResponseEntity.ok(rebelServices.getAll(page));
  }

  @PostMapping
  public ResponseEntity<?> addRebel(@RequestBody Rebel rebel, UriComponentsBuilder uriBuilder) {
    return ResponseEntity.created(uriBuilder.replacePath("/api/rebels/{id}").buildAndExpand(rebelServices.add(rebel)).toUri()).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getRebel(@PathVariable("id") Long id) {
    return rebelServices
        .getRebel(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping("/{id}/location")
  public ResponseEntity<?> reportRebelLocation(@PathVariable("id") Long id, @RequestBody Location location) {
    rebelServices.updateLocation(id,location);
    return ResponseEntity.accepted().build();
  }

  @PutMapping("/{id}/report")
  public ResponseEntity<?> reportTraitorRebel(@PathVariable("id") Long id) {
    rebelServices.reportAsTraitor(id);
    return ResponseEntity.accepted().build();
  }


}
