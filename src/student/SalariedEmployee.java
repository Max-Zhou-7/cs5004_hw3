package student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class SalariedEmployee implements IEmployee {
  private final double MAX_SAL = 1000000.00;
  private String name;
  private String id;
  private double yearlySalary;

  public SalariedEmployee(String name, String id, double yearlySalary) {
    if (name == "" || name == null || id == "" || id == null) {
      throw new IllegalArgumentException("name and id cannot be empty!");
    }
    if (yearlySalary < 0 || yearlySalary > MAX_SAL) {
      throw new IllegalArgumentException("Yearly salary cannot less than 0 or greater than maximum!");
    }
    this.name = name;
    this.id = id;
    this.yearlySalary = yearlySalary;

  }

  /**
   * copy constructor.
   * @param other people
   */
  public SalariedEmployee(SalariedEmployee other) {
    this.name = other.name;
    this.id = other.id;
    this.yearlySalary = other.yearlySalary;
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
   // DecimalFormat dollarFormat = new DecimalFormat(0.00);
    double amount = getBaseSalary() / 12;
    return round(amount);
  }

  /**
   * @return the employee's base salary.
   * Hourly employees answer their hourly rate.
   * Salaried employees answer their yearly salary
   */
  @Override
  public double getBaseSalary() {
    return round(this.yearlySalary);
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
      this.yearlySalary = (1 + raisePercent) * getBaseSalary();
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
   * print string
   * @return string
   */
  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("0.00");
    return "Name: " + getName() + "\n"
            + "ID: " + getID() + "\n"
            + "Base Salary: $" + df.format(getBaseSalary());
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
    SalariedEmployee person = (SalariedEmployee) o;
    return getName().equals(person.name) && getID().equals(person.id);
    }

  /**
   * hashcode.
   * @return hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, id);
  }

}




