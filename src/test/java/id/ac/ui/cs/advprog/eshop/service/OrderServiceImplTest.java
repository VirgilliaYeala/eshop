package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;
    List<Order> orders;

    @BeforeEach
    void setup() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", 
            products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("24681357-wxyz-5678-ijkl-mnopqrstuvwx",
            products, 1708570000L, "Safira Sudrajat");
        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void testCreateOrder() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).save(order);

        Order result = orderService.createOrder(order);
        verify(orderRepository,times(1)).save(order);
        assertEquals(order.getOrderId(), result.getOrderId());
    }

    @Test
    void testCreateOrderIfAlreadyExists() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getOrderId());

        assertNull(orderService.createOrder(order));
        verify(orderRepository,times(0)).save(order);
    }

    @Test
    void testUpdateStatus() {
        Order order = orders.get(1);
        Order newOrder = new Order(order.getOrderId(), order.getOrderProducts(), order.getOrderTime(), 
            order.getAuthor(), OrderStatus.SUCCESS.getValue());
        doReturn(order).when(orderRepository).findById(order.getOrderId());
        doReturn(newOrder).when(orderRepository).save(any(Order.class));

        Order result = orderService.updateStatus(order.getOrderId(), OrderStatus.SUCCESS.getValue());

        assertEquals(order.getOrderId(), result.getOrderId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getOrderStatus());
        verify(orderRepository,times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getOrderId());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.updateStatus(order.getOrderId(), "MEOW");
        });

        verify(orderRepository,times(0)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidOrderId() {
        doReturn(null).when(orderRepository).findById("zczc");

        assertThrows(NoSuchElementException.class, () -> {
            orderService.updateStatus("zczc", OrderStatus.SUCCESS.getValue());
        });

        verify(orderRepository,times(0)).save(any(Order.class));
    }

    @Test
    void testFindByIdIfIdFound() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getOrderId());

        Order result = orderService.findById(order.getOrderId());
        assertEquals(order.getOrderId(), result.getOrderId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        doReturn(null).when(orderRepository).findById("zczc");
        assertNull(orderService.findById("zczc"));
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        Order order = orders.get(1);
        doReturn(orders).when(orderRepository).findAllByAuthor(order.getAuthor());

        List<Order> results = orderService.findAllByAuthor(order.getAuthor());
        for (Order result : results) {
            assertEquals(order.getAuthor(), result.getAuthor());
        }

        assertEquals(2, results.size());
    }

    @Test
    void testFindAllByAuthorIfAllLowercase() {
        Order order = orders.get(1);
        doReturn(new ArrayList<Order>()).when(orderRepository).findAllByAuthor(order.getAuthor().toLowerCase());

        List<Order> results = orderService.findAllByAuthor(order.getAuthor().toLowerCase());
        assertTrue(results.isEmpty());
    }
}