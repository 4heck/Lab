package Domain;

public class Registration {
    private String name;        // Имя
    private String dateRegistration;        // Дата регистрации
    private String numberOfPositions;       // оличество позиций

    public Registration(String name, String dateRegistration, String numberOfPositions) {
        this.name = name;
        this.dateRegistration = dateRegistration;
        this.numberOfPositions = numberOfPositions;
    }
    public Registration(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n" +  name + " " + dateRegistration + " " + numberOfPositions ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getNumberOfPositions() {
        return numberOfPositions;
    }

    public void setNumberOfPositions(String numberOfPositions) {
        this.numberOfPositions = numberOfPositions;
    }
}
