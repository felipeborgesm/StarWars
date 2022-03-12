package com.letscode.api.starwars.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

  private Float latitude;
  private Float longitude;
  @Column(name = "location_name")
  private String name;
}
