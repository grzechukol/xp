package featuresStepsDefinitions;

import com.actions.AddAction;
import com.console.ConsoleView;
import com.views.AddView;
import com.views.ReadView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;

public class AddTransactionFeature {
    AddView addView;
    AddAction addAction;
    ReadView readView;
    ConsoleView consoleView;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Given("I type add option")
    public void i_type_add_option() {
        // Write code here that turns the phrase above into concrete actions
//        addAction = new AddAction();
        System.out.println("Nothing");

        throw new io.cucumber.java.PendingException();
    }

    @When("I can add transaction values")
    public void i_can_add_transaction_values() {
        // Write code here that turns the phrase above into concrete actions
//        addAction = new AddAction();
//        addAction.invoke("add");
//        addAction.invoke("1");
//        addAction.invoke("2");
//        addAction.invoke("3");
        System.out.println("Nothing");

        throw new io.cucumber.java.PendingException();
    }
    @Then("They are saved in the file")
    public void they_are_saved_in_the_file() {
        // Write code here that turns the phrase above into concrete actions
//        addView.show();
//        Assertions.assertEquals("Adding new transaction...", outputStreamCaptor.toString());

        System.out.println("Nothing2");
        throw new io.cucumber.java.PendingException();
    }
}
