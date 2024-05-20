package steps;

/*
20-May-2024 -> using Mac
Team, The idea was follow the recommendations and Tech Stack, to have an optimize and well structured code
-> when possible some functions, were re-utilized, to avoid repeating the code.
-> using global variables only when needed
-> writing clear and common sense names for steps, functions and variables.

Tech Stack: IntelliJ Idea, Cucumber, Gradle, Kotlin, Java, Gherkin, RestAssured, InBuilt Report, Git

System Under Test: a Web page call https://todo.ly/
-> Is an intuitive and easy to use online list and tasks manager.
-> It helps you to get organized, simplify your life, and to get things done.
-> In difference with Swagger PetStore, offers not only API's documentation, also a very intuitive UI,
to perform an exhaustive testing.

Test Cases: Deep validations on POST, PUT, DELETE and the Schema

The Framework is using the pattern Page Object Model (POM)
-> package Helpers: to define some constants.
-> package Runner: A Cucumber runner was configured, triggering a report at root/target
-> package Steps: where the steps definitions were defined
-> package Resources: providing the Feature file and the Schema sample
 */


import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static helpers.Constant.PWD;
import static helpers.Constant.USER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;


public class MyStepdefs {

    Response response;
    String idProject;

    @Given("I have access to App {string}")
    public void IhaveAccessToApp(String app) {
    }

    @When("I send a POST request to {string} using the body")
    public void IsendPOSTrequestUsingBody(String url,String body) {
        this.response= given().
                auth().
                preemptive()
                .basic(USER,PWD).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(body).
                when().
                post(url);

    }

    @Then("The Status Code should be {int}")
    public void TheStatusCodeShouldBe(Integer expectedCode) {
        response.then().log().all().
                statusCode(expectedCode);
    }

    @Then("The Content should be {string}")
    public void TheContentShouldBe(String valueContent) {
        response.then().
                assertThat()
                .body("Content", equalTo(valueContent));
    }

    @Then("The Icon should be {int}")
    public void TheIconShouldBe(Integer valueIcon) {
        response.then().
                assertThat()
                .body("Icon", equalTo(valueIcon));
    }

    @Then("I verify the Schema using jsonSchemaFactory")
    public void IverifyTheSchema () {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().
                setValidationConfiguration(
                        ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()
                ).freeze();

        response.then().
                assertThat().
        body(matchesJsonSchemaInClasspath("verifySchemaCreateProject.json").using(jsonSchemaFactory));
    }

    @And("I capture the Project ID into a variable")
    public void IcaptureProjectIdIntoVariable() {
        this.idProject=response.then().extract().path("Id")+"";
    }

    @When("I send a PUT request to {string} using the body")
    public void IsendPUTrequestUsingBody(String url, String body) {
        response= given().
                auth().
                preemptive()
                .basic(USER,PWD).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(body).
                when().
                put(url.replace("ID_PROYECT",this.idProject));

    }

    @When("I send a DELETE request to {string}")
    public void IsendDELETErequest(String url) {
        response= given().
                auth().
                preemptive()
                .basic(USER,PWD).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                delete(url.replace("ID_PROYECT",this.idProject));

    }

    @Then("The flag Deleted should be {string}")
    public void TheIconShouldBe(String valueDeleted) {
        response.then().
                assertThat()
                .body("Deleted", equalTo(true));
    }



}
