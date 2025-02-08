package student;

/**
 * An interface representing the concept of Employees.
 */
public interface IEmployee {

  // NOTES (you may remove these notes after you understand the assignment if they cause checkStyle issues):
  // The max HOURLY base salary is $50.00 (per hour).
  // The max Salaried base salary is $1 million per year.
  // HourlyEmployees earn 1.5x base salary for any time worked > 40 hours in a period.
  // SalariedEmployees are paid monthly. For one pay period their salary should be baseSalary/12


  /**
   * @return the employee's pay for the given period.
   * Hourly employees are paid weekly based on the number of hours worked.
   * Salaried employees are paid monthly, with a given paycheck of 1/12 their yearly salary
   *
   */
  double getPayForThisPeriod();

  /**
   * @return the employee's base salary.
   * Hourly employees answer their hourly rate.
   * Salaried employees answer their yearly salary
   */
  double getBaseSalary();

  /**
   * @param raisePercent raises the employee's base salary from 0% (minimum) to 10% maximum.
   * The parameter is a value between 0.0 and 10.0. This method converts that value to a decimal value
   * 0 - 0.10 when required for calculations.
   */
  void giveRaiseByPercent(double raisePercent);

  /**
   * @return Returns employee ID.
   */
  String getID();

  /**
   * @return Returns employee name.
   */
  String getName();
}