import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Host extends Person {
    private List<Property> ManagedProperties;
    private List<Owner> cooperatingOwners;

    public Host(int id, String fullName, Date dateOfBirth, String contactInfo) {
        super(id, fullName, dateOfBirth, contactInfo);
        this.ManagedProperties = new ArrayList<>();
        this.cooperatingOwners = new ArrayList<>();
    }

    public List<Property> getProperties() { return ManagedProperties; }
    public List<Owner> getCooperatingOwners() { return cooperatingOwners; }

    public void addProperty(Property property) {
        ManagedProperties.add(property);
    }

    public void addOwner(Owner owner) {
        cooperatingOwners.add(owner);
    }
}
