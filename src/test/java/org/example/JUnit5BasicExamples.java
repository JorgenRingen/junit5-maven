package org.example;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("JUnit 5 Example Tests")
class JUnit5BasicExamples {

  @BeforeAll
  static void beforeAll() {
    System.out.println("Before all test methods");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("Before each test method");
  }

  @AfterEach
  void afterEach() {
    System.out.println("After each test method");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("After all test methods");
  }

  @DisplayName("Test 2 + 2")
  @Test
  void testAddition() {
    assertEquals(4, 2 + 2);
  }

  @Test
  void testBasicArithmetic() {
    // grouped assertions
    assertAll("arithmetic",
        () -> assertEquals(4, 2 + 2),
        () -> assertEquals(2, 4 - 2),
        () -> assertEquals(9, 3 * 3),
        () -> assertEquals(1.5, 3.0 / 2));
  }

  @Test
  void testException() {
    Throwable exception = assertThrows(NullPointerException.class, () -> {
      throw new NullPointerException("🤒");
    });

    assertEquals("🤒", exception.getMessage());
  }

  @Test
  void testExceedingTimeout() {
    // fails with timeout
    assertThrows(AssertionError.class, () ->
        assertTimeout(Duration.ofMillis(100), () ->
            Thread.sleep(150)));
  }

  @Test
  void testNotExceedingTimeout() {
    // completes before timeout
    String message = assertTimeout(Duration.ofHours(12), () -> {
      Thread.sleep(100);
      return "About time";
    });

    assertEquals("About time", message);
  }

  @Tag("slow") // testmethods and testclasses can be tagged
  @Test
  void testSlowMethod() throws Exception {
    Thread.sleep(1);
    assertTrue(true);
  }

  @Test
  void testWithAssumtion() {
    // assertion inside assume-methods are only run if assumption is correct
    assumingThat(true, () ->
        assertEquals(4, 2 + 2));
    assumingThat(false, () ->
        assertEquals(4, 3 + 3));

    // assertions outside assume-methods are always run
    assertNotEquals("🙂", "🙃");
  }

  @RepeatedTest(10)
  void testSomethingMultipleTimes() {
    // will be repeated 10 times
    assertEquals(6, 3 + 3);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Sim", "Salabim"})
  void testWithMultipleParameters(String argument) {
    // will be called with each argument
    assertTrue(argument.startsWith("S"));
  }

  @ParameterizedTest
  @MethodSource("intProvider")
  void testWithMultipleParametersFromMethodSource(int argument) {
    // will be called with return from intProvider
    assertTrue(argument > 0 && argument < 10);
  }

  @Test
  @Disabled("Disabled for demo purposes ☠️")
  void testThatDoesntWork() {
    assertTrue(false);
  }

  private static IntStream intProvider() {
    return IntStream.range(1, 9);
  }

}