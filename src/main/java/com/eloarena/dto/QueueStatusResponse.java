package com.eloarena.dto;

public class QueueStatusResponse {
    private final String status;
    private final Long battleId;

    private QueueStatusResponse(String status, Long battleId) {
        this.status = status;
        this.battleId = battleId;
    }

    public static QueueStatusResponse waiting() {
        return new QueueStatusResponse("WAITING", null);
    }

    public static QueueStatusResponse matched(Long battleId) {
        return new QueueStatusResponse("MATCHED", battleId);
    }

    public static QueueStatusResponse notFound() {
        return new QueueStatusResponse("NOT_FOUND", null);
    }

    public String getStatus() {
        return status;
    }

    public Long getBattleId() {
        return battleId;
    }
}
