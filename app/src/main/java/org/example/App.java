package org.example;

public class App {

  public static class GroceryCounter {
    private int counter;  
    private int overflowCount;

    public GroceryCounter() {
      this.counter = 0;
      this.overflowCount = 0;
    }

    public void tens() {
      increment(1000);
    }

    public void ones() {
      increment(100);
    }

    public void tenths() {
      increment(10);
    }

    public void hundreths() {
      increment(1);
    }

    private void increment(int amount) {
      counter += amount;
      if (counter > 9999) {
        overflowCount++;
        counter = counter % 10000;
      }
    }

    public String total() {
      int dollars = counter / 100;
      int cents = counter % 100;
      return String.format("$%d.%02d", dollars, cents);
    }

    public int overflows() {
      return overflowCount;
    }

    public void clear() {
      counter = 0;
      overflowCount = 0;
    }
  }

  public static void main(String[] args) {
    GroceryCounter counter = new GroceryCounter();

    counter.tens();     
    counter.tens();    
    counter.tenths();  
    counter.hundreths(); 

    System.out.println("Current total: " + counter.total()); // Expected: $20.11
    System.out.println("Overflows: " + counter.overflows()); // Expected: 0

    for (int i = 0; i < 100; i++) {
      counter.tens(); 
    }

    System.out.println("After overflow:");
    System.out.println("Current total: " + counter.total());
    System.out.println("Overflows: " + counter.overflows());

    counter.clear();
    System.out.println("After clear:");
    System.out.println("Current total: " + counter.total()); // Expected: $0.00
    System.out.println("Overflows: " + counter.overflows()); // Expected: 0
  }
}
