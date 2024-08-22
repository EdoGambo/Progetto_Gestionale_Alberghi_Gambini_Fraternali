package Hotel_controller;

import Hotel_source.Customer;
import Hotel_source.CustomerManager;
import Hotel_source.LoyaltyStatus;
import Hotel_view.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {
    private CustomerManager customerManager;
    private CustomerView customerView;

    public CustomerController(CustomerManager customerManager, CustomerView customerView) {
        this.customerManager = customerManager;
        this.customerView = customerView;

        this.customerView.addCreateCustomerListener(new CreateCustomerListener());
        this.customerView.addGetCustomerDetailsListener(new GetCustomerDetailsListener());
        this.customerView.addUpdateCustomerDetailsListener(new UpdateCustomerDetailsListener());
        this.customerView.addDeleteCustomerListener(new DeleteCustomerListener());
        this.customerView.addRegisterComplaintListener(new RegisterComplaintListener());
    }

    class CreateCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = customerView.getName();
            String email = customerView.getEmail();
            String phone = customerView.getPhone();
            LoyaltyStatus loyaltyStatus = customerView.getLoyaltyStatus();
            Customer customer = customerManager.createCustomer(name, email, phone, loyaltyStatus);
            customerView.setCustomerDetails("Customer created: " + customer.getId());
        }
    }

    class GetCustomerDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int customerId = customerView.getCustomerId();
                Customer customer = customerManager.getCustomerDetails(customerId);
                if (customer != null) {
                    customerView.setCustomerDetails("Customer ID: " + customer.getId() +
                            "\nName: " + customer.getName() +
                            "\nEmail: " + customer.getEmail() +
                            "\nPhone: " + customer.getPhone() +
                            "\nLoyalty Status: " + customer.getLoyaltyStatus());
                } else {
                    customerView.setCustomerDetails("Customer not found.");
                }
            } catch (NumberFormatException ex) {
                customerView.setCustomerDetails("Invalid customer ID.");
            }
        }
    }

    class UpdateCustomerDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int customerId = customerView.getUpdateCustomerId();
                String name = customerView.getUpdateName();
                String email = customerView.getUpdateEmail();
                String phone = customerView.getUpdatePhone();
                LoyaltyStatus loyaltyStatus = customerView.getUpdateLoyaltyStatus();
                customerManager.updateCustomerDetails(customerId, name, email, phone, loyaltyStatus);
                customerView.setCustomerDetails("Customer updated: " + customerId);
            } catch (NumberFormatException ex) {
                customerView.setCustomerDetails("Invalid customer ID.");
            }
        }
    }

    class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int customerId = customerView.getDeleteCustomerId();
                customerManager.deleteCustomer(customerId);
                customerView.setCustomerDetails("Customer deleted: " + customerId);
            } catch (NumberFormatException ex) {
                customerView.setCustomerDetails("Invalid customer ID.");
            }
        }
    }

    class RegisterComplaintListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int customerId = customerView.getComplaintCustomerId();
                String complaintDetails = customerView.getComplaintDetails();
                customerManager.registerComplaint(customerId, complaintDetails);
                customerView.setCustomerDetails("Complaint registered for customer ID: " + customerId);
            } catch (NumberFormatException ex) {
                customerView.setCustomerDetails("Invalid customer ID.");
            }
        }
    }
}