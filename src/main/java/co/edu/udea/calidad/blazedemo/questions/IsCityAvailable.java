package co.edu.udea.calidad.blazedemo.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IsCityAvailable implements Question<Boolean> {

    private final String city;
    private final String dropdownName;

    public IsCityAvailable(String dropdownName, String city) {
        this.city = city;
        this.dropdownName = dropdownName;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver driver = (WebDriver) actor.abilityTo(net.serenitybdd.screenplay.abilities.BrowseTheWeb.class).getDriver();
        WebElement dropdown = driver.findElement(By.name(dropdownName));
        return dropdown.getText().contains(city);
    }

    public static IsCityAvailable inDropdown(String dropdownName, String city) {
        return new IsCityAvailable(dropdownName, city);
    }
}
