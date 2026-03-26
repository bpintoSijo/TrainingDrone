package com.sijo.drone.parser;

import com.sijo.drone.domain.Board;
import com.sijo.drone.domain.Direction;
import com.sijo.drone.domain.Moveable;
import com.sijo.drone.utils.TriFunction;

import java.util.Objects;

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
        return new Board(Long.parseLong(values[0]), Long.parseLong(values[1]));
    }

    @Override
    public Moveable parseMoveable(TriFunction<Long, Long, Direction, ? extends Moveable> factory, String positionLine) {
        Objects.requireNonNull(positionLine, "Couldn't parse position because string is null.");

        String[] positionValues = positionLine.split(" ");
        if(positionValues.length < 3) {
            throw new IllegalArgumentException(
                    "Couldn't parse position there is missing character (expected: 'x y direction'): " + positionLine
            );
        }

        Long x = Long.parseLong(positionValues[0]);
        Long y = Long.parseLong(positionValues[1]);
        Direction direction = Direction.parseDirection(positionValues[2]);
        return factory.apply(x, y, direction);
    }
}
