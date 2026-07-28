package com.eloarena.dto;

import jakarta.validation.constraints.NotNull;

public class ReportResultRequest {

    @NotNull
    private Long winnerId;

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
}
