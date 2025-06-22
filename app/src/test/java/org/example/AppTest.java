package org.example;

import org.example.App.GroceryCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

  @Test
  void testInitialTotalIsZero() {
    GroceryCounter counter = new GroceryCounter();
    assertEquals("$0.00", counter.total());
  }

  @Test
  void testSingleButtonPresses() {
    GroceryCounter counter = new GroceryCounter();
    counter.tens();
    counter.ones();
    counter.tenths();
    counter.hundreths();
    assertEquals("$11.11", counter.total());
  }

  @Test
  void testOverflowOnce() {
    GroceryCounter counter = new GroceryCounter();

    for (int i = 0; i < 100; i++) {
      counter.tens();
    }

    assertEquals(10, counter.overflows());
    assertEquals("$0.00", counter.total());

  }

  @Test
  void testMultipleOverflows() {
    GroceryCounter counter = new GroceryCounter();

    for (int i = 0; i < 250; i++) {
      counter.tens();
    }

    assertEquals(25, counter.overflows());
    assertEquals("$0.00", counter.total());

  }

  @Test
  void testClear() {
    GroceryCounter counter = new GroceryCounter();
    counter.ones();
    counter.clear();
    assertEquals("$0.00", counter.total());
    assertEquals(0, counter.overflows());
  }
}
