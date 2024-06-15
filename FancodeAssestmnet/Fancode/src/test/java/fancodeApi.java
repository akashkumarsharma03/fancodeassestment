import io.qameta.allure.*;
import io.restassured.RestAssured;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import models.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Epic("API Testing")
@Feature("Todo Completion")
public class fancodeApi {

    String usersApi = "https://jsonplaceholder.typicode.com/users";
    String todosApi = "https://jsonplaceholder.typicode.com/todos";

    @Test(description = "Validate that all users from FanCode city have more than 50% of their todos completed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Users from FanCode should have more than 50% tasks completed")
    @Step("Verify users' completed tasks percentage")
    @Description("Test to verify that all users from the city FanCode have more than half of their todos completed")

    
    public void fancodetest() {

        Response usersResponse = RestAssured.get(usersApi);
        Assert.assertEquals(usersResponse.statusCode(),200);
        Response todosResponse = RestAssured.get(todosApi);
        Assert.assertEquals(todosResponse.statusCode(), 200);


        ObjectMapper objectMapper = new ObjectMapper();
        userModel[] users = null;
        try {
            users = objectMapper.readValue(usersResponse.asString(), userModel[].class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to parse users from response");
        }

        List<userModel> fanCodeUsers = Arrays.stream(users)
                .filter(user -> "FanCode".equals(user.getAddress().getCity()))
                .collect(Collectors.toList());


        todoModel[] todos = null;
        try {
            todos = objectMapper.readValue(todosResponse.asString(), todoModel[].class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to parse todos from response");
        }


        for (userModel user : fanCodeUsers) {
            List<todoModel> userTodos = Arrays.stream(todos)
                    .filter(todo -> todo.getUserId() == user.getId())
                    .collect(Collectors.toList());

            int completedTodos = (int) userTodos.stream().filter(todoModel::isCompleted).count();
            int totalTodos = userTodos.size();

            if (totalTodos > 0) {
                float completedPercentage = (float) completedTodos / totalTodos * 100;
                Assert.assertTrue(completedPercentage > 50,
                        "User " + user.getId() + " has more than 50% completed tasks");
            } else {
                Assert.fail("User " + user.getId() + " has no todos");
            }
        }
    }

    
}
