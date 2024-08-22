package Hotel_source;

import java.io.*;
import java.util.*;

public class CustomerManager{
    private Map<Integer, Customer> customers = new HashMap<>();
    private int nextCustomerId = 1;
    private static final String CUSTOMER_FILE = "customers.txt";

    public CustomerManager() {
        loadCustomersFromFile();
    }

    public Customer createCustomer(String name, String email, String phone, LoyaltyStatus loyaltyStatus) {
        int id = nextCustomerId++;
        Customer newCustomer = new Customer(id, name, email, phone, loyaltyStatus);
        customers.put(id, newCustomer);
        saveCustomerToFile(newCustomer);
        return newCustomer;
    }

    public Customer getCustomerDetails(int customerId) {
        return customers.get(customerId);
    }

    public void updateCustomerDetails(int customerId, String name, String email, String phone, LoyaltyStatus loyaltyStatus) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            Customer updatedCustomer = new Customer(customerId, name, email, phone, loyaltyStatus);
            customers.put(customerId, updatedCustomer);
            saveAllCustomersToFile();
        }
    }

    public void deleteCustomer(int customerId) {
        customers.remove(customerId);
        saveAllCustomersToFile();
    }

    public void registerComplaint(int customerId, String complaintDetails) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            try (FileWriter writer = new FileWriter("complaints.txt", true)) {
                writer.write("Customer ID: " + customerId + ", Complaint: " + complaintDetails + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCustomerToFile(Customer customer) {
        try (FileWriter writer = new FileWriter(CUSTOMER_FILE, true)) {
            writer.write(customer.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllCustomersToFile() {
        try (FileWriter writer = new FileWriter(CUSTOMER_FILE)) {
            for (Customer customer : customers.values()) {
                writer.write(customer.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Reading line: " + line);  
                try {
                    Customer customer = Customer.fromString(line);
                    customers.put(customer.getId(), customer);
                    if (customer.getId() >= nextCustomerId) {
                        nextCustomerId = customer.getId() + 1;
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Error parsing customer data: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
