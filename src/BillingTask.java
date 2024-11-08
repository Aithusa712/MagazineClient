import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class BillingTask extends Thread {

    private ArrayList<Magazine> magazines;
    private ArrayList<Paying> payingCustomer;
    private ArrayList<Associate> associateCustomer;
    private String targetCustomer;
    private BillingCallback callback;

    // Constructor to initialize necessary data
    public BillingTask(ArrayList<Magazine> magazines, ArrayList<Paying> payingCustomer,
            ArrayList<Associate> associateCustomer, String targetCustomer, BillingCallback callback) {
        this.magazines = magazines;
        this.payingCustomer = payingCustomer;
        this.associateCustomer = associateCustomer;
        this.targetCustomer = targetCustomer;
        this.callback = callback;
    }

    @Override
    public void run() {
        VBox billingVBox = createBillingVBox();

        // Use Platform.runLater to update the UI on the JavaFX application thread
        javafx.application.Platform.runLater(() -> callback.onBillingComplete(billingVBox));
    }

    // Method to create the VBox with Labels
    private VBox createBillingVBox() {
        VBox billingVBox = new VBox(0);
        billingVBox
                .setStyle("-fx-padding: 10; -fx-background-color: white; -fx-border-color: black;-fx-border-width: 1;");

        billingVBox.setMaxWidth(400);
        boolean customerFound = false;

        // Search for the targeted customer
        for (int i = 0; i < payingCustomer.size(); i++) {
            if (payingCustomer.get(i).getName().equalsIgnoreCase(targetCustomer)) {
                customerFound = true;

                // Create and add Labels for customer details
                Label nameLabel = new Label("Name: " + payingCustomer.get(i).getName());
                Label emailLabel = new Label("Email: " + payingCustomer.get(i).getEmail());
                Label weekStartedLabel = new Label("Subscribed since Week: " + payingCustomer.get(i).getWeekStarted());
                Label paymentTypeLabel = new Label("Payment Type: " + payingCustomer.get(i).getPaymentType());

                billingVBox.getChildren().addAll(nameLabel, emailLabel, weekStartedLabel, paymentTypeLabel);

                // Payment breakdown section
                double totalCost = 0.0;
                Label magazineCostLabel = new Label(
                        "\tBase Magazine subscription - " + payingCustomer.get(i).getMagazineCosts(magazines) + "$");
                totalCost += payingCustomer.get(i).getMagazineCosts(magazines);

                Label supplementCostLabel = new Label(
                        "\tSupplement subscription - " + payingCustomer.get(i).getSupplementCosts(magazines) + "$");
                totalCost += payingCustomer.get(i).getSupplementCosts(magazines);

                billingVBox.getChildren().addAll(magazineCostLabel, supplementCostLabel);

                // Associate cost breakdown section
                Label associateHeaderLabel = new Label("\tAssociate Cost Breakdown: ");
                billingVBox.getChildren().add(associateHeaderLabel);

                ArrayList<String> associateList = payingCustomer.get(i).getAssociateList();
                for (int j = 0; j < associateList.size(); j++) {
                    for (int k = 0; k < associateCustomer.size(); k++) {
                        if (associateCustomer.get(k).getName().equalsIgnoreCase(associateList.get(j))) {
                            Label associateEmailLabel = new Label(
                                    "\t\tAssociate's Email Address: " + associateCustomer.get(k).getEmail());
                            Label associateCostLabel = new Label("\t\tAssociate's Total cost: "
                                    + associateCustomer.get(k).getTotalCosts(magazines) + "$");

                            billingVBox.getChildren().addAll(associateEmailLabel, associateCostLabel);
                            totalCost += associateCustomer.get(k).getTotalCosts(magazines);
                        }
                    }
                }

                // Total cost section
                Label totalCostLabel = new Label("Total: " + totalCost + "$");
                billingVBox.getChildren().add(totalCostLabel);

                break; // Exit the loop once the target customer is found and processed
            }
        }

        if (!customerFound) {
            // If the customer was not found, show a message
            Label errorLabel = new Label("Customer with name '" + targetCustomer + "' not found.");
            billingVBox.getChildren().add(errorLabel);
        }

        return billingVBox;
    }

    // Callback interface to handle the completion of the billing task
    public interface BillingCallback {
        void onBillingComplete(VBox billingVBox);
    }
}
