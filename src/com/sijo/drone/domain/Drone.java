package com.sijo.drone.domain;

import com.sijo.drone.utils.Pose;

import java.util.Objects;
import java.util.logging.Logger;

public class Drone implements Moveable {
    private static final Logger LOG = Logger.getLogger(Drone.class.getName());

    private Pose pose;

    public Drone(Pose pose, Board board) {
        Objects.requireNonNull(pose, "Pose must not be null.");
        Objects.requireNonNull(board, "Board must not be null.");

        this.pose = pose;

        if(isOutOfBound(board)) {
            throw new IllegalArgumentException(
                    "Initial position is out of bound: length=" + board.length() + ", height=" + board.height() +
                            "Coordinates: " + pose
            );
        }
    }

    @Override
    public boolean isOutOfBound(Board board) {
        return pose.isOutOfBound(board.length(), board.height());
    }

    @Override
    public boolean forward(Board board) {
        Pose savedPose = pose;
        pose = switch(pose.direction()) {
            case NORTH -> new Pose(pose.x(), pose.y() + 1, pose.direction());
            case EAST -> new Pose(pose.x() + 1, pose.y(), pose.direction());
            case SOUTH -> new Pose(pose.x(), pose.y() - 1, pose.direction());
            case WEST -> new Pose(pose.x() - 1, pose.y(), pose.direction());
        };

        if(isOutOfBound(board)) {
            LOG.warning(
                    "Couldn't move forward because we reached the board limit" +
                    "(lenght: " + board.length() + ", height: " + board.height() + ")\n"+
                    "Drone position: " + this
            );
            pose = savedPose;
            return false;
        }

        return true;
    }

    @Override
    public void turnRight() {
        pose = new Pose(pose.x(), pose.y(), pose.direction().turnRight());
    }

    @Override
    public void turnLeft() {
        pose = new Pose(pose.x(), pose.y(), pose.direction().turnLeft());
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
                default -> LOG.warning("Unknown instruction: " + instruction + " ignored.");
            }
        }
    }



    @Override
    public int hashCode() {
        return Objects.hash(pose);
    }

    @Override
    public String toString() {
        return pose.x() + " " + pose.y() + " " + pose.direction();
    }
}
