package co.edu.udea.calidad.blazedemo.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class NotificationMessageQuestion implements Question<String> {

    private final Target messageElement;

    public NotificationMessageQuestion(Target messageElement) {
        this.messageElement = messageElement;
    }

    public static NotificationMessageQuestion textOf(Target messageElement) {
        return new NotificationMessageQuestion(messageElement);
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(messageElement).answeredBy(actor);
    }
}
