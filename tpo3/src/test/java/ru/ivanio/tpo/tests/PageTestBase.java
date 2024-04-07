package ru.ivanio.tpo.tests;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import ru.ivanio.tpo.CustomParameterResolver;
import ru.ivanio.tpo.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;


@ExtendWith(CustomParameterResolver.class)
public abstract class PageTestBase {
  protected static String baseUrl;
  protected static List<Capabilities> capabilities;

  @ParameterizedTest(name = "{0}")
  @MethodSource("allDrivers")
  @Retention(RetentionPolicy.RUNTIME)
  protected @interface TestWithAllDrivers { }
  static Stream<Arguments> allDrivers() {
    return capabilities.stream().map(caps -> Arguments.of(WebDriverPool.DEFAULT.getDriver(caps)));
  }

  @BeforeAll
  public static void loadConfig() throws Throwable {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    capabilities = config.getCapabilities();
  }

  @BeforeEach
  public void prepareContext(WebDriver driver) {
    driver.manage().window().maximize();
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    preparePages(driver);
  }
  protected abstract void preparePages(WebDriver driver);



  @AfterEach
  public void quitDriver(WebDriver driver) {
    driver.quit();
  }

}
