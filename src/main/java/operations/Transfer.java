package operations;

import Connection.SingletoneEntityManager;
import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Client;


public class Transfer implements Operations {

    private static SingletoneEntityManager connection = SingletoneEntityManager.getInstance();
    private static EntityManager em = connection.getEntityManager();
    private static EntityTransaction tx = em.getTransaction();

    public static boolean executeTransfer(int idFrom, int idTo, double amount) {
        HistoryDaoImpl history = new HistoryDaoImpl(em);
        ClientDaoImpl clientDao = new ClientDaoImpl(em);
        Client client = clientDao.searchClient(idFrom);
        if (client.getBalance() < amount) {
            System.err.println("your balance has no enough money to transfer !!");
            return false;
        }
        tx.begin();
        boolean TransferFrom = clientDao.updateClientBalance(idFrom, -amount);
        boolean TransferTo = clientDao.updateClientBalance(idTo, amount);
        boolean historyFrom = history.addToHistory(idFrom, "Transfer " + amount + "$ to " + idTo);
        boolean historyTo = history.addToHistory(idTo, "Receive " + amount + "$ from " + idFrom);
        if (!TransferFrom || !TransferTo || !historyFrom || !historyTo) {
            tx.rollback();
        } else {
            tx.commit();
        }
        return true;
    }
}
