import java.util.List;
import java.util.ArrayList;

public abstract class Property {
    private int propertyId;
    private String address;
    private double pricing;
    private String status; // Available, Rented, Under Maintenance
    private Owner owner;
    private List<Host> hosts;

    public Property(int propertyId, String address, double pricing, String status, Owner owner) {
        this.propertyId = propertyId;
        this.address = address;
        this.pricing = pricing;
        this.status = status;
        this.owner = owner;
        this.hosts = new ArrayList<>();
    }

    public int getPropertyId() { return propertyId; }
    public String getAddress() { return address; }
    public double getPricing() { return pricing; }
    public String getStatus() { return status; }
    public Owner getOwner() { return owner; }
    public List<Host> getHosts() { return hosts; }

    public void addHost(Host host) {
        hosts.add(host);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

