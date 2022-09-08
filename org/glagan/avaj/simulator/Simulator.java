package org.glagan.avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Simulator {

    private static Scenario parseFile(String path) {
        boolean sawIterations = false;
        List<Flyable> aircrafts = new ArrayList<Flyable>();
        try {
            Scenario scenario = null;
            AircraftFactory factory = new AircraftFactory();
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
                    aircraftScanner.close();
                    aircrafts.add(factory.newAircraft(type, name, longitude, latitude, height));
                }
            }
            scanner.close();

            // Only register aircrafts at the end to avoid generating output on error
            for (Flyable flyable : aircrafts) {
                scenario.registerAircraft(flyable);
            }

            return scenario;
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

        Scenario scenario = Simulator.parseFile(args[0]);
        if (scenario != null) {
            scenario.run();
        }
    }
}
