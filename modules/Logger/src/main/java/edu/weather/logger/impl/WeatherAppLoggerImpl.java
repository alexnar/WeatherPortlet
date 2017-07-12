package edu.weather.logger.impl;

import edu.weather.logger.service.WeatherAppLoggerService;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.logging.*;

/**
 * Weather application global logger
 */
@Component(
        name = "Logger",
        immediate = true,
        service = WeatherAppLoggerService.class
)
public class WeatherAppLoggerImpl implements WeatherAppLoggerService {
    private static final String LOGGER_SETUP_ERROR_MESSAGE = "Logger setup error!";
    private static final String LOG_FILE_PATH = "../weather-log.txt";
    private static final boolean APPEND_LOG_FILE_MODE_ON = true;

    private final Logger logger;

    public WeatherAppLoggerImpl() {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        setupLogger();
    }

    private void setupLogger() {
        logger.removeHandler(new ConsoleHandler());
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, APPEND_LOG_FILE_MODE_ON);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.WARNING, LOGGER_SETUP_ERROR_MESSAGE, e);
        }
    }

    @Override
    public void log(Level level, String msg, Throwable thrown) {
        logger.log(level, msg, thrown);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }




}
