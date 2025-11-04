import java.util.*;

public class RecycloSortApp {

    // ===== Abstract Class =====
    abstract static class Waste {
        protected String name;
        protected String description;

        public Waste(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public abstract String getCategory();
        public abstract String getDisposalGuide();

        public void displayInfo() {
            System.out.println("Waste Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Category: " + getCategory());
            System.out.println("Disposal Guide: " + getDisposalGuide());
            System.out.println("------------------------------------");
        }
    }

    // ===== Subclasses =====
    static class OrganicWaste extends Waste {
        public OrganicWaste(String name, String description) {
            super(name, description);
        }

        public String getCategory() {
            return "Organic Waste";
        }

        public String getDisposalGuide() {
            return "Dispose in the green bin or compost pit.";
        }
    }

    static class RecyclableWaste extends Waste {
        public RecyclableWaste(String name, String description) {
            super(name, description);
        }

        public String getCategory() {
            return "Recyclable Waste";
        }

        public String getDisposalGuide() {
            return "Place in the blue recycling bin or take to a recycling center.";
        }
    }

    static class HazardousWaste extends Waste {
        public HazardousWaste(String name, String description) {
            super(name, description);
        }

        public String getCategory() {
            return "Hazardous Waste";
        }

        public String getDisposalGuide() {
            return "Handle with care. Dispose at a hazardous waste collection site.";
        }
    }

    static class EWaste extends Waste {
        public EWaste(String name, String description) {
            super(name, description);
        }

        public String getCategory() {
            return "Electronic Waste";
        }

        public String getDisposalGuide() {
            return "Return to authorized e-waste recycling centers.";
        }
    }

    // ===== Factory Class =====
    static class WasteFactory {
        public static Waste getWasteType(String type, String name, String desc) {
            switch (type.toLowerCase()) {
                case "organic":
                    return new OrganicWaste(name, desc);
                case "recyclable":
                    return new RecyclableWaste(name, desc);
                case "hazardous":
                    return new HazardousWaste(name, desc);
                case "ewaste":
                    return new EWaste(name, desc);
                default:
                    return null;
            }
        }
    }

    // ===== Main Application =====
    private static Scanner sc = new Scanner(System.in);
    private static List<Waste> wasteList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("♻️  Welcome to RecycloSort – Waste Segregation Guide App ♻️");
        System.out.println("----------------------------------------------------------");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Waste Item");
            System.out.println("2. View All Waste Items");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addWasteItem();
                    break;
                case 2:
                    viewWasteItems();
                    break;
                case 3:
                    System.out.println("🌱 Thank you for using RecycloSort. Keep your city clean!");
                    return;
                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        }
    }

    private static void addWasteItem() {
        System.out.print("Enter Waste Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        System.out.print("Select Waste Type (organic / recyclable / hazardous / ewaste): ");
        String type = sc.nextLine();

        Waste waste = WasteFactory.getWasteType(type, name, desc);
        if (waste != null) {
            wasteList.add(waste);
            System.out.println("✅ Waste added successfully!");
        } else {
            System.out.println("⚠️ Invalid type. Try again!");
        }
    }

    private static void viewWasteItems() {
        if (wasteList.isEmpty()) {
            System.out.println("No waste items added yet.");
        } else {
            System.out.println("\n--- Waste List ---");
            for (Waste w : wasteList) {
                w.displayInfo();
            }
        }
    }
}
