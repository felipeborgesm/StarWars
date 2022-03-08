package com.letscode.api.starwars;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import com.letscode.api.starwars.gateways.persistence.impl.collection.RebelPersistenceCollectionGateWayImpl;
import com.letscode.api.starwars.usecases.CreateRebel;
import com.letscode.api.starwars.usecases.ListRebels;
import com.letscode.api.starwars.usecases.ReportRebel;
import com.letscode.api.starwars.usecases.UpdateRebel;
import com.letscode.api.starwars.usecases.validators.CreateRebelValidator;
import com.letscode.api.starwars.usecases.validators.UpdateRebelValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.Executor;

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
            .build();

    Rebel rebel1 = createRebel.execute(rebel);
    System.out.println(rebel1);

    UpdateRebelValidator updateRebelValidator = new UpdateRebelValidator(rebelPersistenceGateWay);
    UpdateRebel updateRebel = new UpdateRebel(updateRebelValidator, rebelPersistenceGateWay);
    rebel1.setName("Yuri");
    rebel1.setAge(25);
    updateRebel.execute(rebel1);
    System.out.println(rebel1);

    ReportRebel reportRebel = new ReportRebel(rebelPersistenceGateWay);
    reportRebel.execute(rebel1);
    System.out.println(rebel1);

    reportRebel.execute(rebel1);
    reportRebel.execute(rebel1);
    //reportRebel.execute(rebel1);

    System.out.println(rebel1);

    Rebel rebel2 =
            Rebel.builder()
                    .name("felipe2")
                    .age(102)
                    .gender("masculino")
                    .inventory(List.of("comida2"))
                    .location(List.of("2222", "2222", "Rio de Janeiro"))
                    .build();

    Rebel savedRebel2 = createRebel.execute(rebel2);
    System.out.println(savedRebel2);

    System.out.println("List all rebels:");
    ListRebels listRebels = new ListRebels(rebelPersistenceGateWay);
    List<Rebel> rebels = listRebels.execute();
    System.out.println(rebels);
  }
}
