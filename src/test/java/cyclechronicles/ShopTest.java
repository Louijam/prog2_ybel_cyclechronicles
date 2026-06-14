package cyclechronicles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class ShopTest {

    @Test
    void testRejectEbike() {
        Shop shop = new Shop();

        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.EBIKE);

        assertFalse(shop.accept(order));
    }

    @Test
    void testRejectGravel() {
        Shop shop = new Shop();

        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);

        assertFalse(shop.accept(order));
    }

    @Test
    void testAcceptRaceBike() {
        Shop shop = new Shop();

        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Alice");

        assertTrue(shop.accept(order));
    }

    @Test
    void testRejectDuplicateCustomer() {
        Shop shop = new Shop();

        Order order1 = mock(Order.class);
        when(order1.getBicycleType()).thenReturn(Type.RACE);
        when(order1.getCustomer()).thenReturn("Alice");

        Order order2 = mock(Order.class);
        when(order2.getBicycleType()).thenReturn(Type.FIXIE);
        when(order2.getCustomer()).thenReturn("Alice");

        assertTrue(shop.accept(order1));
        assertFalse(shop.accept(order2));
    }

    @Test
    void testRejectWhenFiveOrdersAlreadyPending() {
        Shop shop = new Shop();

        for (int i = 0; i < 5; i++) {
            Order o = mock(Order.class);
            when(o.getBicycleType()).thenReturn(Type.RACE);
            when(o.getCustomer()).thenReturn("Customer" + i);

            assertTrue(shop.accept(o));
        }

        Order sixth = mock(Order.class);
        when(sixth.getBicycleType()).thenReturn(Type.RACE);
        when(sixth.getCustomer()).thenReturn("Customer5");

        assertFalse(shop.accept(sixth));
    }

    @Test
    void testAcceptWhenFourOrdersAlreadyPending() {
        Shop shop = new Shop();

        for (int i = 0; i < 4; i++) {
            Order o = mock(Order.class);
            when(o.getBicycleType()).thenReturn(Type.RACE);
            when(o.getCustomer()).thenReturn("Customer" + i);

            assertTrue(shop.accept(o));
        }

        Order fifth = mock(Order.class);
        when(fifth.getBicycleType()).thenReturn(Type.RACE);
        when(fifth.getCustomer()).thenReturn("Customer4");

        assertTrue(shop.accept(fifth));
    }
}
