package com.kaydelarose.airae.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ingredients")
public class Ingredient {
    @Id
    private String id;
    private String name;
    private String category;
    private List<String> benefits;
    private List<String> skinTypes;
    private List<String> concernsTargeted;
}

