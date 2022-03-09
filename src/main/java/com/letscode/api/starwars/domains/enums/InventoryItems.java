package com.letscode.api.starwars.domains.enums;

public enum InventoryItems {
    Weapon(4), Amunnition(3), Water(2), Food(1);

    private final int valor;

    InventoryItems(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

}
