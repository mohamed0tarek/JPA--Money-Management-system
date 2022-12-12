package view;

import Connection.SingletoneEntityManager;
import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Client;
import model.Titles;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminAuthority {
    private static SingletoneEntityManager connection = SingletoneEntityManager.getInstance();
    private static EntityManager em = connection.getEntityManager();

    private static Scanner scanner = new Scanner(System.in);

    public static void blankWindow() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static boolean adminAddClient() {
        blankWindow();
        System.out.println("""
                Please enter the info.

                """);
        System.out.print("ID : ");
        System.out.println(new ClientDaoImpl(em).lastClient());
        System.out.print("Password : ");
        String password = scanner.nextLine();
        System.out.print("First Name : ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name : ");
        String lastName = scanner.nextLine();
        System.out.print("Balance : ");
        double balance = scanner.nextDouble();

        return new ClientDaoImpl(em).addClient(new Client(password, firstName, lastName, balance, Titles.client));
    }

    public static boolean adminDeleteClient()  {
        blankWindow();
        System.out.println("Please enter the id of client to be deleted ..");
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return new ClientDaoImpl(em).deleteClient(id);
    }

    public static boolean adminShowAllClients() {
        blankWindow();
        System.out.println("The current existing clients");
        System.out.println("------------------------------");
        return new ClientDaoImpl(em).getAllClients();
    }

    public static boolean adminShowAllHistory() {
        blankWindow();
        System.out.println("History Recorded ");
        System.out.println("---------------------------");
        return new HistoryDaoImpl(em).getAllHistory();
    }

    public static boolean adminShowClientHistory() {
        blankWindow();
        System.out.println("Please enter the id of client to show his history ..");
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return new HistoryDaoImpl(em).getClientHistory(id);
    }

    public static boolean adminSearchClient() {
        blankWindow();
        System.out.println("Please enter the id of client List his info ..");
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Client client = new ClientDaoImpl(em).searchClient(id);
        System.out.println(
                "client_id : " + client.getClientId() +
                        "\nfirst_name : " + client.getFirstName() +
                        "\nlast_name : " + client.getLastName() +
                        "\nbalance : " + client.getBalance() +
                        "\ntitle : " + client.getRole() + "\n");
        return true;
    }
}
