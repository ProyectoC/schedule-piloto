package com.schedulepiloto.core.util.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Validator {

    private static final String ERROR_DEFAULT = "There were no errors recorded when performing the validation.";

    private boolean isValid;
    private List<String> errors;
    private List<String> notifications;

    public Validator() {
        this.errors = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public String getFirstError() {
        if (this.errors.isEmpty()) {
            return ERROR_DEFAULT;
        } else {
            return this.errors.get(0);
        }
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }
}
