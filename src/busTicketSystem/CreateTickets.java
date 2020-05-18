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
            //$15/hour Date date, String origin, String destination, Time departure, Timestamp eta, double price
            System.out.println("Creating tickets...");
            Ticket t1= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Houston", Time.valueOf("07:30:00"), Timestamp.valueOf("2020-08-21 20:30:00"), 200.00);
            Ticket t2= new Ticket(Date.valueOf("2020-08-22"), "Houston", "Dallas", Time.valueOf("08:30:00"), Timestamp.valueOf("2020-08-22 13:15:00"), 71.25);
            Ticket t3= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Nashville", Time.valueOf("08:00:00"), Timestamp.valueOf("2020-08-21 12:45:00"), 71.25);
            Ticket t4= new Ticket(Date.valueOf("2020-08-21"), "Nashville", "Knoxville", Time.valueOf("01:30:00"), Timestamp.valueOf("2020-08-21 05:30:00"), 60.00);
            Ticket t5= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Orlando", Time.valueOf("08:30:00"), Timestamp.valueOf("2020-08-21 16:00:00"), 112.50);
            Ticket t6= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Jacksonville", Time.valueOf("08:00:00"), Timestamp.valueOf("2020-08-21 15:00:00"), 105.00);
            Ticket t7= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Jacksonville", Time.valueOf("08:30:00"), Timestamp.valueOf("2020-08-21 12:30:00"), 90.00);
            Ticket t8= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Tampa", Time.valueOf("08:30:00"), Timestamp.valueOf("2020-08-21 13:15:00"), 71.25);
            Ticket t9= new Ticket(Date.valueOf("2020-08-21"), "Orlando", "Miami", Time.valueOf("17:00:00"), Timestamp.valueOf("2020-08-21 21:00:00"), 60.00);
            Ticket t10= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Charlotte", Time.valueOf("07:30:00"), Timestamp.valueOf("2020-08-21 11:15:00"), 63.75);
            Ticket t11= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Charleston", Time.valueOf("08:30:00"), Timestamp.valueOf("2020-08-21 14:00:00"), 82.50);
            Ticket t12= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Memphis", Time.valueOf("08:30:00"), Timestamp.valueOf("2020-08-21 15:30:00"), 105.00);
            Ticket t13= new Ticket(Date.valueOf("2020-08-21"), "Atlanta", "Montgomery", Time.valueOf("10:00:00"), Timestamp.valueOf("2020-08-21 13:00:00"), 45.00);
            Ticket t14= new Ticket(Date.valueOf("2020-08-23"), "Atlanta", "New Orleans", Time.valueOf("08:00:00"), Timestamp.valueOf("2020-08-23 15:30:00"), 112.50);
            Ticket t15= new Ticket(Date.valueOf("2020-08-23"), "Atlanta", "Huntsville", Time.valueOf("09:30:00"), Timestamp.valueOf("2020-08-23 1:00:00"), 42.50);
            Ticket t16= new Ticket(Date.valueOf("2020-08-23"), "Atlanta", "Little Rock", Time.valueOf("07:30:00"), Timestamp.valueOf("2020-08-23 16:00:00"), 127.50);
            Ticket t17= new Ticket(Date.valueOf("2020-08-25"), "Atlanta", "Kansas City", Time.valueOf("07:30:00"), Timestamp.valueOf("2020-08-25 20:30:00"), 195.00);
            Ticket t18= new Ticket(Date.valueOf("2020-08-25"), "Atlanta", "Louisville", Time.valueOf("09:00:00"), Timestamp.valueOf("2020-08-25 16:00:00"), 105.00);
            Ticket t19= new Ticket(Date.valueOf("2020-08-26"), "Atlanta", "Indianapolis", Time.valueOf("09:00:00"), Timestamp.valueOf("2020-08-26 18:00:00"), 135.00);
            Ticket t20= new Ticket(Date.valueOf("2020-08-26"), "Atlanta", "Richmond", Time.valueOf("08:00:00"), Timestamp.valueOf("2020-08-26 16:30:00"), 127.50);
            Ticket t21= new Ticket(Date.valueOf("2020-08-31"), "Atlanta", "New York City", Time.valueOf("16:00:00"), Timestamp.valueOf("2020-09-01 7:30:00"), 232.50);
            Ticket t22= new Ticket(Date.valueOf("2020-08-31"), "Orlando", "Jacksonville", Time.valueOf("09:00:00"), Timestamp.valueOf("2020-08-31 11:15:00"), 33.75);

            session.beginTransaction();
            System.out.println("Beginning transaction...");

            session.save(t1);
            session.save(t2);
            session.save(t3);
            session.save(t4);
            session.save(t5);
            session.save(t6);
            session.save(t7);
            session.save(t8);
            session.save(t9);
            session.save(t10);
            session.save(t11);
            session.save(t12);
            session.save(t13);
            session.save(t14);
            session.save(t15);
            session.save(t16);
            session.save(t17);
            session.save(t18);
            session.save(t19);
            session.save(t20);
            session.save(t21);
            session.save(t22);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
