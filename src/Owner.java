import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Owner extends Person {
    private List<Property> ownedProperties;
    private List<Host> managingHosts;
    private List<RentalAgreement> rentalAgreements;

    public Owner(int id, String fullName, Date dateOfBirth, String contactInfo) {
        super(id, fullName, dateOfBirth, contactInfo);
        this.ownedProperties = new ArrayList<>();
        this.managingHosts = new ArrayList<>();
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
