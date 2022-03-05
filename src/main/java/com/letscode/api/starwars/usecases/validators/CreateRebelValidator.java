package com.letscode.api.starwars.usecases.validators;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.exception.BusinessValidationException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateRebelValidator {
    public List<String> validate(Rebel rebel) {
      List<String> validationErrors = new ArrayList<>();

      if (StringUtils.hasText(rebel.getId())) {
        validationErrors.add("id cadastrado");
      }
      if (!StringUtils.hasText(rebel.getNome())) {
        validationErrors.add("erro no nome");
      }
      if (!StringUtils.hasText(rebel.getIdade())) {
        validationErrors.add("erro na idade");
      }
      if (!StringUtils.hasText(rebel.getGenero())) {
        validationErrors.add("erro no genero");
      }
      if (CollectionUtils.isEmpty(rebel.getLocalizacao())) {
        validationErrors.add("erro na localizacao");
      }
      if (CollectionUtils.isEmpty(rebel.getInventario())) {
        validationErrors.add("erro no inventario");
      }
      if (!validationErrors.isEmpty()) {
        throw new BusinessValidationException(validationErrors);
      }
      return validationErrors;
    }
}
