package Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SingletoneEntityManager {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    private SingletoneEntityManager() {
    }

    private static SingletoneEntityManager connection = null;

    public static SingletoneEntityManager getInstance() {
        if (connection == null)
            return new SingletoneEntityManager();
        return connection;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
