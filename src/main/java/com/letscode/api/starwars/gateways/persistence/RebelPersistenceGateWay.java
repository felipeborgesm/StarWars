package com.letscode.api.starwars.gateways.persistence;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.domains.enums.InventoryItems;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import java.util.List;

public interface RebelPersistenceGateWay {
    Rebel save(Rebel rebel);

    void updateLocation(Rebel rebel, ArrayList coordinates);

    boolean existsById(String rebelId);

    void delete(Rebel movie);

    List<Rebel> findAll();

    Optional<Rebel> findById(String rebelId);

    Map<InventoryItems, Integer> getInventory(String rebelId);

    void updateInventory(String rebelId, Map<InventoryItems, Integer> inventory);

    void createInventory(Rebel rebel, Map<InventoryItems, Integer> inventory);
}
