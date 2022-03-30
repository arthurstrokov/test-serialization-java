package com.gmail.arthurstrokov.serialization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private Phone[] phones;
    private Map<Short, Double> shortDoubleMap;
}
