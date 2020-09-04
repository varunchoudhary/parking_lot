package com.gojek.solution;

public class OutputPrinter {

    public void invalidFile() {
        printWithNewLine("Invalid file given.");
    }

    public void welcome() {
        printWithNewLine("Welcome to Go-Jek Parking lot.");
    }

    public void printWithNewLine(final String msg) {
        System.out.println(msg);
    }

    public void notFound() {
        printWithNewLine("Not found");
    }

    public void parkingLotEmpty() {
        printWithNewLine("Parking lot is empty");
    }

    public void end() {
        printWithNewLine("Thanks for using Go-Jek Parking lot service.");
    }

    public void statusHeader() {
        printWithNewLine("Slot No.    Registration No    Colour");
    }

    public void parkingLotFull() {
        printWithNewLine("Sorry, parking lot is full");
    }

    public void slotAlreadyfilled() {
        printWithNewLine("Sorry, slot not available");
    }
}
