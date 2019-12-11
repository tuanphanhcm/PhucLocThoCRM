package com.tuanphan.phucloctho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {
    private int id;
    private Date createDate;
    private String itemName;
    private int itemPrice;
    private String type;
}
