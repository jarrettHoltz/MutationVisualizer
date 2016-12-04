package triangle;

import org.junit.Test;
import static org.junit.Assert.*;

import static triangle.Triangle.Type;
import static triangle.Triangle.Type.*;

/**
 * Test class for the Triangle implementation.
 */
public class TriangleTest {

    /*
     * Test the classification of an equilateral triangle.
     */
    @Test
    public void test1() {
        Type actual = Triangle.classify(1, 1, 1);
        Type expected = EQUILATERAL;
        assertEquals (expected, actual);
    }

    @Test
    public void triangle() {
        Type actual = Triangle.classify(1, 1, 1);
        Type expected = EQUILATERAL;
        assertEquals (expected, actual);
    }

    @Test
    public void test5() {
        Type actual = Triangle.classify(1, 2, 3);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void zero1() {
        Type actual = Triangle.classify(0, 1, 1);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void zero2() {
        Type actual = Triangle.classify(1, 0, 1);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void zero3() {
        Type actual = Triangle.classify(1, 1, 0);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void isosceles() {
        Type actual = Triangle.classify(2, 2, 1);
        Type expected = ISOSCELES;
        assertEquals (expected, actual);
    }

    @Test
    public void isosceles2() {
        Type actual = Triangle.classify(2, 1, 2);
        Type expected = ISOSCELES;
        assertEquals (expected, actual);
    }

    @Test
    public void isosceles3() {
        Type actual = Triangle.classify(1, 2, 2);
        Type expected = ISOSCELES;
        assertEquals (expected, actual);
    }

    @Test
    public void final_invalid() {
        Type actual = Triangle.classify(1, 10, 1);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void final_invalid2() {
        Type actual = Triangle.classify(1, 1, 10);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

     @Test
    public void final_invalid3() {
        Type actual = Triangle.classify(10, 1, 1);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void scalene() {
        Type actual = Triangle.classify(3, 4, 5);
        Type expected = SCALENE;
        assertEquals (expected, actual);
    }

    @Test
    public void invalidScalene() {
        Type actual = Triangle.classify(3, 4, 8);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void invalidScalene2() {
        Type actual = Triangle.classify(3, 8, 4);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void invalidScalene3() {
        Type actual = Triangle.classify(8, 3, 4);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }



    // Mutation Tests

        @Test
    public void negative1() {
        Type actual = Triangle.classify(-10, 1, 1);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    public void negative4() {
        Type actual = Triangle.classify(-10, -9, 2);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

        public void negative5() {
        Type actual = Triangle.classify(-10, -9, 2);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void negative2() {
        Type actual = Triangle.classify(1, -10, 1);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void negative3() {
        Type actual = Triangle.classify(1, 1, -10);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

        @Test
    public void invalidScalene4() {
        Type actual = Triangle.classify(1, 3, 2);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void invalidScalene5() {
        Type actual = Triangle.classify(3, 1, 2);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void invalidScalene6() {
        Type actual = Triangle.classify(2, 3, 1);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void not_isosc() {
        Type actual = Triangle.classify(2, 4, 2);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void not_isosc2() {
        Type actual = Triangle.classify(2, 2, 4);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void not_isosc3() {
        Type actual = Triangle.classify(4, 2, 2);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void not_isosc4() {
        Type actual = Triangle.classify(7, 3, 3);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void not_isosc5() {
        Type actual = Triangle.classify(3, 3, 7);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void not_isosc6() {
        Type actual = Triangle.classify(3, 7, 3);
        Type expected = INVALID;
        assertEquals (expected, actual);
    }

    @Test
    public void not_mod() {
        Type actual = Triangle.classify(4, 2, 3);
        Type expected = SCALENE;
        assertEquals (expected, actual);
    }

    @Test
    public void not_mod2() {
        Type actual = Triangle.classify(3, 4, 2);
        Type expected = SCALENE;
        assertEquals (expected, actual);
    }

    @Test
    public void not_mod3() {
        Type actual = Triangle.classify(2, 4, 3);
        Type expected = SCALENE;
        assertEquals (expected, actual);
    }


}
