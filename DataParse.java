import java.io.*;

public class DataParse {
    public static void load(String filePath, RedBlackTree tree) {
        File file = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (fields.length != 4) {
                    continue;
                }
                String id = fields[0].trim();
                String name = fields[1].replace("\"", "").trim();
                String category = fields[2].trim();
                double price;
                try {
                    price = Double.parseDouble(fields[3].replace("$", "").trim());
                } catch (NumberFormatException e) {
                    continue;
                }
                Product products = new Product(id, name, category, price);
                tree.insert(products);
            }
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}

