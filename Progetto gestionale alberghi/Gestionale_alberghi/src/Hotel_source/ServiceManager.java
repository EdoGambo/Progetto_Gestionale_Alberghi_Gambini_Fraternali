package Hotel_source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager{
    private List<Service> services;
    private static final String SERVICE_FILE = "services.txt";

    public ServiceManager() {
        this.services = new ArrayList<>();
        loadServicesFromFile();
    }

    public Service addService(String name, double price) {
        Service service = new Service(name, price, true);
        services.add(service);
        saveServicesToFile();
        return service;
    }

    public void updateService(int serviceId, String name, double price) {
        for (Service service : services) {
            if (service.getServiceId() == serviceId) {
                service.setName(name);
                service.setPrice(price);
                saveServicesToFile();
                break;
            }
        }
    }

    public void removeService(int serviceId) {
        services.removeIf(service -> service.getServiceId() == serviceId);
        saveServicesToFile();
    }

    public Service getServiceDetails(int serviceId) {
        for (Service service : services) {
            if (service.getServiceId() == serviceId) {
                return service;
            }
        }
        return null;
    }

    public void setServiceAvailability(int serviceId, boolean isAvailable) {
        for (Service service : services) {
            if (service.getServiceId() == serviceId) {
                service.setAvailable(isAvailable);
                saveServicesToFile();
                break;
            }
        }
    }

    private void saveServicesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SERVICE_FILE))) {
            for (Service service : services) {
                writer.write(service.getServiceId() + "," + service.getName() + "," + service.getPrice() + "," + service.isAvailable() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to service file: " + e.getMessage());
        }
    }

    private void loadServicesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SERVICE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    boolean isAvailable = Boolean.parseBoolean(parts[3]);
                    services.add(new Service(name, price, isAvailable));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from service file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing service data: " + e.getMessage());
        }
    }
}

