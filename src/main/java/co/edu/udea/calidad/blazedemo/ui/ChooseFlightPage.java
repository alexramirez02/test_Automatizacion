package co.edu.udea.calidad.blazedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ChooseFlightPage {
    public static final Target CHOOSE_FLIGHT_BUTTON = Target.the("Botón para elegir el vuelo")
            .located(By.cssSelector("input[type='submit']"));
}
