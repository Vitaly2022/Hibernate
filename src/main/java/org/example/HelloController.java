package org.example;

import Hibernate.Group;
import Hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String mymetod(@RequestParam(value = "userName", required = false) String gogy) {
        System.out.println("Hello " + gogy);
        return "hello"; //имя jsp если стринг то это вью резолвер
    }

    @GetMapping("/path/{userName}") //путь контроллера склеивается с этим путем
    public String helloPath(@PathVariable("userName") String gogy) {
        System.out.println("Hello " + gogy);
        return "hello";
    }

    @GetMapping("/select") //путь контроллера склеивается с этим путем
    public String selectMySql(ModelMap bd) throws SQLException {

        final String URL = "jdbc:mysql://localhost:3306/play";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        preparedStatement = connection.prepareStatement("select * from student");
        resultSet = preparedStatement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString("name"));
        }
        bd.put("names", list);
        return "select";
    }

    @GetMapping("/create")
    public String modelHello()
    {
        Group grooup = new Group();
        grooup.setRoom(123);
        grooup.setTitle("New Hibernate test");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();//обертка jdbc
        Transaction t = session.beginTransaction();

        Integer id  = (Integer) session.save(grooup);//номер айди либо session.persist() тогда не сохраняет id
        t.commit(); //внести изменения

        Group loadedGroup=session.load(Group.class, id);//загружает только по первичному ключу
        System.out.println(loadedGroup.getRoom());
        t = session.beginTransaction();
        loadedGroup.setRoom(333);
        t.commit();
        System.out.println(loadedGroup.getRoom());

        return "input";
    }

    @GetMapping("/from")
    public String modelHello(ModelMap model)
    {
        Group grooup = new Group();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();//обертка jdbc

        Query query = session.createQuery("FROM Group");
        List <Group> groups = query.list();
        model.put("group", groups);


        return "from";
    }

    @GetMapping("/getlist")
    public String getList(ModelMap modell)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // simple select allx`
//        Query query = session.createQuery("FROM Grooup ");
//        List<Grooup> grooups = query.list();
//        modell.put("groups", grooups);

        // select with param
//        Query query = session.createQuery("FROM Grooup WHERE id >= :id ");
//        query.setParameter("id", 10);
//        List<Grooup> grooups = query.list();
//        modell.put("groups", grooups);

        // select with aggregation
        Query query = session.createQuery("SELECT avg(room) FROM Group");
        int maxRoom = (int) query.getSingleResult();
        modell.put("maxRoom", maxRoom);
        return "hello";

    }

    @GetMapping("/criteria")
    public String criteria(ModelMap modell)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // simple select all
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Grooup> criteriaQuery = criteriaBuilder.createQuery(Grooup.class);
//        Root<Grooup> root = criteriaQuery.from(Grooup.class);
//        criteriaQuery.select(root);
//
//        List<Grooup> grooups = session.createQuery(criteriaQuery).getResultList();
//        modell.put("groups", grooups);

        // select with param
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("id"), 10));

        List<Group> grooups = session.createQuery(criteriaQuery).getResultList();
        modell.put("groups", grooups);

        return "criteria";
    }

}
