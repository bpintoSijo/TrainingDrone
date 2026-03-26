package com.sijo.drone.domain;

public record Board(long length, long height) {
    public Board {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be positive or equals to 0.");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Height must be positive or equals to 0.");
        }
    }
}
