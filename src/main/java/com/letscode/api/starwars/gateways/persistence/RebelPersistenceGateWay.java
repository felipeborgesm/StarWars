package com.letscode.api.starwars.gateways.persistence;

import com.letscode.api.starwars.domains.Rebel;

import java.util.ArrayList;
import java.util.Optional;

import java.util.List;

public interface RebelPersistenceGateWay {
    Rebel save(Rebel rebel);

    void updateLocation(Rebel rebel, ArrayList coordinates);

    boolean existsById(String id);

    void delete(Rebel movie);

    List<Rebel> findAll();

    Optional<Rebel> findById(String id);
}
