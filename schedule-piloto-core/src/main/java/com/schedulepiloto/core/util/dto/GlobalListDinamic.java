package com.schedulepiloto.core.util.dto;

import java.util.List;

public class GlobalListDinamic<T> {

    private List<T> items;

    public GlobalListDinamic(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
