import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RentalManagerImp implements RentalManager{
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();
    private List<Tenant> tenants = new ArrayList<>();
    private List<Host> hosts = new ArrayList<>();
    private List<Owner> owners = new ArrayList<>();
    private List<ResidentialProperty> residentialProperties = new ArrayList<>();
    private List<CommercialProperty> commercialProperties = new ArrayList<>();

    public void addAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    };
    public void updateAgreement(int agreementId, Tenant mainTenant, List<Tenant> subTenants, Property leasedProperty, String period, Date contractDate, double rentingFee, String status){
        for(RentalAgreement agreement : rentalAgreements){
            if(agreement.getId() == agreementId){
                agreement.updateAgreement(mainTenant, subTenants, leasedProperty, period, contractDate, rentingFee, status);
            }
        }
    };
    public void deleteAgreement(int agreementId){
        for(int i = 0; i < rentalAgreements.size(); i++){
            if(rentalAgreements.get(i).getId() == agreementId){
                System.out.println("Rental agreement ID" + rentalAgreements.get(i).getId() + "remove successfully" );
                rentalAgreements.remove(i);
                break;
            }
        }
    };
    public List<RentalAgreement> getAllAgreement(){
        return rentalAgreements;
    };
    public List<RentalAgreement> getAgreementByOnwerName(String ownerName){
        for(Owner owner : owners){
            if(Objects.equals(owner.getFullName(), ownerName)){
                return owner.getRentalAgreements();
            }
        }
        return null;
    };
    public List<RentalAgreement> getAgreementByPropertyAdress(String propertyAdress){
        List<RentalAgreement> agreementsByPropertyAdress = new ArrayList<>();
        for(RentalAgreement agreement: rentalAgreements){
            if(Objects.equals(agreement.getProperty().getAddress(), propertyAdress)){
                agreementsByPropertyAdress.add(agreement);
            }
        }
        return agreementsByPropertyAdress;
    };
    public List<RentalAgreement> getAgreementByStatus(String status){
        List<RentalAgreement> agreementsByStatus = new ArrayList<>();
        for(RentalAgreement agreement: rentalAgreements){
            if(Objects.equals(agreement.getProperty().getAddress(), status)){
                agreementsByStatus.add(agreement);
            }
        }
        return agreementsByStatus;
    };

    public List<Tenant> getAllTenant() {return tenants;};
    public List<Host> getAllHost() {return hosts;};
    public List<Owner> getAllOwner() {return owners;};
    public List<ResidentialProperty> getAllResidentialProperty() {return residentialProperties;};
    public List<CommercialProperty> getAllCommercialProperty() { return commercialProperties;};
}
