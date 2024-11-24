import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataPopulator {
    static Map<Integer, Payment> paymentMap = new HashMap<>();
    static Map<Integer, Tenant> tenantMap = new HashMap<>();
    static Map<Integer, Host> hostMap = new HashMap<>();
    static Map<Integer, Owner> ownerMap = new HashMap<>();
    static Map<Integer, ResidentialProperty> residentialMap = new HashMap<>();
    static Map<Integer, CommercialProperty> commercialMap = new HashMap<>();
    static Map<Integer, RentalAgreement> agreementMap = new HashMap<>();

    public static List<Payment> populatePayments(String filePath) {
        List<Payment> payments = new ArrayList<>();
        List<String[]> data = DataLoader.readCSV(filePath);

        for (String[] row : data) {
            if (row[0].equals("paymentId")) continue;
            Date date = null;
            try {
                date = new SimpleDateFormat("MM/dd/yyyy").parse(row[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Payment payment = new Payment(
                    Integer.parseInt(row[0]),
                    Double.parseDouble(row[1]),
                    date,
                    row[3]
            );
            payments.add(payment);
            paymentMap.put(Integer.parseInt(row[0]), payment);
        }
        return payments;
    }

    public static List<Tenant> populateTenants(String filePath) {
        List<Tenant> tenants = new ArrayList<>();
        List<String[]> data = DataLoader.readCSV(filePath);

        for (String[] row : data) {
            if (row[0].equals("id")) continue;
            Date dateOfBirth = null;
            try {
                dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(row[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<Payment> paymentTransactions = new ArrayList<>();
            String idLine = row[5];
            String[] paymentIds = idLine.split(";");
            for (String paymentId : paymentIds) {
                paymentTransactions.add(paymentMap.get(Integer.parseInt(paymentId)));
            }
            List<RentalAgreement> rentalAgreements = new ArrayList<>();

            Tenant tenant = new Tenant(
                    Integer.parseInt(row[0]),
                    row[1],
                    dateOfBirth,
                    row[3],
                    rentalAgreements,
                    paymentTransactions
            );
            tenants.add(tenant);
            tenantMap.put(Integer.parseInt(row[0]), tenant);
        }
        return tenants;
    }

    public static List<Host> populateHosts(String filePath) {
        List<Host> hosts = new ArrayList<>();
        List<String[]> data = DataLoader.readCSV(filePath);

        for (String[] row : data) {
            if (row[0].equals("id")) continue;
            Date dateOfBirth = null;
            try {
                dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(row[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<Property> managedProperties = new ArrayList<>();
            List<Owner> cooperatingOwners = new ArrayList<>();
            List<RentalAgreement> rentalAgreements = new ArrayList<>();

            Host host = new Host(
                    Integer.parseInt(row[0]),
                    row[1],
                    dateOfBirth,
                    row[3],
                    managedProperties,
                    cooperatingOwners,
                    rentalAgreements
            );
            hosts.add(host);
            hostMap.put(Integer.parseInt(row[0]), host);
        }
        return hosts;
    }

    public static List<Owner> populateOwners(String filePath) {
        List<Owner> owners = new ArrayList<>();
        List<String[]> data = DataLoader.readCSV(filePath);

        for (String[] row : data) {
            if (row[0].equals("id")) continue;
            Date dateOfBirth = null;
            try {
                dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(row[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<Property> ownedProperties = new ArrayList<>();
            List<RentalAgreement> rentalAgreements = new ArrayList<>();
            List<Host> managingHosts = new ArrayList<>();
            String idLine = row[5];
            String[] hostIds = idLine.split(";");
            for (String hostId : hostIds) {
                managingHosts.add(hostMap.get(Integer.parseInt(hostId)));
            }
            Owner owner = new Owner(
                    Integer.parseInt(row[0]),
                    row[1],
                    dateOfBirth,
                    row[3],
                    ownedProperties,
                    managingHosts,
                    rentalAgreements
            );
            owners.add(owner);
            ownerMap.put(Integer.parseInt(row[0]), owner);
        }
        return owners;
    }

    public static List<ResidentialProperty> populateResidentials(String filePath){
        List<ResidentialProperty> residentialProperties = new ArrayList<>();
        List<String[]> data = DataLoader.readCSV(filePath);

        for (String[] row : data) {
            if (row[0].equals("propertyId")) continue;
            List<Host> hosts = new ArrayList<>();
            String idLine = row[5];
            String[] hostIds = idLine.split(";");
            for (String hostId : hostIds) {
                hosts.add(hostMap.get(Integer.parseInt(hostId)));
            }
            ResidentialProperty residentialProperty = new ResidentialProperty(
                    Integer.parseInt(row[0]),
                    row[1],
                    Double.parseDouble(row[2]),
                    row[3],
                    ownerMap.get(Integer.parseInt(row[4])),
                    hosts,
                    Integer.parseInt(row[6]),
                    Boolean.parseBoolean(row[7]),
                    Boolean.parseBoolean(row[8])

            );
            residentialProperties.add(residentialProperty);
        }
        return residentialProperties;
    }

    public static List<CommercialProperty> populateCommercials(String filePath){
        List<CommercialProperty> commercialProperties = new ArrayList<>();
        List<String[]> data = DataLoader.readCSV(filePath);

        for (String[] row : data) {
            if (row[0].equals("propertyId")) continue;
            List<Host> hosts = new ArrayList<>();
            String idLine = row[5];
            String[] hostIds = idLine.split(";");
            for (String hostId : hostIds) {
                hosts.add(hostMap.get(Integer.parseInt(hostId)));
            }
            CommercialProperty property = new CommercialProperty(
                    Integer.parseInt(row[0]),
                    row[1],
                    Double.parseDouble(row[2]),
                    row[3],
                    ownerMap.get(Integer.parseInt(row[4])),
                    hosts,
                    row[6],
                    Integer.parseInt(row[7]),
                    Double.parseDouble(row[8])
            );
            commercialProperties.add(property);
            commercialMap.put(Integer.parseInt(row[0]), property);
        }
        return commercialProperties;
    }

    public static List<RentalAgreement> populateAgreements(String filePath){
        List<RentalAgreement> rentalAgreements = new ArrayList<>();
        List<String[]> data = DataLoader.readCSV(filePath);

        for (String[] row : data) {
            if (row[0].equals("agreementId")) continue;
            Date contractDate = null;
            try {
                contractDate = new SimpleDateFormat("MM/dd/yyyy").parse(row[5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<Tenant> subTenants = new ArrayList<>();
            String idLine = row[2];
            String[] tenantIds = idLine.split(";");
            for (String tenantId : tenantIds) {
                subTenants.add(tenantMap.get(Integer.parseInt(tenantId)));
            }
            RentalAgreement rentalAgreement = new RentalAgreement(
                    Integer.parseInt(row[0]),
                    tenantMap.get(Integer.parseInt(row[1])),
                    subTenants,
                    commercialMap.get(Integer.parseInt(row[3])),
                    row[4],
                    contractDate,
                    Double.parseDouble(row[6]),
                    row[7]
            );
            rentalAgreements.add(rentalAgreement);
            agreementMap.put(Integer.parseInt(row[0]), rentalAgreement);
        }
        return rentalAgreements;
    }

    public static void finalizeTenants(String filePath){
        List<String[]> data = DataLoader.readCSV(filePath);
        for (String[] row : data) {
            if (row[0].equals("id")) continue;
            String idLine = row[4];
            String[] agreementIds = idLine.split(";");
            for (String agreementId : agreementIds) {
                tenantMap.get(Integer.parseInt(row[0])).addRentalAgreement(agreementMap.get(Integer.parseInt(agreementId)));
            }
        }
    }

    public static void finalizeHosts(String filePath){
        List<String[]> data = DataLoader.readCSV(filePath);
        for (String[] row : data) {
            if (row[0].equals("id")) continue;
            String idLine = row[4];
            String[] propertyIds = idLine.split(";");
            for (String propertyId : propertyIds) {
                hostMap.get(Integer.parseInt(row[0])).addProperty(commercialMap.get(Integer.parseInt(propertyId)));
            }
            String[] ownerIds = idLine.split(";");
            for (String ownerId : ownerIds) {
                hostMap.get(Integer.parseInt(row[0])).addOwner(ownerMap.get(Integer.parseInt(ownerId)));
            }
            String[] agreementIds = idLine.split(";");
            for (String agreementId : agreementIds) {
                hostMap.get(Integer.parseInt(row[0])).addRentalAgreement(agreementMap.get(Integer.parseInt(agreementId)));
            }
        }
    }

    public static void finalizeOwners(String filePath){
        List<String[]> data = DataLoader.readCSV(filePath);
        for (String[] row : data) {
            if (row[0].equals("id")) continue;
            String idLine = row[4];
            String[] propertyIds = idLine.split(";");
            for (String propertyId : propertyIds) {
                ownerMap.get(Integer.parseInt(row[0])).addProperty(commercialMap.get(Integer.parseInt(propertyId)));
            }
            String[] agreementIds = idLine.split(";");
            for (String agreementId : agreementIds) {
                ownerMap.get(Integer.parseInt(row[0])).addRentalAgreement(agreementMap.get(Integer.parseInt(agreementId)));
            }
        }
    }
}
