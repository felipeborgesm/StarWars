package com.letscode.api.starwars.services;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.domains.Trade;
import com.letscode.api.starwars.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketService {
  private final RebelRepository repository;


  public void trade(Long id, Trade trade) {
    Optional<Rebel> givingRebel = repository.findById(id);
    Optional<Rebel> receivingRebel = repository.findById(trade.getRebelId());

    givingRebel
        .map(rebel -> rebel.withInventory(rebel.getInventory().remove(trade.getGiving())))
        .map(rebel -> rebel.withInventory(rebel.getInventory().add(trade.getReceiving())))
        .map(repository::save)
        .orElseThrow();

    receivingRebel
        .map(rebel -> rebel.withInventory(rebel.getInventory().remove(trade.getReceiving())))
        .map(rebel -> rebel.withInventory(rebel.getInventory().add(trade.getGiving())))
        .map(repository::save)
        .orElseThrow();

  }
}
