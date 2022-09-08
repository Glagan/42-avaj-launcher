package org.glagan.avaj.simulator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output {
    static private FileOutputStream stream;

    static public void write(String message) {
        if (Output.stream != null) {
            try {
                if (message.endsWith("\n")) {
                    Output.stream.write(message.getBytes());
                } else {
                    Output.stream.write((message + "\n").getBytes());
                }
            } catch (IOException e) {
                System.out.println("Failed to write to output file");
            }
        }
    }

    static public void open() throws FileNotFoundException {
        Output.stream = new FileOutputStream("simulation.txt", false);
    }

    static public void close() throws IOException {
        if (Output.stream != null) {
            Output.stream.close();
        }
    }
}
