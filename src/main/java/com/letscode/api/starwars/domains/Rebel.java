package com.letscode.api.starwars.domains;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Rebel {
    private String id;
    private String nome;
    private String idade;
    private String genero;
    private List<String> localizacao = new ArrayList<>();
    private List<String> inventario = new ArrayList<>();
}
