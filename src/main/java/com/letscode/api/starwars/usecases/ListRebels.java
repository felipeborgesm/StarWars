package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListRebels {

  private final RebelPersistenceGateWay rebelPersistenceGateWay;

  public List<Rebel> execute() {
    return rebelPersistenceGateWay.findAll();
  }
}
