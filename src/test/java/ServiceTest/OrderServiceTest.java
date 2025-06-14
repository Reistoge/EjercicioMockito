package ServiceTest;

import orders.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.OrderRepository;
import service.NotificationService;
import service.OrderService;


import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    OrderService service;
    Order order;
    OrderRepository orderRepository;
    NotificationService notificationService;


    @Before
    public void setUp() {
        notificationService = Mockito.mock(NotificationService.class);

        orderRepository = Mockito.mock(OrderRepository.class);

        service = new OrderService(orderRepository, notificationService);

        order = new Order("user@example.com");
    }



    @Test
    public void testPlaceOrder() {
        // testeamos la funcionalidad de placeOrder usando mocks
        // de sus dependencias y una instancia order como parametro de prueba.



        // matchers y strings puros son incompatibles dentro de mocks es por eso que usamos eq("")

        // al momento de llamar el metodo sendConfirmation dentro de OrderService si entra el email de la orden que queremos y cualquier string entonces retorna true.
        when(notificationService.sendConfirmation(Mockito.anyString(),Mockito.anyString())).thenReturn(true);

        // si entra la order como parametro al metodo save del orderRepository entonces retorna true.
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(true);

        boolean result = service.placeOrder(order);

        assertTrue(result);

        // verificamos estrictamente que se llamaron los metodos con los parametros correctos.
        verify(orderRepository).save(order);
        verify(notificationService).sendConfirmation(eq(order.getUserEmail()),Mockito.anyString());


    }
    @Test
    public void testPlaceOrderVariant() {
        // testeamos la funcionalidad de placeOrder usando mocks de sus dependencias.
        // y una instancia order como parametro de prueba.


        order = new Order("user@example.com");

        // matchers y strings puros son incompatibles dentro de mocks es por eso que usamos eq("")

        // al momento de llamar el metodo sendConfirmation dentro de OrderService si entra el email de la orden que queremos y cualquier string entonces retorna true.
        when(notificationService.sendConfirmation(eq(order.getUserEmail()),Mockito.anyString())).thenReturn(true);

        // si entra la order como parametro al metodo save del orderRepository entonces retorna true.
        when(orderRepository.save(order)).thenReturn(true);

        boolean result = service.placeOrder(order);

        assertTrue(result);

        // verificamos que se llamaron los metodos
        verify(orderRepository).save(Mockito.any(Order.class));
        verify(notificationService).sendConfirmation(Mockito.anyString(),Mockito.anyString());


    }
}
