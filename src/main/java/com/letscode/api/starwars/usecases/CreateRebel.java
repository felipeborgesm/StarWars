package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import com.letscode.api.starwars.gateways.persistence.RebeldePersistenceGateWay;
import com.letscode.api.starwars.usecases.validators.CreateRebelValidator;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class CreateRebel {

  private final Map<String, Rebel> rebels;
  private final RebeldePersistenceGateWay rebeldePersistenceGateWay;
  private final CreateRebelValidator createRebelValidator;

  public Rebel execute(Rebel rebel) {
    val validationErrors = createRebelValidator.validate(rebel);

    if (!validationErrors.isEmpty()){
      throw new BusinessValidationException(validationErrors);
    }

    return rebeldePersistenceGateWay.save(rebel);
  }
}
