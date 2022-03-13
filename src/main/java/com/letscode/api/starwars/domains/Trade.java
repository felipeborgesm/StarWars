package com.letscode.api.starwars.domains;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
  @NotNull
  @Positive
  private Long rebelId;

  @NotNull
  private Inventory giving;

  @NotNull
  private Inventory receiving;
}
