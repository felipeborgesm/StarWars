package com.letscode.api.starwars.controller;

import com.github.javafaker.Faker;
import com.letscode.api.starwars.domains.Inventory;
import com.letscode.api.starwars.domains.Location;
import com.letscode.api.starwars.domains.Rebel;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DisplayName("End-to-End Test :: Report Controller ")
class ReportControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RebelRepository repository;
  private final Faker faker = new Faker();
  private final Random random = new Random();

  @BeforeEach
  public void setUp(){
    addRebels(List.of(0,0,0,0,0,0,0,0,0,10));
  }

  @Test
  @DisplayName("10% of traitors")
  void shouldGetTraitorPercentage() throws Exception {
    this.mockMvc.perform(get("/api/reports/traitors")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("10")));
  }

  @Test
  @DisplayName("90% of rebels")
  void shouldGetRebelPercentage() throws Exception {
    this.mockMvc.perform(get("/api/reports/rebels")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("90")));
  }

  @Test
  @DisplayName("Average of Food Resources")
  void shouldGetAverageFood() throws Exception {
    this.mockMvc.perform(get("/api/reports/resource/FOOD")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("10")));
  }

  @Test
  @DisplayName("We lost this to traitors")
  void shouldGetOurLosses() throws Exception {
    this.mockMvc.perform(get("/api/reports/losses")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().json("{\"foodLost\":10,\"waterLost\":20,\"ammoLost\":30,\"weaponLost\":40,\"totalLost\":100}"));
  }


  @AfterEach
  public void tearDown() {
    repository.deleteAll();
  }

  private void addRebels(List<Integer> levels){
    levels.stream().map(this::buildRandomRebel).forEach(repository::save);
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
        .latitude(Float.parseFloat(faker.address().latitude()))
        .longitude(Float.parseFloat(faker.address().longitude()))
        .name(faker.starTrek().location())
        .build();
  }

}