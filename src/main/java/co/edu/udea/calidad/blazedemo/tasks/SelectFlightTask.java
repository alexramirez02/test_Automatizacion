package co.edu.udea.calidad.blazedemo.tasks;

import co.edu.udea.calidad.blazedemo.interactions.ClickButtonAction;
import co.edu.udea.calidad.blazedemo.ui.BlazeDemoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class SelectFlightTask implements Task {

    private final String origen;
    private final String destino;

    public SelectFlightTask(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public static SelectFlightTask withCities(String origen, String destino) {
        return Tasks.instrumented(SelectFlightTask.class, origen, destino);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(origen).from(BlazeDemoPage.FROM_CITY),
                SelectFromOptions.byVisibleText(destino).from(BlazeDemoPage.TO_CITY),
                ClickButtonAction.on(BlazeDemoPage.FIND_FLIGHTS_BUTTON)
        );
    }
}