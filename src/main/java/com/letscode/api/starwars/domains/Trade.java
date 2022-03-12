package com.letscode.api.starwars.domains;


import lombok.*;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
  private Long rebelId;
  private Inventory giving;
  private Inventory receiving;
}
