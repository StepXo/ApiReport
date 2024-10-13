package com.emazon.ApiReport.Domain.Spi;

public interface UserJwtPort {
    String extractUserId();

    String extractEmail(long id);
}
