package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.domains.Trade;
import com.letscode.api.starwars.services.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/market")
public class InventoryController {

  private final MarketService service;

  @PutMapping("/{id}")
  public ResponseEntity<?> tradeItem(@PathVariable("id") Long id, @RequestBody Trade trade) {
    service.trade(id,trade);
    return ResponseEntity.accepted().build();
  }
}
