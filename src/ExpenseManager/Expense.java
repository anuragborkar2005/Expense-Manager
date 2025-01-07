package ExpenseManager;

import java.io.Serializable;

public class Expense implements Serializable {
    private final String date;
    private final String category;
    private final String description;
    private final double amount;

    public Expense(String date, String category, String description, double amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }
    public String date() {
        return date;
    }

    public String category() {
        return category;
    }
    public String description() {
        return description;
    }

    public double amount() {
        return amount;
    }


    public String toString() {
        return date + "|" + category + "|" + description + "|" + amount;
    }

}
