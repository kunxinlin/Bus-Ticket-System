package busTicketSystem;

import entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class CreateTickets {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            System.out.println("Creating tickets...");
            Ticket t1= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "New York City", Time.valueOf("07:30:00"), Timestamp.valueOf("2020-08-21 22:00:00"), 200.00);
            //Ticket t2 = new Ticket("Oliver", "James", "Delta");
            //Ticket t3 = new Ticket("Murasaki", "Ko", "United");

            session.beginTransaction();
            System.out.println("Beginning transaction...");

            session.save(t1);
            //session.save(t2);
            //session.save(t3);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
