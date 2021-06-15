package featuresStepsDefinitions;

import com.actions.AddAction;
import com.actions.ReadAction;
import com.console.ConsoleView;
import com.views.AddView;
import com.views.ReadView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadTransactionFeature {
    ReadAction readAction;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Given("I type read option")
    public void i_type_read_option() throws IOException {
        readAction = new ReadAction();
    }

    @When("I click enter")
    public void i_click_enter() {
        System.setOut(new PrintStream(outputStreamCaptor));

        readAction.invoke("read");
    }

    @Then("I see transactions")
    public void i_see_transactions() {
        assertEquals("Reading transactions...", outputStreamCaptor.toString().trim().substring(0,23));
    }
}
