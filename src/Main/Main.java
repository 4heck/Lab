package Main;

import DAO.RegistrationDAO;
import DAO.SellersDAO;
import DAO.ShopDAO;
import DAO.SupplierDAO;
import Domain.Registration;
import Domain.Sellers;
import Domain.Shop;
import Util.FreeQuery;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Observable;
import java.util.Scanner;
import java.util.function.Supplier;

import static javafx.application.Application.launch;
/*
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


    */public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stg) throws Exception{


        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        tab.setText("Магазины");
        tab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        tabPane.getTabs().add(tab);
        Tab tab2 = new Tab();
        tab2.setText("Поставщик");
        tab2.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        tabPane.getTabs().add(tab2);
        Tab tab3 = new Tab();
        tab3.setText("Продавцы");
        tab3.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        tabPane.getTabs().add(tab3);
        Tab tab4 = new Tab();
        tab4.setText("Регистрация");
        tab4.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        tabPane.getTabs().add(tab4);

        Button btn = new Button();
        btn.setText("Магазин");
        btn.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        System.out.print("Hello World!");
                    }
                }
        );

        Button btn2 = new Button();
        btn2.setText("Поставщик");
        btn2.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        System.out.print("Hello World!");
                    }
                }
        );

        Button btn3 = new Button();
        btn3.setText("Продавец");
        btn3.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        System.out.print("Hello World!");
                    }
                }
        );

        Button btn4 = new Button();
        btn4.setText("Регистрация");
        btn4.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        System.out.print("Hello World!");
                    }
                }
        );

        //Магазин
        TableView<Shop> shoptb = new TableView<Shop>();
        TableColumn<Shop, String> shopcol1 = new TableColumn<Shop, String>("Название");
        TableColumn<Shop, String> shopcol2 = new TableColumn<Shop, String>("Тип");
        shopcol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        shopcol2.setCellValueFactory(new PropertyValueFactory<>("type"));
        ShopDAO shops = new ShopDAO();
        ObservableList<Shop> list= FXCollections.observableArrayList(shops.getAll());
        shoptb.setItems(list);
        shoptb.getColumns().addAll(shopcol1,shopcol2);

        //Поставщик
        TableView<Supplier> suppliertb = new TableView<Supplier>();
        TableColumn<Supplier, String> suppliercol1 = new TableColumn<Supplier, String>("Тип");
        TableColumn<Supplier, String> suppliercol2 = new TableColumn<Supplier, String>("Поставщик");
        suppliercol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        suppliercol2.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        SupplierDAO suppliers = new SupplierDAO();
        ObservableList<Supplier> list2 = FXCollections.observableArrayList(suppliers.getAll());
        suppliertb.setItems(list2);
        suppliertb.getColumns().addAll(suppliercol1,suppliercol2);

        //Продавец
        TableView<Sellers> sellertb = new TableView<Sellers>();
        TableColumn<Sellers, String> sellercol1 = new TableColumn<Sellers, String>("Имя");
        TableColumn<Sellers, String> sellercol2 = new TableColumn<Sellers, String>("Город");
        TableColumn<Sellers, String> sellercol3 = new TableColumn<Sellers, String>("Название");
        TableColumn<Sellers, String> sellercol4 = new TableColumn<Sellers, String>("Цена");
        TableColumn<Sellers, String> sellercol5 = new TableColumn<Sellers, String>("Количество");
        sellercol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellercol2.setCellValueFactory(new PropertyValueFactory<>("city"));
        sellercol3.setCellValueFactory(new PropertyValueFactory<>("products"));
        sellercol4.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellercol5.setCellValueFactory(new PropertyValueFactory<>("amount"));
        SellersDAO sellers = new SellersDAO();
        ObservableList<Sellers> list3 = FXCollections.observableArrayList(sellers.getAll());
        sellertb.setItems(list3);
        sellertb.getColumns().addAll(sellercol1,sellercol2,sellercol3,sellercol4,sellercol5);

        //Регистрация
        TableView<Registration> registrationtb = new TableView<Registration>();
        TableColumn<Registration, String> registrationcol1 = new TableColumn<Registration, String>("Имя");
        TableColumn<Registration, String> registrationcol2 = new TableColumn<Registration, String>("Дата регистрации");
        TableColumn<Registration, String> registrationcol3 = new TableColumn<Registration, String>("Количество позиций");
        registrationcol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        registrationcol2.setCellValueFactory(new PropertyValueFactory<>("dateRegistration"));
        registrationcol3.setCellValueFactory(new PropertyValueFactory<>("numberOfPositions"));
        RegistrationDAO registrations = new RegistrationDAO();
        ObservableList<Registration> list4 = FXCollections.observableArrayList(registrations.getAll());
        registrationtb.setItems(list4);
        registrationtb.getColumns().addAll(registrationcol1,registrationcol2, registrationcol3);

        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);
        pane.getColumnConstraints().add(new ColumnConstraints(700));
        tab.setContent(pane);
        pane.add(shoptb,0,0);
        pane.add(btn,0,1);

        GridPane pane2 = new GridPane();
        pane2.setHgap(5);
        pane2.setVgap(5);
        pane2.getColumnConstraints().add(new ColumnConstraints(700));
        tab2.setContent(pane2);
        pane2.add(suppliertb,0,0);
        pane2.add(btn2,0,1);

        GridPane pane3 = new GridPane();
        pane3.setHgap(5);
        pane3.setVgap(5);
        pane3.getColumnConstraints().add(new ColumnConstraints(700));
        tab3.setContent(pane3);
        pane3.add(sellertb,0,0);
        pane3.add(btn3,0,1);

        GridPane pane4 = new GridPane();
        pane4.setHgap(5);
        pane4.setVgap(5);
        pane4.getColumnConstraints().add(new ColumnConstraints(700));
        tab4.setContent(pane4);
        pane4.add(sellertb,0,0);
        pane4.add(btn4,0,1);

        stg.setScene(new Scene(tabPane,700,500));
        stg.setTitle("Работа с БД");
        stg.show();
    }}

