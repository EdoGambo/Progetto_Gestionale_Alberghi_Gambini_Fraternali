package Hotel_controller;

import Hotel_source.BillingManager;
import Hotel_source.Customer;
import Hotel_source.Expense;
import Hotel_source.ExpenseCategory;
import Hotel_source.FinancialReport;
import Hotel_source.Invoice;
import Hotel_source.PaymentMethod;
import Hotel_view.BillingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BillingController {
    private BillingManager billingManager;
    private BillingView billingView;

    public BillingController(BillingManager billingManager, BillingView billingView) {
        this.billingManager = billingManager;
        this.billingView = billingView;

        this.billingView.addGenerateInvoiceListener(new GenerateInvoiceListener());
        this.billingView.addGenerateExpenseListener(new GenerateExpenseListener());
        this.billingView.addProcessPaymentListener(new ProcessPaymentListener());
        this.billingView.addTrackPaymentsListener(new TrackPaymentsListener());
        this.billingView.addGenerateReportListener(new GenerateReportListener());

        displayAllInvoicesAndExpenses();
    }

    class GenerateInvoiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int invoiceId = billingView.getInvoiceId();
                String customerName = billingView.getCustomerName();
                double amountDue = billingView.getAmountDue();
                LocalDate date = LocalDate.parse(billingView.getDate());
                Customer customer = new Customer(customerName); 
                billingManager.generateInvoice(invoiceId, customer, amountDue, date);
                billingView.showMessage("Invoice generated successfully!");
                displayAllInvoicesAndExpenses();
            } catch (NumberFormatException ex) {
                billingView.showMessage("Please enter valid data for Invoice fields.");
            }
        }
    }

    class GenerateExpenseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int expenseId = billingView.getExpenseId();
                double amount = billingView.getExpenseAmount();
                LocalDate date = LocalDate.parse(billingView.getExpenseDate());
                ExpenseCategory category = ExpenseCategory.valueOf(billingView.getExpenseCategory());
                billingManager.generateExpense(expenseId, amount, date, category);
                billingView.showMessage("Expense generated successfully!");
                displayAllInvoicesAndExpenses();
            } catch (NumberFormatException ex) {
                billingView.showMessage("Please enter valid data for Expense fields.");
            } catch (IllegalArgumentException ex) {
                billingView.showMessage("Invalid expense category.");
            }
        }
    }

    class ProcessPaymentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int invoiceId = billingView.getPaymentInvoiceId();
                String paymentMethod = billingView.getPaymentMethod();
                PaymentMethod method = PaymentMethod.valueOf(paymentMethod.toUpperCase());
                billingManager.processPayment(invoiceId, method);
                billingView.showMessage("Payment processed successfully!");
                displayAllInvoicesAndExpenses();
            } catch (NumberFormatException ex) {
                billingView.showMessage("Please enter valid data for Payment fields.");
            } catch (IllegalArgumentException ex) {
                billingView.showMessage("Invalid payment method.");
            }
        }
    }

    class TrackPaymentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> outstandingPayments = billingManager.trackOutstandingPayments()
                    .stream()
                    .map(invoice -> "Invoice ID: " + invoice.getInvoiceId() + ", Amount Due: " + invoice.getAmountDue())
                    .collect(Collectors.toList());
            billingView.updateOutstandingPayments(outstandingPayments);
        }
    }

    class GenerateReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int year = billingView.getReportYear();
                FinancialReport report = billingManager.generateAnnualFinancialReport(year);
                billingView.updateFinancialReport(report.toString());
                billingView.showMessage("Financial report generated successfully!");
            } catch (NumberFormatException ex) {
                billingView.showMessage("Please enter a valid year.");
            }
        }
    }

    private void displayAllInvoicesAndExpenses() {
        StringBuilder details = new StringBuilder("Invoices:\n");
        for (Invoice invoice : billingManager.getInvoices()) {
            details.append("Invoice ID: ").append(invoice.getInvoiceId())
                    .append(", Customer: ").append(invoice.getCustomer().getName())
                    .append(", Amount Due: ").append(invoice.getAmountDue())
                    .append(", Date: ").append(invoice.getDate())
                    .append(", Paid: ").append(invoice.isPaid())
                    .append(", Payment Method: ").append(invoice.getPaymentMethod())
                    .append("\n");
        }
    
        details.append("\nExpenses:\n");
        for (Expense expense : billingManager.getExpenses()) {
            details.append("Expense ID: ").append(expense.getExpenseId())
                    .append(", Amount: ").append(expense.getAmount())
                    .append(", Date: ").append(expense.getDate())
                    .append(", Category: ").append(expense.getCategory())
                    .append("\n");
        }
    
        billingView.updateInvoiceAndExpenseDetails(details.toString());
        System.out.println("Displayed invoices and expenses.");  
    }
    
}

