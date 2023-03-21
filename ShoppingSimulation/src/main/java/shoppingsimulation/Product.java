package shoppingsimulation;

public class Product {

    private Long id;

    private String name;

    private String barCode;

    private int price;

    public Product(String name, String barCode, int price) {
        this.name = name;
        this.barCode = barCode;
        this.price = price;
    }

    public Product(Long id, String name, String barCode, int price) {
        this.id = id;
        this.name = name;
        this.barCode = barCode;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBarCode() {
        return barCode;
    }

    public int getPrice() {
        return price;
    }
}
