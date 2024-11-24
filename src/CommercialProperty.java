import java.util.List;

public class CommercialProperty extends Property {
    private String businessType;
    private int parkingSpaces;
    private double squareFootage;

    public CommercialProperty(int propertyId, String address, double pricing, String status, Owner owner, List<Host> hosts, String businessType, int parkingSpaces, double squareFootage) {
        super(propertyId, address, pricing, status, owner, hosts);
        this.businessType = businessType;
        this.parkingSpaces = parkingSpaces;
        this.squareFootage = squareFootage;
    }

    public String getBusinessType() { return businessType; }
    public int getParkingSpaces() { return parkingSpaces; }
    public double getSquareFootage() { return squareFootage; }
}
