package DAO;

import Connection.SingletoneEntityManager;
import jakarta.persistence.*;
import model.Client;
import model.Titles;

import java.util.List;


public class ClientDaoImpl implements ClientDao {
    private EntityManager em;
    private EntityTransaction tx;

    public ClientDaoImpl(EntityManager em){
        this.em = em;
        tx = em.getTransaction();
    }

    @Override
    public boolean addClient(Client ct) {
        if (ct.getBalance() < 0) {
            System.out.println("invalid balance amount .. ");
            return false;
        }
        tx.begin();
        em.persist(ct);
        tx.commit();
        return true;
    }

    @Override
    public boolean deleteClient(int id) {
        Client client = em.find(Client.class, id);
        tx.begin();
        em.remove(client);
        tx.commit();
        return true;
    }

    @Override
    public Client searchClient(int id) {
        return em.find(Client.class, id);
    }

    @Override
    public boolean updateClientBalance(int id, double amount) {
        Client client = em.find(Client.class, id);
        client.setBalance(client.getBalance() + amount);
        return true;
    }

    @Override
    public boolean getAllClients() {
        TypedQuery<Client> query = em.createNamedQuery(Client.CLIENT_ALL, Client.class);
        List<Client> result = query.getResultList();
        result.forEach(System.out::println);
        return true;
    }

    @Override
    public int lastClient(){
        TypedQuery<Integer> query = em.createNamedQuery(Client.CLIENT_LAST, Integer.class);
        return query.getSingleResult()+1;
    }
}
