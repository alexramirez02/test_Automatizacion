package co.edu.udea.calidad.blazedemo.tasks;

import co.edu.udea.calidad.blazedemo.ui.PurchasePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;

public class FillPurchaseFormTask implements Task {

    private final String nombre;

    public FillPurchaseFormTask(String nombre) {
        this.nombre = nombre;
    }

    public static FillPurchaseFormTask withName(String nombre) {
        return Tasks.instrumented(FillPurchaseFormTask.class, nombre);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(nombre).into(PurchasePage.INPUT_NAME),
                Click.on(PurchasePage.PURCHASE_BUTTON)
        );
    }
}
