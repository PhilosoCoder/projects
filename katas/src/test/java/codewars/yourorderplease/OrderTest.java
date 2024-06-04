package codewars.yourorderplease;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void test1() {
        assertEquals(Order.order("is2 Thi1s T4est 3a"), ("Thi1s is2 3a T4est"));
    }

    @Test
    void test2() {
        assertEquals(Order.order("4of Fo1r pe6ople g3ood th5e the2"), ("Fo1r the2 g3ood 4of th5e pe6ople"));
    }

    @Test
    void test3() {
        assertEquals(Order.order(""), (""));
    }

}