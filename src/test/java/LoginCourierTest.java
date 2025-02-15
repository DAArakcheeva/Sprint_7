import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.ClientCourier;
import org.example.Courier;
import org.example.CredentialsCourier;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class LoginCourierTest {

    private int courierId;

    ClientCourier client = new ClientCourier();

    @Test
    @DisplayName("Вход курьера с валидными параметрами")
    @Description("Проверка успешного входа курьера с корректными данными")

    public void validParamsLogin() {

        Courier courier = Courier.random();

        ValidatableResponse createResponse = client.createCourier(courier);
        client.checkCreateSuccess(createResponse);

        CredentialsCourier creds = CredentialsCourier.from(courier);

        ValidatableResponse loginResponse = client.loginCourier(creds);

        courierId = client.checkSuccessLogin(loginResponse);

        Assert.assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Вход курьера с некорректными параметрами")
    @Description("Проверка входа курьера с некорректными данными")

    public void invalidParamsLogin() {
        CredentialsCourier invalidParams= CredentialsCourier.invalidParams();
        ValidatableResponse invalidResponse = client.loginCourier(invalidParams);
        client.checkInvalidParamsLoginError(invalidResponse);
    }

    @Test
    @DisplayName("Вход курьера с некорректными учетными данными")
    @Description("Проверка входа курьера с некорректными учетными данными")

    public void invalidCredsLogin() {
        CredentialsCourier invalidCreds = CredentialsCourier.randomCredentialsCourier();
        ValidatableResponse invalidResponse = client.loginCourier(invalidCreds);
        client.checkInvalidCredsLoginError(invalidResponse);
    }

    @After

    public void deleteCourier() {

        if (courierId != 0) {
            ValidatableResponse deleteResponse = client.deleteCourier(courierId);
            client.checkDeleteSuccess(deleteResponse);
        }
    }
}
