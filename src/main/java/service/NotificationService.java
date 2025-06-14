package service;

public interface NotificationService {
    boolean sendConfirmation(String email, String message);
}