package busTicketSystem;

import entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ticketing {

	public static void main(String[] args) {
		displayQuery(getAvailableTickets());
		displayQuery(getAllTickets());
	}

	public static List<Ticket> getAvailableTickets() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Ticket.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			List<Ticket> tickets = session.createQuery("FROM Ticket WHERE first_name IS NULL").getResultList();
			return tickets;

		}
		finally {
			factory.close();
		}
	}

	public static List<Ticket> getAllTickets() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Ticket.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			List<Ticket> tickets = session.createQuery("FROM Ticket").getResultList();
			return tickets;

		}
		finally {
			factory.close();
		}
	}

	public static List<Ticket> getPurchasedTickets() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Ticket.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Ticket> tickets = session.createQuery("FROM Ticket WHERE first_name IS NOT NULL", Ticket.class).list();
			return tickets;
		}  finally {
			factory.close();
		}
	}

	public static void updateInfo(int ticketNum, String firstName, String lastName, String email, String phone, String gender, int age) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Ticket.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nGetting info of ticket: " + ticketNum);
			Ticket ticket = session.get(Ticket.class, ticketNum);

            ticket.setFirstName(firstName);
            ticket.setLastName(lastName);
            ticket.setEmail(email);
            ticket.setPhone(phone);
            ticket.setGender(gender);
            ticket.setAge(age);

			session.getTransaction().commit();

		}
		finally {
			factory.close();
		}
		writeTicketsToFile();
	}

	public static void displayQuery(List<Ticket> tickets) {
		for(Ticket t : tickets) {
			System.out.println(t);
		}
	}

	public static void writeTicketsToFile() {
		String ticketPath = System.getProperty("user.dir") + "\\src\\tickets.txt";
		List<Ticket> ticketList = getPurchasedTickets();
		String ticketFile = ticketList.stream().map(ticket -> ticket.printTicket()).reduce((acc, next) -> acc + "\n" + next).orElse("");
		try {
			Files.write(Paths.get(ticketPath), ticketFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}