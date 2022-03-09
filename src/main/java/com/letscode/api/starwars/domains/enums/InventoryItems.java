package com.letscode.api.starwars.domains.enums;

public enum InventoryItems {
    WEAPON(4), AMMUNITION(3), WATER(2), FOOD(1);

    private final int valor;

    InventoryItems(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

}
