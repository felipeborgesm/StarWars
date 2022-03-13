package com.letscode.api.starwars.domains;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class RebelReport {
  private float traitorPercentage;
  private float averageFood;
  private float averageWater;
  private float averageAmmo;
  private float averageWeapon;
  private Losses traitorLosesInPoints;

  public float getRebelPercentage(){
    return 100f - traitorPercentage;
  }
}
