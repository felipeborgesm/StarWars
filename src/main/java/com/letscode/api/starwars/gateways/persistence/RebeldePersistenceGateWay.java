package com.letscode.api.starwars.gateways.persistence;

import com.letscode.api.starwars.domains.Rebel;

public interface RebeldePersistenceGateWay {
    Rebel save(Rebel rebel);

    boolean existsById(String id);
}
