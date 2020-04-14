package entity;

import javax.persistence.*;
import java.sql.*;

//Student Entity
@Entity //This will let Java know that this is an entity that we are going to map to a database table.
@Table(name = "ticket") //This is for the actual name of the database table name we are mapping to the class.
public class Ticket {

    //Database Mapping
    @Id //This will map the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This is used with auto increment for your primary key.
    @Column(name = "ticket_number") //This is mapping the primary key to the id column in your database.
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

    @Column(name = "first_name") //This will map the firstName field to the column named first_name in your student table.
    private String firstName;

    @Column(name = "last_name") //This will map the lastName field to the column named last_name in your student table.
    private String lastName;

    @Column(name = "email") //This will map the email field to the column named email in your student table.
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

    public Ticket(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
        return "Student{" +
                "ticketNumber=" + ticketNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
