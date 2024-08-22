package Hotel_view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import Hotel_source.Activity;

public class ActivityView extends JFrame {
    private JTextField nameField = new JTextField(20);
    private JTextField costField = new JTextField(20);
    private JTextArea descriptionArea = new JTextArea(5, 20);
    private JButton createActivityButton = new JButton("Create Activity");

    private JTextField activityIdField = new JTextField(20);
    private JButton getActivityDetailsButton = new JButton("Get Activity Details");
    private JTextArea activityDetailsArea = new JTextArea(10, 30);

    private JTextField updateActivityIdField = new JTextField(20);
    private JTextField updateNameField = new JTextField(20);
    private JTextField updateCostField = new JTextField(20);
    private JTextArea updateDescriptionArea = new JTextArea(5, 20);
    private JButton updateActivityButton = new JButton("Update Activity");

    private JTextField deleteActivityIdField = new JTextField(20);
    private JButton deleteActivityButton = new JButton("Delete Activity");

    public ActivityView() {
        setTitle("Hotel Activity Management");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel createActivityPanel = createPanel("Create Activity", new Color(135, 206, 250));
        createActivityPanel.add(new JLabel("Name:"));
        createActivityPanel.add(nameField);
        createActivityPanel.add(new JLabel("Cost:"));
        createActivityPanel.add(costField);
        createActivityPanel.add(new JLabel("Description:"));
        createActivityPanel.add(new JScrollPane(descriptionArea));
        createActivityPanel.add(createActivityButton);

        JPanel getActivityDetailsPanel = createPanel("Get Activity Details", new Color(144, 238, 144));
        getActivityDetailsPanel.add(new JLabel("Activity ID:"));
        getActivityDetailsPanel.add(activityIdField);
        getActivityDetailsPanel.add(getActivityDetailsButton);
        JScrollPane activityDetailsScrollPane = new JScrollPane(activityDetailsArea);
        activityDetailsArea.setEditable(false);
        getActivityDetailsPanel.add(activityDetailsScrollPane);

        JPanel updateActivityDetailsPanel = createPanel("Update Activity Details", new Color(255, 182, 193));
        updateActivityDetailsPanel.add(new JLabel("Activity ID:"));
        updateActivityDetailsPanel.add(updateActivityIdField);
        updateActivityDetailsPanel.add(new JLabel("Name:"));
        updateActivityDetailsPanel.add(updateNameField);
        updateActivityDetailsPanel.add(new JLabel("Cost:"));
        updateActivityDetailsPanel.add(updateCostField);
        updateActivityDetailsPanel.add(new JLabel("Description:"));
        updateActivityDetailsPanel.add(new JScrollPane(updateDescriptionArea));
        updateActivityDetailsPanel.add(updateActivityButton);

        JPanel deleteActivityPanel = createPanel("Delete Activity", new Color(255, 160, 122));
        deleteActivityPanel.add(new JLabel("Activity ID:"));
        deleteActivityPanel.add(deleteActivityIdField);
        deleteActivityPanel.add(deleteActivityButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(createActivityPanel);
        mainPanel.add(getActivityDetailsPanel);
        mainPanel.add(updateActivityDetailsPanel);
        mainPanel.add(deleteActivityPanel);

        this.add(new JScrollPane(mainPanel), BorderLayout.CENTER);
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

    public double getCost() {
        try {
            return Double.parseDouble(costField.getText().trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Cost must be a valid number.");
        }
    }

    public String getDescription() {
        return descriptionArea.getText().trim();
    }

    public void addCreateActivityListener(ActionListener listener) {
        createActivityButton.addActionListener(listener);
    }

    public int getActivityId() throws NumberFormatException {
        String text = activityIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Activity ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public void addGetActivityDetailsListener(ActionListener listener) {
        getActivityDetailsButton.addActionListener(listener);
    }

    public void setActivityDetails(String details) {
        activityDetailsArea.setText(details);
    }

    public int getUpdateActivityId() throws NumberFormatException {
        String text = updateActivityIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Activity ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public String getUpdateName() {
        return updateNameField.getText().trim();
    }

    public double getUpdateCost() {
        try {
            return Double.parseDouble(updateCostField.getText().trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Cost must be a valid number.");
        }
    }

    public String getUpdateDescription() {
        return updateDescriptionArea.getText().trim();
    }

    public void addUpdateActivityListener(ActionListener listener) {
        updateActivityButton.addActionListener(listener);
    }

    public int getDeleteActivityId() throws NumberFormatException {
        String text = deleteActivityIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Activity ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public void addDeleteActivityListener(ActionListener listener) {
        deleteActivityButton.addActionListener(listener);
    }

    public void displayAllActivities(List<Activity> activities) {
        StringBuilder details = new StringBuilder();
        for (Activity activity : activities) {
            details.append("Activity ID: ").append(activity.getId())
                   .append("\nName: ").append(activity.getName())
                   .append("\nCost: ").append(activity.getCost())
                   .append("\nDescription: ").append(activity.getDescription())
                   .append("\n\n");
        }
        activityDetailsArea.setText(details.toString());
    }
}