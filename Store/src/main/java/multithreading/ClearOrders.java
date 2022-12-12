package multithreading;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClearOrders extends Thread {
    private final ProductStorage productStorage = ProductStorage.getInstance();

    @Override
    public void run(){
        while (true){
            try {
            TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread name: " + Thread.currentThread().getName());
            productStorage.clearPurchasedProductList();
            System.out.println("Cart cleared");
            productStorage.printPurchasedProducts();
        }
    }
}
