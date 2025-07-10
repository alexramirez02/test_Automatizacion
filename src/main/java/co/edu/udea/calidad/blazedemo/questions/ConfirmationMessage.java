package co.edu.udea.calidad.blazedemo.questions;

import co.edu.udea.calidad.blazedemo.ui.ConfirmationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ConfirmationMessage implements Question<String> {

    public static ConfirmationMessage value() {
        return new ConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ConfirmationPage.CONFIRMATION_MESSAGE).answeredBy(actor);
    }
}
