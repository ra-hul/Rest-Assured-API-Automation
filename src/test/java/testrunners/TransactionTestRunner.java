package testrunners;

import controllers.TransactionController;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class TransactionTestRunner{

    String customerOne = "";
    String customerTwo = "";
    String merchantNumber = "";

    public String getPhoneNumberOfRole(String role) throws IOException, ParseException {

        JSONArray userArray = Utils.readJSON();

        for (int i = 0; i < userArray.size(); i++){
            JSONObject userObj = (JSONObject) userArray.get(i);
            if (userObj.get("role").equals(role)){
                return (String) userObj.get("phone_number");

            }

        }
        return null;
    }

    public String getPhoneNumberOfRole(String role, String phone_number) throws IOException, ParseException {
        JSONArray userArray = Utils.readJSON();

        for (int i = 0; i < userArray.size(); i++) {
            JSONObject userObj = (JSONObject) userArray.get(i);
            if (userObj.get("role").equals(role) && !userObj.get("phone_number").equals(phone_number)) {
                return (String) userObj.get("phone_number");
            }
        }

        return null;
    }

    @Test(priority = 1, description = "System deposit money to Agent")
    public void depositToAgent() throws IOException, ParseException {
        TransactionController transactionController = new TransactionController();
        String to_phone_number =getPhoneNumberOfRole("Agent");

        JsonPath jsonPath = transactionController.depositToUser("SYSTEM", to_phone_number, 2000);
        String actualMessage = jsonPath.get("message");
        String expectedMessage = "Deposit successful";

        System.out.println(actualMessage);

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test(priority = 2, description = "Agent deposits money to a customer")
    public void depositToCustomer() throws IOException, ParseException {
        TransactionController transactionController = new TransactionController();
        String from_phone_number = getPhoneNumberOfRole("Agent");
        customerOne = getPhoneNumberOfRole("Customer");
        String to_phone_number= customerOne;

        JsonPath jsonPath = transactionController.depositToUser(from_phone_number, to_phone_number, 1500);
        String actualMessage = jsonPath.get("message");
        String expectedMessage = "Deposit successful";

        System.out.println(actualMessage);

        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Test(priority = 3, description = "Customer withdraw money from an agent")
    public void withdrawMoney() throws IOException, ParseException {
        TransactionController transactionController = new TransactionController();

        String from_phone_number = customerOne;
        String to_phone_number = getPhoneNumberOfRole("Agent");

        JsonPath jsonPath = transactionController.withdrawMoney(from_phone_number, to_phone_number, 500);

        String actualMessage = jsonPath.get("message");
        String expectedMessage = "Withdraw successful";

        System.out.println(actualMessage);

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test(priority = 4, description = "One customer sends money to another customer")
    public void sendMoney() throws IOException, ParseException {
        TransactionController transactionController = new TransactionController();

        String from_phone_number = customerOne;
        customerTwo = getPhoneNumberOfRole("Customer", customerOne);
        String to_phone_number = customerTwo;

        JsonPath jsonPath = transactionController.sendMoneyCustomer(from_phone_number,to_phone_number, 500);
        String actualMessage = jsonPath.get("message");
        String expectedMessage = "Send money successful";

        System.out.println(actualMessage);

        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Test(priority = 5, description = "A merchant receives payment")
    public void paymentMoney() throws IOException, ParseException {
        TransactionController transactionController = new TransactionController();

        String from_phone_number = customerTwo;
        String to_phone_number = getPhoneNumberOfRole("Merchant");

        JsonPath jsonPath =  transactionController.PaymentToMerchant(from_phone_number, to_phone_number, 100);
        String actualMessage = jsonPath.get("message");
        String expectedMessage = "Payment successful";

        System.out.println(actualMessage);

        Assert.assertEquals(expectedMessage,actualMessage);

    }

   @Test(priority = 6, description = "Checking the balance")
   public void balanceCheck() throws IOException {
        TransactionController transactionController = new TransactionController();

        String phone_number = customerTwo;

        JsonPath jsonPath = transactionController.checkBalance(phone_number);

        String actualMessage = jsonPath.get("message");
        String expectedMessage = "User balance";

        System.out.println(actualMessage);
        Assert.assertEquals(expectedMessage,actualMessage);
   }

}
