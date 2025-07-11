package co.edu.udea.calidad.blazedemo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectFlightTask implements Task {

    private final String origen;
    private final String destino;

    public SelectFlightTask(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public static SelectFlightTask withCities(String origen, String destino) {
        return instrumented(SelectFlightTask.class, origen, destino);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        boolean origenValido = true;
        boolean destinoValido = true;

        try {
            Select selectOrigen = new Select(driver.findElement(By.name("fromPort")));
            selectOrigen.selectByVisibleText(origen);
        } catch (NoSuchElementException e) {
            origenValido = false;
        }

        try {
            Select selectDestino = new Select(driver.findElement(By.name("toPort")));
            selectDestino.selectByVisibleText(destino);
        } catch (NoSuchElementException e) {
            destinoValido = false;
        }

        boolean datosInvalidos = !(origenValido && destinoValido);
        actor.remember("datosInvalidos", datosInvalidos);

        if (!datosInvalidos) {
            driver.findElement(By.cssSelector("input[type='submit']")).click();
        } else {
            System.out.println("No se puede continuar: Datos faltantes o incorrectos.");
        }
    }
}
