package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import com.letscode.api.starwars.usecases.validators.UpdateRebelLocationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateRebelLocation {

  private final UpdateRebelLocationValidator updateRebelLocationValidator;
  private final RebelPersistenceGateWay rebelPersistenceGateWay;

  public void updateLocation(Rebel rebel, ArrayList coordinates) {
    List<String> validationErrors = updateRebelLocationValidator.validate(rebel);

    if (!validationErrors.isEmpty()){
      throw new BusinessValidationException(validationErrors);
    }

    rebelPersistenceGateWay.updateLocation(rebel, coordinates);
  }

}