package com.tw.step.rover.roversystem;

import com.tw.step.rover.rover.Rover;

import java.util.ArrayList;

public class RoversSystem extends ArrayList<RoverSystem> {
    public void execute(Rover rover) {
        for (RoverSystem roverCommand : this) {
            roverCommand.execute();
        }
    }
}
