package Hotel_source;

import java.time.LocalDate;

public class Expense {
    private int expenseId;
    private double amount;
    private LocalDate date;
    private ExpenseCategory category;

    public Expense(int expenseId, double amount, LocalDate date, ExpenseCategory category) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public int getYearExpense() {
        return date != null ? date.getYear() : -1;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", amount=" + amount +
                ", category=" + category +
                ", date=" + date +
                '}';
    }
}
