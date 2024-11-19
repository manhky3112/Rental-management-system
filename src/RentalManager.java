import java.util.Date;
import java.util.List;

public interface RentalManager {
    void addAgreement(RentalAgreement agreement);
    void updateAgreement(int agreementId, Tenant mainTenant, List<Tenant> subTenants, Property leasedProperty, String period, Date contractDate, double rentingFee, String status);
    void deleteAgreement(int agreementId);
    List<RentalAgreement> getAllAgreement();
    List<RentalAgreement> getAgreementByOnwerName(String ownerName);
    List<RentalAgreement> getAgreementByPropertyAdress(String propertyAdress);
    List<RentalAgreement> getAgreementByStatus(String status);

    List<Tenant> getAllTenant();
    List<Host> getAllHost();
    List<Owner> getAllOwner();
    List<ResidentialProperty> getAllResidentialProperty();
    List<CommercialProperty> getAllCommercialProperty();

}
