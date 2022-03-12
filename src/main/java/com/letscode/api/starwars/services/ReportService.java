package com.letscode.api.starwars.services;

import com.letscode.api.starwars.domains.Losses;
import com.letscode.api.starwars.domains.enums.InventoryItems;
import com.letscode.api.starwars.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final RebelRepository repository;

  public double getTraitorPercentage() {
    return repository.countTraitorPercentage();
  }

  public double getRebelsPercentage() {
    return repository.countRebelsPercentage();
  }

  public double getAverageResourcePerRebel(InventoryItems resourceName){
    switch (resourceName){
      case FOOD: return repository.countAverageFood();
      case AMMO: return repository.countAverageAmmo();
      case WATER: return repository.countAverageWater();
      case WEAPON: return repository.countAverageWeapon();
      default: return 0.0;
    }
  }

  public Losses getLossesToRebels(){
    return Losses
        .builder()
        .foodLost(repository.countByFoodLostToTraitors())
        .waterLost(repository.countByWaterLostToTraitors() * 2)
        .weaponLost(repository.countByWeaponLostToTraitors() * 4)
        .ammoLost(repository.countByAmmoLostToTraitors() * 3)
        .build();
  }

}
