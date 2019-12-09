package com.tuanphan.phucloctho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private int id;
    private String name;
    private String unit;
    private int storageAmount;
    private int brandId;
    private String brandName;
}
