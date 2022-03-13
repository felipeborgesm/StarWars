package com.letscode.api.starwars.services;

import com.letscode.api.starwars.Constants;
import com.letscode.api.starwars.domains.Losses;
import com.letscode.api.starwars.domains.RebelReport;
import com.letscode.api.starwars.domains.enums.InventoryItems;
import com.letscode.api.starwars.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p><b>Rebels Book log</b></p>
 * <p>A report class responsible for generating reports</p>
 * It generate multiple reports of the rebel cause. Used by the Tallyman.
 */
@Service
@RequiredArgsConstructor
public class ReportService {

  private final RebelRepository repository;

  /**
   * <p><b>Traitor's Percentage</b></p>
   * Gives a percentage of how many traitors are among us.
   * @return a percentage of traitors.
   */
  public float getTraitorPercentage() {
    return repository.countTraitorPercentage();
  }

  /**
   * <p><b>Rebel's Percentage</b></p>
   * Gives a percentage of how many rebels are still loyal to the cause.
   * @return a percentage of loyal rebels.
   */
  public float getRebelsPercentage() {
    return 100f - repository.countTraitorPercentage();
  }

  /**
   * <p><b>Average resources</b></p>
   * Gives an average of each kind of resource per rebel.
   * It only considers rebels, ignoring the losses to traitors.
   * @param resourceName The {@link InventoryItems} kind of resource.
   * @return the average of that kind of resource per rebel.
   */
  public float getAverageResourcePerRebel(InventoryItems resourceName){
    switch (resourceName){
      case AMMO: return repository.countAverageAmmo();
      case WATER: return repository.countAverageWater();
      case WEAPON: return repository.countAverageWeapon();
      default: return repository.countAverageFood();
    }
  }

  /**
   * <p><b>Points Lost</b></p>
   * Gives the amount of points per resource lost to traitors.
   * @return A {@link Losses} report of all resources, in points that we lost for traitors.
   */
  public Losses getLossesToTraitors(){
    return Losses
        .builder()
        .foodLost(repository.countByFoodLostToTraitors() * Constants.FOOD_POINTS)
        .waterLost(repository.countByWaterLostToTraitors() * Constants.WATER_POINTS)
        .weaponLost(repository.countByWeaponLostToTraitors() * Constants.WEAPON_POINTS)
        .ammoLost(repository.countByAmmoLostToTraitors() * Constants.AMMO_POINTS)
        .build();
  }

  /**
   * <p><b>Tallyman Report</b></p>
   * A comprehensive report for the Tallyman with all above data combined.
   * @return a singlw {@link RebelReport} with all data.
   */
  public RebelReport getRebelReport(){
    return RebelReport
        .builder()
        .traitorPercentage(getTraitorPercentage())
        .averageAmmo(getAverageResourcePerRebel(InventoryItems.AMMO))
        .averageFood(getAverageResourcePerRebel(InventoryItems.FOOD))
        .averageWater(getAverageResourcePerRebel(InventoryItems.WATER))
        .averageWeapon(getAverageResourcePerRebel(InventoryItems.WEAPON))
        .traitorLosesInPoints(getLossesToTraitors())
        .build();
  }

}
