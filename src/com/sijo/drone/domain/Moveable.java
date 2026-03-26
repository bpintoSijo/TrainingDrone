package com.sijo.drone.domain;

public interface Moveable {

    /**
     * Check if the moveable object is outOfBound.
     * @param board used to check.
     * @return true if we are out of bound else false.
     * */
    boolean isOutOfBound(Board board);

    /**
     * Move forward in a board.
     * @param board where we move forward.
     * @return true if we are in board else false.
     * */
    boolean forward(Board board);

    /**
     * Turn right
     * */
    void turnRight();

    /**
     * Turn left
     * */
    void turnLeft();
}
