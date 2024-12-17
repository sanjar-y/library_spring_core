package spring_library_project.controller;

import spring_library_project.container.ComponentContainer;
import spring_library_project.dto.Book;
import spring_library_project.service.BookService;
import spring_library_project.service.ScannerService;
import spring_library_project.service.StudentBookService;
import spring_library_project.util.ScannerUtil;

import java.time.LocalDate;

public class BookController {
    private StudentBookService studentBookService;
    private ScannerService scannerService;
    private BookService bookService;

    public void setStudentBookService(StudentBookService studentBookService) {
        this.studentBookService = studentBookService;
    }

    public void setScannerService(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    list();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    studentBookService.booksOnHand();
                    break;
                case 6:
                    bookHistory();
                    break;
                case 7:
                    studentBookService.bestBooks();
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
        System.out.println("*** Book ***");
        System.out.println("1. Book list");
        System.out.println("2. Search");
        System.out.println("3. Add book");
        System.out.println("4. Remove book");
        System.out.println("5. Book on hand");
        System.out.println("6. Book history");
        System.out.println("7. Best books:");
        System.out.println("0. Exits");
    }


    public void add() {
        System.out.print("Enter title: ");
        String title = scannerService.getScannerText().next();

        System.out.print("Enter author: ");
        String author = scannerService.getScannerText().next();

        System.out.print("Enter category id: ");
        Integer categoryId = scannerService.getScannerNumber().nextInt();

        System.out.print("Enter available day: ");
        Integer availableDay = scannerService.getScannerNumber().nextInt();

        System.out.print("Enter publishDate (yyyy-MM-dd): ");
        String publishDate = scannerService.getScannerText().next();;

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategoryId(categoryId);
        book.setPublishDate(LocalDate.parse(publishDate));
        book.setAvailableDay(availableDay);

        bookService.add(book);
    }

    public void list() {
        System.out.println("--- Book list ---");
        bookService.all();
    }

    public void search() {
        System.out.print("Enter query:");
        String query = scannerService.getScannerText().next();;
        bookService.search(query);
    }

    public void delete() {
        System.out.print("Enter id:");
        Integer bookId = scannerService.getScannerNumber().nextInt();
        bookService.delete(bookId);
    }

    private void bookHistory() {
        System.out.print("Enter book Id:");
        Integer bookId = scannerService.getScannerNumber().nextInt();
        studentBookService.bookHistory(bookId);
    }
}
