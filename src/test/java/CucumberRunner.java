
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"json:build/cucumber.json", "pretty", "html:build/result"},
        stepNotifications = true,
        tags = "@TC04"
)
//Run Here
public class CucumberRunner {
}
