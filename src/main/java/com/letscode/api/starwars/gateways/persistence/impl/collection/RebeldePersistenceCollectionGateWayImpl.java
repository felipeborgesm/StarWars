package com.letscode.api.starwars.gateways.persistence.impl.collection;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.gateways.persistence.RebeldePersistenceGateWay;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RebeldePersistenceCollectionGateWayImpl implements RebeldePersistenceGateWay {

  private static final Map<String, Rebel> rebels = new HashMap<>();

  @Override
  public Rebel save(Rebel rebel) {
    if (!StringUtils.hasText(rebel.getId())) {
      rebel.setId(UUID.randomUUID().toString());
    }
    rebels.put(rebel.getId(), rebel);
    return rebel;
  }

  @Override
  public boolean existsById(String id) {
    return rebels.containsKey(id);
  }
}
