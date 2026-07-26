package com.eloarena.service;

import org.springframework.stereotype.Service;

@Service
public class EloService {
    private static final int K = 32;

    public double expectedScore(int ra, int rb) {

        return 1.0 / (1.0 + Math.pow(10, (rb - ra) / 400.0));
    }

    public int[] updateRatings(int ra, int rb, boolean winnerIsA) {
        double ea = expectedScore(ra, rb);
        double eb = 1 - ea;

        int sa = winnerIsA ? 1 : 0;
        int sb = winnerIsA ? 0 : 1;

        int newRankingA = (int) Math.round(ra + K * (sa - ea));
        int newRankingB = (int) Math.round(rb + K * (sb - eb));

        return new int[] { newRankingA, newRankingB };
    }
}