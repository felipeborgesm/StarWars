package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import com.letscode.api.starwars.usecases.validators.CreateRebelValidator;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateRebel {

  private final Map<String, Rebel> rebels;
  private final RebelPersistenceGateWay rebelPersistenceGateWay;
  private final CreateRebelValidator createRebelValidator;

  public Rebel execute(Rebel rebel) {
    val validationErrors = createRebelValidator.validate(rebel);

    if (!validationErrors.isEmpty()){
      throw new BusinessValidationException(validationErrors);
    }

    return rebelPersistenceGateWay.save(rebel);
  }
}
