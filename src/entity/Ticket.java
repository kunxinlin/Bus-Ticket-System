package entity;

import javax.persistence.*;
import java.sql.*;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "ticket")
public class Ticket {

    //Database Mapping
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_number")
    private int ticketNumber;

    @Column(name = "date")
    private Date date;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_time")
    private Time departure;

    @Column(name = "estimated_time_arrival")
    private Timestamp eta;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "price")
    private double price;


    //Constructors
    public Ticket() {

    }

    public Ticket(Date date, String origin, String destination, Time departure, Timestamp eta, double price) {
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.eta = eta;
        this.price = price;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Time getDeparture() {
        return departure;
    }

    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    public Timestamp getEta() {
        return eta;
    }

    public void setEta(Timestamp eta) {
        this.eta = eta;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //To string method
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber=" + ticketNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", date='" + date + '\'' +
                ", destination='" + destination + '\'' +
                ", departure time='" + departure + '\'' +
                ", eta='" + eta + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public double getDiscountedPrice() {
        double discountedPrice = price;
        discountedPrice *= gender.toLowerCase().equals("female") ? 0.75 : 1;
        discountedPrice *= age <= 12 ? .5 : age >= 60 ? .4 : 1;
        return discountedPrice;
    }

    public String printTicket() {
        return String.format("Ticket No.: %s || Name: %s, %s || Date: %s || Departure: %s || Departure Time: %s || Arrival: %s || Estimated Arrival Time: %s || Price: %.2f",
                ticketNumber,
                lastName, firstName,
                new SimpleDateFormat("MM/dd/yyyy").format(date),
                origin,
                new SimpleDateFormat("hh:mm aa").format(departure),
                destination,
                new SimpleDateFormat("MM/dd/yyyy hh:mm aa").format(eta.getTime()),
                getDiscountedPrice()
        );
    }

    /*public void writeTicketToFile() throws IOException {
        String tempPath = System.getProperty("user.dir") + "\\src\\tickets.txt";
        Files.createFile(Paths.get(tempPath));
        Files.write(Paths.get(tempPath),
                String.format("""
                              Ticket No.: %s
                              Date: %s
                              Passenger: %s, %s
                              Departure: %s
                              Departure Time: %s
                              Arrival: %s
                              Estimated Arrival Time: %s
                              Price: %s
                              """,
                        ticketNumber,
                        new SimpleDateFormat("MM/dd/yyyy").format(date),
                        lastName, firstName,
                        origin,
                        new SimpleDateFormat("hh:mm aa").format(departure),
                        destination,
                        new SimpleDateFormat("MM/dd/yyyy hh:mm aa").format(eta.getTime()),
                        price
                        ).getBytes(), StandardOpenOption.CREATE_NEW);
    }*/
}
