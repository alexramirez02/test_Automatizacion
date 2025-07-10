package co.edu.udea.calidad.blazedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PurchasePage {
    public static final Target INPUT_NAME = Target.the("Nombre del pasajero").located(By.id("inputName"));
    public static final Target PURCHASE_BUTTON = Target.the("Botón Purchase Flight").located(By.cssSelector("input[type='submit']"));
}
