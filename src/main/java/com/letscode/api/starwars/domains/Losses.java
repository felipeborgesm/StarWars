package com.letscode.api.starwars.domains;

import lombok.*;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Losses {
  @Builder.Default
  private int foodLost = 0;
  @Builder.Default
  private int waterLost = 0;
  @Builder.Default
  private int ammoLost = 0;
  @Builder.Default
  private int weaponLost = 0;

  public int getTotalLost(){
    return foodLost + waterLost + ammoLost + weaponLost;
  }
}
