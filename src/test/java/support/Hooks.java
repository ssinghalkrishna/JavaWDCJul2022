
package support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

//import static support.TestContext.getPosition;
//import static support.TestContext.getRecruiter;

public class Hooks {

//    @Before(value = "@create_position", order = 1)
//    public void createPosition() throws Exception {
//        new SlavaRestWrapper().login(getRecruiter())
//                                .createPosition(getPosition());
//        //to write as .login().createPosition() make it return itself ie RestWrapper,
//        // else write new RestWrapper()... twice or more
//        //higher order num has higher priority, also put it above lower num due to a possible bug
//
//    }
    @Before(order = 0)
    public void scenarioStart() {
        TestContext.initialize();
        TestContext.getDriver().manage().deleteAllCookies();
        TestContext.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After(order = 0)
    public void scenarioEnd(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshotTaker = (TakesScreenshot) TestContext.getDriver();
            byte[] screenshot = screenshotTaker.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
       // TestContext.close();
    }
}


