package com.sijo.drone.parser;

import com.sijo.drone.domain.Board;
import com.sijo.drone.domain.Moveable;
import com.sijo.drone.utils.Pose;

import java.util.function.BiFunction;

/**
 * Interface to parse string input
 * */
public interface InputParser {
    /**
     * Create board from String input.
     *
     * @return Created board.
     *
     */
    Board parseBoard(String input);

    /**
     * Create a moveable object from String input
     *
     * @return A moveable object.
     *
     */
    Moveable parseMoveable(BiFunction<? super Pose, ? super Board, ? extends Moveable> factory,  Board board, String positionLine);
}