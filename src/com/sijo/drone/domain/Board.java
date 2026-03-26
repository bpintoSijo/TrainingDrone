package com.sijo.drone.domain;

public record Board(long length, long height) {
    public Board {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be > 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be > 0");
        }
    }
}
