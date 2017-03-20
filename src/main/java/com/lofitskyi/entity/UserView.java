package com.lofitskyi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class UserView{
    private Long id;
    private String username;
    private List<BigDecimal> wastes = new LinkedList<>();
}

