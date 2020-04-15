package busTicketSystem;

import entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Ticketing {

    public static void updateInfo(int ticketNum, String firstName, String lastName, String email, String phone, String gender, int age){

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            System.out.println("\nGetting info of ticket: " + ticketNum);
            Ticket ticket = session.get(Ticket.class, ticketNum);

            ticket.setFirstName(firstName);
            ticket.setLastName(lastName);
            ticket.setEmail(email);
            ticket.setLastName(phone);
            ticket.setGender(gender);
            ticket.setAge(age);

            session.getTransaction().commit();


        }finally {
            factory.close();
        }

    }
}