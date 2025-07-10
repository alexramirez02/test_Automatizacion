package co.edu.udea.calidad.blazedemo.tasks;

import co.edu.udea.calidad.blazedemo.ui.ChooseFlightPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class ChooseFlightTask implements Task {
    public static ChooseFlightTask choose() {
        return Tasks.instrumented(ChooseFlightTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(ChooseFlightPage.CHOOSE_FLIGHT_BUTTON)
        );
    }
}
