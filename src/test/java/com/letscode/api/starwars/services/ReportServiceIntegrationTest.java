package com.letscode.api.starwars.services;

import com.github.javafaker.Faker;
import com.letscode.api.starwars.domains.*;
import com.letscode.api.starwars.domains.enums.Gender;
import com.letscode.api.starwars.domains.enums.InventoryItems;
import com.letscode.api.starwars.repository.RebelRepository;
import com.letscode.api.starwars.utils.TradeUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Integrated Test :: Report Service")
class ReportServiceIntegrationTest {

  @Autowired
  private ReportService service;

  @Autowired
  private RebelRepository repository;
  private final Faker faker = new Faker();
  private final Random random = new Random();

  @Test
  @DisplayName("Half of my base is traitor")
  void shouldHaveHalfSiths() {
    repository.saveAll(List.of(buildRandomRebel(0), buildRandomRebel(4)));
    assertEquals(50.0, service.getTraitorPercentage());
  }

  @Test
  @DisplayName("Half of my base is rebel")
  void shouldHaveHalfRebels() {
    repository.saveAll(List.of(buildRandomRebel(0), buildRandomRebel(4)));
    assertEquals(50.0, service.getRebelsPercentage());
  }

  @Test
  @DisplayName("Resource Average")
  void shouldHaveAnAveragePercentageOfResource() {

    Rebel rebel = buildRandomRebel(0);
    repository.save(rebel);

    assertEquals((double) rebel.getInventory().getWater(), service.getAverageResourcePerRebel(InventoryItems.WATER));
    assertEquals((double) rebel.getInventory().getFood(), service.getAverageResourcePerRebel(InventoryItems.FOOD));
    assertEquals((double) rebel.getInventory().getWeapon(), service.getAverageResourcePerRebel(InventoryItems.WEAPON));
    assertEquals((double) rebel.getInventory().getAmmo(), service.getAverageResourcePerRebel(InventoryItems.AMMO));

  }

  @Test
  @DisplayName("Sith won a few")
  void shouldHaveLostAFewToTraitors() {

    Rebel rebel = buildRandomRebel(0);
    Rebel sith = buildRandomRebel(4);
    repository.saveAll(List.of(rebel, sith));

    assertEquals(
        TradeUtils.getTradeValue(sith.getInventory()),
        service.getLossesToTraitors().getTotalLost()
    );

  }

  @Test
  @DisplayName("Full Report Working")
  void shouldGetFullReport() {
    Rebel rebel1 = buildRandomRebel(0);
    Rebel rebel2 = buildRandomRebel(0);
    repository.saveAll(List.of(rebel1, rebel2));

    RebelReport report = service.getRebelReport();

    assertThat(report)
        .isNotNull()
        .hasNoNullFieldsOrProperties()
        .hasFieldOrPropertyWithValue("traitorPercentage", 0f)
        .hasFieldOrPropertyWithValue("traitorLosesInPoints", Losses.builder().build());

    assertEquals(
        ((((float) rebel1.getInventory().getAmmo() + (float) rebel2.getInventory().getAmmo()) / 2)),
        (report.getAverageAmmo()));

    assertEquals(
        ((((float) rebel1.getInventory().getFood() + (float) rebel2.getInventory().getFood()) / 2)),
        (report.getAverageFood()));

    assertEquals(
        ((((float) rebel1.getInventory().getWater() + (float) rebel2.getInventory().getWater()) / 2)),
        (report.getAverageWater()));

    assertEquals(
        ((((float) rebel1.getInventory().getWeapon() + (float) rebel2.getInventory().getWeapon()) / 2)),
        (report.getAverageWeapon()));

  }


  @AfterEach
  public void tearDown() {
    repository.deleteAll();
  }

  private Rebel buildRandomRebel(int darknesLevel) {
    String name = faker.starTrek().villain();
    name = name.length() > 120 ? name.substring(0, 120) : name;
    name = name.length() < 5 ? name + "Lerp" : name;
    return Rebel
        .builder()
        .name(name)
        .gender(Gender.UNKNOWN)
        .age(random.nextInt(75) + 1)
        .inventory(
            Inventory
                .builder()
                .ammo(random.nextInt(10))
                .food(random.nextInt(10))
                .water(random.nextInt(10))
                .weapon(random.nextInt(10))
                .build()
        )
        .location(buildLocation())
        .reportCounter(darknesLevel)
        .build();
  }

  private Location buildLocation() {
    return Location
        .builder()
        .latitude(Float.parseFloat(faker.address().latitude().replace(",",".")))
        .longitude(Float.parseFloat(faker.address().longitude().replace(",",".")))
        .name(faker.starTrek().location())
        .build();
  }

}