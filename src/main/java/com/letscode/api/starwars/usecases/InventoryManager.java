package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.domains.enums.InventoryItems;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class InventoryManager {

  private final RebelPersistenceGateWay rebelPersistenceGateWay;

  public void addItem(String rebelId, InventoryItems item) {
    Map<InventoryItems, Integer> inventory = rebelPersistenceGateWay.getInventory(rebelId);
    inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    rebelPersistenceGateWay.updateInventory(rebelId, inventory);
  }

  public void removeItem(String rebelId, InventoryItems item) {
    Map<InventoryItems, Integer> inventory = rebelPersistenceGateWay.getInventory(rebelId);
    inventory.put(item, inventory.getOrDefault(item, 0) - 1);
    rebelPersistenceGateWay.updateInventory(rebelId, inventory);
  }

  public void createInventory(Rebel rebel, Map<InventoryItems, Integer> inventory) {
    rebelPersistenceGateWay.createInventory(rebel, inventory);
  }

  Map<InventoryItems, Integer> inventory =
      Map.ofEntries(Map.entry(InventoryItems.WEAPON, InventoryItems.WEAPON.getValor()));


}
