import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Application {

    @Override
    public void start(Stage stage) {

        Scanner userInput = new Scanner(System.in);
        ArrayList<Magazine> magazines = new ArrayList<Magazine>(); // Magazine Object
        ArrayList<Paying> payingCustomer = new ArrayList<Paying>(); // Paying Customer Object
        ArrayList<Associate> associateCustomer = new ArrayList<Associate>(); // Associate Customer Object
        ArrayList<Supplement> tempSupplements = new ArrayList<Supplement>();
        loadData(magazines, payingCustomer, associateCustomer);

        // View Layout GridPane
        GridPane viewLayoutPane = new GridPane();
        viewLayoutPane.setHgap(5);
        viewPane(viewLayoutPane, magazines, payingCustomer, associateCustomer);
        GridPane editLayoutPane = new GridPane();
        editPane(editLayoutPane, viewLayoutPane, editLayoutPane, magazines, payingCustomer, associateCustomer,
                tempSupplements);
        GridPane createLayoutPane = new GridPane();
        createLayoutPane.setAlignment(Pos.CENTER);
        createPane(viewLayoutPane, createLayoutPane, magazines, payingCustomer, associateCustomer, tempSupplements);

        GridPane mainLayoutPane = new GridPane();
        // mainLayoutPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        mainLayoutPane.setMinSize(500, 600);
        mainLayoutPane.setPrefSize(500, 600);
        mainLayoutPane.setPadding(new Insets(10, 10, 10, 10));
        mainLayoutPane.setVgap(5);
        mainLayoutPane.setHgap(5);
        HBox menuBtnBox = new HBox(10);
        // menuBtnBox.setStyle("-fx-border-color: purple; -fx-border-width: 2;");
        menuBtnBox.setAlignment(Pos.BASELINE_LEFT);
        menuBtnBox.setMinHeight(30);
        Button viewBtn = new Button("View");
        Button createBtn = new Button("Create");
        Button editBtn = new Button("Edit");
        menuBtnBox.getChildren().addAll(viewBtn, createBtn, editBtn);
        mainLayoutPane.add(menuBtnBox, 0, 0); // Column 0, Row 0
        mainLayoutPane.add(viewLayoutPane, 0, 1); // Column 0, Row 0

        // Scene
        GridPane.setColumnSpan(menuBtnBox, 2);
        Scene scene = new Scene(mainLayoutPane);
        stage.setTitle("Magazine Service");
        stage.setScene(scene);
        stage.show();
        userInput.close();

        // Event Handler

        viewBtn.setOnAction(event -> {
            // Handle the event here
            for (Node node : mainLayoutPane.getChildren()) {
                // Check if the node is in the desired position (Row 1, Column 0)
                if (GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 0) {
                    // Remove the node
                    mainLayoutPane.getChildren().remove(node);
                    break; // Once the node is removed, exit the loop
                }
            }
            mainLayoutPane.add(viewLayoutPane, 0, 1);

        });

        editBtn.setOnAction(event -> {
            // Handle the event here
            for (Node node : mainLayoutPane.getChildren()) {
                // Check if the node is in the desired position (Row 1, Column 0)
                if (GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 0) {
                    // Remove the node
                    mainLayoutPane.getChildren().remove(node);
                    break; // Once the node is removed, exit the loop
                }
            }
            mainLayoutPane.add(editLayoutPane, 0, 1);

        });

        createBtn.setOnAction(event -> {
            // Handle the event here
            for (Node node : mainLayoutPane.getChildren()) {
                // Check if the node is in the desired position (Row 1, Column 0)
                if (GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 0) {
                    // Remove the node
                    mainLayoutPane.getChildren().remove(node);
                    break; // Once the node is removed, exit the loop
                }
            }
            mainLayoutPane.add(createLayoutPane, 0, 1);

        });

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void viewPane(GridPane viewLayoutPane, ArrayList<Magazine> magazines,
            ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer) {
        viewLayoutPane.getChildren().clear();
        VBox billingPane = new VBox();
        Label informationPanelLabel = new Label("Information Panel:");
        informationPanelLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label emptyLabel = new Label("");
        emptyLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label magazineNamLabel = new Label();
        Label magazineCostLabel = new Label();
        Label supplementNameLabel = new Label();
        Label supplementPriceLabel = new Label();
        Label customerTypeLabel = new Label();
        Label customerNameLabel = new Label();
        Label customerEmailLabel = new Label();
        Label weekStartedLabel = new Label();
        Label paymentTypeLabel = new Label();
        Label subscriptionPromptLabel = new Label();
        Label associatePromptLabel = new Label();
        Button billingBtn = new Button("Show Billing History?");
        TreeView<String> viewSupplement = new TreeView<>();
        TreeView<String> viewCustomer = new TreeView<>();
        ListView<String> subscriptionListView = new ListView<String>();
        subscriptionListView.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        subscriptionListView.setMinHeight(50);
        subscriptionListView.setMaxHeight(50);
        // subscriptionListView.setMaxWidth(300);
        ListView<String> associateListView = new ListView<String>();
        associateListView.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        associateListView.setMinHeight(50);
        associateListView.setMaxHeight(50);
        // associateListView.setMaxWidth(300);
        ScrollPane supplementList = new ScrollPane(viewSupplement);
        supplementList.setPrefSize(200, 300);
        supplementList.setStyle("-fx-border-color: Black; -fx-border-width: 2;");
        ScrollPane customerList = new ScrollPane(viewCustomer);
        customerList.setPrefSize(200, 300);
        customerList.setStyle("-fx-border-color: Black; -fx-border-width: 2; ");
        VBox informationPanelTop = new VBox();
        // informationPanelTop.setMinSize(200, 400);
        informationPanelTop.setSpacing(10);
        informationPanelTop.setStyle("-fx-background-color: lightblue;");
        informationPanelTop.setPadding(new Insets(5));
        informationPanelTop.getChildren().addAll(informationPanelLabel, magazineNamLabel, magazineCostLabel,
                supplementNameLabel, supplementPriceLabel);
        VBox informationPanelBottom = new VBox();
        informationPanelBottom
                .setStyle("-fx-border-color: Black; -fx-border-width: 2; -fx-background-color: transparent;");
        // informationPanelBottom.setMaxHeight(500);
        informationPanelBottom.setPadding(new Insets(5));
        informationPanelBottom.setSpacing(10);

        informationPanelBottom.getChildren().addAll(emptyLabel,
                customerTypeLabel, customerNameLabel,
                customerEmailLabel,
                paymentTypeLabel, weekStartedLabel, subscriptionPromptLabel, subscriptionListView, associatePromptLabel,
                associateListView, billingBtn);
        billingBtn.setVisible(false);

        viewLayoutPane.add(supplementList, 0, 0);
        viewLayoutPane.add(customerList, 0, 1);
        viewLayoutPane.add(informationPanelTop, 1, 0);
        viewLayoutPane.add(informationPanelBottom, 1, 0);
        GridPane.setRowSpan(informationPanelTop, 2);
        GridPane.setRowSpan(informationPanelBottom, 2);
        viewLayoutPane.setVgap(5);
        // GridPane.setColumnSpan(informationPanelBottom, 2);
        // GridPane.setColumnSpan(informationPanelTop, 2);

        // Tree view for Supplement
        TreeItem<String> magazineRoot = new TreeItem<>("Magazine");
        magazineRoot.setExpanded(true);

        // Iterate through each magazine in the list
        for (int i = 0; i < magazines.size(); i++) {
            String magazineName = magazines.get(i).getMagazineName();
            TreeItem<String> magazineTreeItem = new TreeItem<>(magazineName);

            // Add supplements as child nodes of the magazine item
            if (magazines.get(i).getSupplementCount() != 0) {
                for (int j = 0; j < magazines.get(i).getSupplementCount(); j++) {
                    String supplementName = magazines.get(i).getSupplementIndex(j);
                    TreeItem<String> supplementTreeItem = new TreeItem<>(supplementName);
                    magazineTreeItem.getChildren().add(supplementTreeItem);
                }
            }

            magazineRoot.getChildren().add(magazineTreeItem);
        }

        viewSupplement.setRoot(magazineRoot);

        // End of Tree View For Supplement

        // Tree view for Customer
        TreeItem<String> customerRoot = new TreeItem<>("Customers");
        customerRoot.setExpanded(true);
        // Paying Customer Tree View
        TreeItem<String> payingCustomerItem = new TreeItem<>("Paying Customers");
        for (int i = 0; i < payingCustomer.size(); i++) {
            payingCustomerItem.getChildren().add(new TreeItem<String>(payingCustomer.get(i).getName()));

        }
        payingCustomerItem.setExpanded(true);
        customerRoot.getChildren().add(payingCustomerItem);
        // Associate Customer Tree View
        TreeItem<String> associateCustomerItem = new TreeItem<>("Associate Customer");
        for (int i = 0; i < associateCustomer.size(); i++) {
            associateCustomerItem.getChildren().add(new TreeItem<>(associateCustomer.get(i).getName()));
        }
        associateCustomerItem.setExpanded(true);
        customerRoot.getChildren().add(associateCustomerItem);
        viewCustomer.setRoot(customerRoot);

        viewCustomer.setOnMouseClicked(event -> {
            billingBtn.setVisible(false);
            magazineNamLabel.setText("");
            magazineCostLabel.setText("");
            supplementNameLabel.setText("");
            supplementPriceLabel.setText("");
            customerTypeLabel.setText("");
            customerNameLabel.setText("");
            customerEmailLabel.setText("");
            paymentTypeLabel.setText("");
            weekStartedLabel.setText("");
            subscriptionPromptLabel.setText("");
            associatePromptLabel.setText("");
            subscriptionListView.getItems().clear();
            associateListView.getItems().clear();
            billingPane.getChildren().clear();
            // Get the clicked TreeItem from the TreeView
            TreeItem<String> selectedItem = viewCustomer.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String selectedCustomerName = selectedItem.getValue();
                String paymentType = "N/A";
                String associate = "N/A";
                // Find the corresponding supplement based on the selected name
                Customer selectedCustomer = null;
                ArrayList<String> subscriptionList = null;
                ArrayList<String> associateList = null;
                boolean found = false;
                for (int i = 0; i < payingCustomer.size(); i++) {
                    if (payingCustomer.get(i).getName().equals(selectedCustomerName)) {

                        String customerName = payingCustomer.get(i).getName();
                        String customerEmail = payingCustomer.get(i).getEmail();
                        int weekStarted = payingCustomer.get(i).getWeekStarted();
                        paymentType = payingCustomer.get(i).getPaymentType();
                        subscriptionList = payingCustomer.get(i).getSubscriptionList();
                        associateList = payingCustomer.get(i).getAssociateList();
                        billingBtn.setVisible(true);
                        selectedCustomer = new Paying(customerName, customerEmail, weekStarted, paymentType);
                        found = true;
                        break;
                    }

                }

                if (!found) {

                    for (int i = 0; i < associateCustomer.size(); i++) {
                        if (associateCustomer.get(i).getName().equals(selectedCustomerName)) {

                            String customerName = associateCustomer.get(i).getName();
                            String customerEmail = associateCustomer.get(i).getEmail();
                            int weekStarted = associateCustomer.get(i).getWeekStarted();
                            associate = associateCustomer.get(i).getAssociate();
                            subscriptionList = associateCustomer.get(i).getSubscriptionList();
                            selectedCustomer = new Associate(customerName, customerEmail, weekStarted, associate,
                                    payingCustomer);
                            break;
                        }

                    }
                }

                // Display the Customer's details in the information panel
                if (selectedCustomer != null) {

                    if (found) {
                        associateListView.getItems().clear();
                        customerTypeLabel.setText("Customer Type: Paying");
                        customerNameLabel.setText("Customer Name: " + selectedCustomer.getName());
                        customerEmailLabel.setText("Customer Email: " + selectedCustomer.getEmail());
                        paymentTypeLabel.setText("Payment Type" + paymentType);
                        weekStartedLabel.setText("Subscribed since week: " + selectedCustomer.getWeekStarted());
                        subscriptionPromptLabel.setText("Subscribed to: ");
                        associatePromptLabel.setText("Associated Customer/s: ");
                        String[] subArray = (String[]) subscriptionList.toArray(new String[0]);
                        subscriptionListView.getItems().setAll(subArray);
                        String[] associateArray = (String[]) associateList.toArray(new String[0]);
                        associateListView.getItems().addAll(associateArray);

                        new BillingTask(magazines, payingCustomer, associateCustomer, selectedCustomerName,
                                new BillingTask.BillingCallback() {
                                    @Override
                                    public void onBillingComplete(VBox billingVBox) {
                                        billingPane.getChildren().clear();
                                        // Replace or add the VBox to your layout pane
                                        // Assuming informationPanelBottom is where you want to show the billing details
                                        billingPane.getChildren().add(billingVBox);
                                    }
                                }).start(); // Start the thread to calculate and generate the VBox

                    } else {
                        billingPane.getChildren().clear();
                        customerTypeLabel.setText("Customer Type: Associate");
                        customerNameLabel.setText("Customer Name: " + selectedCustomer.getName());
                        customerEmailLabel.setText("Customer Email: " + selectedCustomer.getEmail());
                        paymentTypeLabel.setText("Payment Type" + paymentType);
                        weekStartedLabel.setText("Subscribed since week: " + selectedCustomer.getWeekStarted());
                        subscriptionPromptLabel.setText("Subscribed to: ");
                        associatePromptLabel.setText("Associated Customer/s: ");
                        String[] subArray = (String[]) subscriptionList.toArray(new String[0]);
                        subscriptionListView.getItems().setAll(subArray);
                        associateListView.getItems().clear();
                        associateListView.getItems().add(associate);

                    }
                }
            }
        });

        viewSupplement.setOnMouseClicked(event -> {
            String magazineName = "";
            Double magazineCost = null;
            String supplementName = "";
            Double weeklyCost = null;
            billingBtn.setVisible(false);
            magazineNamLabel.setText("");
            magazineCostLabel.setText("");
            supplementNameLabel.setText("");
            supplementPriceLabel.setText("");
            customerTypeLabel.setText("");
            customerNameLabel.setText("");
            customerEmailLabel.setText("");
            paymentTypeLabel.setText("");
            weekStartedLabel.setText("");
            subscriptionPromptLabel.setText("");
            associatePromptLabel.setText("");
            subscriptionListView.getItems().clear();
            associateListView.getItems().clear();
            billingPane.getChildren().clear();
            // Get the clicked TreeItem from the TreeView
            TreeItem<String> selectedItem = viewSupplement.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String selectedSupplementName = selectedItem.getValue();

                // Find the corresponding supplement based on the selected name
                boolean supplementFound = false;
                for (int i = 0; i < magazines.size(); i++) {
                    for (int j = 0; j < magazines.get(i).getSupplementCount(); j++) {
                        if (magazines.get(i).getSupplementIndex(j).equals(selectedSupplementName)) {

                            magazineName = magazines.get(i).getMagazineName();
                            magazineCost = magazines.get(i).getMagazineCost();
                            supplementName = magazines.get(i).getSupplement(selectedSupplementName);
                            weeklyCost = magazines.get(i).getSupplementCost(selectedSupplementName);
                            supplementFound = true;
                        }
                    }
                }

                // Display the supplement's details in the information panel
                if (supplementFound) {

                    supplementNameLabel.setText("Supplement Name: " + supplementName);
                    supplementPriceLabel.setText("Supplement Price: $" + weeklyCost);

                    magazineNamLabel.setText("Magazine Name: " + magazineName);
                    magazineCostLabel.setText("Magazine Price: $" + magazineCost);
                }
            }
        });

        billingBtn.setOnAction(event -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Customer Billing History");
            alert.getDialogPane().setContent(billingPane);
            alert.setHeaderText("Billing History");
            alert.showAndWait();
        });
    }

    public static void createPane(GridPane viewLayoutPane, GridPane createLayoutPane, ArrayList<Magazine> magazines,
            ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer,
            ArrayList<Supplement> tempSupplements) {

        createLayoutPane.getChildren().clear();

        VBox supplementFieldBox = new VBox();
        supplementFieldBox.setVisible(false);
        supplementFieldBox.setPadding(new Insets(10));
        supplementFieldBox.setSpacing(10);

        VBox customerFieldBox = new VBox(10);
        customerFieldBox.setVisible(false);
        customerFieldBox.setPadding(new Insets(10));

        HBox selectionPane = new HBox();
        selectionPane.setAlignment(Pos.BASELINE_CENTER);
        selectionPane.setPadding(new Insets(10));
        selectionPane.setMaxWidth(Double.MAX_VALUE);

        createLayoutPane.add(selectionPane, 0, 0);
        createLayoutPane.add(supplementFieldBox, 0, 1);
        createLayoutPane.add(customerFieldBox, 0, 1);

        // createOption Radio Group
        ToggleGroup createOption = new ToggleGroup();
        RadioButton magazineRadio = new RadioButton("Magazine  ");
        magazineRadio.setToggleGroup(createOption);
        RadioButton customerRadio = new RadioButton("Customer");
        customerRadio.setToggleGroup(createOption);
        selectionPane.getChildren().addAll(magazineRadio, customerRadio);
        // Magazine Field

        // Label for Supplement Name
        Label supplementLabel = new Label("Supplement Name:");
        TextField supplementNameField = new TextField();
        supplementNameField.setMaxWidth(200);
        Label weeklyCostLabel = new Label("Weekly Cost:");
        TextField weeklyCostField = new TextField();
        weeklyCostField.setMaxWidth(200);
        Button submitMagazineButton = new Button("Submit");
        submitMagazineButton.setAlignment(Pos.CENTER_LEFT);

        supplementFieldBox.getChildren().addAll(supplementLabel, supplementNameField, weeklyCostLabel, weeklyCostField,
                submitMagazineButton);

        // Customer Field

        // Label for Customer's Name
        Label nameLabel = new Label("Name:");

        // TextField for Customer's Name
        TextField nameField = new TextField();
        nameField.setMaxWidth(400);

        // Label for Customer's Email
        Label emailLabel = new Label("Email:");

        // TextField for Customer's Email
        TextField emailField = new TextField();
        emailField.setMaxWidth(400);

        // ArrayList to retrieve and store all magazine supplements
        ArrayList<String> tempSubList = new ArrayList<String>();
        for (int i = 0; i < magazines.size(); i++) {
            for (int j = 0; j < magazines.get(i).getSupplementCount(); j++) {
                tempSubList.add(magazines.get(i).getSupplementIndex(j));
            }
        }

        // Label for Subscription Label Box
        Label subscriptionLabel = new Label("Subscription:");
        ComboBox<String> subscriptionCombo = new ComboBox<>();
        subscriptionCombo.getItems().add("N/A");
        subscriptionCombo.getItems().addAll(tempSubList);
        subscriptionCombo.setValue("N/A");

        // Customer Type Radio. Paying / Associate. Default: Paying
        ToggleGroup customerTypeGroup = new ToggleGroup();
        Label createCustomerTypeLabel = new Label("Customer Type:");
        RadioButton payingRadio = new RadioButton("Paying");
        payingRadio.setToggleGroup(customerTypeGroup);
        RadioButton associatedRadio = new RadioButton("Associate");
        associatedRadio.setToggleGroup(customerTypeGroup);
        payingRadio.setSelected(true);
        HBox customerTypeBox = new HBox(10, payingRadio, associatedRadio);

        // Label For Payment Radio buttons
        Label createPaymentTypeLabel = new Label("Payment Type:");

        // Customer Type Radio. Paying / Associate. Default: Paying
        ToggleGroup paymentTypeGroup = new ToggleGroup();
        RadioButton creditCardRadio = new RadioButton("Credit Card");
        creditCardRadio.setToggleGroup(paymentTypeGroup);
        RadioButton debitCardRadio = new RadioButton("Debit Card");
        debitCardRadio.setToggleGroup(paymentTypeGroup);
        createPaymentTypeLabel.setVisible(false);
        HBox paymentTypeBox = new HBox(10, creditCardRadio, debitCardRadio);

        // Label for Associate Name:
        Label associateLabel = new Label("Associate Name: ");

        // Text Field for Associate Name
        TextField associateField = new TextField();
        associateField.setMaxWidth(400);

        // Container for Associate Name and Field
        VBox associateTypeBox = new VBox(associateLabel, associateField);
        associateTypeBox.setVisible(false);

        // Submit Button
        Button submitCustomerButton = new Button("Submit");
        submitCustomerButton.setAlignment(Pos.CENTER_LEFT);

        // Add all Labels and Fields
        customerFieldBox.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, subscriptionLabel,
                subscriptionCombo,
                createCustomerTypeLabel, customerTypeBox, createPaymentTypeLabel, paymentTypeBox,
                associateTypeBox, submitCustomerButton);

        // Event Handler and Listeners

        // Listener for createOption Radio buttons
        createOption.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (createOption.getSelectedToggle() == magazineRadio) {
                // Show the Supplement Name and Weekly Cost fields
                supplementFieldBox.setVisible(true);
                customerFieldBox.setVisible(false);
            } else if (createOption.getSelectedToggle() == customerRadio) {
                // Hide the fields for Supplement Name and Weekly Cost
                supplementFieldBox.setVisible(false);
                customerFieldBox.setVisible(true);
            }
        });

        // Listener for customerTypeGroup Radio buttons
        customerTypeGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (payingRadio.isSelected()) {
                // Show the payment type options if "Paying" is selected
                createPaymentTypeLabel.setVisible(true);
                paymentTypeBox.setVisible(true);
                associateTypeBox.setVisible(false);
            } else {
                // Hide the payment type options if "Associated" is selected
                createPaymentTypeLabel.setVisible(false);
                paymentTypeBox.setVisible(false);
                associateTypeBox.setVisible(true);
            }
        });

        submitMagazineButton.setOnAction(event -> {
            boolean valid = true;
            String message = "";
            String supplementName = "";
            Double supplementCost = null;

            if (supplementNameField.getText().trim().isEmpty()) {
                valid = false;
                message += "Supplement Name is required\n";
            }

            if (weeklyCostField.getText().trim().isEmpty()) {
                valid = false;
                message += "weekly Cost is required or \n";

            }
            String check = weeklyCostField.getText();
            if (!onlyNumbers(check)) {
                valid = false;
                message += "weekly Cost Field cannot contain letters or symbols\n";
            }

            if (valid) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Form Submitted");
                alert.setHeaderText("Successfully added Supplement");
                alert.showAndWait();
                supplementName = supplementNameField.getText();
                supplementCost = Double.parseDouble(check);
                Supplement temp = new Supplement(supplementName, supplementCost);
                tempSupplements.add(temp);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Validation");
                alert.setContentText(message);
                alert.showAndWait();
                supplementNameField.clear();
                weeklyCostField.clear();

            }

            createPane(viewLayoutPane, createLayoutPane, magazines, payingCustomer, associateCustomer, tempSupplements);

        });

        // Submit Customer Button Event
        submitCustomerButton.setOnAction(event -> {

            boolean userExist = false;
            boolean valid = true;
            String message = "";
            String name = nameField.getText();
            String email = emailField.getText();
            String associateName = associateField.getText();
            String subscription = subscriptionCombo.getValue();
            String customerType = payingRadio.isSelected() ? "Paying" : "Associate";
            String paymentType = creditCardRadio.isSelected() ? "Credit Card" : "Debit Card";

            if (nameField.getText().trim().isEmpty()) {
                valid = false;
                message += "Name is Required \n";
            }

            if (emailField.getText().trim().isEmpty()) {
                valid = false;
                message += "Email is Required \n";
            }

            if (customerType.equals("Associate")) {
                for (int i = 0; i < payingCustomer.size(); i++) {
                    System.out.print(payingCustomer.get(i).getName() + "\n");
                    if (payingCustomer.get(i).getName().equalsIgnoreCase(associateName)) {
                        userExist = true;
                        break;
                    }
                }
                if (!userExist) {
                    valid = false;
                    message += "Paying customer for associate does not exist.\n";
                }
            }

            if (valid) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Form Submitted");
                alert.setHeaderText("Successfully added Customer");
                alert.showAndWait();

                if (customerType.equals("Paying")) {
                    payingCustomer.add(new Paying(name, email, 3, paymentType));
                    if (!subscriptionCombo.getValue().equals("N/A")) {
                        for (Paying paying : payingCustomer) {
                            if (name.equalsIgnoreCase(paying.getName())) {
                                paying.setSubscription(subscription);
                            }
                        }

                    }

                } else {

                    associateCustomer.add(new Associate(name, email, 3, associateName, payingCustomer));
                    if (!subscriptionCombo.getValue().equals("N/A")) {
                        associateCustomer.get(associateCustomer.size() - 1).setSubscription(subscription);
                    }

                }

                System.out.print(subscription);
                createPane(viewLayoutPane, createLayoutPane, magazines, payingCustomer, associateCustomer,
                        tempSupplements);
                viewPane(viewLayoutPane, magazines, payingCustomer, associateCustomer);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Validation");
                alert.setContentText(message);
                alert.showAndWait();
                nameField.clear();
                emailField.clear();
                subscriptionCombo.setValue("N/A");

            }

        });

    }

    public static void loadData(ArrayList<Magazine> magazines, ArrayList<Paying> payingCustomer,
            ArrayList<Associate> associateCustomer) {

        // Note Magazine = Weekly Cost | week | Name
        magazines.add(new Magazine(50, 0, "MagWeek01"));
        magazines.add(new Magazine(50, 1, "MagWeek02"));
        magazines.add(new Magazine(50, 2, "MagWeek03"));
        magazines.add(new Magazine(50, 3, "MagWeek04"));

        magazines.get(0).setSupplement(new Supplement("TechTrends Weekly", 4.99));
        magazines.get(1).setSupplement(new Supplement("Fitness Focus", 3.50));
        magazines.get(2).setSupplement(new Supplement("Travel Explorer", 5.25));
        magazines.get(3).setSupplement(new Supplement("Culinary Delights", 3.75));

        // Note Paying = Name | Email | Week Started | Payment Method
        payingCustomer.add(new Paying("Olivia Bennett", "olivia.bennett@example.com", 0, "Debit Card"));
        payingCustomer.add(new Paying("Liam Harris", "liam.harris@example.com", 0, "Credit Card"));
        payingCustomer.add(new Paying("Emma Walker", "emma.walker@example.com", 0, "Credit Card"));

        // Note Associate = Name | Email | Week Started | Associated Customer | Paying
        // ArrayList
        associateCustomer.add(new Associate("Noah Turner", "noah.turner@example.com", 0, "Olivia Bennet",
                payingCustomer));
        associateCustomer.add(new Associate("Sophia Mitchell", "sophia.mitchell@example.com", 0, "Liam Harris",
                payingCustomer));
        associateCustomer.add(new Associate("James Carter", "james.carter@example.com", 0, "Emma Walker",
                payingCustomer));

        payingCustomer.get(0).setSubscription("TechTrends Weekly");
        payingCustomer.get(0).setSubscription("Fitness Focus");
        payingCustomer.get(1).setSubscription("Culinary Delights");
        payingCustomer.get(1).setSubscription("TechTrends Weekly");
        payingCustomer.get(2).setSubscription("Travel Explorer");

        associateCustomer.get(0).setSubscription("TechTrends Weekly");
        associateCustomer.get(1).setSubscription("Fitness Focus");
        associateCustomer.get(1).setSubscription("TechTrends Weekly");
        associateCustomer.get(1).setSubscription("Travel Explorer");
        associateCustomer.get(2).setSubscription("TechTrends Weekly");
        associateCustomer.get(2).setSubscription("Culinary Delights");

    }

    public static void editPane(GridPane editLayoutPane, GridPane viewLayoutPane, GridPane createLayoutPane,
            ArrayList<Magazine> magazines,
            ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer,
            ArrayList<Supplement> tempSupplements) {

        // Clear the existing content in the editLayoutPane
        editLayoutPane.getChildren().clear();

        // Create Buttons for selecting the edit option
        Button editSupplementButton = new Button("Edit Supplement");
        Button editCustomerButton = new Button("Edit Customer");
        Button editMagazineButton = new Button("Edit Magazine");
        Button editAddSupplementButton = new Button("Add Supplement");

        // Add the buttons to the layout
        editLayoutPane.add(editSupplementButton, 0, 0);
        editLayoutPane.add(editCustomerButton, 0, 1);
        editLayoutPane.add(editMagazineButton, 0, 2);
        editLayoutPane.add(editAddSupplementButton, 0, 3);

        // Add space between buttons
        editLayoutPane.setVgap(10);

        // Add event handlers for buttons
        editSupplementButton.setOnAction(event -> showEditSupplementDialog(editLayoutPane, viewLayoutPane,
                createLayoutPane, tempSupplements, magazines, payingCustomer, associateCustomer));

        editCustomerButton.setOnAction(event -> showEditCustomerDialog(editLayoutPane, viewLayoutPane, createLayoutPane,
                tempSupplements, magazines, payingCustomer, associateCustomer));

        editMagazineButton.setOnAction(event -> showEditMagazineDialog(editLayoutPane, viewLayoutPane, createLayoutPane,
                tempSupplements, magazines, payingCustomer, associateCustomer));

        editAddSupplementButton
                .setOnAction(event -> showEditAddSupplementButton(editLayoutPane, viewLayoutPane, createLayoutPane,
                        tempSupplements, magazines, payingCustomer, associateCustomer));

    }

    // Show the custom dialog for editing a supplement
    private static void showEditSupplementDialog(GridPane editLayoutPane, GridPane viewLayoutPane,
            GridPane createLayoutPane, ArrayList<Supplement> tempSupplements,
            ArrayList<Magazine> magazines, ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer) {
        // Create a dialog
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Edit Supplement");

        ArrayList<String> tempSubList = new ArrayList<String>();
        for (int i = 0; i < magazines.size(); i++) {
            for (int j = 0; j < magazines.get(i).getSupplementCount(); j++) {
                tempSubList.add(magazines.get(i).getSupplementIndex(j));
            }
        }

        for (int i = 0; i < tempSupplements.size(); i++) {
            if (!tempSubList.contains(tempSupplements.get(i).getName())) {
                tempSubList.add(tempSupplements.get(i).getName());
            }
        }

        Label supplementComboLabel = new Label("Subscription:");
        ComboBox<String> supplementCombo = new ComboBox<>();
        supplementCombo.getItems().addAll(tempSubList);
        supplementCombo.setValue("");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType removeButtonType = new ButtonType("Remove", ButtonBar.ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType, removeButtonType);

        // Create a VBox for the form contents
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        // TextField for supplement name
        TextField supplementNameField = new TextField();
        supplementNameField.setPromptText("Enter Supplement Name");

        // TextField for supplement cost
        TextField supplementCostField = new TextField();
        supplementCostField.setPromptText("Enter Supplement Cost");

        // Add fields to the form layout
        vbox.getChildren().addAll(supplementComboLabel, supplementCombo, supplementNameField, supplementCostField);

        // Add the form to the dialog
        dialog.getDialogPane().setContent(vbox);

        // Handle Save button click
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {

                try {
                    // Save or update the supplement data
                    String supplementName = supplementNameField.getText();
                    double supplementCost = Double.parseDouble(supplementCostField.getText());
                    for (int i = 0; i < magazines.size(); i++) {
                        if (magazines.get(i).checkSupplement(supplementCombo.getValue())) {

                            magazines.get(i).removeSupplement(supplementCombo.getValue());
                            magazines.get(i).setSupplement(new Supplement(supplementName, supplementCost));
                        }
                    }

                    for (int i = 0; i < payingCustomer.size(); i++) {
                        if (payingCustomer.get(i).getSubscriptionList().contains(supplementCombo.getValue())) {
                            payingCustomer.get(i).renameSupplement(supplementCombo.getValue(), supplementName);
                        }
                    }

                    for (int i = 0; i < associateCustomer.size(); i++) {
                        if (associateCustomer.get(i).getSubscriptionList().contains(supplementCombo.getValue())) {
                            associateCustomer.get(i).renameSupplement(supplementCombo.getValue(), supplementName);
                        }
                    }

                    System.out.println("Saving Supplement: " + supplementName + " with cost: " + supplementCost);

                    // You can update the tempSupplements list here if needed

                } catch (NumberFormatException e) {
                    showAlert("Invalid input", "Supplement cost must be a valid number.");
                }
            }

            if (dialogButton == removeButtonType) {

                for (int i = 0; i < magazines.size(); i++) {

                    if (magazines.get(i).checkSupplement(supplementCombo.getValue())) {
                        magazines.get(i).removeSupplement(supplementCombo.getValue());
                    }

                }

                for (int i = 0; i < payingCustomer.size(); i++) {
                    if (payingCustomer.get(i).getSubscriptionList().contains(supplementCombo.getValue())) {
                        payingCustomer.get(i).removeSupplement(supplementCombo.getValue());
                    }
                }

                for (int i = 0; i < associateCustomer.size(); i++) {
                    if (associateCustomer.get(i).getSubscriptionList().contains(supplementCombo.getValue())) {
                        associateCustomer.get(i).removeSupplement(supplementCombo.getValue());
                    }
                }
                for (int i = 0; i < magazines.size(); i++) {
                    if (magazines.get(i).checkSupplement(supplementCombo.getValue())) {
                        magazines.get(i).removeSupplement(supplementCombo.getValue());
                    }
                }
            }

            viewPane(viewLayoutPane, magazines, payingCustomer, associateCustomer);
            createPane(viewLayoutPane, createLayoutPane, magazines, payingCustomer, associateCustomer,
                    tempSupplements);
            editPane(editLayoutPane, viewLayoutPane, createLayoutPane, magazines, payingCustomer,
                    associateCustomer, tempSupplements);
            return null;
        });

        supplementCombo.setOnAction(event -> {
            supplementNameField.setText(supplementCombo.getValue());
            double cost = 0.0f;
            for (int i = 0; i < magazines.size(); i++) {
                for (int j = 0; j < magazines.get(i).getSupplementCount(); j++) {
                    if (magazines.get(i).getSupplementIndex(j).equalsIgnoreCase(
                            supplementCombo.getValue())) {

                        cost = magazines.get(i).getSupplementCost(supplementCombo.getValue());
                        System.out.println(cost);
                        break;
                    }
                }

            }
            supplementCostField.setText(Double.toString(cost));
            // You can now perform any action based on the selected item
        });

        // Show the dialog and wait for a response
        dialog.showAndWait();
    }

    // Show the custom dialog for editing customer details
    private static void showEditCustomerDialog(GridPane editLayoutPane, GridPane viewLayoutPane,
            GridPane createLayoutPane, ArrayList<Supplement> tempSupplements,
            ArrayList<Magazine> magazines, ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer) {
        // Create a dialog
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Edit Customer");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType removeButtonType = new ButtonType("Remove", ButtonBar.ButtonData.APPLY);

        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType, removeButtonType);

        // Create a VBox for the form contents
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        ArrayList<String> customerList = new ArrayList<>();
        for (int i = 0; i < payingCustomer.size(); i++) {
            customerList.add(payingCustomer.get(i).getName());
        }
        for (int i = 0; i < associateCustomer.size(); i++) {
            customerList.add(associateCustomer.get(i).getName());
        }

        Label customerComboLabel = new Label("Customer:");
        ComboBox<String> customerCombo = new ComboBox<>();
        customerCombo.getItems().addAll(customerList);
        customerCombo.setValue("");

        // TextField for customer name
        TextField customerNameField = new TextField();
        customerNameField.setPromptText("Enter Customer Name");

        // TextField for customer email
        TextField customerEmailField = new TextField();
        customerEmailField.setPromptText("Enter Customer Email");

        // Add fields to the form layout
        vbox.getChildren().addAll(customerComboLabel, customerCombo, customerNameField, customerEmailField);

        // Add the form to the dialog
        dialog.getDialogPane().setContent(vbox);

        // Handle Save button click
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String customerName = customerNameField.getText();
                String customerEmail = customerEmailField.getText();
                for (int i = 0; i < payingCustomer.size(); i++) {
                    if (payingCustomer.get(i).getName().equalsIgnoreCase(customerCombo.getValue())) {
                        payingCustomer.get(i).editCustomer(customerName, customerEmail);
                    }
                }
                for (int i = 0; i < associateCustomer.size(); i++) {
                    if (associateCustomer.get(i).getName().equalsIgnoreCase(customerCombo.getValue())) {
                        associateCustomer.get(i).editCustomer(customerName, customerEmail);
                    }
                }
                // Save or update the customer data
                System.out.println("Saving Customer: " + customerName + " with email: " + customerEmail);
                // Add logic here to save the customer info to your list

            }
            if (dialogButton == removeButtonType) {
                for (int i = 0; i < payingCustomer.size(); i++) {
                    if (payingCustomer.get(i).getName().equalsIgnoreCase(customerCombo.getValue())) {
                        for (int j = 0; j < associateCustomer.size(); j++) {
                            if (associateCustomer.get(j).getAssociate().equalsIgnoreCase(customerCombo.getValue())) {
                                associateCustomer.remove(j);
                                break;
                            }
                        }
                        payingCustomer.remove(i);
                        break;
                    }
                }
                for (int i = 0; i < associateCustomer.size(); i++) {
                    if (associateCustomer.get(i).getName().equalsIgnoreCase(customerCombo.getValue())) {
                        associateCustomer.remove(i);
                    }
                }

            }
            viewPane(viewLayoutPane, magazines, payingCustomer, associateCustomer);
            createPane(viewLayoutPane, createLayoutPane, magazines, payingCustomer, associateCustomer,
                    tempSupplements);
            editPane(editLayoutPane, viewLayoutPane, createLayoutPane, magazines, payingCustomer,
                    associateCustomer, tempSupplements);
            return null;
        });

        customerCombo.setOnAction(event -> {
            customerNameField.setText(customerCombo.getValue());
            String tempEmail = "";

            for (int i = 0; i < payingCustomer.size(); i++) {
                if (payingCustomer.get(i).getName().equalsIgnoreCase(customerCombo.getValue())) {
                    tempEmail = payingCustomer.get(i).getEmail();
                    break;
                }
            }
            for (int i = 0; i < associateCustomer.size(); i++) {
                if (associateCustomer.get(i).getName().equalsIgnoreCase(customerCombo.getValue())) {
                    tempEmail = associateCustomer.get(i).getEmail();
                    break;
                }
            }

            customerEmailField.setText(tempEmail);

            // You can now perform any action based on the selected item
        });

        // Show the dialog and wait for a response
        dialog.showAndWait();
    }

    // Show the custom dialog for editing a magazine
    private static void showEditMagazineDialog(GridPane editLayoutPane, GridPane viewLayoutPane,
            GridPane createLayoutPane, ArrayList<Supplement> tempSupplements,
            ArrayList<Magazine> magazines, ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer) {
        // Create a dialog
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Edit Magazine");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

        // Create a VBox for the form contents
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        ArrayList<String> magazineList = new ArrayList<>();

        for (int i = 0; i < magazines.size(); i++) {
            magazineList.add(magazines.get(i).getMagazineName());
        }

        Label magazineComboLabel = new Label("Magazine:");
        ComboBox<String> magazineCombo = new ComboBox<>();
        magazineCombo.getItems().addAll(magazineList);
        magazineCombo.setValue("");

        // TextField for magazine name
        TextField magazineNameField = new TextField();
        magazineNameField.setPromptText("Enter Magazine Name");

        // TextField for magazine cost
        TextField magazineCostField = new TextField();
        magazineCostField.setPromptText("Enter Magazine Cost");

        // Add fields to the form layout
        vbox.getChildren().addAll(magazineComboLabel, magazineCombo, magazineNameField, magazineCostField);

        // Add the form to the dialog
        dialog.getDialogPane().setContent(vbox);

        // Handle Save button click
        dialog.setResultConverter(dialogButton -> {
            String magazineName = magazineNameField.getText();
            String magazineCostText = magazineCostField.getText();
            double magazineCost = Double.parseDouble(magazineCostText);
            if (dialogButton == saveButtonType) {

                try {
                    // Save or update the magazine data
                    for (int i = 0; i < magazines.size(); i++) {
                        if (magazines.get(i).getMagazineName().equalsIgnoreCase(magazineCombo.getValue())) {
                            magazines.get(i).editMagazine(magazineName, magazineCost);
                            break;

                        }
                    }
                    System.out.println("Saving Magazine: " + magazineName + " with cost: " + magazineCost);
                    // You can update magazines list here
                } catch (NumberFormatException e) {
                    showAlert("Invalid input", "Magazine cost must be a valid number.");
                }
            }

            viewPane(viewLayoutPane, magazines, payingCustomer, associateCustomer);
            createPane(viewLayoutPane, createLayoutPane, magazines, payingCustomer, associateCustomer,
                    tempSupplements);
            editPane(editLayoutPane, viewLayoutPane, createLayoutPane, magazines, payingCustomer,
                    associateCustomer, tempSupplements);
            return null;
        });

        magazineCombo.setOnAction(event -> {
            magazineNameField.setText(magazineCombo.getValue());

            for (int i = 0; i < magazines.size(); i++) {
                if (magazines.get(i).getMagazineName().equalsIgnoreCase(magazineCombo.getValue())) {
                    magazineCostField.setText(Double.toString(magazines.get(i).getMagazineCost()));
                }
            }

            // You can now perform any action based on the selected item
        });

        // Show the dialog and wait for a response
        dialog.showAndWait();
    }

    public static void showEditAddSupplementButton(GridPane editLayoutPane, GridPane viewLayoutPane,
            GridPane createLayoutPane, ArrayList<Supplement> tempSupplements,
            ArrayList<Magazine> magazines, ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Edit Magazine");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

        // Create a VBox for the form contents
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        ArrayList<String> magazineList = new ArrayList<>();

        for (int i = 0; i < magazines.size(); i++) {
            magazineList.add(magazines.get(i).getMagazineName());
        }
        Label magazineComboLabel = new Label("Magazine:");
        ComboBox<String> magazineCombo = new ComboBox<>();
        magazineCombo.getItems().addAll(magazineList);
        magazineCombo.setValue("");

        ArrayList<String> tempSupplementList = new ArrayList<>();
        for (int i = 0; i < tempSupplements.size(); i++) {
            tempSupplementList.add(tempSupplements.get(i).getName());
        }
        Label supplementComboLabel = new Label("Available Supplements:");
        ComboBox<String> supplementCombo = new ComboBox<>();
        supplementCombo.getItems().addAll(tempSupplementList);
        supplementCombo.setValue("");

        // Add fields to the form layout
        vbox.getChildren().addAll(magazineComboLabel, magazineCombo, supplementComboLabel, supplementCombo);

        // Add the form to the dialog
        dialog.getDialogPane().setContent(vbox);

        // Handle Save button click
        dialog.setResultConverter(dialogButton -> {
            String magazineName = magazineCombo.getValue();
            String targetSupplement = supplementCombo.getValue();
            double tempSupplementCost = 0.0f;
            if (dialogButton == saveButtonType) {
                // Save or update the magazine data

                for (int i = 0; i < tempSupplements.size(); i++) {
                    if (tempSupplements.get(i).getName().equalsIgnoreCase(targetSupplement)) {
                        tempSupplementCost = tempSupplements.get(i).getWeeklyCost();
                    }

                }
                for (int i = 0; i < magazines.size(); i++) {
                    if (magazines.get(i).getMagazineName().equalsIgnoreCase(magazineName)) {
                        Supplement tempSuppObj = new Supplement(targetSupplement, tempSupplementCost);
                        magazines.get(i).setSupplement(tempSuppObj);
                        break;

                    }
                }

            }
            viewPane(viewLayoutPane, magazines, payingCustomer, associateCustomer);
            createPane(viewLayoutPane, createLayoutPane, magazines, payingCustomer, associateCustomer,
                    tempSupplements);
            editPane(editLayoutPane, viewLayoutPane, createLayoutPane, magazines, payingCustomer,
                    associateCustomer, tempSupplements);
            return null;
        });

        // Show the dialog and wait for a response
        dialog.showAndWait();

    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean onlyNumbers(String input) {

        // Regular expression to check if the text contains at least one digit
        return input.matches("^[0-9]*\\.?[0-9]*$");
    }

}