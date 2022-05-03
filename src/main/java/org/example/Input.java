package org.example;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Input {
    private final Scanner scan;
    private final PrintStream out;

    public Input(InputStream in, PrintStream out) {
        this.scan = new Scanner(in);
        this.out = out;
    }

    public String start() {
        out.println("Enter an expression!");
        return scan.nextLine();
    }
}
