package spring_library_project.service;

import spring_library_project.container.ComponentContainer;
import spring_library_project.dto.Book;
import spring_library_project.dto.Category;
import spring_library_project.dto.Profile;
import spring_library_project.dto.StudentBook;
import spring_library_project.enums.StudentBookStatus;
import spring_library_project.repository.BookRepository;
import spring_library_project.repository.StudentBookRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StudentBookService {
    private BookRepository bookRepository;
    private StudentBookRepository studentBookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setStudentBookRepository(StudentBookRepository studentBookRepository) {
        this.studentBookRepository = studentBookRepository;
    }

    public void takeBook(Integer bId) {
        // studentId
        Book book = bookRepository.getById(bId);
        if (book == null) {
            System.out.println("Mazgi. Book not exists");
            return;
        }
        Integer profileId = ComponentContainer.currentProfile.getId();
        List<StudentBook> studentBookList = studentBookRepository.studentBookOnHand(profileId, StudentBookStatus.TAKEN);
        if (studentBookList.size() >= 5) {
            System.out.println("You can take only 5 books");
            return;
        }
        StudentBook studentBook = new StudentBook();
        studentBook.setBookId(bId);
        studentBook.setStudentId(profileId);
        studentBook.setCreatedDate(LocalDateTime.now());
        studentBook.setStatus(StudentBookStatus.TAKEN);

        LocalDate deadlineDate = LocalDate.now().plusDays(book.getAvailableDay());
        studentBook.setDeadlineDate(deadlineDate);
        int result = studentBookRepository.save(studentBook);
        if (result == 1) {
            System.out.println("Book taken");
        } else {
            System.out.println("Error bo'ldi.");
        }
    }

    public void booksOnHandByStudentId() {
        List<StudentBook> studentBookList = studentBookRepository.studentBookOnHand(ComponentContainer.currentProfile.getId(), StudentBookStatus.TAKEN);
        for (StudentBook st : studentBookList) {
            String title = st.getBook().getTitle();
            String author = st.getBook().getAuthor();
            Integer availableDay = st.getBook().getAvailableDay();

            String categoryName = st.getBook().getCategory().getName();
            LocalDateTime takenDate = st.getCreatedDate();

            LocalDateTime deadLine = takenDate.plusDays(availableDay);

            String str = String.format("%s, %s, %s, %s, %s - %s", st.getBook().getId(), title, author, categoryName, takenDate, deadLine);
            System.out.println(str);
        }
    }

    public void returnBook(Integer bId) {
        Integer sId = ComponentContainer.currentProfile.getId();
//        StudentBook st = ComponentContainer.studentBookRepository.getStudentBook(sId, bId);
//        if (st == null) {
//            System.out.println("No date");
//            return;
//        }
//        int effectedRow = ComponentContainer.studentBookRepository.returnBook(st.getId());
//        if (effectedRow != 0) {
//            System.out.println("Book returned");
//        } else {
//            System.out.println("Error ...");
//        }

        int effectedRow = studentBookRepository.returnBook(sId, bId);
        if (effectedRow != 0) {
            System.out.println("Book returned");
        } else {
            System.out.println("Error ...");
        }
    }

    public void takenBookHistory() {
        List<StudentBook> studentBookList = studentBookRepository.studentBookOnHand(ComponentContainer.currentProfile.getId(), null);
        for (StudentBook st : studentBookList) {
            String title = st.getBook().getTitle();
            String author = st.getBook().getAuthor();

            String categoryName = st.getBook().getCategory().getName();

            String str = String.format("%s, %s, %s, %s, %s - %s", st.getBook().getId(), title, author, categoryName, st.getCreatedDate(), st.getReturnedDate());
            System.out.println(str);
        }
    }

    public void booksOnHand() {
        List<StudentBook> studentBookList = studentBookRepository.booksOnHand();
        for (StudentBook st : studentBookList) {
            Book book = st.getBook();
            Profile student = st.getStudent();
            String categoryName = book.getCategory().getName();

            String str = String.format("%s, %s, %s, %s, %s - %s, %s, %s, %s, ", book.getId(), book.getTitle(), book.getAuthor(),
                    categoryName, st.getCreatedDate(), st.getDeadlineDate(),
                    student.getName(), student.getSurname(), student.getPhone());
            System.out.println(str);
        }
    }

    public void bookHistory(Integer bookId) {
        List<StudentBook> studentBookList = studentBookRepository.bookHistory(bookId);
        for (StudentBook st : studentBookList) {
            Profile student = st.getStudent();

            String str = String.format("%s - %s, %s, %s, %s ", st.getCreatedDate(), st.getReturnedDate(),
                    student.getName(), student.getSurname(), student.getPhone());
            System.out.println(str);
        }
    }

    public void bestBooks() {
        List<StudentBook> studentBookList = studentBookRepository.bestBooks();
        for (StudentBook st : studentBookList) {
            Book book = st.getBook();
            Category category = book.getCategory();

            String str = String.format("%d %s, %s, %s, %s", book.getId(), book.getTitle(),
                    book.getAuthor(), category.getName(), st.getTakenCount());
            System.out.println(str);
        }
    }
}
