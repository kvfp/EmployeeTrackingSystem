/*
    Description: This is the Hourly interface that the AdministrativeAssistant class implements.
*/

// our of the 3 methods, only getHoursWorking returns anything (int value)
public interface Hourly {
    public int getHoursWorking();
    public void changeHoursWorking(int hoursToSet);
    public void giveRaise(double amountToIncrease);
}
