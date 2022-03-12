package com.letscode.api.starwars.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Inventory {
 private Integer water = 0;
 private Integer food = 0;
 private Integer ammo = 0;
 private Integer weapon = 0;

 public Inventory add(Inventory inventory){
  water += inventory.getWater();
  food += inventory.getFood();
  ammo += inventory.getAmmo();
  weapon += inventory.getWeapon();
  return this;
 }

 public Inventory remove(Inventory inventory){
  water -= inventory.getWater();
  food -= inventory.getFood();
  ammo -= inventory.getAmmo();
  weapon -= inventory.getWeapon();
  return this;
 }
}
