package com.tw.step.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;
import com.tw.step.rover.roversystem.RoverSystem;
import com.tw.step.rover.roversystem.RoverSystemParser;
import com.tw.step.rover.roversystem.RoverSystemScanner;

import java.util.Map;

public class App {
    static void main() {
        String text = """
                5 5
                R1 1 2 N
                R2 3 3 E
                R1: FFRFF
                R2: FFF
                """;

        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();
        Boundary boundary = scanner.scanPlateau();
        CommandCreator commandCreator = new CommandCreator();
        RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, boundary, commandCreator);
        Map<String, RoverSystem> systems = roverSystemParser.parseMultiples();
        for (String roverId : systems.keySet()) {
            RoverSystem roverSystem = systems.get(roverId);
            roverSystem.execute();
            System.out.println(roverId + ": " + roverSystem);
        }

    }
}
