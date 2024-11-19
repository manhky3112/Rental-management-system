import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Host extends Person {
    private List<Property> ManagedProperties;
    private List<Owner> cooperatingOwners;
    private List<RentalAgreement> rentalAgreements;

    public Host(int id, String fullName, Date dateOfBirth, String contactInfo) {
        super(id, fullName, dateOfBirth, contactInfo);
        this.ManagedProperties = new ArrayList<>();
        this.cooperatingOwners = new ArrayList<>();
    }

    public List<Property> getProperties() { return ManagedProperties; }
    public List<Owner> getCooperatingOwners() { return cooperatingOwners; }
    public List<RentalAgreement> getRentalAgreements() {return rentalAgreements;}

    public void addProperty(Property property) {
        ManagedProperties.add(property);
    }

    public void addOwner(Owner owner) {
        cooperatingOwners.add(owner);
    }

    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }
}
