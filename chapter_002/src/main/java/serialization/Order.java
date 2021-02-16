package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlAttribute
    private int id;

    @XmlAttribute
    private boolean payed;

    @XmlElementWrapper(name = "comments")
    @XmlElement(name = "comment")
    private String[] comments;

    private Buyer buyer;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private Product[] products;

    public Order() {}

    public int getId() {
        return id;
    }

    public boolean isPayed() {
        return payed;
    }

    public String[] getComments() {
        return comments;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Product[] getProducts() {
        return products;
    }

    public Order(int id, boolean payed, String[] comments, Buyer buyer, Product[] products) {
        this.id = id;
        this.payed = payed;
        this.comments = comments;
        this.buyer = buyer;
        this.products = products;
    }

    public static void main(String[] args) throws Exception {
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

        JAXBContext context = JAXBContext.newInstance(Order.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(order, writer);
            xml = writer.getBuffer().toString();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Order orderCheck = (Order) unmarshaller.unmarshal(reader);
            System.out.println(order.equals(orderCheck));
        }

        System.out.println(new JSONObject(order).toString());
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

    @XmlRootElement(name = "buyer")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Buyer {
        @XmlAttribute
        private int id;

        @XmlAttribute
        private String fio;

        public Buyer() {}

        public Buyer(int id, String fio) {
            this.id = id;
            this.fio = fio;
        }

        public int getId() {
            return id;
        }

        public String getFio() {
            return fio;
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

    @XmlRootElement(name = "product")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Product {
        @XmlAttribute
        private int id;

        @XmlAttribute
        private String name;

        @XmlAttribute
        private int count;

        @XmlAttribute
        private BigDecimal price;

        public Product() {}

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

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }
}
