package config;

import lombok.SneakyThrows;
import lombok.Synchronized;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

public class TestConfiguration {
    private static Configuration instance;

    private TestConfiguration() {
    }

    @SneakyThrows
    @Synchronized
    private static void loadConfiguration() {
        if (instance == null) {
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(new Parameters().properties()
                                    .setFileName(System.getProperty("autotest.config.file", "autotest.properties")));
            instance = builder.getConfiguration();
        }
    }

    public static Configuration getConfiguration() {
        if (instance == null) {
            loadConfiguration();
        }
        return instance;
    }
}
