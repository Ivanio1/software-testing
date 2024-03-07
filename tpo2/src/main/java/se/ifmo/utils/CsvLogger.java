package se.ifmo.utils;

import lombok.Data;

import java.io.*;

@Data
public class CsvLogger {
    private String filePath = "";
    private double step;
    private double lowerBorder;
    private double upperBorder;

    public void setFilePath(String fileName) {
        this.filePath = fileName;
    }

    public void clearFile() {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void logger(double x, double y) {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(filePath, true))) {
            printStream.printf("%s, %s \n", x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}