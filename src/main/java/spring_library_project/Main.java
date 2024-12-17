package spring_library_project;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_library_project.controller.MainController;

public class Main {
    public static void main(String[] args) {

        // login: adminjon
        // pswd:  12345
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        MainController main = (MainController) context.getBean("mainController");
        main.start();
    }
}