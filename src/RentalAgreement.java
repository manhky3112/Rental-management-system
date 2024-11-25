import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalAgreement {
    private final int agreementId;
    private Tenant mainTenant;
    private List<Tenant> subTenants = new ArrayList<>();
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

    public void updateMainTenant(Tenant tenant){
        this.mainTenant = tenant;
    }

    public void addSubTenant(Tenant tenant){
        this.subTenants.add(tenant);
    }

    public void removeSubTenant(int tenantId){
        for(int i = 0; i < this.subTenants.size(); i ++){
            if(this.subTenants.get(i).getId() == tenantId){
                this.subTenants.remove(i);
                break;
            }
        }
    }

    public void updateProperty(Property property){
        this.leasedProperty = property;
    }

    public void updatePeriod(String period){
        this.period = period;
    }

    public void updateContractDate(Date contractDate){
        this.contractDate = contractDate;
    }

    public void updateRentingFee(Double rentingFee){
        this.rentingFee = rentingFee;
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public int getId() { return this.agreementId; }
    public Tenant getTenant() { return this.mainTenant; }
    public List<Tenant> getSubTenants() { return this.subTenants; }
    public Property getProperty() { return this.leasedProperty; }
    public String getPeriod() { return this.period; }
    public Date getContractDate() { return this.contractDate; }
    public double getRentingFee() { return this.rentingFee; }
    public String getStatus() { return this.status; }
}
