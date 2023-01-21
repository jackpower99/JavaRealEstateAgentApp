package sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    Property p1, p2;

    @org.junit.jupiter.api.Test

    @BeforeEach
    void setUp() {

        p1 = new Property("11", "dd","dd","dd","dd","55","ee",1);
    }

    @AfterEach
    void tearDown() {

        p1 = null;
    }

    @Test
    void testSetPrice() {

    }

    @Test
    void getPrice() {
    }
}