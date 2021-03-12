/*
    CSC 205: 37534 (Tues 9AM-12:30PM)
    Program 1: Company Employee Tracking System
    Author(s): Vivien Frances Pabuna (viv2163452)
    Description: This is the Hourly interface that the AdministrativeAssistant class implements.
*/

// our of the 3 methods, only getHoursWorking returns anything (int value)
public interface Hourly {
    public int getHoursWorking();
    public void changeHoursWorking(int hoursToSet);
    public void giveRaise(double amountToIncrease);
}
