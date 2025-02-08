package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HourlyEmployee implements IEmployee {
  private String name;
  private String id;
  private double hourlySalary;
  private double normalHours;
  private double extraHours;
  private final double MAX_SAL = 50.00;

  public HourlyEmployee(String name, String id, double hourlySalary, double normalHours) {
    if (name == "" || name == null || id == "" || id == null) {
      throw new IllegalArgumentException("name and id cannot be empty!");
    }
    if (normalHours < 0 || normalHours > 80) {
      throw new IllegalArgumentException("Working hours cannot less than 0 or greater than 80!");
    }
    if (hourlySalary < 0 || hourlySalary > MAX_SAL) {
      throw new IllegalArgumentException("Hourly Salary cannot less than 0 or greater than Maximum!");
    }

    this.name = name;
    this.id = id;
    this.hourlySalary = hourlySalary;
    this.normalHours = normalHours;
    this.extraHours = 0;
  }


  /**
   * copy constructor.
   * @param other people
   */
  public HourlyEmployee(HourlyEmployee other) {
    this.name = other.name;
    this.id = other.id;
    this.hourlySalary = other.hourlySalary;
    this.normalHours = other.normalHours;
    this.extraHours = other.extraHours;
  }


  /**
   * round to two decimal number.
   * @param value double
   * @return double with 2 decimal number.
   */
  public static double round(double value) {
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }




  /**
   * @return the employee's pay for the given period.
   * Hourly employees are paid weekly based on the number of hours worked.
   * Salaried employees are paid monthly, with a given paycheck of 1/12 their yearly salary
   */
  @Override
  public double getPayForThisPeriod() {
    if (this.extraHours == 0) {
      double result = getBaseSalary() * this.normalHours;
      return round(result);
    }
    else {
      double base = getBaseSalary() * 40;
      double result = this.extraHours * 1.5 * getBaseSalary() + base;
      this.extraHours = 0;
      return round(result);
    }
  }

  /**
   * @return the employee's base salary.
   * Hourly employees answer their hourly rate.
   * Salaried employees answer their yearly salary
   */
  @Override
  public double getBaseSalary() {
    return round(this.hourlySalary);
  }

  /**
   * @param raisePercent raises the employee's base salary from 0% (minimum) to 10% maximum.
   *                     The parameter is a value between 0.0 and 10.0. This method converts that value to a decimal value
   *                     0 - 0.10 when required for calculations.
   */
  @Override
  public void giveRaiseByPercent(double raisePercent) {
    if (raisePercent < 0 || raisePercent > 10) {
      throw new IllegalArgumentException("The raise percent should be greater than 0 and less than 10!");
    }
    raisePercent = raisePercent / 100;
    if ((1 + raisePercent) * getBaseSalary() <= MAX_SAL) {
      this.hourlySalary = (1 + raisePercent) * getBaseSalary();
    }

  }

  /**
   * @return Returns employee ID.
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * @return Returns employee name.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * This method allows HourlyEmployees to supersede the number of hours worked for the week.
   * @param hours new weekly hours
   */
  public void setSpecialHours(double hours) {
    if (hours < 0 || hours > 80) {
      throw new IllegalArgumentException("Working hours cannot less than 0 or greater than 80!");
    }
    if (hours > 40) {
      hours -= 40;
      this.extraHours = hours;
    }

  }

  /**
   * print string
   * @return string
   */
  @Override
  public String toString() {
    return "Name: " + getName() + "/n"
            + "ID: " + getID() + "/n"
            + "Base Salary: $" + getBaseSalary();
  }

  /**
   * test two people are equal by name and id.
   * @param o Employee
   * @return ture or false
   */
  @Override
  public boolean equals(Object o) {
    if (this ==  o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HourlyEmployee person = (HourlyEmployee) o;
    return getName().equals(person.name) && getID().equals(person.id);
  }

}
