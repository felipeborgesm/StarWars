package com.letscode.api.starwars.repository;

import com.letscode.api.starwars.domains.Rebel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends PagingAndSortingRepository<Rebel,Long> {

  @Query(nativeQuery = true, value = "select ((select count(id) from rebel where report_counter >= 3) /  (select count(id) from rebel)) * 100")
  double countTraitorPercentage();

  @Query(nativeQuery = true, value = "select ((select count(id) from rebel where report_counter < 3) /  (select count(id) from rebel)) * 100")
  double countRebelsPercentage();

  @Query(nativeQuery = true, value = "SELECT sum(food) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  double countAverageFood();

  @Query(nativeQuery = true, value = "SELECT sum(water) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  double countAverageWater();

  @Query(nativeQuery = true, value = "SELECT sum(ammo) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  double countAverageAmmo();

  @Query(nativeQuery = true, value = "SELECT sum(weapon) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  double countAverageWeapon();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(food),0)) from rebel where report_counter  >= 3")
  int countByFoodLostToTraitors();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(water),0)) from rebel where report_counter  >= 3")
  int countByWaterLostToTraitors();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(ammo),0)) from rebel where report_counter  >= 3")
  int countByAmmoLostToTraitors();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(weapon),0)) from rebel where report_counter  >= 3")
  int countByWeaponLostToTraitors();

}
