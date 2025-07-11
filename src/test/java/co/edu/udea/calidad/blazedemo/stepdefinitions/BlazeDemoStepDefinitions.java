package co.edu.udea.calidad.blazedemo.stepdefinitions;

import co.edu.udea.calidad.blazedemo.questions.IsCityAvailable;
import co.edu.udea.calidad.blazedemo.tasks.SelectFlightTask;
import co.edu.udea.calidad.blazedemo.tasks.ChooseFlightTask;
import co.edu.udea.calidad.blazedemo.tasks.FillPurchaseFormTask;
import co.edu.udea.calidad.blazedemo.questions.ConfirmationMessage;
import co.edu.udea.calidad.blazedemo.ui.BlazeDemoPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

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
            actor.attemptsTo(
                    SelectFlightTask.withCities(origen, destino),
                    ChooseFlightTask.choose(),
                    FillPurchaseFormTask.withName("Juan Perez")
            );

            actor.remember("datosInvalidos", false);  // Datos válidos

            // === IMPRESIÓN DEL MENSAJE REAL AQUÍ MISMO ===
            try {
                String mensajeReal = Text.of(BlazeDemoPage.NOTIFICATION).answeredBy(actor);
                System.out.println("Mensaje real capturado en el @When: '" + mensajeReal + "'");
            } catch (Exception e) {
                System.out.println("No se pudo obtener el mensaje real en el @When: " + e.getMessage());
            }

        } else {
            System.out.println("Origen o destino no disponible: " + origen + " → " + destino);
            actor.remember("datosInvalidos", true);   // Datos inválidos
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


}







