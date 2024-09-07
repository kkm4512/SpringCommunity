package com.terror.springcommunity.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TimeStampDto {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void addDateTime(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
