package operations;

import Connection.SingletoneEntityManager;
import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Deposite implements Operations {
    private static SingletoneEntityManager connection = SingletoneEntityManager.getInstance();
    private static EntityManager em = connection.getEntityManager();
    private static EntityTransaction tx = em.getTransaction();

    public static boolean addToBalance(int id, double amount) {
        if (amount <= 0.9) {
            System.out.println("invalid input .. please entre positive amount greater than 1");
            return false;
        }
        ClientDaoImpl clientDao = new ClientDaoImpl(em);
        HistoryDaoImpl historyDao = new HistoryDaoImpl(em);
        tx.begin();
        boolean addSuccessfully = clientDao.updateClientBalance(id, amount);
        boolean depositHistory = historyDao.addToHistory(id, " Deposit " + amount + "$ ");
        if (!addSuccessfully || !depositHistory) {
            tx.rollback();
        } else {
            tx.commit();
        }
        return true;
    }
}
