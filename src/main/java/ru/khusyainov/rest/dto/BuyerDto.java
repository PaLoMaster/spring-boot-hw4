package ru.khusyainov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDto {

    private int id;
    private String name;
    private CartDto cart;
}