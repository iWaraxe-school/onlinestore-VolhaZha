package interaction;

import store.Store;
import xml.ProductComparator;

import java.util.Scanner;

public class Interaction {
    Store onlineStore = new Store();
    ProductComparator productComparator = new ProductComparator(onlineStore);

    public void Interactive() {
        Scanner in = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.print("Enter sort, top or quit: ");
        String data = in.nextLine();

        switch (data) {
            case "top":
                productComparator.top5();
                break;
            case "sort":
                productComparator.sortProducts();
                break;
            case "quit":
                System.out.println("Exit.");
                System.exit(0);
            default:
                System.out.println("Try again.");
    }
    }
}
