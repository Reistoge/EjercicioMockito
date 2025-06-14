package orders;
public class Order {
    private String userEmail;
    public Order(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserEmail() {
        return userEmail;
    }
}