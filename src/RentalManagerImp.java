
import java.util.*;

public class RentalManagerImp implements RentalManager{
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();
    private List<Tenant> tenants = new ArrayList<>();
    private List<Host> hosts = new ArrayList<>();
    private List<Owner> owners = new ArrayList<>();
    private List<ResidentialProperty> residentialProperties = new ArrayList<>();
    private List<CommercialProperty> commercialProperties = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    public void initializeData(String filePath, String dataType){
        switch (dataType) {
            case "Payment":
                payments = DataPopulator.populatePayments(filePath);
                break;
            case "Tenant":
                tenants = DataPopulator.populateTenants(filePath);
                break;
            case "Host":
                hosts = DataPopulator.populateHosts(filePath);
                break;
            case "Owner":
                owners = DataPopulator.populateOwners(filePath);
                break;
            case "ResidentialProperty":
                residentialProperties = DataPopulator.populateResidentials(filePath);
                break;
            case "CommercialProperty":
                commercialProperties = DataPopulator.populateCommercials(filePath);
                break;
            case "RentalAgreement":
                rentalAgreements = DataPopulator.populateAgreements(filePath);
                break;
            case "TenantRemaining":
                DataPopulator.finalizeTenants(filePath);
                break;
            case "HostRemaining":
                DataPopulator.finalizeHosts(filePath);
                break;
            case "OwnerRemaining":
                DataPopulator.finalizeOwners(filePath);
                break;
            default:
        }
    }

    public void addAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }

    public void updateAgreement(int agreementId, Object update, String updateChoice){
        RentalAgreement editAgreement = null;
        for(RentalAgreement agreement : rentalAgreements){
            if(agreement.getId() == agreementId){
                editAgreement = agreement;
                break;
            }
        }
        switch (updateChoice) {
            case "Main Tenant":
                if (update instanceof Integer tenantId) {
                    for (Tenant tenant : tenants) {
                        if (tenant.getId() == tenantId) {
                            assert editAgreement != null;
                            editAgreement.updateMainTenant(tenant);
                            break;
                        }
                    }
                }
                break;
            case "Add Sub Tenant":
                if (update instanceof Integer tenantId) {
                    for (Tenant tenant : tenants) {
                        if (tenant.getId() == tenantId) {
                            assert editAgreement != null;
                            editAgreement.addSubTenant(tenant);
                            break;
                        }
                    }
                }
                break;
            case "Remove Sub Tenant":
                if (update instanceof Integer tenantId) {
                    assert editAgreement != null;
                    editAgreement.removeSubTenant(tenantId);
                }
                break;
            case "Property":
                if (update instanceof Integer) {
                    int propertyId = (Integer) update;
                    for (CommercialProperty commercialProperty : commercialProperties) {
                        if (commercialProperty.getPropertyId() == propertyId) {
                            assert editAgreement != null;
                            editAgreement.updateProperty(commercialProperty);
                            break;
                        }
                    }
                }
                break;
            case "Period":
                if (update instanceof String period) {
                    assert editAgreement != null;
                    editAgreement.updatePeriod(period);
                }
                break;
            case "Contract Date":
                if (update instanceof Date contractDate) {
                    assert editAgreement != null;
                    editAgreement.updateContractDate(contractDate);
                }
                break;
            case "Renting Fee":
                if (update instanceof Double rentingFee) {
                    assert editAgreement != null;
                    editAgreement.updateRentingFee(rentingFee);
                }
                break;
            case "Status":
                if (update instanceof String status) {
                    assert editAgreement != null;
                    editAgreement.updateStatus(status);
                }
                break;
            default:
        }
    }

    public void deleteAgreement(int agreementId){
        for(int i = 0; i < rentalAgreements.size(); i++){
            if(rentalAgreements.get(i).getId() == agreementId){
                System.out.println("Rental agreement ID " + rentalAgreements.get(i).getId() + " remove successfully" );
                rentalAgreements.remove(i);
                break;
            }
        }
    }

    public List<RentalAgreement> getAllAgreements(){
        return rentalAgreements;
    }

    public List<RentalAgreement> getAgreementsByOwnerName(String ownerName){
        for(Owner owner : owners){
            if(Objects.equals(owner.getFullName(), ownerName)){
                return owner.getRentalAgreements();
            }
        }
        return Collections.emptyList();
    }

    public List<RentalAgreement> getAgreementsByPropertyAddress(String propertyAddress){
        List<RentalAgreement> agreementsByPropertyAddress = new ArrayList<>();
        for(RentalAgreement agreement: rentalAgreements){
            if(Objects.equals(agreement.getProperty().getAddress(), propertyAddress)){
                agreementsByPropertyAddress.add(agreement);
            }
        }
        return agreementsByPropertyAddress;
    }
    
    public List<RentalAgreement> getAgreementsByStatus(String status){
        List<RentalAgreement> agreementsByStatus = new ArrayList<>();
        for(RentalAgreement agreement: rentalAgreements){
            if(Objects.equals(agreement.getStatus(), status)){
                agreementsByStatus.add(agreement);
            }
        }
        return agreementsByStatus;
    }

    public List<RentalAgreement> sortReport(List<RentalAgreement> agreements, String sortType){
        switch (sortType) {
            case "Status":
                agreements.sort(Comparator.comparing(RentalAgreement::getStatus));
                break;
            case "Contract Date":
                agreements.sort(Comparator.comparing(RentalAgreement::getContractDate));
                break;
            case "Period":
                agreements.sort(Comparator.comparing(RentalAgreement::getPeriod));
                break;
        }
        return agreements;
    }

    public List<Tenant> getAllTenants() {return tenants;}
    public List<Host> getAllHosts() {return hosts;}
    public List<Owner> getAllOwners() {return owners;}
    public List<ResidentialProperty> getAllResidentialProperties() {return residentialProperties;}
    public List<CommercialProperty> getAllCommercialProperties() { return commercialProperties;}
}
