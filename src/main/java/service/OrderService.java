package service;

import orders.Order;
import repository.OrderRepository;

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