/*
    CSC 205: 37534 (Tues 9AM-12:30PM)
    Program 1: Company Employee Tracking System
    Author(s): Vivien Frances Pabuna (viv2163452)
    Description: This is a subclass of the Employee class. 
    It overrides some methods and declares some to provide appropriate functionality for hourly workers.
*/

// extends employee and implements hourly
public class AdministrativeAssistant extends Employee implements Hourly {

    // constructor w/ 3 values
    public AdministrativeAssistant(String nameToSet, double salaryToSet, int weeklyHoursToSet){
        name = nameToSet;
        salary = salaryToSet;
        weeklyHours = weeklyHoursToSet;
    }

    // constructor w/ 2 values
    public AdministrativeAssistant(String nameToSet, double salaryToSet){
        name = nameToSet;
        salary = salaryToSet;
    }

    // returns employee's hours per week
    public int getHoursWorking() {
        return weeklyHours;
    }

    // changes hours per week
    public void changeHoursWorking(int hoursToSet) {
        weeklyHours = hoursToSet;
    }

    // implements giveRaise of the abstract Employee class
    // increases current salary by an actual amount (not percentage)
    public void giveRaise(double amountToIncrease) {
        salary = salary + amountToIncrease;
    }

    // overrides getPaid as hourly workers get paid for the hours worked in the last 2 weeks
    void getPaid() {
        double cashIncrease = salary*weeklyHours*2;
        cash = cash + cashIncrease;
    }
}
