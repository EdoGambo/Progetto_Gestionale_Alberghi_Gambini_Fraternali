package Hotel_controller;

import Hotel_source.Service;
import Hotel_source.ServiceManager;
import Hotel_view.ServiceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceController {
    private ServiceView serviceView;
    private ServiceManager serviceManager;

    public ServiceController(ServiceView serviceView, ServiceManager serviceManager) {
        this.serviceView = serviceView;
        this.serviceManager = serviceManager;

        // Register event listeners
        this.serviceView.addAddServiceListener(new AddServiceListener());
        this.serviceView.addUpdateServiceListener(new UpdateServiceListener());
        this.serviceView.addRemoveServiceListener(new RemoveServiceListener());
        this.serviceView.addGetServiceDetailsListener(new GetServiceDetailsListener());
    }

    class AddServiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = serviceView.getServiceName();
                double price = serviceView.getServicePrice();
                serviceManager.addService(name, price);
                JOptionPane.showMessageDialog(serviceView, "Service added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(serviceView, "Error adding service: " + ex.getMessage());
            }
        }
    }

    class UpdateServiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int serviceId = serviceView.getUpdateServiceId();
                String name = serviceView.getUpdateName();
                double price = serviceView.getUpdatePrice();
                serviceManager.updateService(serviceId, name, price);
                JOptionPane.showMessageDialog(serviceView, "Service updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(serviceView, "Error updating service: " + ex.getMessage());
            }
        }
    }

    class RemoveServiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int serviceId = serviceView.getRemoveServiceId();
                serviceManager.removeService(serviceId);
                JOptionPane.showMessageDialog(serviceView, "Service removed successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(serviceView, "Error removing service: " + ex.getMessage());
            }
        }
    }

    class GetServiceDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int serviceId = serviceView.getServiceId();
                Service service = serviceManager.getServiceDetails(serviceId);
                if (service != null) {
                    serviceView.setServiceDetails(service.toString());
                } else {
                    JOptionPane.showMessageDialog(serviceView, "Service not found!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(serviceView, "Error retrieving service details: " + ex.getMessage());
            }
        }
    }
}

