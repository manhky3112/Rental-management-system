import java.util.Date;
import java.util.List;

public interface RentalManager {
    void addAgreement(RentalAgreement agreement);
    void updateAgreement(int agreementId, Tenant mainTenant, List<Tenant> subTenants, Property leasedProperty, String period, Date contractDate, double rentingFee, String status);
    void deleteAgreement(int agreementId);
    List<RentalAgreement> getAllAgreements();
    List<RentalAgreement> getAgreementsByOwnerName(String ownerName);
    List<RentalAgreement> getAgreementsByPropertyAddress(String propertyAddress);
    List<RentalAgreement> getAgreementsByStatus(String status);
    List<RentalAgreement> sortReport(List<RentalAgreement> agreements, String sortType);

    List<Tenant> getAllTenants();
    List<Host> getAllHosts();
    List<Owner> getAllOwners();
    List<ResidentialProperty> getAllResidentialProperties();
    List<CommercialProperty> getAllCommercialProperties();

}
