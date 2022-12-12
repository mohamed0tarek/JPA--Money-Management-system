package view;

import Connection.SingletoneEntityManager;
import DAO.ClientDaoImpl;
import jakarta.persistence.EntityManager;
import model.Client;
import model.Titles;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static SingletoneEntityManager connection = SingletoneEntityManager.getInstance();
    private static EntityManager em = connection.getEntityManager();


    public static void main(String[] args) {
        boolean flage = false;
        do {
            flage = false;
            System.out.println("""
                    Welcome Sir.
                    Please choose from the following ..

                    1 - Sign in as client
                    2 - Sign in as admin
                    3 - Exit
                    """);
            System.out.print("your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            blankWindow();
            Client client = Authentication(choice);
            if (client == null) return;

            switch (choice) {
                case 1:
                    clientWindow(client.getFirstName(), client.getClientId());
                    break;
                case 2:
                    adminWindow(client.getFirstName());
                    break;
                case 3:
                    em.close();
                    connection.getEntityManagerFactory().close();
                    return;
                default: {
                    System.err.println("invalid choice .. ");
                    System.out.println("please try again ..");
                    flage = true;
                }
            }
        } while (flage);
    }

    public static void blankWindow() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    private static Client Authentication(int choice) {
        blankWindow();
        Titles title = (choice == 1) ? Titles.client : Titles.admin;
        System.out.println("Please enter your ID and Password\n");
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();
        Client client = new ClientDaoImpl(em).searchClient(id);
        if (client != null && client.getRole().equals(title) && client.getPassword().equals(password)) {
            return client;
        } else {
            System.err.println("Invalid id or password\n");
            System.out.print("want to try again (enter 1 -> yes/ 0 -> no) : ");
            int c = scanner.nextInt();
            scanner.nextLine();
            if (c == 0) {
                return null;
            } else {
                Authentication(choice);
            }
        }
        return null;
    }

    private static void adminWindow(String name) {
        blankWindow();
        System.out.println("Welcome " + name);
        System.out.println("""
                Please choose the operation..

                1 - Add client
                2 - Delete client
                3 - Show all clients in the system
                4 - Show all operations history of clients
                5 - Show history of a client
                6 - search for a client
                7 - exit
                """);
        boolean flage = false;
        do {
            flage = false;
            System.out.print("your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    AdminAuthority.adminAddClient();
                    break;
                case 2:
                    AdminAuthority.adminDeleteClient();
                    break;
                case 3:
                    AdminAuthority.adminShowAllClients();
                    break;
                case 4:
                    AdminAuthority.adminShowAllHistory();
                    break;
                case 5:
                    AdminAuthority.adminShowClientHistory();
                    break;
                case 6:
                    AdminAuthority.adminSearchClient();
                    break;
                case 7:
                    return;
                default: {
                    System.err.println("invalid choice .. ");
                    System.out.println("please try again ..");
                    flage = true;
                }
            }

            if (!flage) {
                System.out.print("\nPress enter .... ");
                scanner.hasNextLine();
                adminWindow(name);
            }
        } while (flage);
    }

    private static void clientWindow(String name, int id) {
        blankWindow();
        System.out.println("Welcome " + name);
        System.out.println("""
                Please choose the operation..

                1 - Account Info.
                2 - Transfer to another account
                3 - Deposit
                4 - Withdraw
                5 - Show latest operations
                6 - exit
                """);
        boolean flage = false;
        do {
            flage = false;
            System.out.print("your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ClientAuthority.AccountInformation(id);
                    break;
                case 2:
                    ClientAuthority.transferMoneyToAccount(id);
                    break;
                case 3:
                    ClientAuthority.depositMoney(id);
                    break;
                case 4:
                    ClientAuthority.withdrawMoney(id);
                    break;
                case 5:
                    ClientAuthority.showMyHistory(id);
                    break;
                case 6:
                    return;
                default: {
                    System.err.println("invalid choice .. ");
                    System.out.println("please try again ..");
                    flage = true;
                }
            }
            if (!flage) {
                System.out.print("\nPress enter .... ");
                scanner.hasNextLine();
                clientWindow(name, id);
            }
        } while (flage);
    }
}
