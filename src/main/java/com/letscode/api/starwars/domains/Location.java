package com.letscode.api.starwars.domains;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.letscode.api.starwars.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

  @NotNull
  @Min(message = Constants.LATITUDE_MESSAGE,value = -90)
  @Max(message = Constants.LATITUDE_MESSAGE,value = 90)
  private Float latitude;

  @NotNull
  @Min(message = Constants.LONGITUDE_MESSAGE,value = -180)
  @Max(message = Constants.LONGITUDE_MESSAGE,value = 180)
  private Float longitude;

  @Column(name = "location_name",length = 255)
  @NotNull
  @Size(min = 3,max = 255)
  private String name;
}
