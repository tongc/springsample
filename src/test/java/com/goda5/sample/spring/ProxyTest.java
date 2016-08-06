package com.goda5.sample.spring;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by tong on 06/08/2016.
 */
public class ProxyTest {
    @Test
    public void userCanLoginByUsername() {
        System.setProperty("selenide.browser", "com.goda5.sample.spring.CustomWebDriverProvider");

        open("/login");
        $(By.name("user.name")).setValue("johny");
        $("#submit").click();
        $(".loading_progress").should(disappear); // Waits until element disappears
        $("#username").shouldHave(text("Hello, Johny!")); // Waits until element gets text
    }
}
