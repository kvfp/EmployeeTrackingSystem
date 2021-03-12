/*
    CSC 205: 37534 (Tues 9AM-12:30PM)
    Program 1: Company Employee Tracking System
    Author(s): Vivien Frances Pabuna (viv2163452)
    Description: This is an abstract class that the SoftwareEngineer and AdministrativeAssistant classes inherit from.
*/

public abstract class Employee {
    
    // data
    protected String name;
    protected double salary;
    protected double cash;

    // although only the AdministrativeAssistant class should have this variable, I use it to help distinguish the two
    // only the SoftwareEngineer class will have weeklyHours = -1 in the end
    protected int weeklyHours = -1; 

    // methods for basic returning
    String getName() {
        return name;
    }

    double getCash() {
        return cash;
    }

    double getSalary() {
        return salary;
    }

    // pay is bi-weekly, paid a total of 26 times a year => salary
    // the AdministrativeAssistant class will override this, however
    void getPaid() {
        double cashIncrease = (salary/26.0);
        cash = cash + cashIncrease;
    }

    // as an abstract method, this will be implemented in the two subclasses
    public abstract void giveRaise(double percentage);

}
