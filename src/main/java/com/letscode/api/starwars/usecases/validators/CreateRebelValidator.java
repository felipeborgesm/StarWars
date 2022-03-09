package com.letscode.api.starwars.usecases.validators;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CreateRebelValidator {
    public List<String> validate(Rebel rebel) {
      List<String> validationErrors = new ArrayList<>();

      if (CollectionUtils.isEmpty(rebel.getLocation()) || rebel.getLocation().size() != 3) {
        validationErrors.add("Location error.");
      }
      if (!validationErrors.isEmpty()) {
        throw new BusinessValidationException(validationErrors);
      }
      return validationErrors;
    }
}
