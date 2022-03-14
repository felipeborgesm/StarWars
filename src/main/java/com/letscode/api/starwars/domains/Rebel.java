package com.letscode.api.starwars.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.api.starwars.domains.enums.Gender;

import lombok.*;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rebel {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  @Length(min = 5, max = 127)
  @Column(length = 127)
  private String name;

  @NotNull
  @Positive
  private int age = 1;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Gender gender;

  @Embedded
  @NotNull
  private Location location;

  @Embedded
  @NotNull
  private Inventory inventory;

  @Builder.Default
  @JsonIgnore
  private int reportCounter = 0;

  @Transient
  @JsonIgnore
  public boolean isTraitor() {
    return reportCounter >= 3;
  }
}
