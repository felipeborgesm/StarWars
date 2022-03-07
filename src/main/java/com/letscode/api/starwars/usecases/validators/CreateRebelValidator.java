package com.letscode.api.starwars.usecases.validators;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CreateRebelValidator {
    public List<String> validate(Rebel rebel) {
      List<String> validationErrors = new ArrayList<>();

      if (StringUtils.hasText(rebel.getId())) {
        validationErrors.add("Id already registered.");
      }
      if (!StringUtils.hasText(rebel.getName())) {
        validationErrors.add("Rebel must have a name.");
      }
      if (!StringUtils.hasText(String.valueOf(rebel.getAge()))) {
        validationErrors.add("Rebel must have an age.");
      } else if(rebel.getAge() < 0) {
        validationErrors.add("Rebel age must be greater than 0.");
      }
      if (!StringUtils.hasText(rebel.getGender())) {
        validationErrors.add("Rebel must have a gender.");
      }
      if (CollectionUtils.isEmpty(rebel.getLocation())) {
        validationErrors.add("Location error.");
      }
      if (CollectionUtils.isEmpty(rebel.getInventory())) {
        validationErrors.add("Inventory error.");
      }
      if (!validationErrors.isEmpty()) {
        throw new BusinessValidationException(validationErrors);
      }
      return validationErrors;
    }
}
