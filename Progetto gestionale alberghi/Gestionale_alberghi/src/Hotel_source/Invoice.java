package Hotel_source;

import java.time.LocalDate;

public class Invoice {
    private int invoiceId;
    private Customer customer;
    private double amountDue;
    private LocalDate date;
    private boolean paid;
    private PaymentMethod paymentMethod;

    public Invoice(int invoiceId, Customer customer, double amountDue, LocalDate date) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.amountDue = amountDue;
        this.date = date;
        this.paid = false; 
        this.paymentMethod = null; 
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
