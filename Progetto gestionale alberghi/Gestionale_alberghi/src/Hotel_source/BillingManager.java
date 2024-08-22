package Hotel_source;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BillingManager {
    private List<Invoice> invoices;
    private List<Expense> expenses;
    private static final String INVOICE_FILE = "invoices.txt";
    private static final String EXPENSE_FILE = "expenses.txt";

    public BillingManager() {
        this.invoices = new ArrayList<>();
        this.expenses = new ArrayList<>();
        loadInvoicesFromFile();
        loadExpensesFromFile();
    }

    public void generateInvoice(int invoiceId, Customer customer, double amountDue, LocalDate date) {
        Invoice invoice = new Invoice(invoiceId, customer, amountDue, date);
        invoices.add(invoice);
        saveInvoicesToFile();
    }

    public List<Invoice> getInvoices(){
        return this.invoices;
    }

    public List<Expense> getExpenses(){
        return this.expenses;
    }

    public void generateExpense(int expenseId, double amount, LocalDate date, ExpenseCategory category) {
        Expense expense = new Expense(expenseId, amount, date, category);
        expenses.add(expense);
        saveExpensesToFile();
    }

    public void processPayment(int invoiceId, PaymentMethod method) {
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId() == invoiceId) {
                invoice.setPaymentMethod(method);
                invoice.setPaid(true);
                saveInvoicesToFile();
                return;
            }
        }
    }

    public List<Invoice> trackOutstandingPayments() {
        List<Invoice> outstandingPayments = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (!invoice.isPaid()) {
                outstandingPayments.add(invoice);
            }
        }
        return outstandingPayments;
    }

    public FinancialReport generateAnnualFinancialReport(int year) {
        double totalIncome = 0;
        double totalExpenses = 0;

        for (Invoice invoice : invoices) {
            if (invoice.getDate().getYear() == year && invoice.isPaid()) {
                totalIncome += invoice.getAmountDue();
            }
        }

        for (Expense expense : expenses) {
            if (expense.getDate().getYear() == year) {
                totalExpenses += expense.getAmount();
            }
        }

        return new FinancialReport(year, totalIncome, totalExpenses);
    }

    private void loadInvoicesFromFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(INVOICE_FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Reading line: " + line);  
            String[] parts = line.split(",");
            if (parts.length >= 5) {  
                int invoiceId = Integer.parseInt(parts[0]);
                String customerName = parts[1];
                double amountDue = Double.parseDouble(parts[2]);
                LocalDate date = LocalDate.parse(parts[3]);
                boolean isPaid = Boolean.parseBoolean(parts[4]);
                PaymentMethod method = null;
                if (parts.length == 6 && !parts[5].isEmpty()) {
                    method = PaymentMethod.valueOf(parts[5].toUpperCase());
                }
                Customer customer = new Customer(customerName); 
                Invoice invoice = new Invoice(invoiceId, customer, amountDue, date);
                invoice.setPaid(isPaid);
                invoice.setPaymentMethod(method);
                invoices.add(invoice);
                System.out.println("Loaded invoice: " + invoice);  
            }
        }
    } catch (IOException e) {
        System.err.println("Error loading invoices from file: " + e.getMessage());
    } catch (DateTimeParseException | IllegalArgumentException e) {
        System.err.println("Error parsing invoice data: " + e.getMessage());
    }
}

    

    private void saveInvoicesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INVOICE_FILE))) {
            for (Invoice invoice : invoices) {
                bw.write(invoice.getInvoiceId() + "," + invoice.getCustomer().getName() + "," +
                        invoice.getAmountDue() + "," + invoice.getDate() + "," +
                        invoice.isPaid() + "," + (invoice.getPaymentMethod() != null ? invoice.getPaymentMethod().toString() : ""));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving invoices to file: " + e.getMessage());
        }
    }

    private void loadExpensesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(EXPENSE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int expenseId = Integer.parseInt(parts[0]);
                    double amount = Double.parseDouble(parts[1]);
                    LocalDate date = LocalDate.parse(parts[2]);
                    ExpenseCategory category = ExpenseCategory.valueOf(parts[3]);
                    expenses.add(new Expense(expenseId, amount, date, category));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading expenses from file: " + e.getMessage());
        }
    }

    private void saveExpensesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EXPENSE_FILE))) {
            for (Expense expense : expenses) {
                bw.write(expense.getExpenseId() + "," + expense.getAmount() + "," +
                        expense.getDate() + "," + expense.getCategory());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving expenses to file: " + e.getMessage());
        }
    }
}

