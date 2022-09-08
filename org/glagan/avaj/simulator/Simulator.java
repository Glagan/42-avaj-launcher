package org.glagan.avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.glagan.avaj.simulator.Exceptions.InvalidAircraftType;

public class Simulator {

    private static Scenario parseFile(String path) {
        boolean sawIterations = false;
        Scenario scenario = null;
        List<Flyable> aircrafts = new ArrayList<Flyable>();
        AircraftFactory factory = new AircraftFactory();

        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!sawIterations) {
                    int iterations = Integer.parseInt(line);
                    scenario = new Scenario(iterations);
                    sawIterations = true;
                } else {
                    Scanner aircraftScanner = new Scanner(line);
                    aircraftScanner.useDelimiter(" ");
                    String type = aircraftScanner.next();
                    String name = aircraftScanner.next();
                    int longitude = aircraftScanner.nextInt();
                    int latitude = aircraftScanner.nextInt();
                    int height = aircraftScanner.nextInt();
                    if (aircraftScanner.hasNext()) {
                        aircraftScanner.close();
                        System.out.println("Invalid remaining data after an aircraft declaration");
                        return null;
                    }
                    aircraftScanner.close();
                    aircrafts.add(factory.newAircraft(type, name, longitude, latitude, height));
                }
            }
            scanner.close();

            if (!sawIterations) {
                System.out.println("Missing iterations count in scenario file");
                return null;
            }

            if (aircrafts.size() == 0) {
                System.out.println("No aircrafts in scenario file");
                return null;
            }

            // Only register aircrafts at the end to avoid generating output on error
            for (Flyable flyable : aircrafts) {
                scenario.registerAircraft(flyable);
            }

            return scenario;
        } catch (InvalidAircraftType e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Aircraft format in scenario file");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in scenario file");
        } catch (FileNotFoundException e) {
            System.out.println("Scenario file not found");
        }

        return null;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Missing required scenario file path");
            return;
        }

        try {
            Output.open();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open output file");
            return;
        }

        Scenario scenario = Simulator.parseFile(args[0]);
        if (scenario != null) {
            scenario.run();
        }

        try {
            Output.close();
        } catch (IOException e) {
            System.out.println("Failed to close output file");
        }
    }
}
