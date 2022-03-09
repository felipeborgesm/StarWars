package com.letscode.api.starwars;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import com.letscode.api.starwars.gateways.persistence.impl.collection.RebelPersistenceCollectionGateWayImpl;
import com.letscode.api.starwars.usecases.*;
import com.letscode.api.starwars.usecases.validators.CreateRebelValidator;
import com.letscode.api.starwars.usecases.validators.UpdateRebelLocationValidator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {

    // SpringApplication.run(Application.class, args);
    standalone();
  }

  private static void standalone() {
    CreateRebelValidator createRebelValidator = new CreateRebelValidator();
    RebelPersistenceGateWay rebelPersistenceGateWay = new RebelPersistenceCollectionGateWayImpl();
    CreateRebel createRebel = new CreateRebel(createRebelValidator, rebelPersistenceGateWay);

    Rebel rebel =
        Rebel.builder()
            .name("felipe")
            .age(10)
            .gender("masculino")
            .inventory(List.of("comida"))
            .location(List.of("32131231", "543534", "Sao Paulo"))
            .inventory(List.of("Weapon", "Food"))
            .build();

    Rebel rebel1 = createRebel.execute(rebel);
    System.out.println(rebel1);

    UpdateRebelLocationValidator updateRebelLocationValidator =
        new UpdateRebelLocationValidator(rebelPersistenceGateWay);
    UpdateRebelLocation updateRebelLocation =
        new UpdateRebelLocation(updateRebelLocationValidator, rebelPersistenceGateWay);
    //    rebel1.setName("Yuri");
    //    rebel1.setAge(25);

    ArrayList<String> location = new ArrayList<>(Arrays.asList("43242", "97897", "brasilia"));
    updateRebelLocation.updateLocation(rebel1, location);
    System.out.println("mudando a localizacao");
    System.out.println(rebel1);

    ReportRebel reportRebel = new ReportRebel(rebelPersistenceGateWay);
    reportRebel.execute(rebel1);
    System.out.println(rebel1);

    reportRebel.execute(rebel1);
    reportRebel.execute(rebel1);
    // reportRebel.execute(rebel1);

    System.out.println(rebel1);

    Rebel rebel2 =
        Rebel.builder()
            .name("felipe2")
            .age(102)
            .gender("masculino")
            .location(List.of("2222", "2222", "Rio de Janeiro"))
            .inventory(List.of("Water", "Water", "Water", "Water"))
            .build();

    Rebel savedRebel2 = createRebel.execute(rebel2);
    System.out.println(savedRebel2);

    System.out.println("List all rebels:");
    ListRebels listRebels = new ListRebels(rebelPersistenceGateWay);
    List<Rebel> rebels = listRebels.execute();
    System.out.println(rebels);

    System.out.println("Find rebel by id:");
    FindRebelById findRebelById = new FindRebelById(rebelPersistenceGateWay);
    Optional<Rebel> rebelFound = findRebelById.execute("1");
    System.out.println(rebelFound);
    Optional<Rebel> rebelFound2 = findRebelById.execute(rebel2.getId());
    System.out.println(rebelFound2);
    
    CreateInventory createInventory = null;
    System.out.println(createInventory.print());
  }
}
