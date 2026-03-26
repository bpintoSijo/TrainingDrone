package com.sijo.drone.parser;

import com.sijo.drone.domain.Board;
import com.sijo.drone.domain.Direction;
import com.sijo.drone.domain.Moveable;
import com.sijo.drone.utils.Pose;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * String parser to create board and moveable objects from a text file.
 * */
public class TextParser implements InputParser {
    private static final TextParser INSTANCE = new TextParser();

    private TextParser() {
    }

    public static TextParser getInstance() {
        return INSTANCE;
    }

    @Override
    public Board parseBoard(String input) {
        String[] values = input.split(" ");
        if(values.length < 2) {
            throw new IllegalArgumentException("Could not parse board correctly. Input: " + input);
        }
        try {
            return new Board(Long.parseLong(values[0]), Long.parseLong(values[1]));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Could not parse board correctly with Input: " + input);
        }
    }

    @Override
    public Moveable parseMoveable(BiFunction<? super Pose, ? super Board, ? extends Moveable> factory, Board board, String positionLine) {
        Objects.requireNonNull(positionLine, "Couldn't parse position because string is null.");

        String[] positionValues = positionLine.split(" ");
        if(positionValues.length < 3) {
            throw new IllegalArgumentException(
                    "Couldn't parse position there is missing character (expected: 'x y direction'): " + positionLine
            );
        }

        long x = Long.parseLong(positionValues[0]);
        long y = Long.parseLong(positionValues[1]);
        Direction direction = Direction.parseDirection(positionValues[2]);
        Pose pose = new Pose(x, y, direction);
        return factory.apply(pose, board);
    }
}
