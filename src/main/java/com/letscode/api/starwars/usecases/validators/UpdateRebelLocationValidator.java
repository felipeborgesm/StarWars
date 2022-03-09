package com.letscode.api.starwars.usecases.validators;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UpdateRebelLocationValidator {

  private final RebelPersistenceGateWay rebelPersistenceGateWay;

  public List<String> validate(Rebel rebel) {
    List<String> validationErrors = new ArrayList<>();
    if (!rebelPersistenceGateWay.existsById(rebel.getId())) {
      validationErrors.add("Rebel does not exist.");
    }
    if (!StringUtils.hasText(rebel.getName())) {
      validationErrors.add("Name is required.");
    }
    if (!StringUtils.hasText(String.valueOf(rebel.getAge()))) {
      validationErrors.add("Age is required.");
    }else if(rebel.getAge() < 0) {
      validationErrors.add("Rebel age must be greater than 0.");
    }
    if (!StringUtils.hasText(rebel.getGender())) {
      validationErrors.add("Genre is required.");
    }
    if (CollectionUtils.isEmpty(Arrays.asList(rebel.getLocation()))) {
      validationErrors.add("Location is required.");
    }
    if (CollectionUtils.isEmpty(rebel.getInventory())) {
      validationErrors.add("Inventory is required.");
    }
    return validationErrors;
  }
}
