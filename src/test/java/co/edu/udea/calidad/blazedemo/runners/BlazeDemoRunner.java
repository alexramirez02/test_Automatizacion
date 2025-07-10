package co.edu.udea.calidad.blazedemo.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = "co.edu.udea.calidad.blazedemo.stepdefinitions"
)
public class BlazeDemoRunner {
}