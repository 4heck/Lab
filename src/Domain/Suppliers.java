package Domain;

public class Suppliers {
    private String type; //Тип
    private String supplier; //Поставщик

    public Suppliers(String type, String supplier) {
        this.type = type;
        this.supplier = supplier;
    }
    public Suppliers(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n" + type + " " + supplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
