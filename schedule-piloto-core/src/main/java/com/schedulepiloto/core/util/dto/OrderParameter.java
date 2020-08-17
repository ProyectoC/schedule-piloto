package com.schedulepiloto.core.util.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderParameter implements Comparable<OrderParameter> {

    private String type;
    private String value;
    private int priority;

    @Override
    public int compareTo(OrderParameter other) {
        return Integer.compare(priority, other.getPriority());
    }
}