package edu.weather.logger.service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger interface.
 * Define method of getting logger.
 */
public interface WeatherAppLoggerService {
    Logger getLogger();
    void log(Level level, String msg, Throwable thrown);
}
