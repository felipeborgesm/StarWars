package com.letscode.api.starwars.usecases.validators;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.gateways.persistence.RebeldePersistenceGateWay;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UpdateRebelValidator {

  private final RebeldePersistenceGateWay rebeldePersistenceGateWay;

  public List<String> validate(Rebel rebel) {
    List<String> validationErrors = new ArrayList<>();
    if (!rebeldePersistenceGateWay.existsById(rebel.getId())) {
      validationErrors.add("rebelde nao existe");
    }
    if (!StringUtils.hasText(rebel.getNome())) {
      validationErrors.add("nome obrigatorio");
    }
    if (!StringUtils.hasText(rebel.getIdade())) {
      validationErrors.add("idade obrigatoria");
    }
    if (!StringUtils.hasText(rebel.getGenero())) {
      validationErrors.add("genero obrigatorio");
    }
    if (CollectionUtils.isEmpty(rebel.getLocalizacao())) {
      validationErrors.add("localizacao obrigatoria");
    }
    if (CollectionUtils.isEmpty(rebel.getInventario())) {
      validationErrors.add("inventario obrigatorio");
    }
    return validationErrors;
  }
}
