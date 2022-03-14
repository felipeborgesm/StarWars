package com.letscode.api.starwars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.letscode.api.starwars.domains.Inventory;
import com.letscode.api.starwars.domains.Location;
import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.domains.Trade;
import com.letscode.api.starwars.domains.enums.Gender;
import com.letscode.api.starwars.repository.RebelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DisplayName("End-to-End Test :: Inventory Controller ")
class InventoryControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RebelRepository repository;
  private final Faker faker = new Faker();
  private final Random random = new Random();
  private final ObjectMapper mapper = new ObjectMapper();
  private List<Long> ids = new ArrayList<>();

  @BeforeEach
  public void setUp() {
    addRebels(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 10));
  }

  @Test
  @DisplayName("No Trade if no rebel")
  void shouldNotTradeIfNoRebelExist() throws Exception {
    String body = mapper.writeValueAsString(buildTrade(999L));
    this
        .mockMvc
        .perform(
            put("/api/market/365")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string("A rebel with ID 365 wasn't found in our records"));
  }


  @Test
  @DisplayName("No Trade if no rebel target")
  void shouldNotTradeIfNoRebelExistAsTarget() throws Exception {
    String body = mapper.writeValueAsString(buildTrade(999L));
    this
        .mockMvc
        .perform(
            put("/api/market/" + ids.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string("A rebel with ID 999 wasn't found in our records"));

  }

  @Test
  @DisplayName("Should not trade with traitor")
  void shouldNotTradeWithTraitor() throws Exception {
  String body = mapper.writeValueAsString(buildTrade(ids.get(9)));
    this
        .mockMvc
        .perform(
            put("/api/market/" + ids.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andDo(print())
        .andExpect(status().isPreconditionFailed())
        .andExpect(content().string(String.format("A rebel with ID %d is a traitor",ids.get(9))));
  }

  @Test
  @DisplayName("Should not trade if traitor")
  void shouldNotTradeIfTraitor() throws Exception {
    String body = mapper.writeValueAsString(buildTrade(ids.get(1)));
    this
        .mockMvc
        .perform(
            put("/api/market/" + ids.get(9))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andDo(print())
        .andExpect(status().isPreconditionFailed())
        .andExpect(content().string(String.format("A rebel with ID %d is a traitor",ids.get(9))));
  }

  @Test
  @DisplayName("Should not complete trade if ends negative")
  void shouldNotTradeIfEndsNegative() throws Exception {
    Trade trade = buildTrade(ids.get(0));
    trade = trade.withGiving(trade.getGiving().withAmmo(999));
    String body = mapper.writeValueAsString(trade);
    this
        .mockMvc
        .perform(
            put("/api/market/" + ids.get(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andDo(print())
        .andExpect(status().isNotAcceptable())
        .andExpect(content().string(String.format("A rebel with ID %d cannot trade more items than it have",ids.get(1))));

  }

  @Test
  @DisplayName("Should trade with rebel")
  void shouldTradeWithRebel() throws Exception {

    String body = mapper.writeValueAsString(buildTrade(ids.get(1)));
    this
        .mockMvc
        .perform(
            put("/api/market/" + ids.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andDo(print())
        .andExpect(status().isAccepted())
        .andExpect(content().string(""));

    Rebel givingRebelAfter = repository.findById(ids.get(0)).get();
    assertEquals(9,givingRebelAfter.getInventory().getAmmo());
    assertEquals(13,givingRebelAfter.getInventory().getFood());

  }

  @Test
  @DisplayName("Can't trade with self")
  void shouldNotTradeWithSelf() throws Exception {
    String body = mapper.writeValueAsString(buildTrade(ids.get(1)));
    this
        .mockMvc
        .perform(
            put("/api/market/" + ids.get(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andDo(print())
        .andExpect(status().isConflict())
        .andExpect(content().string(String.format("Rebel %d cannot trade with self",ids.get(1))));
  }

  @AfterEach
  public void tearDown() {
    repository.deleteAll();
  }

  private Trade buildTrade(Long to) {
    return Trade
        .builder()
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
        ).rebelId(to).build();
  }

  private void addRebels(List<Integer> levels) {
    ids = new ArrayList<>();
    levels
        .stream()
        .map(this::buildRandomRebel)
        .map(repository::save)
        .mapToLong(Rebel::getId)
        .forEach(ids::add);
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
                .ammo(10)
                .food(10)
                .water(10)
                .weapon(10)
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