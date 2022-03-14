package com.letscode.api.starwars.utils;

import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.letscode.api.starwars.domains.Inventory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit Test :: Trade Utils")
class TradeUtilsTest {

  private final Random random = new Random();

  @Test
  @DisplayName("Trade Value")
  void shouldGetTradeValueProperly() {
    Inventory target = buildInventory(1,1,1,1);
    assertEquals(10, TradeUtils.getTradeValue(target));
  }

  @Test
  @DisplayName("Trade Value 0 when null")
  void shouldGetTradeValue0() {
    assertEquals(0, TradeUtils.getTradeValue(null));
  }

  @Test
  @DisplayName("Add to inventory")
  void shouldAddToInventory(){

    Inventory origin = buildInventory(1,1,1,1);
    Inventory trade = buildInventory(1,1,1,1);
    Inventory target = buildInventory(2,2,2,2);
    assertEquals(target, TradeUtils.add(origin,trade));

  }

  @Test
  @DisplayName("Remove from inventory")
  void shouldRemoveToInventory(){

    Inventory origin = buildInventory(1,1,1,1);
    Inventory trade = buildInventory(1,1,1,1);
    Inventory target = buildInventory(0,0,0,0);
    assertEquals(target, TradeUtils.remove(origin,trade));

  }


  @Test
  @DisplayName("Keep null inventory on add")
  void shouldNotAddIfInventoryIsNull(){

    Inventory trade = buildInventory(1,1,1,1);
    assertNull(TradeUtils.add(null,trade));

  }

  @Test
  @DisplayName("Keep inventory on null trade")
  void shouldNotAddIfTradeIsNull(){
    Inventory origin = buildInventory(1,1,1,1);
    Inventory target = buildInventory(1,1,1,1);
    assertEquals(target, TradeUtils.add(origin,null));
  }

  @Test
  @DisplayName("Inventory will still be null")
  void shouldKeepNullInventoryOnRem(){

    Inventory origin = buildInventory(1,1,1,1);
    Inventory trade = buildInventory(1,1,1,1);
    Inventory target = buildInventory(0,0,0,0);
    assertNull(TradeUtils.remove(null,trade));

  }

  @Test
  @DisplayName("Keep inventory on null trade")
  void shouldNotRemIfTradeIsNull(){

    Inventory origin = buildInventory(1,1,1,1);
    Inventory target = buildInventory(1,1,1,1);
    assertEquals(target, TradeUtils.remove(origin,null));

  }

  @Test
  @DisplayName("Should be able to remove items from inventory")
  void shouldBeAbleToRemove(){
    Inventory origin = buildInventory(1,1,1,1);
    Inventory target = buildInventory(1,1,1,1);
    assertTrue(TradeUtils.canRemove(origin,target));
  }

  @Test
  @DisplayName("Can't remove if ends up negative")
  void shouldNotBeAbleToRemove(){
    Inventory origin = buildInventory(1,1,1,1);
    Inventory target = buildInventory(2,1,1,1);
    assertFalse(TradeUtils.canRemove(origin,target));
  }

  private Inventory buildInventory() {
    return buildInventory(
        random.nextInt(),
        random.nextInt(),
        random.nextInt(),
        random.nextInt()
    );
  }

  private Inventory buildInventory(int ammo, int food, int water, int weapon) {
    return Inventory
        .builder()
        .ammo(ammo)
        .food(food)
        .water(water)
        .weapon(weapon)
        .build();
  }
}
