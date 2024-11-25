import java.util.Date;
import java.util.List;

public interface RentalManager {
    void initializeData(String filePath, String dataType);

    void addAgreement(RentalAgreement agreement);
    void updateAgreement(int agreementId, Object update, String updateChoice);
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
