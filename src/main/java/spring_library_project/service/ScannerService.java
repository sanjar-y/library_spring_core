package spring_library_project.service;

import java.util.Scanner;

public class ScannerService {
    private Scanner scannerText = new Scanner(System.in);
    private Scanner scannerNumber = new Scanner(System.in);

    public Scanner getScannerText() {
        return scannerText;
    }

    public Scanner getScannerNumber() {
        return scannerNumber;
    }
}
