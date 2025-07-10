package co.edu.udea.calidad.blazedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConfirmationPage {
    public static final Target CONFIRMATION_MESSAGE = Target.the("Mensaje de confirmación")
            .located(By.tagName("h1"));
}
