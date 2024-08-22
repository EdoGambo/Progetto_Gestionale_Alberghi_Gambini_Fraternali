package Hotel_view;

import javax.swing.*;

import Hotel_source.Skill;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EmployeeView extends JFrame {
    private JTextField nameField = new JTextField(20);
    private JTextField salaryField = new JTextField(20);
    private JComboBox<Skill> skillComboBox = new JComboBox<>(Skill.values());
    private JButton addEmployeeButton = new JButton("Add Employee");

    private JTextField employeeIdField = new JTextField(20);
    private JButton getEmployeeDetailsButton = new JButton("Get Employee Details");
    private JTextArea employeeDetailsArea = new JTextArea(10, 30);

    private JTextField updateEmployeeIdField = new JTextField(20);
    private JTextField updateNameField = new JTextField(20);
    private JTextField updateSalaryField = new JTextField(20);
    private JComboBox<Skill> updateSkillComboBox = new JComboBox<>(Skill.values());
    private JButton updateEmployeeDetailsButton = new JButton("Update Employee Details");

    private JTextField removeEmployeeIdField = new JTextField(20);
    private JButton removeEmployeeButton = new JButton("Remove Employee");

    private JTextField attendanceEmployeeIdField = new JTextField(20);
    private JTextField attendanceDateField = new JTextField(20);
    private JCheckBox isPresentCheckBox = new JCheckBox("Is Present");
    private JButton trackAttendanceButton = new JButton("Track Attendance");

    public EmployeeView() {
        Color addEmployeeColor = new Color(173, 216, 230); 
        Color getEmployeeDetailsColor = new Color(144, 238, 144); 
        Color updateEmployeeDetailsColor = new Color(255, 182, 193); 
        Color removeEmployeeColor = new Color(255, 228, 181); 
        Color trackAttendanceColor = new Color(221, 160, 221);

        Color componentBackgroundColor = new Color(240, 248, 255); 
        Color foregroundColor = Color.BLACK;

        JPanel addEmployeePanel = new JPanel(new GridLayout(0, 2));
        addEmployeePanel.setBorder(BorderFactory.createTitledBorder("Add Employee"));
        addEmployeePanel.setBackground(addEmployeeColor);
        addEmployeePanel.setForeground(foregroundColor);

        addEmployeePanel.add(createLabel("Name:", foregroundColor));
        addEmployeePanel.add(createTextField(nameField, componentBackgroundColor, foregroundColor));
        addEmployeePanel.add(createLabel("Salary:", foregroundColor));
        addEmployeePanel.add(createTextField(salaryField, componentBackgroundColor, foregroundColor));
        addEmployeePanel.add(createLabel("Skill:", foregroundColor));
        addEmployeePanel.add(skillComboBox);
        addEmployeePanel.add(addEmployeeButton);

        JPanel getEmployeeDetailsPanel = new JPanel(new GridLayout(0, 2));
        getEmployeeDetailsPanel.setBorder(BorderFactory.createTitledBorder("Get Employee Details"));
        getEmployeeDetailsPanel.setBackground(getEmployeeDetailsColor);
        getEmployeeDetailsPanel.setForeground(foregroundColor);

        getEmployeeDetailsPanel.add(createLabel("Employee ID:", foregroundColor));
        getEmployeeDetailsPanel.add(createTextField(employeeIdField, componentBackgroundColor, foregroundColor));
        getEmployeeDetailsPanel.add(getEmployeeDetailsButton);
        JScrollPane employeeDetailsScrollPane = new JScrollPane(employeeDetailsArea);
        getEmployeeDetailsPanel.add(employeeDetailsScrollPane);

        JPanel updateEmployeeDetailsPanel = new JPanel(new GridLayout(0, 2));
        updateEmployeeDetailsPanel.setBorder(BorderFactory.createTitledBorder("Update Employee Details"));
        updateEmployeeDetailsPanel.setBackground(updateEmployeeDetailsColor);
        updateEmployeeDetailsPanel.setForeground(foregroundColor);

        updateEmployeeDetailsPanel.add(createLabel("Employee ID:", foregroundColor));
        updateEmployeeDetailsPanel.add(createTextField(updateEmployeeIdField, componentBackgroundColor, foregroundColor));
        updateEmployeeDetailsPanel.add(createLabel("Name:", foregroundColor));
        updateEmployeeDetailsPanel.add(createTextField(updateNameField, componentBackgroundColor, foregroundColor));
        updateEmployeeDetailsPanel.add(createLabel("Skill:", foregroundColor));
        updateEmployeeDetailsPanel.add(updateSkillComboBox);
        updateEmployeeDetailsPanel.add(createLabel("Salary:", foregroundColor));
        updateEmployeeDetailsPanel.add(createTextField(updateSalaryField, componentBackgroundColor, foregroundColor));
        updateEmployeeDetailsPanel.add(updateEmployeeDetailsButton);

        JPanel removeEmployeePanel = new JPanel(new GridLayout(0, 2));
        removeEmployeePanel.setBorder(BorderFactory.createTitledBorder("Remove Employee"));
        removeEmployeePanel.setBackground(removeEmployeeColor);
        removeEmployeePanel.setForeground(foregroundColor);

        removeEmployeePanel.add(createLabel("Employee ID:", foregroundColor));
        removeEmployeePanel.add(createTextField(removeEmployeeIdField, componentBackgroundColor, foregroundColor));
        removeEmployeePanel.add(removeEmployeeButton);

        JPanel trackAttendancePanel = new JPanel(new GridLayout(0, 2));
        trackAttendancePanel.setBorder(BorderFactory.createTitledBorder("Track Attendance"));
        trackAttendancePanel.setBackground(trackAttendanceColor);
        trackAttendancePanel.setForeground(foregroundColor);

        trackAttendancePanel.add(createLabel("Employee ID:", foregroundColor));
        trackAttendancePanel.add(createTextField(attendanceEmployeeIdField, componentBackgroundColor, foregroundColor));
        trackAttendancePanel.add(createLabel("Date (YYYY-MM-DD):", foregroundColor));
        trackAttendancePanel.add(createTextField(attendanceDateField, componentBackgroundColor, foregroundColor));
        trackAttendancePanel.add(createLabel("Is Present:", foregroundColor));
        trackAttendancePanel.add(isPresentCheckBox);
        trackAttendancePanel.add(trackAttendanceButton);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(addEmployeePanel);
        mainPanel.add(getEmployeeDetailsPanel);
        mainPanel.add(updateEmployeeDetailsPanel);
        mainPanel.add(removeEmployeePanel);
        mainPanel.add(trackAttendancePanel);

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

    public String getName() {
        return nameField.getText().trim();
    }

    public double getSalary() throws NumberFormatException {
        String text = salaryField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Salary field is empty");
        }
        return Double.parseDouble(text);
    }

    public Skill getSkill() {
        return (Skill) skillComboBox.getSelectedItem();
    }

    public void addAddEmployeeListener(ActionListener listener) {
        addEmployeeButton.addActionListener(listener);
    }

    public int getEmployeeId() throws NumberFormatException {
        String text = employeeIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Employee ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public void addGetEmployeeDetailsListener(ActionListener listener) {
        getEmployeeDetailsButton.addActionListener(listener);
    }

    public void setEmployeeDetails(String details) {
        employeeDetailsArea.setText(details);
    }

    public int getUpdateEmployeeId() throws NumberFormatException {
        String text = updateEmployeeIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Employee ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public String getUpdateName() {
        return updateNameField.getText().trim();
    }

    public double getUpdateSalary() throws NumberFormatException {
        String text = updateSalaryField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Salary field is empty");
        }
        return Double.parseDouble(text);
    }

    public Skill getUpdateSkill() {
        return (Skill) updateSkillComboBox.getSelectedItem();
    }

    public void addUpdateEmployeeDetailsListener(ActionListener listener) {
        updateEmployeeDetailsButton.addActionListener(listener);
    }

    public int getRemoveEmployeeId() throws NumberFormatException {
        String text = removeEmployeeIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Employee ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public void addRemoveEmployeeListener(ActionListener listener) {
        removeEmployeeButton.addActionListener(listener);
    }

    public int getAttendanceEmployeeId() throws NumberFormatException {
        String text = attendanceEmployeeIdField.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("Employee ID field is empty");
        }
        return Integer.parseInt(text);
    }

    public LocalDate getAttendanceDate() {
        return LocalDate.parse(attendanceDateField.getText().trim());
    }

    public boolean getIsPresent() {
        return isPresentCheckBox.isSelected();
    }

    public void addTrackAttendanceListener(ActionListener listener) {
        trackAttendanceButton.addActionListener(listener);
    }
}
