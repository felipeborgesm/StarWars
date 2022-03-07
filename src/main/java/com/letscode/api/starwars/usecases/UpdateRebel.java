package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import com.letscode.api.starwars.usecases.validators.UpdateRebelValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateRebel {

  private final RebelPersistenceGateWay rebelPersistenceGateWay;
  private UpdateRebelValidator updateRebelValidator;

  public Rebel execute(Rebel rebel) {
    List<String> validationErrors = updateRebelValidator.validate(rebel);

    if (!validationErrors.isEmpty()){
      throw new BusinessValidationException(validationErrors);
    }

    return rebelPersistenceGateWay.save(rebel);
  }

}