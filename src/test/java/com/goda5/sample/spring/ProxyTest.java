package com.goda5.sample.spring;

import org.junit.Test;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @Test
    public void testDateFormat() throws ParseException {
        String date = "2016-08-08";
        String format = "yyyy-MM-dd";
        String format1 = "yyyy-MM-dd'Z'";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parsed = simpleDateFormat.parse(date);
        System.out.println(parsed);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(format1);
        String format2 = simpleDateFormat1.format(parsed);
        System.out.println(format2);
    }
}
