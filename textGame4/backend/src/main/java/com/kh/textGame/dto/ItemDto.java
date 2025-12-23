package com.kh.textGame.dto;

import com.kh.textGame.entity.Item.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private Long id;

    private String name;

    private String description;

    private String img;

    private ItemType type;

    private int price;

    private int value;
}