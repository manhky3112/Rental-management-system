import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Tenant extends Person {
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();
    private List<Payment> paymentTransactions = new ArrayList<>();

    public Tenant(int id, String fullName, Date dateOfBirth, String contactInfo, List<RentalAgreement> rentalAgreements, List<Payment> paymentTransactions) {
        super(id, fullName, dateOfBirth, contactInfo);
        this.rentalAgreements = rentalAgreements;
        this.paymentTransactions = paymentTransactions;
    }

    public List<RentalAgreement> getRentalAgreements() { return rentalAgreements; }
    public List<Payment> getPaymentTransactions() { return paymentTransactions; }

    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }

    public void addPayment(Payment payment) {
        paymentTransactions.add(payment);
    }
}
