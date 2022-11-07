package xml;

import domain.Product;
import store.Store;

import java.util.*;
import java.util.stream.Stream;

public class ProductComparator {
    private static final Comparator<Product> NAME_COMPARATOR = Comparator.comparing(Product::getName, String::compareToIgnoreCase);
    private static final Comparator<Product> PRICE_COMPARATOR = Comparator.comparing(Product::getPrice);
    private static final Comparator<Product> RATE_COMPARATOR = Comparator.comparing(Product::getRate);

    private static final Map<String,Comparator<Product>> COMPARATOR_MAP = new HashMap<String, Comparator<Product>>(){{
        put("name", NAME_COMPARATOR);
        put("price", PRICE_COMPARATOR);
        put("rate", RATE_COMPARATOR);
    }};

    Store store;

    public ProductComparator(Store store) {
        this.store = store;
    }

    private Comparator<Product> chooseComparator (Map.Entry<String,String> entry){
        String comparatorName = entry.getKey();
        Comparator<Product> productComparator = COMPARATOR_MAP.get(comparatorName);
        if(entry.getValue().equals("desc")){
            productComparator = productComparator.reversed();
        }
        return productComparator;
    }

    private Comparator<Product> buildComparator(Map<String, String> comparatorConfig) {
        try {
            return comparatorConfig.entrySet().stream()
                    .map(this::chooseComparator)
                    .filter(Objects::nonNull)
                    .reduce(Comparator::thenComparing)
                    .orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Product> productStream(){
        return store.getAllProducts().stream();
    }
    public void sortProducts () {
        System.out.println("-----------------------------");
        System.out.println("   SortedListOFAllProducts");
        System.out.println("-----------------------------");

        XMLparser parser = new XMLparser();
        Map<String, String> map = parser.parse();
        Comparator<Product> productComparator = buildComparator(map);

        productStream().sorted(productComparator)
                .forEach(System.out::println);
    }

    public void top5 () {
        System.out.println("-----------------------------");
        System.out.println("   Top5");
        System.out.println("-----------------------------");
        productStream()
                .sorted(PRICE_COMPARATOR.reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}