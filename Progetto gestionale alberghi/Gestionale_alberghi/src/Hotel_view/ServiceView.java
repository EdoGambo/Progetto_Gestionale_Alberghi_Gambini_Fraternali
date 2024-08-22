package Hotel_view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServiceView extends JFrame {
    private JTextField nameField = new JTextField(20);
    private JTextField priceField = new JTextField(20);
    private JButton addServiceButton = new JButton("Add Service");
    
    private JTextField updateServiceIdField = new JTextField(20);
    private JTextField updateNameField = new JTextField(20);
    private JTextField updatePriceField = new JTextField(20);
    private JButton updateServiceButton = new JButton("Update Service");
    
    private JTextField removeServiceIdField = new JTextField(20);
    private JButton removeServiceButton = new JButton("Remove Service");
    
    private JTextField getServiceIdField = new JTextField(20);
    private JButton getServiceDetailsButton = new JButton("Get Service Details");
    private JTextArea serviceDetailsArea = new JTextArea(10, 30);

    public ServiceView() {
        Color addServiceColor = new Color(173, 216, 230); 
        Color updateServiceColor = new Color(144, 238, 144);
        Color removeServiceColor = new Color(255, 182, 193);
        Color getServiceDetailsColor = new Color(255, 228, 181);

        Color componentBackgroundColor = new Color(240, 248, 255);
        Color foregroundColor = Color.BLACK;

        JPanel addServicePanel = new JPanel(new GridLayout(0, 2));
        addServicePanel.setBorder(BorderFactory.createTitledBorder("Add Service"));
        addServicePanel.setBackground(addServiceColor);
        addServicePanel.setForeground(foregroundColor);
        addServicePanel.add(createLabel("Name:", foregroundColor));
        addServicePanel.add(createTextField(nameField, componentBackgroundColor, foregroundColor));
        addServicePanel.add(createLabel("Price:", foregroundColor));
        addServicePanel.add(createTextField(priceField, componentBackgroundColor, foregroundColor));
        addServicePanel.add(addServiceButton);

        JPanel updateServicePanel = new JPanel(new GridLayout(0, 2));
        updateServicePanel.setBorder(BorderFactory.createTitledBorder("Update Service"));
        updateServicePanel.setBackground(updateServiceColor);
        updateServicePanel.setForeground(foregroundColor);
        updateServicePanel.add(createLabel("Service ID:", foregroundColor));
        updateServicePanel.add(createTextField(updateServiceIdField, componentBackgroundColor, foregroundColor));
        updateServicePanel.add(createLabel("Name:", foregroundColor));
        updateServicePanel.add(createTextField(updateNameField, componentBackgroundColor, foregroundColor));
        updateServicePanel.add(createLabel("Price:", foregroundColor));
        updateServicePanel.add(createTextField(updatePriceField, componentBackgroundColor, foregroundColor));
        updateServicePanel.add(updateServiceButton);

        JPanel removeServicePanel = new JPanel(new GridLayout(0, 2));
        removeServicePanel.setBorder(BorderFactory.createTitledBorder("Remove Service"));
        removeServicePanel.setBackground(removeServiceColor);
        removeServicePanel.setForeground(foregroundColor);
        removeServicePanel.add(createLabel("Service ID:", foregroundColor));
        removeServicePanel.add(createTextField(removeServiceIdField, componentBackgroundColor, foregroundColor));
        removeServicePanel.add(removeServiceButton);

        JPanel getServicePanel = new JPanel(new GridLayout(0, 2));
        getServicePanel.setBorder(BorderFactory.createTitledBorder("Get Service Details"));
        getServicePanel.setBackground(getServiceDetailsColor);
        getServicePanel.setForeground(foregroundColor);
        getServicePanel.add(createLabel("Service ID:", foregroundColor));
        getServicePanel.add(createTextField(getServiceIdField, componentBackgroundColor, foregroundColor));
        getServicePanel.add(getServiceDetailsButton);
        JScrollPane serviceDetailsScrollPane = new JScrollPane(serviceDetailsArea);
        getServicePanel.add(serviceDetailsScrollPane);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(addServicePanel);
        mainPanel.add(updateServicePanel);
        mainPanel.add(removeServicePanel);
        mainPanel.add(getServicePanel);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(mainPanel), BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setVisible(true);
    }

    private JLabel createLabel(String text, Color foregroundColor) {
        JLabel label = new JLabel(text);
        label.setForeground(foregroundColor);
        return label;
    }

    private JTextField createTextField(JTextField textField, Color backgroundColor, Color foregroundColor) {
        textField.setBackground(backgroundColor);
        textField.setForeground(foregroundColor);
        return textField;
    }

    public String getServiceName() {
        return nameField.getText().trim();
    }

    public double getServicePrice() {
        return Double.parseDouble(priceField.getText().trim());
    }

    public void addAddServiceListener(ActionListener listener) {
        addServiceButton.addActionListener(listener);
    }

    public int getUpdateServiceId() throws NumberFormatException {
        return Integer.parseInt(updateServiceIdField.getText().trim());
    }

    public String getUpdateName() {
        return updateNameField.getText().trim();
    }

    public double getUpdatePrice() {
        return Double.parseDouble(updatePriceField.getText().trim());
    }

    public void addUpdateServiceListener(ActionListener listener) {
        updateServiceButton.addActionListener(listener);
    }

    public int getRemoveServiceId() throws NumberFormatException {
        return Integer.parseInt(removeServiceIdField.getText().trim());
    }

    public void addRemoveServiceListener(ActionListener listener) {
        removeServiceButton.addActionListener(listener);
    }

    public int getServiceId() throws NumberFormatException {
        return Integer.parseInt(getServiceIdField.getText().trim());
    }

    public void addGetServiceDetailsListener(ActionListener listener) {
        getServiceDetailsButton.addActionListener(listener);
    }

    public void setServiceDetails(String details) {
        serviceDetailsArea.setText(details);
    }
}
