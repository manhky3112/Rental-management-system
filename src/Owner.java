import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Owner extends Person {
    private List<Property> ownedProperties = new ArrayList<>();
    private List<Host> managingHosts = new ArrayList<>();
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();

    public Owner(int id, String fullName, Date dateOfBirth, String contactInfo, List<Property> ownedProperties, List<Host> managingHosts, List<RentalAgreement> rentalAgreements) {
        super(id, fullName, dateOfBirth, contactInfo);
        this.ownedProperties = ownedProperties;
        this.managingHosts = managingHosts;
        this.rentalAgreements = rentalAgreements;
    }

    public List<Property> getOwnedProperties() { return ownedProperties; }
    public List<Host> getManagingHosts() { return managingHosts; }
    public List<RentalAgreement> getRentalAgreements() {return rentalAgreements;}

    public void addProperty(Property property) {
        ownedProperties.add(property);
    }

    public void addHost(Host host) {
        managingHosts.add(host);
    }

    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }
}
