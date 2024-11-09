import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class BillingTask extends Thread {

    private ArrayList<Magazine> magazines;
    private ArrayList<Paying> payingCustomer;
    private ArrayList<Associate> associateCustomer;
    private String targetCustomer;
    private BillingCallback callback;

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

        javafx.application.Platform.runLater(() -> callback.onBillingComplete(billingVBox));
    }

    private VBox createBillingVBox() {
        VBox billingVBox = new VBox(0);
        billingVBox
                .setStyle("-fx-padding: 10; -fx-background-color: white; -fx-border-color: black;-fx-border-width: 1;");

        billingVBox.setMaxWidth(400);
        boolean customerFound = false;

        for (int i = 0; i < payingCustomer.size(); i++) {
            if (payingCustomer.get(i).getName().equalsIgnoreCase(targetCustomer)) {
                customerFound = true;

                Label nameLabel = new Label("Name: " + payingCustomer.get(i).getName());
                Label emailLabel = new Label("Email: " + payingCustomer.get(i).getEmail());
                Label weekStartedLabel = new Label("Subscribed since Week: " + payingCustomer.get(i).getWeekStarted());
                Label paymentTypeLabel = new Label("Payment Type: " + payingCustomer.get(i).getPaymentType());

                billingVBox.getChildren().addAll(nameLabel, emailLabel, weekStartedLabel, paymentTypeLabel);

                double totalCost = 0.0;
                Label magazineCostLabel = new Label(
                        "\tBase Magazine subscription - " + payingCustomer.get(i).getMagazineCosts(magazines) + "$");
                totalCost += payingCustomer.get(i).getMagazineCosts(magazines);

                Label supplementCostLabel = new Label(
                        "\tSupplement subscription - " + payingCustomer.get(i).getSupplementCosts(magazines) + "$");
                totalCost += payingCustomer.get(i).getSupplementCosts(magazines);

                billingVBox.getChildren().addAll(magazineCostLabel, supplementCostLabel);

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

                Label totalCostLabel = new Label("Total: " + totalCost + "$");
                billingVBox.getChildren().add(totalCostLabel);

                break;
            }
        }

        if (!customerFound) {
            Label errorLabel = new Label("Customer with name '" + targetCustomer + "' not found.");
            billingVBox.getChildren().add(errorLabel);
        }

        return billingVBox;
    }

    public interface BillingCallback {
        void onBillingComplete(VBox billingVBox);
    }
}
