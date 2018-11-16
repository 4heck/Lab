package Domain;

public class Sellers {
    private String name; //Имя
    private String city; //Город
    private String products; //Название товара
    private int price; //Цена
    private int amount; //Кол-во

    public Sellers(String name, String city, String products, int price, int amount) {
        this.name = name;
        this.city = city;
        this.products = products;
        this.price = price;
        this.amount = amount;
    }

    public Sellers(String name, String city, String products){
        this.name = name;
        this.city = city;
        this.products = products;
    }

    public Sellers(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return  "\n" + name + " " + city + " " + products + " " + price + " " + amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
