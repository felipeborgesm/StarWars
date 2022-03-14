package com.letscode.api.starwars.domains;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Inventory {

 @NotNull
 @PositiveOrZero(message = "Water must be zero or a positive value")
 private Integer water = 0;
 @NotNull
 @PositiveOrZero(message = "Food must be zero or a positive value")
 private Integer food = 0;
 @NotNull
 @PositiveOrZero(message = "Ammo must be zero or a positive value")
 private Integer ammo = 0;
 @NotNull
 @PositiveOrZero(message = "Weapon must be zero or a positive value")
 private Integer weapon = 0;

}
