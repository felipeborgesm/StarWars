package com.letscode.api.starwars.services;

import com.github.javafaker.Faker;
import com.letscode.api.starwars.domains.Inventory;
import com.letscode.api.starwars.domains.Location;
import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.domains.Trade;
import com.letscode.api.starwars.domains.enums.Gender;
import com.letscode.api.starwars.exception.CannotTradeMoreThenHaveException;
import com.letscode.api.starwars.exception.CannotTradeWithSelfException;
import com.letscode.api.starwars.exception.RebelNotFoundException;
import com.letscode.api.starwars.exception.TraitorRebelException;
import com.letscode.api.starwars.repository.RebelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Integrated Test :: Market Service")
class MarketServiceIntegrationTest {

  @Autowired
  private MarketService service;

  @Autowired
  private RebelRepository repository;

  private final Faker faker = new Faker();
  private final Random random = new Random();

  @Test
  @DisplayName("No Trade if no rebel")
  void shouldNotTradeIfNoRebelExist(){
    assertThrows(RebelNotFoundException.class,() -> service.trade(1L,Trade.builder().rebelId(2L).build()));
  }

  @Test
  @DisplayName("No Trade if no rebel target")
  void shouldNotTradeIfNoRebelExistAsTarget(){
    Long id = repository.save(buildRandomRebel()).getId();
    assertThrows(RebelNotFoundException.class,() -> service.trade(id,Trade.builder().giving(
            Inventory
                .builder()
                .ammo(0)
                .food(0)
                .water(0)
                .weapon(0)
                .build()
        )
        .receiving(
            (
                Inventory
                    .builder()
                    .ammo(0)
                    .food(0)
                    .water(0)
                    .weapon(0)
                    .build()
            )
        ).rebelId(999L).build()));
  }

  @Test
  @DisplayName("Should not trade with traitor")
  void shouldNotTradeWithTraitor(){
    Long id = repository.save(buildRandomRebel()).getId();
    Long idTarget = repository.save(buildRandomRebel(true)).getId();
    assertThrows(TraitorRebelException.class,() -> service.trade(id,
        Trade
            .builder()
            .rebelId(idTarget)
            .giving(
                Inventory
                    .builder()
                    .ammo(0)
                    .food(0)
                    .water(0)
                    .weapon(0)
                    .build()
            )
            .receiving(
                (
                    Inventory
                        .builder()
                        .ammo(0)
                        .food(0)
                        .water(0)
                        .weapon(0)
                        .build()
                )
            )
            .build()
    ));
  }

  @Test
  @DisplayName("Should not trade if traitor")
  void shouldNotTradeIfTraitor(){
    Long id = repository.save(buildRandomRebel(true)).getId();
    Long idTarget = repository.save(buildRandomRebel()).getId();
    assertThrows(TraitorRebelException.class,() -> service.trade(id,Trade.builder().rebelId(idTarget).build()));
  }

  @Test
  @DisplayName("Should not complete trade if ends negative")
  void shouldNotTradeIfEndsNegative(){
    Long id = repository.save(buildRandomRebel()).getId();
    Long idTarget = repository.save(buildRandomRebel()).getId();

    assertThrows(CannotTradeMoreThenHaveException.class,() -> service.trade(id,
        Trade
            .builder()
            .rebelId(idTarget)
            .giving(
                Inventory
                    .builder()
                    .ammo(random.nextInt(99))
                    .food(random.nextInt(99))
                    .water(random.nextInt(99))
                    .weapon(random.nextInt(99))
                    .build()
            )
            .receiving(
                (
                    Inventory
                        .builder()
                        .ammo(random.nextInt(99))
                        .food(random.nextInt(99))
                        .water(random.nextInt(99))
                        .weapon(random.nextInt(99))
                        .build()
                )
            )
            .build()
    ));

  }

  @Test
  @DisplayName("Should trade with rebel")
  void shouldTradeWithRebel(){

    Rebel givingRebel =  buildRandomRebel();
    givingRebel.setInventory(Inventory.builder().ammo(1).food(0).water(0).weapon(0).build());
    givingRebel = repository.save(givingRebel);

    Rebel receivingRebel =  buildRandomRebel();
    receivingRebel.setInventory(Inventory.builder().ammo(0).food(3).water(0).weapon(0).build());
    receivingRebel = repository.save(receivingRebel);

    service.trade(givingRebel.getId(),
        Trade
            .builder()
            .rebelId(receivingRebel.getId())
            .giving(
                Inventory
                    .builder()
                    .ammo(1)
                    .food(0)
                    .water(0)
                    .weapon(0)
                    .build()
            )
            .receiving(
                (
                    Inventory
                        .builder()
                        .ammo(0)
                        .food(3)
                        .water(0)
                        .weapon(0)
                        .build()
                )
            )
            .build()
    );

    Rebel givingRebelAfter = repository.findById(givingRebel.getId()).get();
    assertEquals(0,givingRebelAfter.getInventory().getAmmo());
    assertEquals(3,givingRebelAfter.getInventory().getFood());

  }

  @Test
  @DisplayName("Can't trade with self")
  void shouldNotTradeWithSelf(){
    Rebel givingRebel = repository.save(buildRandomRebel());

    assertThrows(CannotTradeWithSelfException.class,() ->
    service.trade(givingRebel.getId(),
        Trade
            .builder()
            .rebelId(givingRebel.getId())
            .giving(
                Inventory
                    .builder()
                    .ammo(0)
                    .food(0)
                    .water(0)
                    .weapon(0)
                    .build()
            )
            .receiving(
                (
                    Inventory
                        .builder()
                        .ammo(0)
                        .food(0)
                        .water(0)
                        .weapon(0)
                        .build()
                )
            )
            .build()
    ));
  }



  @AfterEach
  public void tearDown() {
    repository.deleteAll();
  }

  private Rebel buildRandomRebel() {
    return buildRandomRebel(false);
  }

  private Rebel buildRandomRebel(boolean isTraitor) {
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
        .reportCounter(isTraitor ? 10 : 0)
        .build();
  }

  private Location buildLocation() {
    return Location
        .builder()
        .latitude(Float.parseFloat(faker.address().latitude()))
        .longitude(Float.parseFloat(faker.address().longitude()))
        .name(faker.starTrek().location())
        .build();
  }

}