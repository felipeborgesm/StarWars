package com.letscode.api.starwars.repository;

import com.letscode.api.starwars.domains.Rebel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends PagingAndSortingRepository<Rebel,Long> {

  @Query(nativeQuery = true, value = "SELECT (CAST((SELECT count(id) from rebel where report_counter >= 3) AS NUMERIC(100, 2)) /  CAST((select count(id) from rebel) AS NUMERIC(100, 2))) * 100")
  Float countTraitorPercentage();

  @Query(nativeQuery = true, value = "SELECT CAST(sum(food) AS NUMERIC(100, 2)) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  Float countAverageFood();

  @Query(nativeQuery = true, value = "SELECT CAST(sum(water) AS NUMERIC(100, 2)) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  Float countAverageWater();

  @Query(nativeQuery = true, value = "SELECT CAST(sum(ammo) AS NUMERIC(100, 2)) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  Float countAverageAmmo();

  @Query(nativeQuery = true, value = "SELECT CAST(sum(weapon) AS NUMERIC(100, 2)) / (select count(id) from rebel where report_counter < 3) from rebel where report_counter  < 3")
  Float countAverageWeapon();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(food),0)) from rebel where report_counter  >= 3")
  Integer countByFoodLostToTraitors();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(water),0)) from rebel where report_counter  >= 3")
  Integer countByWaterLostToTraitors();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(ammo),0)) from rebel where report_counter  >= 3")
  Integer countByAmmoLostToTraitors();

  @Query(nativeQuery = true,value = "SELECT (IFNULL(sum(weapon),0)) from rebel where report_counter  >= 3")
  Integer countByWeaponLostToTraitors();

}
