package utils;

import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import setup.UserModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static void setEnv(String key, String value) throws ConfigurationException {

        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key,value);
        config.save();
    }

    public static int generateRandomID(int min, int max){
        double random = Math.random()*(max - min) + min;
        int randId = (int) random;
        return randId;
    }

    public static UserModel generateInfo(String role) throws IOException {
        Faker fake = new Faker();
        UserModel model = new UserModel();

        model.setName(fake.name().fullName());
        model.setEmail(fake.internet().emailAddress().toLowerCase());
        model.setPassword("0123456roadtosdet");
        String phoneNumber = "01502" + generateRandomID(100000, 999999);
        model.setPhone_number(phoneNumber);
        model.setNid(String.valueOf(Utils.generateRandomID(10000000, 99999999)));
        model.setRole(role);

        return model;
    }

    public static JSONArray readJSON() throws IOException, ParseException {
        String fileLocation = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray userArray = (JSONArray) parser.parse(new FileReader(fileLocation));
        return userArray;
    }

    public static void writeJSON(JSONArray userArray) throws IOException {
        String fileLocation = "./src/test/resources/users.json";
        FileWriter writer = new FileWriter(fileLocation);

        writer.write(userArray.toString());
        writer.flush();

    }

    public static void saveTheUser(UserModel model) throws IOException, ParseException {
        JSONArray userArray = readJSON();

        JSONObject userObj = new JSONObject();
        userObj.put("name",model.getName());
        userObj.put("email", model.getEmail());
        userObj.put("password",model.getPassword());
        userObj.put("phone_number", model.getPhone_number());
        userObj.put("nid",model.getNid());
        userObj.put("role",model.getRole());

        userArray.add(userObj);

        writeJSON(userArray);

    }

}
