package com.letscode.api.starwars.services;

import com.letscode.api.starwars.domains.Inventory;
import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.domains.Trade;
import com.letscode.api.starwars.exception.CannotTradeMoreThenHaveException;
import com.letscode.api.starwars.exception.CannotTradeWithSelfException;
import com.letscode.api.starwars.exception.RebelNotFoundException;
import com.letscode.api.starwars.exception.TraitorRebelException;
import com.letscode.api.starwars.repository.RebelRepository;
import com.letscode.api.starwars.utils.TradeUtils;
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

    validateTrade(id, trade.getGiving(), givingRebel);
    validateTrade(trade.getRebelId(), trade.getReceiving(), receivingRebel);
    if(id.equals(trade.getRebelId()))
      throw new CannotTradeWithSelfException(id);

    givingRebel
        .map(rebel -> rebel.withInventory(TradeUtils.remove(rebel.getInventory(),trade.getGiving())))
        .map(rebel -> rebel.withInventory(TradeUtils.add(rebel.getInventory(),trade.getReceiving())))
        .map(repository::save)
        .orElseThrow();

    receivingRebel
        .map(rebel -> rebel.withInventory(TradeUtils.remove(rebel.getInventory(),trade.getReceiving())))
        .map(rebel -> rebel.withInventory(TradeUtils.add(rebel.getInventory(),trade.getGiving())))
        .map(repository::save)
        .orElseThrow();

  }

  private void validateTrade(Long id, Inventory trade, Optional<Rebel> givingRebel) {
    if(givingRebel.isEmpty())
      throw new RebelNotFoundException(id);
    if(givingRebel.get().isTraitor())
      throw new TraitorRebelException(id);
    if(!TradeUtils.canRemove(givingRebel.get().getInventory(), trade))
      throw new CannotTradeMoreThenHaveException(id);
  }
}
