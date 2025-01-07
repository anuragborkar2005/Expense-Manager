package ExpenseManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseManager {
    public static List<Expense> expenses = new ArrayList<Expense>() ;
    private static  final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try{
            expenses = FileHandler.loadExpenses();
        }
        catch (IOException e){
            System.out.println("No existing expenses. Create new");
        }
        while(true) {
            System.out.println("-----------------------------------------Personal Expense Manager----------------------------------");
            System.out.println("1.Add Expenses");
            System.out.println("2.View Expenses");
            System.out.println("3.Generate Report");
            System.out.println("4.Backup Data");
            System.out.println("5.Restore Data");
            System.out.println("6.Save and Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 4:
                    generateBackup();
                    break;
                case 5:
                    restoreBackup();
                    break;
                case 3:
                    generateReport();
                    break;
                case 6:
                    saveAndExit();
                    break;
                default:
                    System.out.println("Invalid Choice! Try again");
                    break;
            }
        }
    }

    private static void restoreBackup() {
        try(BufferedReader reader = new BufferedReader(new FileReader("backup_expenses.txt"));BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Backup restored successfully");
        }
        catch (IOException e){
            System.out.println("Backup could not be restored");
        }
    }

    private static void generateBackup(){
        try(BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"));BufferedWriter writer = new BufferedWriter(new FileWriter("backup_expenses.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Backup generated successfully");
        }
        catch (IOException e){
            System.out.println("Backup could not be generated");
        }
    }

    private static void viewExpenses() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-50s %-10s\n","Date", "Category", "Description","Amount");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for(Expense expense: expenses){
            System.out.printf("%-20s %-20s %-50s %-10.2f\n",expense.date(), expense.category(), expense.description(),expense.amount());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    private static void addExpense()  {
        System.out.print("Enter Date(YYYY-MM-DD): ");
        String date = input.nextLine();
        System.out.print("Enter Category(e.g., Food, Travel..): ");
        String category = input.nextLine();
        System.out.print("Enter Description: ");
        String description = input.nextLine();
        System.out.print("Enter Amount: ");
        double amount = input.nextDouble();
        expenses.add(new Expense(date, category, description, amount));
        System.out.println("Expense added successfully!");
    }

    public static  void saveAndExit(){
        try {
            FileHandler.saveExpenses(expenses);
            System.out.println("Expenses saved successfully");
        } catch (IOException e) {
            System.out.println("Expenses could not be saved");
        }
        System.exit(0);
    }

    public static  void generateReport(){
        System.out.print("Enter Category for Report(or All): ");
        String category = input.nextLine();
        double total = 0;
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-50s %-10s\n","Date", "Category", "Description","Amount");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for(Expense expense: expenses){
            if(category.equalsIgnoreCase("All") || category.equalsIgnoreCase(expense.category())){
                System.out.printf("%-20s %-20s %-50s %-10.2f\n",expense.date(), expense.category(), expense.description(),expense.amount());
                total += expense.amount();
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.println("Total Expenses: " + total);
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Thank you for using Expense Manager!");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
}