# Ejercicio: Uso de Mockito para Caso de Test JUnit4

## Contexto
La empresa SmartCommerce desea probar una clase llamada OrderService, encargada de procesar órdenes de compra.
Esta clase depende de dos componentes:

* Un repositorio OrderRepository que guarda la orden en la base de datos.
* Un servicio NotificationService que envía una confirmación por correo electrónico al cliente.

A continuación se entrega una versión simplificada de estas clases e interfaces:
````java
public interface OrderRepository {
    boolean save(Order order);
}
public interface NotificationService {
    boolean sendConfirmation(String email, String message);
}
public class Order {
    private String userEmail;
    public Order(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserEmail() {
        return userEmail;
    }
}
public class OrderService {
    private OrderRepository repository;
    private NotificationService notificationService;
    public OrderService(OrderRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }
    public boolean placeOrder(Order order) {
        boolean saved = repository.save(order);
        if (saved) {
            return notificationService.sendConfirmation(order.getUserEmail(),
            "Your order has been placed!");
        }
        return false;
}
}
````
## Tarea
Escriba una clase de prueba OrderServiceTest que utilice JUnit 4 y Mockito para verificar el siguiente comportamiento
del método placeOrder:
*  Si el método repository.save(order) retorna true, el método sendConfirmation debe ser llamado con el correo
electrónico correcto y retornar true.
*  Simule ambos comportamientos utilizando when(...).thenReturn(...).
*  No utilice anotaciones como @Mock o @InjectMocks; instancie los mocks de forma manual.


## Solución 
Se hizo uso del when() y thenReturn() de Mockito para simular el comportamiento de los métodos del repositorio y del servicio de notificaciones.
tambien se utilizo verify para asegurar que los metodos estan siendo llamados correctamente con su parametro correspondiente.

El codigo de solucion se encuentra dentro de la clase OrderServiceTest

 




 