
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"json:build/cucumber.json", "pretty", "html:build/result.html"},
        stepNotifications = true,
        tags = "@CCPayment"
)
//Run Here
public class CucumberRunner {
}
