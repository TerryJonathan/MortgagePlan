package ax.codetest.mortgageplanweb.customerFromFile;

import ax.codetest.mortgageplanweb.models.Customers;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *  CustomerHandler is responsible for creating a list of customers
 *  <br>
 *  prospects.txt found locally in root of project folder is where
 *  CustomerHandler will create its initial list from
 */
public class CustomerHandler {
    private List<Customers> customersList;
    public List<Customers> getCustomersList() {
        return customersList;
    }

    /**
     * Method that reads a local file and creates a list of customers from it.
     */
    public void populateListFromFile(){
        String stringFromFile= readFile();
        this.customersList= customersListFromFile(stringFromFile);
    }

    /**
     * Method that reads a local file in this case prospects.txt located in project root
     * @return content of file as a string
     */
    private String readFile(){
        StringBuilder content= new StringBuilder();
        boolean isFirstLine= true;// Boolean to track if it's the first line
        // File should in this case be placed in project root folder
        Path path= Paths.get(System.getProperty("user.dir") + File.separator+"prospects.txt");

        try (BufferedReader bReader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line;
            while ((line = bReader.readLine()) != null) {
                // Skip first line. Save all other lines that contain more than 5 characters.
                if(line.length() > 5 && !isFirstLine) {
                    content.append(line);
                    // Adding new line
                    content.append("\n");
                }
                isFirstLine= false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * Method that formats a string so that a number of customers can be created
     * @param fileString prospects.txt in String format
     * @return A List<Customers>
     */
    private List<Customers> customersListFromFile(String fileString ){
        List<Customers> customers = new ArrayList<>();

        // Remove all unwanted characters ^except .,\p{IsAlphabetic}0-9  newline space
        fileString= fileString.replaceAll("[^.,\\p{IsAlphabetic}0-9\n ]+", "");

        // Split string on new line.
        String[] lines = fileString.split("\\n");
        // If text would or could be alot longer then perhaps use Stringbuilder instead

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 4) { // Then there shouldn't be any completely missing parts
                customers.add(customerFromFile(parts));
            }
        }
        return customers;
    }

    /**
     * Method that creates Customers objects from an array of Strings
     * @param parts the array containing customer information in string format
     * @return A Customer
     */
    private Customers customerFromFile(String[] parts){
        StringBuilder name = new StringBuilder();
        for (int j = parts.length - 4; j >= 0; j--) {
            name.insert(0, parts[j] + " ");
        }
        name = new StringBuilder(name.toString().trim()); // In case of additional space added at end.
        return new Customers(
                name.toString(),
                Double.parseDouble(parts[parts.length - 3]),
                Double.parseDouble(parts[parts.length - 2]),
                Integer.parseInt(parts[parts.length - 1]));
    }
}
