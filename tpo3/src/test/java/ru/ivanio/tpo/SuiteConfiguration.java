package ru.ivanio.tpo;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Loads test suite configuration from resource files.
 */
public class SuiteConfiguration {

  private static final String DEBUG_PROPERTIES = "/debug.properties";

  private Properties properties;

  public SuiteConfiguration() throws IOException {
  	this(System.getProperty("application.properties", DEBUG_PROPERTIES));
  }

  public SuiteConfiguration(String fromResource) throws IOException {
    properties = new Properties();
    properties.load(SuiteConfiguration.class.getResourceAsStream(fromResource));
  }

  public List<Capabilities> getCapabilities() throws IOException {
    String[] capabilitiesFiles = properties.getProperty("capabilities").split(",");

    List<Capabilities> allCapabilities = new ArrayList<>();

    for (var capabilitiesFile : capabilitiesFiles) {
      Properties capsProps = new Properties();
      capsProps.load(SuiteConfiguration.class.getResourceAsStream(capabilitiesFile));

      DesiredCapabilities capabilities = new DesiredCapabilities();
      for (String name : capsProps.stringPropertyNames()) {
        String value = capsProps.getProperty(name);
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
          capabilities.setCapability(name, Boolean.valueOf(value));
        } else if (value.startsWith("file:")) {
          capabilities.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
        } else if (name.equals("driverKey")) {
          System.setProperty(value, properties.getProperty(value));
        } else if (name.equalsIgnoreCase("webdriver.firefox.bin")) {
          System.setProperty(name, value);
        } else {
          capabilities.setCapability(name, value);
        }
      }
      allCapabilities.add(capabilities);
    }

    return allCapabilities;
  }

  public boolean hasProperty(String name) {
    return properties.containsKey(name);
  }

  public String getProperty(String name) {
    return properties.getProperty(name);
  }
}
