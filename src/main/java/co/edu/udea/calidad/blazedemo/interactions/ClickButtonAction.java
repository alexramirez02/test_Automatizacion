package co.edu.udea.calidad.blazedemo.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class ClickButtonAction implements Interaction {
    private final Target button;

    public ClickButtonAction(Target button) {
        this.button = button;
    }

    public static ClickButtonAction on(Target button) {
        return Tasks.instrumented(ClickButtonAction.class, button);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(button));
    }
}