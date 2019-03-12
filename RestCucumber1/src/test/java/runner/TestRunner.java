package runner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "steps" }, tags = { "@singleKey" }, plugin = { "pretty",
"html:target/cucumber" }, monochrome = false)

 
public class TestRunner {

}
