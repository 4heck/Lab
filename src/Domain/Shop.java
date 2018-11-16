package Domain;

public class Shop {
    private String name; //Название
    private String type; //Тип магазина

    public Shop(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Shop(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n" + name+" "+type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
