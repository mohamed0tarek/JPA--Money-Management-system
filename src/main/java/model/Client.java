package model;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
@NamedQuery(name = Client.CLIENT_ALL, query = "select ct from Client ct")
@NamedQuery(name = Client.CLIENT_LAST, query = "select max(ct.clientId) from Client ct")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private int clientId;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private double balance;
    @Enumerated(EnumType.STRING)
    private Titles role;
    @Transient
    public static final String CLIENT_ALL = "Client.ALL";
    @Transient
    public static final String CLIENT_LAST = "Client.Last";

    public Client() {
    }

    public Client(String password, String firstName, String lastName, double balance, Titles role) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.role = role;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Titles getRole() {
        return role;
    }

    public void setRole(Titles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client { " +
                "clientId= " + clientId +
                ", password= '" + password + '\'' +
                ", firstName= '" + firstName + '\'' +
                ", lastName= '" + lastName + '\'' +
                ", balance= " + balance +
                ", role= " + role +
                '}';
    }
}
