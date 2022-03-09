package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.enums.InventoryItems;
import java.util.Map;

public class CreateInventory {
  Map<InventoryItems, Integer> itemsPoints =
      Map.ofEntries(Map.entry(InventoryItems.Weapon, InventoryItems.Weapon.getValor()));


}
