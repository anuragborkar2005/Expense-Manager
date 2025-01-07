package ExpenseManager;

import java.io.IOException;
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
            System.out.println("2.Save and Exit");
            System.out.println("3.Generate Report");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    saveAndExit();
                    break;
                case 3:
                    generateReport();
                    break;
                default:
                    System.out.println("Invalid Choice! Try again");
                    break;
            }
        }
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
        } catch (IOException e) {
            System.out.println("Expenses saved successfully");
            e.printStackTrace();
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