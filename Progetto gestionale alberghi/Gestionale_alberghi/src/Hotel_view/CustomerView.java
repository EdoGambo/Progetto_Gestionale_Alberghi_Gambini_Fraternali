package Hotel_view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Hotel_source.LoyaltyStatus;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerView extends JFrame {
    private JTextField nameField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JTextField phoneField = new JTextField(20);
    private JComboBox<LoyaltyStatus> loyaltyStatusComboBox = new JComboBox<>(LoyaltyStatus.values());
    private JButton createCustomerButton = new JButton("Create Customer");

    private JTextField customerIdField = new JTextField(20);
    private JButton getCustomerDetailsButton = new JButton("Get Customer Details");
    private JTextArea customerDetailsArea = new JTextArea(10, 30);

    private JTextField updateCustomerIdField = new JTextField(20);
    private JTextField updateNameField = new JTextField(20);
    private JTextField updateEmailField = new JTextField(20);
    private JTextField updatePhoneField = new JTextField(20);
    private JComboBox<LoyaltyStatus> updateLoyaltyStatusComboBox = new JComboBox<>(LoyaltyStatus.values());
    private JButton updateCustomerDetailsButton = new JButton("Update Customer Details");

    private JTextField deleteCustomerIdField = new JTextField(20);
    private JButton deleteCustomerButton = new JButton("Delete Customer");

    private JTextField complaintCustomerIdField = new JTextField(20);
    private JTextField complaintDetailsField = new JTextField(20);
    private JButton registerComplaintButton = new JButton("Register Complaint");

    public CustomerView() {
        JPanel createCustomerPanel = createPanel("Create Customer", new Color(173, 216, 230));
        createCustomerPanel.add(new JLabel("Name:"));
        createCustomerPanel.add(nameField);
        createCustomerPanel.add(new JLabel("Email:"));
        createCustomerPanel.add(emailField);
        createCustomerPanel.add(new JLabel("Phone:"));
        createCustomerPanel.add(phoneField);
        createCustomerPanel.add(new JLabel("Loyalty Status:"));
        createCustomerPanel.add(loyaltyStatusComboBox);
        createCustomerPanel.add(createCustomerButton);

        JPanel getCustomerDetailsPanel = createPanel("Get Customer Details", new Color(144, 238, 144));
        getCustomerDetailsPanel.add(new JLabel("Customer ID:"));
        getCustomerDetailsPanel.add(customerIdField);
        getCustomerDetailsPanel.add(getCustomerDetailsButton);
        JScrollPane customerDetailsScrollPane = new JScrollPane(customerDetailsArea);
        getCustomerDetailsPanel.add(customerDetailsScrollPane);

        JPanel updateCustomerDetailsPanel = createPanel("Update Customer Details", new Color(255, 182, 193));
        updateCustomerDetailsPanel.add(new JLabel("Customer ID:"));
        updateCustomerDetailsPanel.add(updateCustomerIdField);
        updateCustomerDetailsPanel.add(new JLabel("Name:"));
        updateCustomerDetailsPanel.add(updateNameField);
        updateCustomerDetailsPanel.add(new JLabel("Email:"));
        updateCustomerDetailsPanel.add(updateEmailField);
        updateCustomerDetailsPanel.add(new JLabel("Phone:"));
        updateCustomerDetailsPanel.add(updatePhoneField);
        updateCustomerDetailsPanel.add(new JLabel("Loyalty Status:"));
        updateCustomerDetailsPanel.add(updateLoyaltyStatusComboBox);
        updateCustomerDetailsPanel.add(updateCustomerDetailsButton);

        JPanel deleteCustomerPanel = createPanel("Delete Customer", new Color(255, 160, 122));
        deleteCustomerPanel.add(new JLabel("Customer ID:"));
        deleteCustomerPanel.add(deleteCustomerIdField);
        deleteCustomerPanel.add(deleteCustomerButton);

        JPanel registerComplaintPanel = createPanel("Register Complaint", new Color(238, 232, 170));
        registerComplaintPanel.add(new JLabel("Customer ID:"));
        registerComplaintPanel.add(complaintCustomerIdField);
        registerComplaintPanel.add(new JLabel("Complaint Details:"));
        registerComplaintPanel.add(complaintDetailsField);
        registerComplaintPanel.add(registerComplaintButton);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(createCustomerPanel);
        mainPanel.add(getCustomerDetailsPanel);
        mainPanel.add(updateCustomerDetailsPanel);
        mainPanel.add(deleteCustomerPanel);
        mainPanel.add(registerComplaintPanel);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(mainPanel), BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setVisible(true);
    }

    private JPanel createPanel(String title, Color color) {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBorder(createTitledBorder(title));
        panel.setBackground(color);
        return panel;
    }

    private TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(new Font("Arial", Font.BOLD, 14));
        border.setTitleColor(Color.DARK_GRAY);
        border.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return border;
    }

    public String getName() {
        return nameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPhone() {
        return phoneField.getText().trim();
    }

    public LoyaltyStatus getLoyaltyStatus() {
        return (LoyaltyStatus) loyaltyStatusComboBox.getSelectedItem();
    }

    public void addCreateCustomerListener(ActionListener listener) {
        createCustomerButton.addActionListener(listener);
    }

    public int getCustomerId() throws NumberFormatException {
        String text = customerIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Customer ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public void addGetCustomerDetailsListener(ActionListener listener) {
        getCustomerDetailsButton.addActionListener(listener);
    }

    public void setCustomerDetails(String details) {
        customerDetailsArea.setText(details);
    }

    public int getUpdateCustomerId() throws NumberFormatException {
        String text = updateCustomerIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Customer ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public String getUpdateName() {
        return updateNameField.getText().trim();
    }

    public String getUpdateEmail() {
        return updateEmailField.getText().trim();
    }

    public String getUpdatePhone() {
        return updatePhoneField.getText().trim();
    }

    public LoyaltyStatus getUpdateLoyaltyStatus() {
        return (LoyaltyStatus) updateLoyaltyStatusComboBox.getSelectedItem();
    }

    public void addUpdateCustomerDetailsListener(ActionListener listener) {
        updateCustomerDetailsButton.addActionListener(listener);
    }

    public int getDeleteCustomerId() throws NumberFormatException {
        String text = deleteCustomerIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Customer ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public void addDeleteCustomerListener(ActionListener listener) {
        deleteCustomerButton.addActionListener(listener);
    }

    public int getComplaintCustomerId() throws NumberFormatException {
        String text = complaintCustomerIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Customer ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public String getComplaintDetails() {
        return complaintDetailsField.getText().trim();
    }

    public void addRegisterComplaintListener(ActionListener listener) {
        registerComplaintButton.addActionListener(listener);
    }
}