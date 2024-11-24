package controllers;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import setup.Setup;
import setup.TransactionModel;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TransactionController extends Setup {

    public TransactionController() throws IOException {
        initConfigFile();
    }

    public JsonPath depositToUser(String from_account, String to_account, int amount){
        TransactionModel model = new TransactionModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);

        RestAssured.baseURI= prop.getProperty("baseUrl");
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                .body(model).when().post("/transaction/deposit");

        return res.jsonPath();
    }

    public JsonPath withdrawMoney(String from_account, String to_account, int amount){

        TransactionModel model = new TransactionModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);

        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res = given().contentType("application/json")
                .header("Authorization","Bearer " + prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                .body(model).when().post("/transaction/withdraw");

        return res.jsonPath();
    }

    public JsonPath sendMoneyCustomer(String from_account, String to_account, int amount){
        TransactionModel model = new TransactionModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);

        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + ((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey")).body(model)
                .when().post("/transaction/sendmoney");

        return res.jsonPath();

    }

    public JsonPath PaymentToMerchant(String from_account, String to_account, int amount){
        TransactionModel model = new TransactionModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);

        RestAssured.baseURI=prop.getProperty("baseUrl");
        String BearerToken =  prop.getProperty("token");
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + BearerToken)
                .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey")).body(model)
                .when().post("/transaction/payment");

        return res.jsonPath();
    }

    public JsonPath checkBalance(String phone_number) {
        RestAssured.baseURI = prop.getProperty("baseUrl");

        Response res = given().contentType("application/json").header("Authorization", "Bearer " + prop.getProperty("token")).header("X-AUTH-SECRET-KEY", prop
                .getProperty("partnerKey")).get("/transaction/balance/" + phone_number);

        return res.jsonPath();
    }
}
