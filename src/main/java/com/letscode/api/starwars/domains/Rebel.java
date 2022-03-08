package com.letscode.api.starwars.domains;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Rebel {
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private boolean isTraitor = false;
    private Integer reportCounter;
    private List<String> location = new ArrayList<>();
    private List<String> inventory = new ArrayList<>();
}
