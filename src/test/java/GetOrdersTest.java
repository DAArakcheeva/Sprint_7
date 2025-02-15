import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.ClientOrder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class GetOrdersTest {

    @Test
    @DisplayName("Получить список заказов")
    @Description("Проверка получения списка заказов")

    public void getOrders() {
        ClientOrder clientOrder = new ClientOrder();

        ValidatableResponse getOrdersResponse = clientOrder.getOrders();
        ArrayList<String> ordersList = clientOrder.checkGetOrdersSuccess(getOrdersResponse);

        Assert.assertNotNull(ordersList);
    }
}
