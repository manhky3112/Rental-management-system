import javax.xml.crypto.Data;
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

    public void updateAgreement(int agreementId, Tenant mainTenant, List<Tenant> subTenants, Property leasedProperty, String period, Date contractDate, double rentingFee, String status){
        for(RentalAgreement agreement : rentalAgreements){
            if(agreement.getId() == agreementId){
                agreement.updateAgreement(mainTenant, subTenants, leasedProperty, period, contractDate, rentingFee, status);
            }
        }
    }

    public void deleteAgreement(int agreementId){
        for(int i = 0; i < rentalAgreements.size(); i++){
            if(rentalAgreements.get(i).getId() == agreementId){
                System.out.println("Rental agreement ID" + rentalAgreements.get(i).getId() + "remove successfully" );
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
            if(Objects.equals(agreement.getProperty().getAddress(), status)){
                agreementsByStatus.add(agreement);
            }
        }
        return agreementsByStatus;
    }

    public List<RentalAgreement> sortReport(List<RentalAgreement> agreements, String sortType){
        switch (sortType) {
            case "status":
                agreements.sort(Comparator.comparing(RentalAgreement::getStatus));
                break;
            case "contractDate":
                agreements.sort(Comparator.comparing(RentalAgreement::getContractDate));
                break;
            case "period":
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
