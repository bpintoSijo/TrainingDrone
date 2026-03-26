package com.sijo.drone.utils;

import com.sijo.drone.domain.Direction;

public record Pose(long x, long y, Direction direction) {

    public Pose {
        if(x < 0) {
            throw new IllegalArgumentException("Axis x can't be negative. x = " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException("Axis y can't be negative. y = " + y);
        }

        if(direction == null) {
            throw new IllegalArgumentException("Direction can't be null.");
        }
    }

    public boolean isOutOfBound(long length, long height) {
        return (x < 0 || x > length) || (y < 0 || y > height);
    }
}
