import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, List<Order>> productStream = orders
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Order::getProduct
                        ));

        Map<String, Double> productSumStream = orders
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Order::getProduct,
                                Collectors.summingDouble(Order::getCost)
                        ));


        List<Map.Entry<String, Double>> productTopSumSortStream = orders
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Order::getProduct,
                                Collectors.summingDouble(Order::getCost)

                        ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
                .limit(3)
                .toList();


        productStream.forEach((product, orderList) -> {
            System.out.println(product + ": " + orderList);
        });

        System.out.println();

        productSumStream.forEach((product, totalCost) -> {
            System.out.println(product + ": " + totalCost);
        });

        System.out.println();

        productTopSumSortStream.forEach(System.out::println);
    }
}
