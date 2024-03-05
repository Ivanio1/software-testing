package se.ifmo.utils;

import lombok.Data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@Data
public class CsvLogger {
    private String filePath = "";
    private double step;
    private double lowerBorder;
    private double upperBorder;

    public void setFilePath(String fileName) {
        this.filePath = fileName;
    }

    public void logger(double x, double y) {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(filePath, true))) {
            printStream.printf("%s, %s \n", x, y);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}