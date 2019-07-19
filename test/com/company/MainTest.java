package com.company;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import sun.awt.SunHints;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private String str;
    @BeforeAll
    public static void beforeAll(TestInfo testInfo){
        System.out.println("Run Once before all "+testInfo.getDisplayName());
    }

    @BeforeEach
    void initializeTestData(TestInfo testInfo){
        System.out.println("Output by Developer Prepare Data: "+testInfo.getDisplayName());
    }

    @AfterEach
    void cleanUpData(TestInfo testInfo){
        System.out.println("Output by Developer Clean Data: "+testInfo.getDisplayName());
    }

    @Test
    @DisplayName("When str is null thrown an exception")
    void length_exception(){
        String str = null;
        assertThrows(NullPointerException.class, ()->str.length());
    }

    @Test
    @Disabled
    @Ignore
    void length_exception_disabled(){
        String str = null;
        assertThrows(NullPointerException.class, ()->str.length());
    }

    @Test
    void length_greater_then_zero(){
        assertTrue("ABCD".length()>0);
        assertTrue("DEFG".length()>0);
        assertTrue("ABD".length()>0);
        assertTrue("AB".length()>0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCD","ABC", "A","DEF"})
    void length_greater_then_zero_parameterized(String str){
        assertTrue(str.length()>0);
    }

    @ParameterizedTest(name="{0} uppercase is {1}")
    @CsvSource(value = {"abcd, ABCD","abc, ABC", "'',''","abcdefg, ABCDEFG"})
    void uppercase(String word, String capitalizedWord){
        assertEquals(capitalizedWord, word.toUpperCase());
    }

    @Test
    @RepeatedTest(10)
    void main() {
        int actual = "ABCD".length();
        int expectedOutput = 4;
        assertEquals(expectedOutput,actual);
    }

    @Test
    void performanctest(){
        assertTimeout(Duration.ofSeconds(5), ()->{
            for(int i=0; i<= 1000000; i++){
                int j=i;
                System.out.println(j);
            }
        });
    }

    @Nested
    @DisplayName("For an empty String")
    class EmptyStringTests{

        @BeforeEach
        void setToEmpty(){
            str = "";
        }

        @Test
        void lengthIsZero(){
            assertTrue(str.length()==0);
        }

        @Test
        void uppercaseIsEmpty(){
            assertEquals("", str.toUpperCase());
        }
    }
}