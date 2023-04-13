import java.io.Serializable;

public class Car implements Serializable {
    private int price;

    public Car(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                '}';
    }
}
