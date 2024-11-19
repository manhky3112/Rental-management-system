import java.util.Date;

public class Payment {
    private int paymentId;
    private double amount;
    private Date date;
    private String paymentMethod;

    public Payment(int paymentId, double amount, Date date, String paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() { return paymentId; }
    public double getAmount() { return amount; }
    public Date getDate() { return date; }
    public String getPaymentMethod() { return paymentMethod; }
}
