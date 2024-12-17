package spring_library_project.util;


import spring_library_project.container.ComponentContainer;
import spring_library_project.service.ScannerService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {
    private static ScannerService scannerService;

    public void setScannerService(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    public static int getAction() {
        System.out.print("Enter action: ");
        try {
            return scannerService.getScannerNumber().nextInt();
        } catch (InputMismatchException e) {
            scannerService.getScannerNumber().next();
            System.out.println("\nPlease enter number.\n");
            return -1;
        }
    }
}
