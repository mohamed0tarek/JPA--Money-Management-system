package DAO;

import model.Client;

import java.sql.Connection;
import java.sql.SQLException;

public interface ClientDao {
    public boolean addClient(Client ct);

    public boolean deleteClient(int id);

    public Client searchClient(int id);

    public boolean updateClientBalance(int id, double amount);

    public boolean getAllClients();

    public int lastClient();
}
