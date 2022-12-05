package dz1_1611;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainDz1 {
    public static void main(String[] args) {
        System.out.println("Insert English or Daught");
        Scanner scan = new Scanner(System.in);
        String lang;
        lang=scan.nextLine();
        ApplicationContext context = new AnnotationConfigApplicationContext(DzConfig.class);
        Language language = context.getBean(lang,Language.class);
        language.getMessage();
    }
}
