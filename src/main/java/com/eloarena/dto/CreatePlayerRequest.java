package com.eloarena.dto;

import jakarta.validation.constraints.NotBlank;

public class CreatePlayerRequest {

    @NotBlank
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
