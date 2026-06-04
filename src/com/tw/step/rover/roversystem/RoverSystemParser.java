package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

import java.util.HashMap;
import java.util.Map;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final Boundary boundary;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, Boundary boundary, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.boundary = boundary;
        this.commandCreator = commandCreator;
    }

    private Rover parseRover() {
        Coordinate coordinate = scanner.scanCoordinate();
        Direction heading = scanner.scanDirection();
        return new Rover(coordinate, heading);
    }

    public RoverSystem parse() {
        RoverSystem roverSystem = new RoverSystem();
        Rover rover = parseRover();
        roverSystem.addRover(rover);
        return roverSystem;
    }

    public Map<String, RoverSystem> parseMultiples() {
        Map<String, RoverSystem> systems = new HashMap<>();

        while (scanner.peek() != null) {
            if (!scanner.peek().contains(":")) {
                String roverId = scanner.consume();
                RoverSystem roverSystem = parse();
                systems.put(roverId, roverSystem);
            } else {
                String rover = scanner.consume();
                String roverId = rover.substring(0, rover.length() - 1);
                RoverSystem system = systems.get(roverId);
                RoverCommands commands = parseRoverCommands();
                system.addCommands(commands);
            }
        }

        return systems;
    }

    private RoverCommands parseRoverCommands() {
        RoverCommands roverCommands = new RoverCommands();
        String instructions = scanner.consume();
        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(instructions.charAt(i), navigator, boundary);
            roverCommands.add(roverCommand);
        }

        return roverCommands;
    }
}
