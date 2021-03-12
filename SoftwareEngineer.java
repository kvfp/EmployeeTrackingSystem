/*
    CSC 205: 37534 (Tues 9AM-12:30PM)
    Program 1: Company Employee Tracking System
    Author(s): Vivien Frances Pabuna (viv2163452)
    Description: This is a subclass of the Employee class. 
    It overrides some methods and declares some to provide appropriate functionality for non-hourly (salary) workers.
*/

public class SoftwareEngineer extends Employee {
    
    // constructor w/ 2 values
    public SoftwareEngineer(String nameToSet, double salaryToSet) {
        cash = 0.0;
        name = nameToSet;
        salary = salaryToSet;
    }
    
    // implements giveRaise method of the abstract Employee class
    // increases current salary by a percentage
    public void giveRaise(double percentage) {
        double salaryIncrease = salary * (percentage/100);
        salary = salary + salaryIncrease;
    }

}
