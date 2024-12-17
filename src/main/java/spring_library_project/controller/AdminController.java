package spring_library_project.controller;

import spring_library_project.util.ScannerUtil;

public class AdminController {
    private BookController bookController;
    private CategoryController categoryController;
    private StudentProfileController studentProfileController;
    private ProfileController profileController;

    public void setBookController(BookController bookController) {
        this.bookController = bookController;
    }

    public void setCategoryController(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    public void setStudentProfileController(StudentProfileController studentProfileController) {
        this.studentProfileController = studentProfileController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    bookController.start();
                    break;
                case 2:
                    categoryController.start();
                    break;
                case 3:
                    studentProfileController.start();
                    break;
                case 4:
                    profileController.start();
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
        System.out.println("*** Menu ***");
        System.out.println("1. Book");
        System.out.println("2. Category");
        System.out.println("3. Student");
        System.out.println("4. Profile");
        System.out.println("0. Exit");
    }
}
