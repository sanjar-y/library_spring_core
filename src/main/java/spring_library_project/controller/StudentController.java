package spring_library_project.controller;

import spring_library_project.container.ComponentContainer;
import spring_library_project.service.BookService;
import spring_library_project.service.ScannerService;
import spring_library_project.service.StudentBookService;
import spring_library_project.util.ScannerUtil;

public class StudentController {
    private StudentBookService studentBookService;
    private BookService bookService;
    private ScannerService scannerService;

    public void setStudentBookService(StudentBookService studentBookService) {
        this.studentBookService = studentBookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setScannerService(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    public void start() {
        System.out.println("Mazgi you must return the following books:");
        studentBookService.booksOnHand();
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    bookService.all();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    takeBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    studentBookService.booksOnHand();
                    break;
                case 6:
                    studentBookService.takenBookHistory();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Mazgi bu hatoku.");
            }

        }

    }

    public void showMenu() {
        System.out.println("*** Student Menu ***");
        System.out.println("1. BookList");
        System.out.println("2. Search");
        System.out.println("3. Take book");
        System.out.println("4. Return book");
        System.out.println("5. Books on hand");
        System.out.println("6. Take book history");
        System.out.println("0. Exit");
    }


    public void search() {
        System.out.print("Enter query:");
        String query = scannerService.getScannerText().next();
        bookService.search(query);
    }

    public void takeBook() {
        System.out.print("Enter book Id:");
        Integer bId = scannerService.getScannerNumber().nextInt();
        studentBookService.takeBook(bId);
    }

    private void returnBook() {
        System.out.print("Enter book Id:");
        Integer bId = scannerService.getScannerNumber().nextInt();
        studentBookService.returnBook(bId);
    }

}
