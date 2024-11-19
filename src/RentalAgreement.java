import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalAgreement {
    private int agreementId;
    private Tenant mainTenant;
    private List<Tenant> subTenants;
    private Property leasedProperty;
    private String period; // daily, weekly, fortnightly, monthly
    private Date contractDate;
    private double rentingFee;
    private String status; // New, Active, Completed

    public RentalAgreement(int agreementId, Tenant mainTenant, List<Tenant> subTenants, Property property, String period, Date contractDate, double rentingFee, String status) {
        this.agreementId = agreementId;
        this.mainTenant = mainTenant;
        this.subTenants = subTenants;
        this.leasedProperty = property;
        this.period = period;
        this.contractDate = contractDate;
        this.rentingFee = rentingFee;
        this.status = status;
    }

    public void updateAgreement(Tenant mainTenant, List<Tenant> subTenants, Property property, String period, Date contractDate, double rentingFee, String status) {
        this.mainTenant = mainTenant;
        this.subTenants = subTenants;
        this.leasedProperty = property;
        this.period = period;
        this.contractDate = contractDate;
        this.rentingFee = rentingFee;
        this.status = status;
    }

    public int getId() { return agreementId; }
    public Tenant getTenant() { return mainTenant; }
    public List<Tenant> getSubTenants() { return subTenants; }
    public Property getProperty() { return leasedProperty; }
    public String getPeriod() { return period; }
    public Date getContractDate() { return contractDate; }
    public double getRentingFee() { return rentingFee; }
    public String getStatus() { return status; }

    public void addSubTenant(Tenant subTenant) {
        subTenants.add(subTenant);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
