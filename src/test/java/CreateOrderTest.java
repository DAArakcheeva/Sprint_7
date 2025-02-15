import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.ClientOrder;
import org.example.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{"GREY", "BLACK"}},
                {new String[]{}},
        };
    }

    @Test
    @DisplayName("Заказ")
    @Description("Создание заказа")
    public void createOrder() {

        Order order = new Order("Пирожок", "Пирог", "г. Москва", "Университет", "81111111111", 2, "2024-04-16", "Седьмой спринт, ура!", color);

        ClientOrder clientOrder = new ClientOrder();
        ValidatableResponse createOrderResponse = clientOrder.createOrder(order);
        int id = clientOrder.checkSuccessCreate(createOrderResponse);

        Assert.assertNotEquals(0, id);
    }
}