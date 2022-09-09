package org.glagan.avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.glagan.avaj.simulator.Exceptions.InvalidAircraftDefinition;
import org.glagan.avaj.simulator.Exceptions.InvalidAircraftType;

public class Simulator {

    private static Flyable parseAircraftFromLine(String line) throws InvalidAircraftType, InvalidAircraftDefinition {
        Flyable aircraft = null;
        Scanner aircraftScanner = new Scanner(line);
        aircraftScanner.useDelimiter(" ");
        try {
            String type = aircraftScanner.next();

            String name = aircraftScanner.next();
            if (name.length() == 0) {
                throw new InvalidAircraftDefinition("Invalid empty aircraft name");
            }

            int longitude = aircraftScanner.nextInt();
            if (longitude < 0) {
                throw new InvalidAircraftDefinition("Invalid longitude in an aircraft declaration");
            }

            int latitude = aircraftScanner.nextInt();
            if (latitude < 0) {
                throw new InvalidAircraftDefinition("Invalid latitude in an aircraft declaration");
            }

            int height = aircraftScanner.nextInt();
            if (!aircraftScanner.hasNext()) {
                aircraft = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
            } else {
                throw new InvalidAircraftDefinition("Invalid remaining data after an aircraft declaration");
            }
        } catch (InvalidAircraftType e) {
            throw e;
        } catch (InvalidAircraftDefinition e) {
            throw e;
        } finally {
            aircraftScanner.close();
        }
        return aircraft;
    }

    private static Scenario parseScenarioFromFilePath(String path) {
        boolean sawIterations = false;
        Scenario scenario = null;
        List<Flyable> aircrafts = new ArrayList<Flyable>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!sawIterations) {
                    int iterations = Integer.parseInt(line);
                    scenario = new Scenario(iterations);
                    sawIterations = true;
                } else {
                    Flyable aircraft = Simulator.parseAircraftFromLine(line);
                    if (aircraft != null) {
                        aircrafts.add(aircraft);
                    }
                }
            }

            if (!sawIterations) {
                throw new Exception("Missing iterations count in scenario file");
            }

            if (aircrafts.size() == 0) {
                throw new Exception("No aircrafts in scenario file");
            }

            // Only register aircrafts at the end to avoid generating output on error
            for (Flyable flyable : aircrafts) {
                scenario.registerAircraft(flyable);
            }
        } catch (InvalidAircraftType e) {
            System.out.println(e.getMessage());
        } catch (InvalidAircraftDefinition e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Aircraft format in scenario file");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in scenario file");
        } catch (FileNotFoundException e) {
            System.out.println("Scenario file not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return scenario;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Missing required scenario file path");
            return;
        } else if (args.length > 1) {
            System.out.println("Too many arguments");
            return;
        }

        try {
            Output.open();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open output file");
            return;
        }

        Scenario scenario = Simulator.parseScenarioFromFilePath(args[0]);
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
