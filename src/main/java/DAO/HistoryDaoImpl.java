package DAO;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Client;
import model.History;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryDaoImpl implements HistoryDao {

    private EntityManager em;

    public HistoryDaoImpl(EntityManager em){
        this.em = em;
    }


    @Override
    public boolean addToHistory(int ct_id, String operation) {
        Client client = em.find(Client.class, ct_id);
        em.persist(new History(operation, client));
        return true;
    }

    @Override
    public boolean getAllHistory() {
        TypedQuery<History> query = em.createNamedQuery(History.HISTORY_ALL, History.class);
        List<History> result = query.getResultList();
        result.forEach(System.out::println);
        return true;
    }

    @Override
    public boolean getClientHistory(int ct_id) {
        TypedQuery<History> query = em.createNamedQuery(History.HISTORY_OF_CLIENT, History.class);
        query.setParameter("id", ct_id);
        List<History> result = query.getResultList();
        result.forEach(System.out::println);
        return true;
    }
}
