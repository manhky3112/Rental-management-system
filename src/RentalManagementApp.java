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
        switch (choice) {
            case 1: return "status";
            case 2: return "contractDate";
            case 3: return "period";
            default:
                System.out.println("Invalid choice, sort type auto set to default (Status)");
                return "status";
        }

    }

    public static void main(String[] args) {
        System.out.println("\n==========WELCOME TO RENTAL MANAGEMENT APP===========\n");
        RentalManagerImp rentalManager = new RentalManagerImp();
        Scanner scanner = new Scanner(System.in);
        int menu = 1;
        String sortType = "Status";
        while (true) {
            if(menu == 1){
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
                        System.out.println("\nAll Rental Agreements sort by " + sortType);
                        for (RentalAgreement agreement : agreements) {
                            System.out.println(agreement);
                        }
                        break;
                    case 2:
                        System.out.print("\nEnter owner name involved: ");
                        String ownerName = scanner.nextLine();
                        sortType = sortChoice();
                        agreements = rentalManager.sortReport(rentalManager.getAgreementsByOwnerName(ownerName), sortType);
                        System.out.println("\nAll Rental Agreements involved with Owner " + ownerName + " sort by " + sortType);
                        for (RentalAgreement agreement : agreements) {
                            System.out.println(agreement);
                        }
                        break;
                    case 3:
                        System.out.print("\nEnter Property involved address: ");
                        String propertyAddress = scanner.nextLine();
                        sortType = sortChoice();
                        agreements = rentalManager.sortReport(rentalManager.getAgreementsByPropertyAddress(propertyAddress), sortType);
                        System.out.println("\nAll Rental Agreements involved with Property at " + propertyAddress + " sort by " + sortType);
                        for (RentalAgreement agreement : agreements) {
                            System.out.println(agreement);
                        }
                        break;
                    case 4:
                        System.out.print("\nEnter Rental Agreement Status: ");
                        String status = scanner.nextLine();
                        sortType = sortChoice();
                        agreements = rentalManager.sortReport(rentalManager.getAgreementsByStatus(status), sortType);
                        System.out.println("\nAll " + status + " Rental Agreements sort by " + sortType);
                        for (RentalAgreement agreement : agreements) {
                            System.out.println(agreement);
                        }
                        break;
                    case 5:
                        System.out.print("\nEnter Host Name: ");
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
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        List<Tenant> tenants = rentalManager.getAllTenants();
                        System.out.println("\nAll available Tenants within the system");
                        for (Tenant tenant : tenants) {
                            System.out.println(tenant);
                        }
                        break;
                    case 2:
                        List<Host> hosts = rentalManager.getAllHosts();
                        System.out.println("\nAll available Hosts within the system");
                        for (Host host : hosts) {
                            System.out.println(host);
                        }
                        break;
                    case 3:
                        List<Owner> owners = rentalManager.getAllOwners();
                        System.out.println("\nAll available Owners within the system");
                        for (Owner owner : owners) {
                            System.out.println(owner);
                        }
                        break;
                    case 4:
                        List<ResidentialProperty> residentialProperties = rentalManager.getAllResidentialProperties();
                        System.out.println("\nAll available Residential Property within the system");
                        for (ResidentialProperty residentialProperty : residentialProperties) {
                            System.out.println(residentialProperty);
                        }
                        break;
                    case 5:
                        List<CommercialProperty> commercialProperties = rentalManager.getAllCommercialProperties();
                        System.out.println("\nAll available Commercial Property within the system");
                        for (CommercialProperty commercialProperty : commercialProperties) {
                            System.out.println(commercialProperty);
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