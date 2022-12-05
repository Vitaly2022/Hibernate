package HibernateDz;

import Hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class HibMain {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Select an action");
        System.out.println("1 - View journal");
        System.out.println("2 - Add new student");
        System.out.println("3 - Change student details");
        int menuSelection = scan.nextInt();

        if (menuSelection == 1) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("FROM Autor");
            List<Autor> autor = query.list();
            for (Autor aut : autor) {
                System.out.println(aut);
            }
        }

        if (menuSelection == 2) {
            Scanner scan2 = new Scanner(System.in);
            System.out.println("Insert name:");
            String name = scan2.nextLine();
            System.out.println("Insert surname:");
            String surname = scan2.nextLine();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Autor aut = new Autor();
            aut.setName(name);
            aut.setSurname(surname);
            Transaction t = session.beginTransaction();
            Integer id = (Integer) session.save(aut);
            t.commit();
        }

        if (menuSelection == 3) {
            System.out.println("Insert ID rename:");
            Scanner scan3 = new Scanner(System.in);
            int id = scan3.nextInt();
            System.out.println("You pick:");
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("FROM Autor where id=:id");
            query.setParameter("id", id);
            List<Autor> autor1 = query.list();
            for (Autor aut : autor1) {
                System.out.println(aut);
            }
            Scanner scan4 = new Scanner(System.in);
            System.out.println("Insert new name:");
            String newName = scan4.nextLine();
            System.out.println("Insert surname:");
            String newSurname = scan4.nextLine();
            Autor loadedAutor = session.load(Autor.class, id);
            loadedAutor.setName(newName);
            loadedAutor.setSurname(newSurname);
            t.commit();
            System.out.println("!!!!!!!!!!!!!Rename Complit!!!!!!!!!!!!");
        }

    }
}
