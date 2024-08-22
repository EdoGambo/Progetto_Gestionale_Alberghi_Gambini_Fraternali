package Hotel_view;

import Hotel_controller.*;
import Hotel_source.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JButton activityButton;
    private JButton billingButton;
    private JButton customerButton;
    private JButton employeeButton;
    private JButton reservationButton;
    private JButton serviceButton;

    public MainView() {
        setTitle("Hotel Management System");
        setSize(400, 300);
        setLayout(new GridLayout(3, 2)); 

        activityButton = new JButton("Activity Manager");
        billingButton = new JButton("Billing Manager");
        customerButton = new JButton("Customer Manager");
        employeeButton = new JButton("Employee Manager");
        reservationButton = new JButton("Reservation Manager");
        serviceButton = new JButton("Service Manager");

        add(activityButton);
        add(billingButton);
        add(customerButton);
        add(employeeButton);
        add(reservationButton);
        add(serviceButton);

        activityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openActivityManager();
            }
        });

        billingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBillingManager();
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCustomerManager();
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEmployeeManager();
            }
        });

        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReservationManager();
            }
        });

        serviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openServiceManager();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void openActivityManager() {
        this.dispose(); 
        ActivityManager activityManager = new ActivityManager();
        ActivityView activityView = new ActivityView(); 
        new ActivityController(activityManager, activityView); 
    }

    private void openBillingManager() {
        this.dispose(); 
        BillingManager billingManager = new BillingManager(); 
        BillingView billingView = new BillingView(); 
        new BillingController(billingManager, billingView); 
    }

    private void openCustomerManager() {
        this.dispose(); 
        CustomerManager customerManager = new CustomerManager(); 
        CustomerView customerView = new CustomerView(); 
        new CustomerController(customerManager, customerView); 
    }

    private void openEmployeeManager() {
        this.dispose(); 
        EmployeeManager employeeManager = new EmployeeManager(); 
        EmployeeView employeeView = new EmployeeView(); 
        new EmployeeController(employeeView, employeeManager); 
    }

    private void openReservationManager() {
        this.dispose(); 
        ReservationManager reservationManager = new ReservationManager(); 
        ReservationView reservationView = new ReservationView(); 
        new ReservationController(reservationManager, reservationView); 
    }

    private void openServiceManager() {
        this.dispose(); 
        ServiceManager serviceManager = new ServiceManager(); 
        ServiceView serviceView = new ServiceView(); 
        new ServiceController(serviceView, serviceManager); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView(); 
            }
        });
    }
}

