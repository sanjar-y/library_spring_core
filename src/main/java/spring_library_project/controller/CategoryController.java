package spring_library_project.controller;

import spring_library_project.container.ComponentContainer;
import spring_library_project.dto.Category;
import spring_library_project.service.CategoryService;
import spring_library_project.service.ScannerService;
import spring_library_project.util.ScannerUtil;

public class CategoryController {
    private CategoryService categoryService;
    private ScannerService scannerService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setScannerService(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    categoryService.list();
                    break;
                case 2:
                    deleteCategory();
                    break;
                case 3:
                    addCategory();
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
        System.out.println("*** Category ***");
        System.out.println("1. Category list");
        System.out.println("2. Delete category");
        System.out.println("3. Add category");
        System.out.println("0. Exit");
    }

    public void addCategory() {
        System.out.print("Enter name: ");
        String name = scannerService.getScannerNumber().next();

        Category category = new Category();
        category.setName(name);

        categoryService.create(category);
    }

    public void deleteCategory() {
        System.out.print("Enter id: ");
        Integer id = scannerService.getScannerNumber().nextInt();

        categoryService.delete(id);
    }
}
