package Hotel_view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class BillingView extends JFrame {
    private JTextField invoiceIdField;
    private JTextField customerNameField;
    private JTextField amountDueField;
    private JTextField dateField;
    private JButton generateInvoiceButton;

    private JTextField expenseIdField;
    private JTextField expenseAmountField;
    private JTextField expenseDateField;
    private JTextField expenseCategoryField;
    private JButton generateExpenseButton;

    private JTextField paymentInvoiceIdField;
    private JTextField paymentMethodField;
    private JButton processPaymentButton;

    private JTextField reportYearField;
    private JButton generateReportButton;

    private JButton trackPaymentsButton;

    private JTextArea invoiceAndExpenseDetailsArea;
    private JTextArea outstandingPaymentsArea;
    private JTextArea financialReportArea;

    public BillingView() {
        setTitle("Billing Management");
        setSize(800, 600);
        setLayout(new GridLayout(7, 1)); 

        JPanel invoicePanel = createPanel("Generate Invoice", new Color(173, 216, 230));
        invoicePanel.add(new JLabel("Invoice ID:"));
        invoiceIdField = new JTextField();
        invoicePanel.add(invoiceIdField);
        invoicePanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField();
        invoicePanel.add(customerNameField);
        invoicePanel.add(new JLabel("Amount Due:"));
        amountDueField = new JTextField();
        invoicePanel.add(amountDueField);
        invoicePanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        invoicePanel.add(dateField);
        generateInvoiceButton = new JButton("Generate Invoice");
        invoicePanel.add(generateInvoiceButton);
        add(invoicePanel);

        JPanel expensePanel = createPanel("Generate Expense", new Color(144, 238, 144));
        expensePanel.add(new JLabel("Expense ID:"));
        expenseIdField = new JTextField();
        expensePanel.add(expenseIdField);
        expensePanel.add(new JLabel("Amount:"));
        expenseAmountField = new JTextField();
        expensePanel.add(expenseAmountField);
        expensePanel.add(new JLabel("Date (YYYY-MM-DD):"));
        expenseDateField = new JTextField();
        expensePanel.add(expenseDateField);
        expensePanel.add(new JLabel("Category:"));
        expenseCategoryField = new JTextField();
        expensePanel.add(expenseCategoryField);
        generateExpenseButton = new JButton("Generate Expense");
        expensePanel.add(generateExpenseButton);
        add(expensePanel);

        JPanel paymentPanel = createPanel("Process Payment", new Color(255, 182, 193));
        paymentPanel.add(new JLabel("Invoice ID:"));
        paymentInvoiceIdField = new JTextField();
        paymentPanel.add(paymentInvoiceIdField);
        paymentPanel.add(new JLabel("Payment Method:"));
        paymentMethodField = new JTextField();
        paymentPanel.add(paymentMethodField);
        processPaymentButton = new JButton("Process Payment");
        paymentPanel.add(processPaymentButton);
        add(paymentPanel);

        JPanel reportPanel = createPanel("Generate Report", new Color(255, 160, 122));
        reportPanel.add(new JLabel("Year:"));
        reportYearField = new JTextField();
        reportPanel.add(reportYearField);
        generateReportButton = new JButton("Generate Report");
        reportPanel.add(generateReportButton);
        add(reportPanel);

        JPanel trackPaymentsPanel = createPanel("Track Outstanding Payments", new Color(238, 232, 170));
        trackPaymentsButton = new JButton("Track Outstanding Payments");
        trackPaymentsPanel.add(trackPaymentsButton);
        add(trackPaymentsPanel);

        invoiceAndExpenseDetailsArea = new JTextArea();
        invoiceAndExpenseDetailsArea.setBorder(createTitledBorder("Invoices and Expenses"));
        add(new JScrollPane(invoiceAndExpenseDetailsArea));

        outstandingPaymentsArea = new JTextArea();
        outstandingPaymentsArea.setBorder(createTitledBorder("Outstanding Payments"));
        add(new JScrollPane(outstandingPaymentsArea));

        financialReportArea = new JTextArea();
        financialReportArea.setBorder(createTitledBorder("Financial Report"));
        add(new JScrollPane(financialReportArea));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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

    public int getInvoiceId() {
        return Integer.parseInt(invoiceIdField.getText().trim());
    }

    public String getCustomerName() {
        return customerNameField.getText().trim();
    }

    public double getAmountDue() {
        return Double.parseDouble(amountDueField.getText().trim());
    }

    public String getDate() {
        return dateField.getText().trim();
    }

    public int getExpenseId() {
        return Integer.parseInt(expenseIdField.getText().trim());
    }

    public double getExpenseAmount() {
        return Double.parseDouble(expenseAmountField.getText().trim());
    }

    public String getExpenseDate() {
        return expenseDateField.getText().trim();
    }

    public String getExpenseCategory() {
        return expenseCategoryField.getText().trim();
    }

    public int getPaymentInvoiceId() {
        return Integer.parseInt(paymentInvoiceIdField.getText().trim());
    }

    public String getPaymentMethod() {
        return paymentMethodField.getText().trim();
    }

    public int getReportYear() {
        return Integer.parseInt(reportYearField.getText().trim());
    }

    public void addGenerateInvoiceListener(ActionListener listener) {
        generateInvoiceButton.addActionListener(listener);
    }

    public void addGenerateExpenseListener(ActionListener listener) {
        generateExpenseButton.addActionListener(listener);
    }

    public void addProcessPaymentListener(ActionListener listener) {
        processPaymentButton.addActionListener(listener);
    }

    public void addTrackPaymentsListener(ActionListener listener) {
        trackPaymentsButton.addActionListener(listener);
    }

    public void addGenerateReportListener(ActionListener listener) {
        generateReportButton.addActionListener(listener);
    }

    public void updateInvoiceAndExpenseDetails(String details) {
        invoiceAndExpenseDetailsArea.setText(details);
    }

    public void updateOutstandingPayments(List<String> outstandingPayments) {
        StringBuilder details = new StringBuilder();
        for (String payment : outstandingPayments) {
            details.append(payment).append("\n");
        }
        outstandingPaymentsArea.setText(details.toString());
    }

    public void updateFinancialReport(String report) {
        financialReportArea.setText(report);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}