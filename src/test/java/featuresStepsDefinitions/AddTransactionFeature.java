package featuresStepsDefinitions;

import com.actions.AddAction;
import com.console.ConsoleView;
import com.views.AddView;
import com.views.ReadView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTransactionFeature {
    AddAction addAction;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Given("I type add option")
    public void i_type_add_option() {
        addAction = new AddAction();

    }

    @When("I add transaction values")
    public void i_add_transaction_values() {
        System.setOut(new PrintStream(outputStreamCaptor));
        String input = "1\n" + "1\n" + "1\n" +"1\n" + "1" ;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        addAction.invoke("add");
        assertEquals("Adding new transaction...\r\n" + "Name: Description: Price: Transaction added succesfully!",
                outputStreamCaptor.toString().trim());
    }
    @Then("They are saved in the file")
    public void they_are_saved_in_the_file() {
        System.out.println("Transaction added succesfully!");
    }
}
