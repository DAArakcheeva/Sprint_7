package org.example;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;


public class ClientOrder {

    // Адрес сайта
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    // Получение списка заказов
    public static final String ORDER_PATH = "/api/v1/orders";
    // метод отправляет POST-запрос для создания заказа
    @Step("Send POST request to /api/v1/orders")
    public ValidatableResponse createOrder(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    // метод проверяет успешность создания заказа
    @Step("Get validate response from POST request to /api/v1/orders")
    public Integer checkSuccessCreate(ValidatableResponse createResponse) {
        return createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
    }

    // метод отправляет GET-запрос для получения списка всех заказов
    @Step("Send GET request to /api/v1/orders")
    public ValidatableResponse getOrders() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }

    // метод проверяет успешность получения списка заказов
    @Step("Get validate response from GET request to /api/v1/orders")
    public ArrayList checkGetOrdersSuccess(ValidatableResponse getOrdersResponse) {
        return getOrdersResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("orders");
    }
}
