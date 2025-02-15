import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.ClientCourier;
import org.example.Courier;
import org.example.CredentialsCourier;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {
    private int courierId;
    private int courierCloneId;

    ClientCourier client = new ClientCourier();

    @Test
    @DisplayName("Валидные данные курьера")
    @Description("Создание курьера с валидными параметрами")

    public void createValidParamsCourier() {
        Courier courier = Courier.random();

        ValidatableResponse createResponse = client.createCourier(courier);
        client.checkCreateSuccess(createResponse);
        CredentialsCourier creds = CredentialsCourier.from(courier);

        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = client.checkSuccessLogin(loginResponse);
    }


    @Test
    @DisplayName("Дубликат курьера")
    @Description("Создание дубля курьера")

    public void createCloneCourier() {
        Courier courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        client.checkCreateSuccess(createResponse);
        CredentialsCourier creds = CredentialsCourier.from(courier);

        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = client.checkSuccessLogin(loginResponse);

        Courier cloneCourier = new Courier(courier.getLogin(), courier.getPassword(), courier.getFirstName());
        ValidatableResponse createCloneResponse = client.createCourier(cloneCourier);
        client.ckeckCloneCourierError(createCloneResponse);
    }

    @Test
    @DisplayName("Невалидные данные курьера")
    @Description("Создание курьера с невалидными параметрами")

    public void createInvalidParamsCourier() {
        Courier courier = Courier.invalid();
        ValidatableResponse invalidResponse = client.createCourier(courier);
        client.checkInvalidParamsError(invalidResponse);

    }

    @After
    public void deleteCourier() {

        if (courierId != 0) {
            ValidatableResponse deleteResponse = client.deleteCourier(courierId);
            client.checkDeleteSuccess(deleteResponse);
        }
    }
}