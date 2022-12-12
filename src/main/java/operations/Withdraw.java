package operations;

import Connection.SingletoneEntityManager;
import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Client;


public class Withdraw implements Operations {

    private static SingletoneEntityManager connection = SingletoneEntityManager.getInstance();
    private static EntityManager em = connection.getEntityManager();
    private static EntityTransaction tx = em.getTransaction();

    public static boolean removeFromBalance(int id, double amount) {
        ClientDaoImpl clientDao = new ClientDaoImpl(em);
        HistoryDaoImpl historyDao = new HistoryDaoImpl(em);
        if (amount <= 0) {
            System.out.println("invalid input .. please entre positive amount greater than 1");
            return false;
        }
        if (clientDao.searchClient(id).getBalance() < amount) {
            System.out.println("no enough money in your account .. please try again with smaller amount");
            return false;
        }
        tx.begin();
        boolean removeSuccessfully = clientDao.updateClientBalance(id, -amount);
        boolean withdrawHistory = historyDao.addToHistory(id, "Withdraw " + amount + "$");
        if (!removeSuccessfully || !withdrawHistory) {
            tx.rollback();
        } else {
            tx.commit();
        }
        return true;
    }
}
