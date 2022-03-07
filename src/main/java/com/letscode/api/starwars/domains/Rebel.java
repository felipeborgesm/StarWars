package com.letscode.api.starwars.domains;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Rebel {
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private boolean isTraitor = false;
    private List<String> location = new ArrayList<>();
    private List<String> inventory = new ArrayList<>();
}
