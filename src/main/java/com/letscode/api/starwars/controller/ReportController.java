package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.domains.enums.InventoryItems;
import com.letscode.api.starwars.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

  private final ReportService service;

  @GetMapping("/traitors")
  public ResponseEntity<?> traitorPercentageReport() {
    return ResponseEntity.ok(service.getTraitorPercentage());
  }

  @GetMapping("/rebels")
  public ResponseEntity<?> rebelsPercentageReport() {
    return ResponseEntity.ok(service.getRebelsPercentage());
  }

  @GetMapping("/resource/{resourceName}")
  public ResponseEntity<?> resourcePercentage(@PathVariable("resourceName") InventoryItems resourceName) {
    return ResponseEntity.ok(service.getAverageResourcePerRebel(resourceName));
  }

  @GetMapping("/losses")
  public ResponseEntity<?> lossesReport() {
    return ResponseEntity.ok(service.getLossesToRebels());
  }


}
