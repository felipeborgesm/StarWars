package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import com.letscode.api.starwars.gateways.persistence.RebeldePersistenceGateWay;
import com.letscode.api.starwars.usecases.validators.UpdateRebelValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UpdateRebel {

  private final RebeldePersistenceGateWay rebeldePersistenceGateWay;
  private UpdateRebelValidator updateRebelValidator;

  public Rebel execute(Rebel rebel) {
    List<String> validationErrors = updateRebelValidator.validate(rebel);

    if (!validationErrors.isEmpty()){
      throw new BusinessValidationException(validationErrors);
    }

    return rebeldePersistenceGateWay.save(rebel);
  }

}