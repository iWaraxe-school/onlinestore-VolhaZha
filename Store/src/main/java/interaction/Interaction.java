package interaction;

import multithreading.CreateOrders;
import store.Store;
import xml.ProductComparator;

import java.util.Scanner;

public class Interaction {
    Store onlineStore = Store.getInstance();
    ProductComparator productComparator = new ProductComparator(onlineStore);

    public void Interactive() {
        try {
            boolean flag = true;
            while (flag) {
                Scanner in = new Scanner(System.in);
                System.out.println("-----------------------------");
                System.out.print("Enter sort, top, order or quit: ");
                String data = in.nextLine();

                switch (data) {
                    case "top":
                        productComparator.top5();
                        break;
                    case "sort":
                        productComparator.sortProducts();
                        break;
                    case "order":
                        new CreateOrders().start();
                        break;
                    case "quit":
                        flag = false;
                        System.out.println("Exit.");
                        System.exit(0);
                    default:
                        System.out.println("Try again.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
