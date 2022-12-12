package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "history")
@NamedQuery(name = History.HISTORY_ALL, query = "select h from History h order by h.history_id desc ")
@NamedQuery(name = History.HISTORY_OF_CLIENT, query = "select h from History h where h.clientId.clientId = :id order by h.history_id desc ")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int history_id;
    private String operation;
    private String date;
    @ManyToOne
    @JoinColumn(name = "ct_id")
    Client clientId;
    @Transient
    public static final String HISTORY_ALL = "History.All";
    @Transient
    public static final String HISTORY_OF_CLIENT = "History.One";

    public History() {
    }

    public History(String operation, Client clientId) {
        var f = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm:ss");
        this.operation = operation;
        this.clientId = clientId;
        this.date = LocalDateTime.now().format(f);
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        var f = DateTimeFormatter.ofPattern("yyyy MM dd 'at' hh:mm");
        return "History {" +
                "history_id= " + history_id +
                ", operation= '" + operation + '\'' +
                ", date= " + date +
                ", clientId= " + clientId.getClientId() +
                '}';
    }
}