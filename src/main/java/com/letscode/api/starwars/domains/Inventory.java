package com.letscode.api.starwars.domains;

import com.letscode.api.starwars.domains.enums.InventoryItems;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Inventory {
  private String id;
  Map<InventoryItems, Integer> items = new HashMap<>();
}
