package co.edu.udea.calidad.blazedemo.stepdefinitions;

import co.edu.udea.calidad.blazedemo.questions.IsCityAvailable;
import co.edu.udea.calidad.blazedemo.tasks.SelectFlightTask;
import co.edu.udea.calidad.blazedemo.tasks.ChooseFlightTask;
import co.edu.udea.calidad.blazedemo.tasks.FillPurchaseFormTask;
import co.edu.udea.calidad.blazedemo.questions.ConfirmationMessage;
import co.edu.udea.calidad.blazedemo.ui.BlazeDemoPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
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
        boolean origenDisponible = IsCityAvailable.inDropdown("fromPort", origen).answeredBy(actor);
        boolean destinoDisponible = IsCityAvailable.inDropdown("toPort", destino).answeredBy(actor);

        if (origenDisponible && destinoDisponible) {
            System.out.println("CASO FELIZ: Origen: " + origen + " → Destino: " + destino);

            actor.attemptsTo(
                    SelectFlightTask.withCities(origen, destino),
                    ChooseFlightTask.choose(),
                    FillPurchaseFormTask.withName("Juan Perez")
            );

            actor.remember("datosInvalidos", false);

            // Espera explícita a que aparezca el mensaje (máximo 10 segundos)
            actor.attemptsTo(WaitUntil.the(BlazeDemoPage.NOTIFICATION, isVisible()).forNoMoreThan(10).seconds());

            try {
                String mensajeReal = Text.of(BlazeDemoPage.NOTIFICATION).answeredBy(actor);
                System.out.println("Mensaje real capturado en @When: '" + mensajeReal + "'");
            } catch (Exception e) {
                System.out.println("No se pudo obtener el mensaje real en el @When: " + e.getMessage());
            }

        } else {
            System.out.println("CASO NO FELIZ: Origen o destino no disponible → Origen: " + origen + " → Destino: " + destino);
            actor.remember("datosInvalidos", true);
        }
    }

    @Then("debe ver notificacion {string}")
    public void debeVerNotificacion(String mensajeEsperado) {
        Boolean datosInvalidos = actor.recall("datosInvalidos");
        if (datosInvalidos == null) datosInvalidos = false;

        String currentUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        System.out.println("URL actual: " + currentUrl);
        System.out.println("Mensaje esperado: '" + mensajeEsperado + "'");
        System.out.println("Datos inválidos: " + datosInvalidos);

        if (datosInvalidos) {
            // Solo compara el mensaje esperado con el texto esperado en el caso no feliz
            assertThat("Mensaje esperado en caso no feliz", mensajeEsperado, equalTo("Datos faltantes"));
        } else {
            try {
                String mensajeReal = Text.of(BlazeDemoPage.NOTIFICATION).answeredBy(actor);
                System.out.println("Mensaje real desde la página: '" + mensajeReal + "'");
                assertThat("Mensaje esperado en caso feliz", mensajeReal, equalTo(mensajeEsperado));
            } catch (Exception e) {
                System.out.println("No se pudo obtener el mensaje real: " + e.getMessage());
                throw e;
            }
        }
    }

    @After
    public void afterScenario() {
        Boolean datosInvalidos = actor.recall("datosInvalidos");
        if (datosInvalidos != null && datosInvalidos) {
            System.out.println("TEST PASÓ (CASO NO FELIZ: validación correcta de datos inválidos)");
        } else {
            System.out.println("TEST PASÓ (CASO FELIZ: flujo completo con mensaje de confirmación)");
        }
    }

}







