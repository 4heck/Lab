package Main;

import DAO.RegistrationDAO;
import DAO.SellersDAO;
import DAO.ShopDAO;
import DAO.SupplierDAO;
import Domain.Registration;
import Domain.Shop;
import Util.FreeQuery;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Scanner;
import static javafx.application.Application.launch;

     public class Main {



        public static void main(String[] args) {
        System.out.println("Введите вариант работы программы:");
        System.out.println("1. Вывести регистрации");
        System.out.println("2. Вывести продавцов");
        System.out.println("3. Вывести поставщиков");
        System.out.println("4. Вывести магазины");
        System.out.println("5. Внести из txt в регистрацию");
        System.out.println("6. Внести из txt в продавцов");
        System.out.println("7. Внести из txt в магазины");
        System.out.println("8. Внести из txt в поставщиков");
        System.out.println("9. freeQuery");
        System.out.println("10. Удалить(shop)");
        System.out.println();

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        RegistrationDAO registrationDAO = new RegistrationDAO();
        SellersDAO sellersDAO = new SellersDAO();
        ShopDAO shopDAO = new ShopDAO();
        Shop shop = new Shop("Вишня");
        SupplierDAO supplierDAO = new SupplierDAO();
        FreeQuery freeQuery = new FreeQuery();


        switch(choice){
            case 1 :
                System.out.println(registrationDAO.getAll());
                break;
            case 2 :
                System.out.println(sellersDAO.getAll());
                break;
            case 3 :
                System.out.println(supplierDAO.getAll());
                break;
            case 4 :
                System.out.println(shopDAO.getAll());
                break;
            case 5 :
                registrationDAO.readFileInsert("Регистрация");
                break;
            case 6 :
                sellersDAO.readFileInsert("Продавцы");
                break;
            case 7 :
                shopDAO.readFileInsert("Магазин");
                break;
            case 8 :
                supplierDAO.readFileInsert("Поставщик");
                break;
            case 9 :
                freeQuery.freeQuery();
                break;
            case 10 :
                shopDAO.delete(shop);
                break;
            default:
                break;
        }}}


    /*public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stg) throws Exception{

        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);

        Button btn = new Button();
        btn.setText("Button name");
        btn.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        System.out.print("Hello World!");
                    }
                }
        );

        TableView<Shop> tb = new TableView<Shop>();
        TableColumn<Shop, String> col1 = new TableColumn<Shop, String>("Имя");
        TableColumn<Shop, String> col2 = new TableColumn<Shop, String>("Продукт");

            col1.setCellValueFactory(new PropertyValueFactory<>("name"));
            col2.setCellValueFactory(new PropertyValueFactory<>("type"));

        Shop name1 = new Shop("Вася","Товар");
        Shop name2 = new Shop("Вася2","Товар2");
        Shop name3 = new Shop("Вася3","Товар3");

        ObservableList<Shop> list= FXCollections.observableArrayList(name1,name2,name3);
        tb.setItems(list);

        tb.getColumns().addAll(col1,col2);

        pane.add(tb,0,0);
        pane.add(btn,1,0);

        stg.setScene(new Scene(pane,500,500));
        stg.setTitle("Название формы");
        stg.show();
    }}
*/
