package com.eloarena.service;

import java.time.Instant;

record MatchTicket(Long playerId, int rating, Instant enqueuedAt) {
}