package spring_library_project.service;


import spring_library_project.container.ComponentContainer;
import spring_library_project.dto.Book;
import spring_library_project.dto.Category;
import spring_library_project.repository.BookRepository;
import spring_library_project.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookService {
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void add(Book book) {
        categoryService.list();
        if (!isValid(book)) {
            return;
        }

        Category category = categoryRepository.getById(book.getCategoryId());
        if (category == null) {
            System.out.println("Category not found.");
            return;
        }
        book.setCreatedDate(LocalDateTime.now());
        book.setVisible(true);
        int effectedRow = bookRepository.save(book);
        if (effectedRow == 1) {
            System.out.println("Book saved.");
        } else {
            System.out.println("Book not saved.");
        }
    }

    public boolean isValid(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank() || book.getTitle().length() < 3) {
            System.out.println("Title required.");
            return false;
        }

        if (book.getAuthor() == null || book.getAuthor().isBlank() || book.getAuthor().length() < 3) {
            System.out.println("Author required.");
            return false;
        }

        if (book.getAvailableDay() == null || book.getAvailableDay() <= 0) {
            System.out.println("Available day required.");
            return false;
        }
        return true;
    }

    public void all() {
        List<Book> bookList = bookRepository.getAll();
        System.out.printf("-------------------------------------------------------------------%n");
        System.out.printf("                               Book list                        %n");
        System.out.printf("-------------------------------------------------------------------%n");
        System.out.printf("| %-4s | %-20s | %-15s | %-15s |%n", "Id", "Title", "Author", "Category name");
        System.out.printf("-------------------------------------------------------------------%n");
        bookList.forEach(book -> {
            System.out.printf("| %-4d | %-20s | %-15s | %-15s |%n", book.getId(), book.getTitle(), book.getAuthor(), book.getCategory().getName());
        });
        System.out.printf("-------------------------------------------------------------------%n");
    }

    public void search(String query) {
        List<Book> bookList = bookRepository.search(query);
        bookList.forEach(book -> {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getCategory().getName());
        });
    }

    public void delete(Integer bookId) {
        int effectedRows = bookRepository.delete(bookId);
        if (effectedRows == 1) {
            System.out.println("Book deleted");
        } else {
            System.out.println("Book not deleted");
        }
    }

    public void byCategoryId(Integer categoryId) {
        List<Book> bookList = bookRepository.getAllByCategoryId(categoryId);
        bookList.forEach(book -> {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getCategory().getName());
        });
    }
}
