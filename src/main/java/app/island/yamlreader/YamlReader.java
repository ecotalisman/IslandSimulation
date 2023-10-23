package app.island.yamlreader;

import app.island.annotations.Config;
import app.island.exceptions.IslandException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public class YamlReader {
    private static YamlReader instance;

    private YamlReader() {

    }

    public static YamlReader getInstance() {
        if (instance == null) {
            instance = new YamlReader();
        }
        return instance;
    }

    public <T> T loadConfiguredObject(@NotNull Class<T> type) {
        if (!type.isAnnotationPresent(Config.class)) {
            throw new IllegalArgumentException(type.getSimpleName() + " must have the @Config annotation");
        }
        return readYaml(getConfigFileLocation(type), type);
    }

    private URL getConfigFileLocation(@NotNull Class<?> type) {
        Config annotation = type.getAnnotation(Config.class);
        return type.getClassLoader().getResource(annotation.fileName());
    }

    private <T> T readYaml(@NotNull URL filePath, Class<T> type) {
        YAMLMapper yamlMapper = new YAMLMapper();
        T readObject;
        try {
            readObject = yamlMapper.readValue(filePath, type);
        } catch (IOException e) {
            String message = String.format("Error reading config file: %s for class: %s",
                    filePath.getFile(),
                    type);
            throw new IslandException(message, e);
        }
        return readObject;
    }
}
