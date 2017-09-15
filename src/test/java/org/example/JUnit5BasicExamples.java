package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

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
    void testNotExceedingTimeout() {
        String message = assertTimeout(Duration.ofHours(12), () -> {
            Thread.sleep(100);
            return "About time";
        });

        assertEquals("About time", message);
    }

    @Test
    void testExceedingTimeout() {
        assertThrows(AssertionError.class, () ->
                assertTimeout(Duration.ofMillis(100), () ->
                        Thread.sleep(150)));
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
        assertEquals(6, 3 + 3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sim", "Salabim"})
    void testWithMultipleParameters(String argument) {
        assertTrue(argument.startsWith("S"));
    }

    @ParameterizedTest
    @MethodSource("intProvider")
    void testWithMultipleParametersFromMethodSource(int argument) {
        assertTrue(argument > 0 && argument < 10);
    }

    private static IntStream intProvider() {
        return IntStream.range(1, 9);
    }

    @Test
    @Disabled("Disabled for demo purposes ☠️")
    void testThatDoesntWork() {
        assertTrue(false);
    }

}