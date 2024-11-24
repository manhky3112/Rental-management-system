import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Host extends Person {
    private List<Property> managedProperties = new ArrayList<>();
    private List<Owner> cooperatingOwners = new ArrayList<>();
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();

    public Host(int id, String fullName, Date dateOfBirth, String contactInfo, List<Property> managedProperties, List<Owner> cooperatingOwners, List<RentalAgreement> rentalAgreements) {
        super(id, fullName, dateOfBirth, contactInfo);
        this.managedProperties = managedProperties;
        this.cooperatingOwners = cooperatingOwners;
        this.rentalAgreements = rentalAgreements;
    }

    public List<Property> getProperties() { return managedProperties; }
    public List<Owner> getCooperatingOwners() { return cooperatingOwners; }
    public List<RentalAgreement> getRentalAgreements() {return rentalAgreements;}

    public void addProperty(Property property) {
        managedProperties.add(property);
    }

    public void addOwner(Owner owner) {
        cooperatingOwners.add(owner);
    }

    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }
}
