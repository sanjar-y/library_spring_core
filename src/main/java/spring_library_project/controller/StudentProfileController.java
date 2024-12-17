package spring_library_project.controller;


import spring_library_project.container.ComponentContainer;
import spring_library_project.enums.ProfileRole;
import spring_library_project.enums.ProfileStatus;
import spring_library_project.service.ProfileService;
import spring_library_project.service.ScannerService;
import spring_library_project.util.ScannerUtil;

public class StudentProfileController {
    private ProfileService profileService;
    private ScannerService scannerService;

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
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
                    profileService.studentList();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    blockStudent();
                    break;
                case 4:
                    activeStudent();
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
        System.out.println("*** Student ***");
        System.out.println("1. Student list");
        System.out.println("2. Search student");
        System.out.println("3. Block student");
        System.out.println("3. Activate student");
        System.out.println("4. Student by book");
        System.out.println("0. Exits");
    }


    public void search() {
        System.out.print("Enter query: ");
        String query = scannerService.getScannerText().next();
        profileService.search(query, ProfileRole.STUDENT);
    }

    public void blockStudent() {
        System.out.print("Enter id: ");
        Integer id = scannerService.getScannerNumber().nextInt();
        profileService.changeStudentStatus(id, ProfileStatus.BLOCK);
    }

    public void activeStudent() {
        System.out.print("Enter id: ");
        Integer id = scannerService.getScannerNumber().nextInt();
        profileService.changeStudentStatus(id, ProfileStatus.ACTIVE);
    }

}
