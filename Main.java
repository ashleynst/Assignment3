import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        String filePath = "src/amazon-product-data.csv";
        DataParse.load(filePath, tree);

        Scanner scanner = new Scanner(System.in);

        boolean searching = true;
        while (searching) {
            System.out.print("Product ID to search: ");
            String search = scanner.nextLine();
            System.out.println("Searching for product ID: " + search);
            searchMain(tree, search);

            System.out.print("Do you want to search again? (yes/no): ");
            String answer = scanner.nextLine();
            if (answer.equals("no"))
                searching = false;
        }

        String[][] insert = {{"4c69b61db1fc16e7013b43fc926e502d", "DB Longboards CoreFlex Crossbow 41\" Bamboo Fiberglass Longboard Complete", "Sports & Outdoors | Outdoor Recreation | Skates, Skateboards & Scooters | Skateboarding | Standard Skateboards & Longboards | Longboards", "$237.68"},
                {"7859383hfb43", "Pink Cat Bed", "Cat", "$19.99"}};
        for (String[] i : insert) {
            String id = i[0].trim();
            String name = i[1].trim();
            String category = i[2].trim();
            String priceString = i[3].trim().replace("$", "");
            double price;
            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                System.err.println("Invalid price format for product ID: " + id);
                return;
            }

            insertMain(tree, id, name, category, price);
        }
    }

    private static void searchMain(RedBlackTree tree, String id) {
        Product s = tree.search(id);
        if (s != null) {
            System.out.println("Product: " + s);
        } else {
            System.out.println("Product: " + id + " not found.");
        }
    }

    private static void insertMain(RedBlackTree tree, String id, String name, String category, double price) {
        Product i = tree.search(id);
        if (i != null) {
            System.out.println("Error, product already exists");
        } else {
            Product newP = new Product(id, name, category, price);
            tree.insert(newP);
            System.out.print("Inserting new product with ID: ");
            System.out.println(newP);
        }
    }
}

