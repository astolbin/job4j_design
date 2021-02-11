package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class Order {
    private final int id;
    private final boolean payed;
    private final String[] comments;
    private final Buyer buyer;
    private final Product[] products;

    public Order(int id, boolean payed, String[] comments, Buyer buyer, Product[] products) {
        this.id = id;
        this.payed = payed;
        this.comments = comments;
        this.buyer = buyer;
        this.products = products;
    }

    public static void main(String[] args) {
        Buyer buyer = new Buyer(1, "Test 1");
        Product[] products = {
                new Product(1, "product 1", 1, new BigDecimal("23.0")),
                new Product(2, "product 2", 3, new BigDecimal("5.5")),
                new Product(3, "product 3", 2, new BigDecimal("8.3"))
        };
        String[] comments = {
                "comment 1",
                "comment 2"
        };
        Order order = new Order(1, false, comments, buyer, products);

        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(order);

        Order check = gson.fromJson(json, Order.class);
        System.out.println(order.equals(check));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                payed == order.payed &&
                Arrays.equals(comments, order.comments) &&
                Objects.equals(buyer, order.buyer) &&
                Arrays.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, payed, buyer);
        result = 31 * result + Arrays.hashCode(comments);
        result = 31 * result + Arrays.hashCode(products);
        return result;
    }

    private static class Buyer {
        private final int id;
        private final String fio;

        public Buyer(int id, String fio) {
            this.id = id;
            this.fio = fio;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Buyer)) return false;
            Buyer buyer = (Buyer) o;
            return id == buyer.id &&
                    Objects.equals(fio, buyer.fio);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, fio);
        }
    }

    private static class Product {
        private final int id;
        private final String name;
        private final int count;
        private final BigDecimal price;

        public Product(int id, String name, int count, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.count = count;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return id == product.id &&
                    count == product.count &&
                    Objects.equals(name, product.name) &&
                    Objects.equals(price, product.price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, count, price);
        }
    }
}
