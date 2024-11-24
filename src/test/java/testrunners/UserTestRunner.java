package testrunners;

import controllers.UserController;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import setup.Setup;
import setup.UserModel;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {

    @Test(priority = 1, description = "Admin can Login", enabled = true)
    public void doLogin() throws IOException, ConfigurationException {
        UserController userController = new UserController();
        userController.doLogin("admin@roadtocareer.net", "1234");
    }

    @Test(priority = 2, description = "Admin can create First Customer")
    public void createCustomerNumberOne() throws IOException, ParseException, ConfigurationException {
        UserModel model = Utils.generateInfo("Customer");

        Utils.saveTheUser(model);

        UserController userController = new UserController();
        JsonPath jsonPath = userController.createUser(model);
        int userId = jsonPath.get("user.id");
        Utils.setEnv("userId", String.valueOf(userId));
        
    }
    @Test(priority = 3, description = "Admin can create Second customer")
    public void createCustomerNumberTwo() throws IOException, ParseException, ConfigurationException {
        UserModel model = Utils.generateInfo("Customer");

        Utils.saveTheUser(model);

        UserController userController = new UserController();
        JsonPath jsonPath = userController.createUser(model);
        int userId = jsonPath.get("user.id");
        Utils.setEnv("userId", String.valueOf(userId));

    }


    @Test(priority = 4, description = "Admin can create agent role")
    public void createAgent() throws IOException, ParseException, ConfigurationException {
        UserModel model = Utils.generateInfo("Agent");

        Utils.saveTheUser(model);

        UserController userController = new UserController();
        JsonPath jsonPath = userController.createUser(model);
        int userId = jsonPath.get("user.id");
        Utils.setEnv("userId", String.valueOf(userId));
    }

    @Test(priority = 5, description = "Admin can create merchant")
    public void createMerchant() throws IOException, ParseException, ConfigurationException {
        UserModel model = Utils.generateInfo("Merchant");

        Utils.saveTheUser(model);

        UserController userController = new UserController();
        JsonPath jsonPath = userController.createUser(model);
        int userId = jsonPath.get("user.id");
        Utils.setEnv("userId", String.valueOf(userId));

    }
}
