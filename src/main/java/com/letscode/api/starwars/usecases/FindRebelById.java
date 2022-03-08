package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FindRebelById {

  private final RebelPersistenceGateWay rebelPersistenceGateWay;

  public Optional<Rebel> execute(final String id) {
    if (id == null) {
      throw new BusinessValidationException("Rebel id is required");
    }
    return rebelPersistenceGateWay.findById(id);
  }

}
