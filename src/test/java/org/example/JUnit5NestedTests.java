package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Demonstrating how to use nested tests to express relationship among several group of tests")
class JUnit5NestedTests {

  @Nested
  @DisplayName("Group 1")
  class Group1 {

    @Test
    void test1() {
      assertTrue(true);
    }

    @Test
    void test2() {
      assertFalse(false);
    }
  }

  @Nested
  @DisplayName("Group 2")
  class Group2 {

    @Test
    void test1() {
      assertTrue(true);
    }

    @Test
    void test42() {
      assertTrue(true);
    }
  }

}
