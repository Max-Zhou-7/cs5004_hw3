package student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class IEmployeeTest {
  HourlyEmployee snoopyHours = new HourlyEmployee("Snoopy", "111-CHLY-BRWN", 17.50, 20);
  SalariedEmployee lucy = new SalariedEmployee("Lucy", "222-22-2222", 70000.00);
  IEmployee woodStock = new SalariedEmployee("Woodstock", "33-CHIRP", 180000.50);;



  // sample test provided to students
  @Test
  public void testGetErrorWhenCreatingEmployee() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      IEmployee e = new HourlyEmployee(null, null, 15.51, 30);
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      IEmployee e = new HourlyEmployee("Part-timer", "PT-TIME", -1, 30);
    });

  }

  @Test
  public void testGetErrorWhenRaiseMore() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      IEmployee e = new HourlyEmployee("Apple", "1234", 48, 30);
      e.giveRaiseByPercent(-5);
      IEmployee er = new SalariedEmployee("Google", "1111", 953000);
      er.giveRaiseByPercent(-5);
    });
  }

  @Test
  public void testSpecialHoursTime() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
    HourlyEmployee e = new HourlyEmployee("Apple", "1234", 48, 30);
    e.setSpecialHours(90);
    });
  }


  @Test
  public void testGetHappyDayEmployee() {
    lucy.giveRaiseByPercent(5.0);
    assertEquals(73500.00, lucy.getBaseSalary());
    assertEquals("Lucy", lucy.getName());
    assertEquals("222-22-2222",lucy.getID());
    SalariedEmployee lucy2 = new SalariedEmployee(lucy);
    assertEquals(true, lucy.equals(lucy2));

    snoopyHours.giveRaiseByPercent(5.0);
    assertEquals(18.38, snoopyHours.getBaseSalary());
    assertEquals("Snoopy", snoopyHours.getName());
    assertEquals("111-CHLY-BRWN", snoopyHours.getID());
    HourlyEmployee snoopyHours2 = new HourlyEmployee(snoopyHours);
    assertEquals(true, snoopyHours.equals(snoopyHours2));
  }

  @Test
  public void testGetPayForThisPeriod() {
    assertEquals(5833.33, lucy.getPayForThisPeriod(),0);
    assertEquals(350.00, snoopyHours.getPayForThisPeriod(),0);
    assertEquals(15000.04, woodStock.getPayForThisPeriod(),0);
  }

  @Test
  public void testGetBaseSalary() {
    assertEquals(70000.00, lucy.getBaseSalary(),0);
    assertEquals(17.50, snoopyHours.getBaseSalary(),0);
    assertEquals(180000.50, woodStock.getBaseSalary(),0);
  }

  @Test
  public void testGetID() {
    assertEquals("222-22-2222",lucy.getID());
    assertEquals("111-CHLY-BRWN", snoopyHours.getID());
    assertEquals("33-CHIRP", woodStock.getID());
  }

  @Test
  public void testGetName() {
    assertEquals("Lucy", lucy.getName());
    assertEquals("Snoopy", snoopyHours.getName());
    assertEquals("Woodstock", woodStock.getName());
  }

  @Test
  public void testSpecialHoursAndPay() {
    snoopyHours.setSpecialHours(70);
    assertEquals(1487.5, snoopyHours.getPayForThisPeriod());
    assertEquals(350.0, snoopyHours.getPayForThisPeriod());
  }

  @Test
  public void testSurpassMax() {
    IEmployee e = new HourlyEmployee("Apple", "1234", 48, 30);
    e.giveRaiseByPercent(5);
    assertEquals(48, e.getBaseSalary());
    IEmployee er = new SalariedEmployee("Google", "1111", 953000);
    er.giveRaiseByPercent(5);
    assertEquals(953000.0, er.getBaseSalary());
  }

  @Test
  public void testToString() {
    assertEquals("Name: Snoopy\nID: 111-CHLY-BRWN\nBase Salary: $17.50", snoopyHours.toString());
    assertEquals("Name: Lucy\nID: 222-22-2222\nBase Salary: $70000.00", lucy.toString());
  }



}
