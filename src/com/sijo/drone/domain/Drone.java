package com.sijo.drone.domain;

import java.util.Objects;
import java.util.logging.Logger;

public class Drone implements Moveable {
    private static final Logger LOG = Logger.getLogger(Drone.class.getName());

    private long x;
    private long y;
    private Direction direction;

    public Drone(long x, long y, Direction direction) {
        if(x < 0) {
            throw new IllegalArgumentException("Axis x can't be negative. x = " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException("Axis y can't be negative. y = " + y);
        }

        if(direction == null) {
            throw new IllegalArgumentException("Direction can't be null.");
        }

        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public boolean isOutOfBound(Board board) {
        return (x < 0 || x > board.length()) || (y < 0 || y > board.height());
    }

    @Override
    public boolean forward(Board board) {
        long savedX = x;
        long savedY = y;

        switch(direction) {
            case NORTH -> y++;
            case EAST -> x++;
            case SOUTH -> y--;
            case WEST -> x--;
        }

        if(isOutOfBound(board)) {
            LOG.warning(
                    "Couldn't move forward because we reached the board limit" +
                    "(lenght: " + board.length() + ", height: " + board.height() + ")\n"+
                    "Drone position: " + this
            );
            x = savedX;
            y = savedY;
            return false;
        }

        return true;
    }

    @Override
    public void turnRight() {
        direction = direction.turnRight();
    }

    @Override
    public void turnLeft() {
        direction = direction.turnLeft();
    }

    @Override
    public void executeInstructions(Board board, String instructions) {
        if (instructions == null) {
            // We do nothing
            return;
        }

        for(var i = 0; i < instructions.length(); i++) {
            char instruction = instructions.charAt(i);
            switch(instruction) {
                case 'F' -> forward(board);
                case 'L' -> turnLeft();
                case 'R' -> turnRight();
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Drone drone)) return false;
        return x == drone.x && y == drone.y && direction == drone.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
