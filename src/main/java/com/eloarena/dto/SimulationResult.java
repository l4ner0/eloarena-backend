package com.eloarena.dto;

import java.util.List;

import com.eloarena.model.Player;

public class SimulationResult {
    private final int battlesSimulated;
    private final List<Player> standings;

    public SimulationResult(int battlesSimulated, List<Player> standings) {
        this.battlesSimulated = battlesSimulated;
        this.standings = standings;
    }

    public int getBattlesSimulated() {
        return battlesSimulated;
    }

    public List<Player> getStandings() {
        return standings;
    }
}