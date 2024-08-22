package Hotel_source;

public class FinancialReport {
    private int year;
    private double totalIncome;
    private double totalExpenses;

    public FinancialReport(int year, double totalIncome, double totalExpenses) {
        this.year = year;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
    }

    public int getYear() {
        return year;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getNetProfit() {
        return totalIncome - totalExpenses;
    }

    @Override
    public String toString() {
        return "Year: " + year + "\n" +
               "Total Income: $" + totalIncome + "\n" +
               "Total Expenses: $" + totalExpenses + "\n" +
               "Net Profit: $" + getNetProfit() + "\n";
    }
}
