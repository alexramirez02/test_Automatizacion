package co.edu.udea.calidad.blazedemo.stepdefinitions;

import co.edu.udea.calidad.blazedemo.tasks.SelectFlightTask;
import co.edu.udea.calidad.blazedemo.ui.BlazeDemoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BlazeDemoStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver driver;

    Actor actor = Actor.named("Usuario");

    @Given("el usuario accede a la pagina de vuelos")
    public void elUsuarioAccedeAPaginaDeVuelos() {
        actor.can(BrowseTheWeb.with(driver));
        driver.get("https://blazedemo.com/");
    }

    @When("el usuario selecciona {string} como origen y {string} como destino")
    public void elUsuarioSeleccionaOrigenYDestino(String origen, String destino) {
        actor.attemptsTo(SelectFlightTask.withCities(origen, destino));
    }

    @Then("debe ver notificacion {string}")
    public void debeVerNotificacion(String mensajeEsperado) {
        assertThat(Text.of(BlazeDemoPage.NOTIFICATION).answeredBy(actor), equalTo(mensajeEsperado));
    }
}
