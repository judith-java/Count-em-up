package org.example;

public class App {

  public static class GroceryCounter {
    private int counter;
    private int overflowCount;
    private int maxValue;

    // Default constructor (0 start, max 9999)
    public GroceryCounter() {
      this(0, 9999);
    }

    // Constructor with custom start value (validates range)
    public GroceryCounter(int startValue) {
      this(startValue, 9999);
    }

    // Constructor with custom start value and max value (validates both)
    public GroceryCounter(int startValue, int maxValue) {
      if (maxValue < 0) {
        throw new IllegalArgumentException("Max value must be positive");
      }
      if (startValue < 0 || startValue > maxValue) {
        throw new IllegalArgumentException("Start value must be between 0 and max value");
      }
      this.counter = startValue;
      this.maxValue = maxValue;
      this.overflowCount = 0;
    }

    // Increment digit methods
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

    // Decrement digit methods
    public void decrementTens() {
      decrement(1000);
    }

    public void decrementOnes() {
      decrement(100);
    }

    public void decrementTenths() {
      decrement(10);
    }

    public void decrementHundreths() {
      decrement(1);
    }

    // Increment by arbitrary amount
    public void increment(int amount) {
      if (amount < 0) {
        throw new IllegalArgumentException("Increment amount must be non-negative");
      }
      counter += amount;
      // Calculate overflows and wrap counter
      while (counter > maxValue) {
        overflowCount++;
        counter -= (maxValue + 1);
      }
    }

    // Decrement by arbitrary amount (helper)
    private void decrement(int amount) {
      if (amount < 0) {
        throw new IllegalArgumentException("Decrement amount must be non-negative");
      }
      counter -= amount;
      while (counter < 0) {
        // Underflow: wrap around from maxValue
        overflowCount = Math.max(0, overflowCount - 1); // Decrease overflow count but don't go negative
        counter += (maxValue + 1);
      }
    }

    // Show total as $dollars.cents
    public String total() {
      int dollars = counter / 100;
      int cents = counter % 100;
      return String.format("$%d.%02d", dollars, cents);
    }

    // Number of overflows
    public int overflows() {
      return overflowCount;
    }

    // Reset everything
    public void clear() {
      counter = 0;
      overflowCount = 0;
    }
  }

  public static void main(String[] args) {
    GroceryCounter counter = new GroceryCounter(50, 9999);

    counter.tens();    // +1000
    counter.ones();    // +100
    counter.tenths();  // +10
    counter.hundreths(); // +1

    System.out.println("Total: " + counter.total()); // $11.61
    System.out.println("Overflows: " + counter.overflows()); // 0

    // Increment by 9900 (should overflow once: 50+1111=1161 + 9900 = 11061 > 9999)
    counter.increment(9900);
    System.out.println("Total after increment 9900: " + counter.total());
    System.out.println("Overflows after increment: " + counter.overflows());

    // Decrement tens digit by one (1000)
    counter.decrementTens();
    System.out.println("Total after decrement tens: " + counter.total());

    // Decrement beyond zero to test underflow and overflow count decrement
    for (int i = 0; i < 20000; i++) {
      counter.decrementHundreths();
    }
    System.out.println("Total after large decrement: " + counter.total());
    System.out.println("Overflows after large decrement: " + counter.overflows());

    counter.clear();
    System.out.println("After clear: " + counter.total());
    System.out.println("Overflows after clear: " + counter.overflows());
  }
}
