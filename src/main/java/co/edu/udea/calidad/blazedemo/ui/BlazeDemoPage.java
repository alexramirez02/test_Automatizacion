package co.edu.udea.calidad.blazedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BlazeDemoPage {

    public static final Target FROM_CITY = Target.the("Ciudad de origen").located(By.name("fromPort"));
    public static final Target TO_CITY = Target.the("Ciudad de destino").located(By.name("toPort"));
    public static final Target FIND_FLIGHTS_BUTTON = Target.the("Buscar vuelos").located(By.cssSelector("input[type='submit']"));
    public static final Target NOTIFICATION = Target.the("Mensaje de confirmación").located(By.tagName("h3"));
}
