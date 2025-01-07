package ExpenseManager;

import java.io.*;
import java.util.ArrayList;
import ExpenseManager.Expense;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "expenses.txt";

    public static void saveExpenses(List<Expense> expenses) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.write(expense.toString());
                writer.newLine();
            }
        }
    }

    public static  List<Expense> loadExpenses() throws  IOException {
        List<Expense> expenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String date = parts[0];
                String category = parts[1];
                String description = parts[2];
                double amount = Double.parseDouble(parts[3]);
                expenses.add(new Expense(date, category, description, amount));
            }


        }
        return expenses;
    }
}
