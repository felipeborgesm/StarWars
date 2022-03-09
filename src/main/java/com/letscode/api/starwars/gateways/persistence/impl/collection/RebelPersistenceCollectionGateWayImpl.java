package com.letscode.api.starwars.gateways.persistence.impl.collection;

import com.letscode.api.starwars.domains.Inventory;
import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.domains.enums.InventoryItems;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import org.springframework.util.StringUtils;

import java.util.*;

public class RebelPersistenceCollectionGateWayImpl implements RebelPersistenceGateWay {

  private static final Map<String, Rebel> rebels = new HashMap<>();

  @Override
  public Rebel save(Rebel rebel) {
    if (rebel.getId() == null || !StringUtils.hasText(rebel.getId())) {
      rebel.setId(UUID.randomUUID().toString());
    }
    rebels.put(rebel.getId(), rebel);
    return rebel;
  }

  @Override
  public void updateLocation(Rebel rebel, ArrayList coordinates) { rebel.setLocation(coordinates); }

  @Override
  public boolean existsById(String id) {
    return rebels.containsKey(id);
  }

  @Override
  public void delete(Rebel rebel) {
    rebels.remove(rebel.getId());
  }

  @Override
  public List<Rebel> findAll() {
    return new ArrayList<>(rebels.values());
  }

  @Override
  public Optional<Rebel> findById(String id) {
    return Optional.ofNullable(rebels.get(id));
  }

  @Override
  public Map<InventoryItems, Integer> getInventory(String rebelId) {
    return null;
  }

  @Override
  public void createInventory(Rebel rebel, Map<InventoryItems, Integer> inventory) {
    rebel.setInventory((Inventory) inventory);
  }

  @Override
  public void updateInventory(String rebelId, Map<InventoryItems, Integer> inventory){
    Rebel rebel = rebels.get(rebelId);
    rebel.setInventory((Inventory) inventory);
  }
}
