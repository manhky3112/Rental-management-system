import java.util.List;

public class ResidentialProperty extends Property {
    private int numBedrooms;
    private boolean hasGarden;
    private boolean isPetFriendly;

    public ResidentialProperty(int propertyId, String address, double pricing, String status, Owner owner, List<Host> hosts, int numBedrooms, boolean hasGarden, boolean isPetFriendly) {
        super(propertyId, address, pricing, status, owner, hosts);
        this.numBedrooms = numBedrooms;
        this.hasGarden = hasGarden;
        this.isPetFriendly = isPetFriendly;
    }

    public int getNumBedrooms() { return numBedrooms; }
    public boolean hasGarden() { return hasGarden; }
    public boolean isPetFriendly() { return isPetFriendly; }
}
