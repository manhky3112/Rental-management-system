import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class RentalManagementApp {

    public static String sortChoice(){
        System.out.println("\nSort report by:");
        System.out.println("1. Status (default)");
        System.out.println("2. Contract Date");
        System.out.println("3. Period");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return switch (choice) {
            case 1 -> "Status";
            case 2 -> "Contract Date";
            case 3 -> "Period";
            default -> {
                System.out.println("Invalid choice, sort type auto set to default (Status)");
                yield "Status";
            }
        };
    }

    public static void showAgreementsDetail(List<RentalAgreement> agreements){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        int idWidth = 5;
        int mainTenantWidth = 25;
        int subTenantWidth = 35;
        int propertyWidth = 30;
        int periodWidth = 15;
        int contractDateWidth = 18;
        int rentingFeeWidth = 15;
        int statusWidth = 15;
        System.out.printf("%-" + idWidth + "s%-" + mainTenantWidth + "s%-" + subTenantWidth + "s%-" + propertyWidth + "s%-" + periodWidth + "s%-" + contractDateWidth + "s%-" + rentingFeeWidth + "s%-" + statusWidth + "s%n",
                "ID", "Main Tenant(ID-Name)", "Sub Tenants(ID-Name)", "Leased Property(ID-Address)", "Period", "Contract Date", "Renting Fee", "Status");

        for (RentalAgreement agreement : agreements) {
            StringBuilder subTenants = new StringBuilder();
            for (int i = 0; i < agreement.getSubTenants().size(); i++) {
                subTenants.append(agreement.getSubTenants().get(i).getId()).append("-").append(agreement.getSubTenants().get(i).getFullName());
                if (i != agreement.getSubTenants().size() - 1) {
                    subTenants.append(", ");
                }
            }

            System.out.printf("%-" + idWidth + "d%-" + mainTenantWidth + "s%-" + subTenantWidth + "s%-" + propertyWidth + "s%-" + periodWidth + "s%-" + contractDateWidth + "s%-" + rentingFeeWidth + ".2f%-" + statusWidth + "s%n",
                    agreement.getId(),
                    agreement.getTenant().getId() + "-" + agreement.getTenant().getFullName(),
                    subTenants,
                    agreement.getProperty().getPropertyId() + "-" + agreement.getProperty().getAddress(),
                    agreement.getPeriod(),
                    dateFormat.format(agreement.getContractDate()),
                    agreement.getRentingFee(),
                    agreement.getStatus());
        }
    }

    public static void main(String[] args) {
        RentalManagerImp rentalManager = new RentalManagerImp();
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\Payment.csv", "Payment");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\Tenant.csv", "Tenant");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\Host.csv", "Host");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\Owner.csv", "Owner");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\ResidentialProperty.csv", "ResidentialProperty");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\CommercialProperty.csv", "CommercialProperty");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\RentalAgreement.csv", "RentalAgreement");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\Tenant.csv", "TenantRemaining");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\Host.csv", "HostRemaining");
        rentalManager.initializeData("C:\\kyanh workspace\\Java\\ASM1 - Rental management system\\DataCSV\\Owner.csv", "OwnerRemaining");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Scanner scanner = new Scanner(System.in);
        int lineLength = 40;
        int menu = 1;
        String sortType = "Status";
        while (true) {
            if(menu == 1){
                System.out.println("\n==========WELCOME TO RENTAL MANAGEMENT APP===========\n");
                System.out.println("1. Managing Rental Agreements");
                System.out.println("2. Managing Other Entities");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        menu = 2;
                        break;
                    case 2:
                        menu = 3;
                        break;
                    case 3:
                        System.out.println("Exiting application");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }else if(menu == 2){
                System.out.println("\n----------RENTAL AGREEMENT MANAGEMENT MENU-----------");
                System.out.println("1. List all Rental Agreements");
                System.out.println("2. List all Rental Agreements by Owner Name");
                System.out.println("3. List all Rental Agreements by Property Address");
                System.out.println("4. List all Rental Agreements by Status");
                System.out.println("5. Add Rental Agreement");
                System.out.println("6. Update Rental Agreement");
                System.out.println("7. Delete rental Agreement");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                List<RentalAgreement> agreements;
                int agreementId;
                switch (choice) {
                    case 1:
                        sortType = sortChoice();
                        agreements = rentalManager.sortReport(rentalManager.getAllAgreements(), sortType);
                        System.out.println("\n" + "-".repeat(lineLength) + "All Rental Agreements sort by " + sortType + "-".repeat(lineLength));
                        showAgreementsDetail(agreements);
                        break;
                    case 2:
                        System.out.print("\nEnter owner name involved: ");
                        String ownerName = scanner.nextLine();
                        sortType = sortChoice();
                        agreements = rentalManager.sortReport(rentalManager.getAgreementsByOwnerName(ownerName), sortType);
                        System.out.println("\n" + "-".repeat(lineLength) + "All Rental Agreements involved with Owner " + ownerName + " sort by " + sortType + "-".repeat(lineLength));
                        showAgreementsDetail(agreements);
                        break;
                    case 3:
                        System.out.print("\nEnter Property involved address: ");
                        String propertyAddress = scanner.nextLine();
                        sortType = sortChoice();
                        agreements = rentalManager.sortReport(rentalManager.getAgreementsByPropertyAddress(propertyAddress), sortType);
                        System.out.println("\n" + "-".repeat(lineLength) + "All Rental Agreements involved with Property at " + propertyAddress + " sort by " + sortType + "-".repeat(lineLength));
                        showAgreementsDetail(agreements);
                        break;
                    case 4:
                        System.out.println("\nChoose Rental Agreement Status:");
                        System.out.println("1. New\n2. Active\n3. Completed");
                        System.out.print("Enter your choice: ");
                        int option = scanner.nextInt();
                        String status = switch (option) {
                            case 1 -> "New";
                            case 2 -> "Active";
                            case 3 -> "Completed";
                            default -> {
                                System.out.println("Invalid choice, auto set status to New");
                                yield "New";
                            }
                        };
                        sortType = sortChoice();
                        agreements = rentalManager.sortReport(rentalManager.getAgreementsByStatus(status), sortType);
                        System.out.println("\n" + "-".repeat(lineLength) + "All " + status + " Rental Agreements sort by " + sortType + "-".repeat(lineLength));
                        showAgreementsDetail(agreements);
                        break;
                    case 5:
                        System.out.print("\nEnter Host Full Name: ");
                        break;
                    case 6:
                        System.out.print("\nEnter Rental Agreement ID you want to update: ");
                        agreementId = scanner.nextInt();
                        break;
                    case 7:
                        System.out.print("\nEnter Rental Agreement ID you want to delete: ");
                        agreementId = scanner.nextInt();
                        rentalManager.deleteAgreement(agreementId);
                        break;
                    case 8:
                        menu = 1;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }else if(menu == 3){
                System.out.println("\n----------ENTITIES MANAGEMENT MENU-----------");
                System.out.println("1. List all Tenant");
                System.out.println("2. List all Host");
                System.out.println("3. List all Owner");
                System.out.println("4. List all Residential Property");
                System.out.println("5. List all Commercial Property");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                int idWidth = 5;
                int nameWidth = 20;
                int dobWidth = 17;
                int contactWidth = 30;
                int hostsWidth = 35;
                int ownersWidth = 40;
                int propertiesWidth = 45;
                int agreementsWidth = 25;

                int addressWidth = 20;
                int pricingWidth = 13;
                int statusWidth = 13;
                int ownerWidth = 20;

                int bedroomsWidth = 12;
                int gardenWidth = 10;
                int petWidth = 10;

                int businessTypeWidth = 17;
                int parkingSpacesWidth = 18;
                int squareFootageWidth = 20;

                switch (choice) {
                    case 1:
                        List<Tenant> tenants = rentalManager.getAllTenants();
                        System.out.println("-".repeat(lineLength) + "All available Tenants within the system" + "-".repeat(lineLength));
                        int paymentsWidth = 20;

                        System.out.printf("%-" + idWidth + "s%-" + nameWidth + "s%-" + dobWidth + "s%-" + contactWidth + "s%-" + agreementsWidth + "s%-" + paymentsWidth + "s%n",
                                "ID", "Full Name", "Date of Birth", "Contact Info", "Rental Agreements(ID)", "Payments(ID)");

                        for (Tenant tenant : tenants) {
                            StringBuilder rentalAgreements = new StringBuilder();
                            for (int i = 0; i < tenant.getRentalAgreements().size(); i++) {
                                rentalAgreements.append(tenant.getRentalAgreements().get(i).getId());
                                if (i != tenant.getRentalAgreements().size() - 1) {
                                    rentalAgreements.append(", ");
                                }
                            }

                            StringBuilder payments = new StringBuilder();
                            for (int i = 0; i < tenant.getPaymentTransactions().size(); i++) {
                                payments.append(tenant.getPaymentTransactions().get(i).getPaymentId());
                                if (i != tenant.getPaymentTransactions().size() - 1) {
                                    payments.append(", ");
                                }
                            }

                            System.out.printf("%-" + idWidth + "d%-" + nameWidth + "s%-" + dobWidth + "s%-" + contactWidth + "s%-" + agreementsWidth + "s%-" + paymentsWidth + "s%n",
                                    tenant.getId(),
                                    tenant.getFullName(),
                                    dateFormat.format(tenant.getDateOfBirth()),
                                    tenant.getContactInfo(),
                                    rentalAgreements,
                                    payments);
                        }
                        break;
                    case 2:
                        List<Host> hosts = rentalManager.getAllHosts();
                        System.out.println("\n" + "-".repeat(lineLength) + "All available Hosts within the system" + "-".repeat(lineLength));

                        System.out.printf("%-" + idWidth + "s%-" + nameWidth + "s%-" + dobWidth + "s%-" + contactWidth + "s%-" + propertiesWidth + "s%-" + ownersWidth + "s%-" + agreementsWidth + "s%n",
                                "ID", "Full Name", "Date of Birth", "Contact Info", "Managing Properties(ID-Address)", "Cooperating Owners(ID-Name)", "Rental Agreements(ID)");

                        for (Host host : hosts) {
                            StringBuilder properties = new StringBuilder();
                            for (int i = 0; i < host.getProperties().size(); i++) {
                                properties.append(host.getProperties().get(i).getPropertyId()).append("-").append(host.getProperties().get(i).getAddress());
                                if (i != host.getProperties().size() - 1) {
                                    properties.append(", ");
                                }
                            }

                            StringBuilder owners = new StringBuilder();
                            for (int i = 0; i < host.getCooperatingOwners().size(); i++) {
                                owners.append(host.getCooperatingOwners().get(i).getId()).append("-").append(host.getCooperatingOwners().get(i).getFullName());
                                if (i != host.getCooperatingOwners().size() - 1) {
                                    owners.append(", ");
                                }
                            }

                            StringBuilder agreements = new StringBuilder();
                            for (int i = 0; i < host.getRentalAgreements().size(); i++) {
                                agreements.append(host.getRentalAgreements().get(i).getId());
                                if (i != host.getRentalAgreements().size() - 1) {
                                    agreements.append(", ");
                                }
                            }

                            System.out.printf("%-" + idWidth + "d%-" + nameWidth + "s%-" + dobWidth + "s%-" + contactWidth + "s%-" + propertiesWidth + "s%-" + ownersWidth + "s%-" + agreementsWidth + "s%n",
                                    host.getId(),
                                    host.getFullName(),
                                    dateFormat.format(host.getDateOfBirth()),
                                    host.getContactInfo(),
                                    properties,
                                    owners,
                                    agreements);
                        }
                        break;
                    case 3:
                        List<Owner> owners = rentalManager.getAllOwners();
                        System.out.println("\n" + "-".repeat(lineLength) + "All available Owners within the system" + "-".repeat(lineLength));

                        System.out.printf("%-" + idWidth + "s%-" + nameWidth + "s%-" + dobWidth + "s%-" + contactWidth + "s%-" + propertiesWidth + "s%-" + hostsWidth + "s%-" + agreementsWidth + "s%n",
                                "ID", "Full Name", "Date of Birth", "Contact Info", "Owned Properties(ID-Address)", "Managing Hosts(ID-Name)", "Rental Agreements(ID)");
                        for (Owner owner : owners) {
                            StringBuilder properties = new StringBuilder();
                            for (int i = 0; i < owner.getOwnedProperties().size(); i++) {
                                properties.append(owner.getOwnedProperties().get(i).getPropertyId()).append("-").append(owner.getOwnedProperties().get(i).getAddress());
                                if (i != owner.getOwnedProperties().size() - 1) {
                                    properties.append(", ");
                                }
                            }

                            StringBuilder cooperatingHosts = new StringBuilder();
                            for (int i = 0; i < owner.getManagingHosts().size(); i++) {
                                cooperatingHosts.append(owner.getManagingHosts().get(i).getId()).append("-").append(owner.getManagingHosts().get(i).getFullName());
                                if (i != owner.getManagingHosts().size() - 1) {
                                    cooperatingHosts.append(", ");
                                }
                            }

                            StringBuilder agreements = new StringBuilder();
                            for (int i = 0; i < owner.getRentalAgreements().size(); i++) {
                                agreements.append(owner.getRentalAgreements().get(i).getId());
                                if (i != owner.getRentalAgreements().size() - 1) {
                                    agreements.append(", ");
                                }
                            }

                            System.out.printf("%-" + idWidth + "d%-" + nameWidth + "s%-" + dobWidth + "s%-" + contactWidth + "s%-" + propertiesWidth + "s%-" + hostsWidth + "s%-" + agreementsWidth + "s%n",
                                    owner.getId(),
                                    owner.getFullName(),
                                    dateFormat.format(owner.getDateOfBirth()),
                                    owner.getContactInfo(),
                                    properties,
                                    cooperatingHosts,
                                    agreements);
                        }
                        break;
                    case 4:
                        List<ResidentialProperty> residentialProperties = rentalManager.getAllResidentialProperties();
                        System.out.println("\n" + "-".repeat(lineLength) + "All available Residential Property within the system" + "-".repeat(lineLength));

                        System.out.printf("%-" + idWidth + "s%-" + addressWidth + "s%-" + pricingWidth + "s%-" + statusWidth + "s%-" + ownerWidth + "s%-" + hostsWidth + "s%-" + bedroomsWidth + "s%-" + gardenWidth + "s%-" + petWidth + "s%n",
                                "ID", "Address", "Pricing", "Status", "Owner(ID-Name)", "Hosts(ID-Name)", "Bedrooms", "Garden", "Pet-Friendly");

                        for (ResidentialProperty residential : residentialProperties) {
                            StringBuilder managingHosts = new StringBuilder();
                            for (int i = 0; i < residential.getHosts().size(); i++) {
                                managingHosts.append(residential.getHosts().get(i).getId()).append("-").append(residential.getHosts().get(i).getFullName());
                                if (i != residential.getHosts().size() - 1) {
                                    managingHosts.append(", ");
                                }
                            }
                            System.out.printf("%-" + idWidth + "d%-" + addressWidth + "s%-" + pricingWidth + ".2f%-" + statusWidth + "s%-" + ownerWidth + "s%-" + hostsWidth + "s%-" + bedroomsWidth + "d%-" + gardenWidth + "s%-" + petWidth + "s%n",
                                    residential.getPropertyId(),
                                    residential.getAddress(),
                                    residential.getPricing(),
                                    residential.getStatus(),
                                    residential.getOwner().getId() + "-" + residential.getOwner().getFullName(),
                                    managingHosts,
                                    residential.getNumBedrooms(),
                                    residential.hasGarden() ? "Yes" : "No",
                                    residential.isPetFriendly() ? "Yes" : "No");
                        }
                        break;
                    case 5:
                        List<CommercialProperty> commercialProperties = rentalManager.getAllCommercialProperties();
                        System.out.println("\n" + "-".repeat(lineLength) + "All available Commercial Property within the system" + "-".repeat(lineLength));

                        System.out.printf("%-" + idWidth + "s%-" + addressWidth + "s%-" + pricingWidth + "s%-" + statusWidth + "s%-" + ownerWidth + "s%-" + hostsWidth + "s%-" + businessTypeWidth + "s%-" + parkingSpacesWidth + "s%-" + squareFootageWidth + "s%n",
                                "ID", "Address", "Pricing", "Status", "Owner(ID-Name)", "Hosts(ID-Name)", "Business Type", "Parking Spaces", "Square Footage");

                        for (CommercialProperty commercial : commercialProperties) {
                            StringBuilder managingHosts = new StringBuilder();
                            for (int i = 0; i < commercial.getHosts().size(); i++) {
                                managingHosts.append(commercial.getHosts().get(i).getId()).append("-").append(commercial.getHosts().get(i).getFullName());
                                if (i != commercial.getHosts().size() - 1) {
                                    managingHosts.append(", ");
                                }
                            }

                            System.out.printf("%-" + idWidth + "d%-" + addressWidth + "s%-" + pricingWidth + ".2f%-" + statusWidth + "s%-" + ownerWidth + "s%-" + hostsWidth + "s%-" + businessTypeWidth + "s%-" + parkingSpacesWidth + "d%-" + squareFootageWidth + ".2f%n",
                                    commercial.getPropertyId(),
                                    commercial.getAddress(),
                                    commercial.getPricing(),
                                    commercial.getStatus(),
                                    commercial.getOwner().getId() + "-" + commercial.getOwner().getFullName(),
                                    managingHosts,
                                    commercial.getBusinessType(),
                                    commercial.getParkingSpaces(),
                                    commercial.getSquareFootage());
                        }
                        break;
                    case 6:
                        menu = 1;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        }
    }
}