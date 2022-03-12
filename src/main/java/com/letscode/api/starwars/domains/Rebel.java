package com.letscode.api.starwars.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.api.starwars.domains.enums.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
  private int age = 0;

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
