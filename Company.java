/*
    Description: This is the primary class for the program. 
    It handles the main menu options and helps the user manage employee additions, payments, and perform various modifications.
    It depends heavily on the other classes within the package and their methods.
*/

import java.util.Scanner;
import java.util.ArrayList;

public class Company {
    // initialize important reference variables
    static Scanner kb;
    static boolean didQuit = false;
    static ArrayList<Employee> allEmployees = new ArrayList<Employee>();
    static ArrayList<AdministrativeAssistant> hourlyEmployees = new ArrayList<AdministrativeAssistant>();

    // main method loops the menu system until user quits
    public static void main(String[] args) {
        kb = new Scanner(System.in);
        while (didQuit == false) {
            handleMenu();
        }
    }

    // retrieves menu option from user and delivers appropriate methods
    public static void handleMenu() {

        // prints out menu options
        String[] dialogueSequence = { "What do you want to do?", "A. Add an Employee", "B. List all Employees",
                "C. Give an Employee a Raise", "D. Give Paychecks", "E. Change someones hours", "F. Quit" };
        for (String dialogue : dialogueSequence) {
            System.out.println(dialogue);
        }

        // ensures that the option input type is correct
        boolean recievedValidChar = false;
        char menuChoice = '\0';
        while (recievedValidChar == false) {
            try {
                menuChoice = (kb.nextLine().toLowerCase()).charAt(0);
                if (menuChoice != '\0') {
                    recievedValidChar = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid option");
            }
        }

        // uses the appropriate method based on user selection
        if (menuChoice == "a".charAt(0)) {
            addNewEmployee();
        } else if (menuChoice == "b".charAt(0)) {
            listEmployees();
        } else if (menuChoice == "c".charAt(0)) {
            giveRaise();
        } else if (menuChoice == "d".charAt(0)) {
            givePaychecks();
        } else if (menuChoice == "e".charAt(0)) {
            changeHours();
        } else if (menuChoice == "f".charAt(0)) {
            didQuit = true;
            System.out.println("Bye!");
        } else {
            System.out.println("Invalid option");
        }
    }

    public static void addNewEmployee() {
        if (allEmployees.size() < 5) {

            // asks for employee name
            System.out.println("What is their name?");
            String newName = kb.nextLine();

            // asks for employee salary
            System.out.println("What is their salary (yearly or hourly)?");
            double newSalary = kb.nextDouble();

            // initializes variables to be set by user (new)
            Boolean newHourly = null;
            int newWeeklyHours;
            String hourlyInput;
            kb.nextLine(); // clears kb from double input

            // asks if employee is hourly
            System.out.println("Are they an hourly worker? (Y/N)");
            while (newHourly == null) {
                hourlyInput = kb.nextLine();
                hourlyInput = hourlyInput.toLowerCase();

                // if yes, ask for weekly hours, and create employee (AdministrativeAssistant
                // object)
                // also ensure input is correct
                if (hourlyInput.equals("y")) {
                    newHourly = true;
                    System.out.println("How many hours per week do they work?");
                    newWeeklyHours = kb.nextInt();

                    while (newWeeklyHours <= 0) {
                        System.out.println("Hours per week must be a positive number");
                        newWeeklyHours = kb.nextInt();
                    }

                    kb.nextLine(); // clears kb

                    // creates object and adds to both lists
                    AdministrativeAssistant newEmployee = new AdministrativeAssistant(newName, newSalary,
                            newWeeklyHours);
                    allEmployees.add(newEmployee);
                    hourlyEmployees.add(newEmployee);

                    // if no, create employee (SoftwareEngineer object)
                } else if (hourlyInput.equals("n")) {
                    newHourly = false;
                    SoftwareEngineer newEmployee = new SoftwareEngineer(newName, newSalary);

                    // adds to employees list
                    allEmployees.add(newEmployee);

                } else { // let user know their input is incorrect
                    System.out.println("Invalid input");
                }
            }

            // display success message
            System.out.println(newName + " was hired!");
        } else {
            System.out.println("Maximum employee count reached");
        }
    }

    public static void listEmployees() {
        if (allEmployees.size() > 0) {
            for (Employee e : allEmployees) {
                String role;
                if (e.weeklyHours == -1) {
                    role = "Software Engineer";
                    System.out.println(
                            e.getName() + " " + "Salary: " + e.getSalary() + " Cash: " + e.getCash() + " " + role);
                } else {
                    role = "Administrative Assistant";
                    System.out.println(
                            e.getName() + " " + "Hourly Wage: " + e.getSalary() + " Cash: " + e.getCash() + " " + role);
                }
            }
        } else {
            System.out.println("Nobody works here!");
        }
    }

    // loop through all company employees to find target employee for raise - only
    // if there are any!
    public static void giveRaise() {
        if (allEmployees.size() > 0) {
            System.out.println("Who do you want to give a raise to?");
            Employee raiseRecipient = null;
            String recipientName = "";

            // repeatedly look for employee object using the user-provided name
            while (raiseRecipient == null) {
                recipientName = kb.nextLine();
                for (Employee e : allEmployees) {
                    if (e.getName().equals(recipientName)) {
                        raiseRecipient = e;
                    }
                }
                if (raiseRecipient == null) { // inform user of the need for exact spelling and capitalization
                    System.out.println("Employee not found. Please enter the name exactly");
                }
            }

            // ask for raise detail
            System.out.println("What raise do you want to give them?");
            double raiseAmount = kb.nextDouble();
            kb.nextLine(); // clear kb

            // call giveRaise method of AdministrativeAssistant class
            raiseRecipient.giveRaise(raiseAmount);

            // display success message
            System.out.println(recipientName + " is happy!");
        } else {
            System.out.println("Nobody works here!");
        }
    }

    // loop through all company employees, pay each one
    // only if there are any, though
    public static void givePaychecks() {
        if (allEmployees.size() > 0) {
            for (Employee e : allEmployees) {
                e.getPaid();
            }
            // display success message
            System.out.println("Hooray for money!");
        } else {
            System.out.println("Nobody works here!");
        }
    }

    // change weekly hours of a specific employee, if any hourly employees do exist
    public static void changeHours() {
        if (hourlyEmployees.size() > 0) {

            // find hourly employee object through its name
            System.out.println("Change hours for who?");
            AdministrativeAssistant changeRecipient = null;
            String recipientName = "";
            while (changeRecipient == null) {
                recipientName = kb.nextLine();
                // loop through hourly employees arraylist to find the specific employee
                for (AdministrativeAssistant e : hourlyEmployees) {
                    if (e.getName().equals(recipientName)) {
                        changeRecipient = e;
                        break;
                    }
                }
                // inform user of the need for proper spelling and capitalization of employee
                // name
                if (changeRecipient == null) {
                    System.out.println("Employee not found. Please enter the name exactly");
                }
            }

            // inform user of hourly employee's current hours per week (for reference)
            System.out.println(recipientName + " currently works " + changeRecipient.getHoursWorking()
                    + " hours per week. What would you like to change it to?");
            int newHourCount = -1;

            // insist on correct input for hour count
            while (newHourCount < 0) {
                kb.nextLine();
                try {
                    newHourCount = kb.nextInt();
                    if (newHourCount < 0) {
                        System.out.println("Invalid input");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    newHourCount = -1;
                }
            }

            kb.nextLine(); // clear kb

            // use changeHoursWorking method of AdministrativeAssistant class
            changeRecipient.changeHoursWorking(newHourCount);

            // display welcome message
            System.out.println(recipientName + " will now work " + newHourCount + " hours per week");
        } else {
            System.out.println("No hourly employees work here!");
        }
    }
}
