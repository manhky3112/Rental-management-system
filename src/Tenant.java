import java.util.Date;
import java.util.List;
import java.util.ArrayList;

class Tenant extends Person {
    private List<RentalAgreement> rentalAgreements;
    private List<Payment> paymentTransactions;

    public Tenant(int id, String fullName, Date dateOfBirth, String contactInfo) {
        super(id, fullName, dateOfBirth, contactInfo);
        this.rentalAgreements = new ArrayList<>();
        this.paymentTransactions = new ArrayList<>();
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
