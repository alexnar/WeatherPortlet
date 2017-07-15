package edu.weather.datagetter.impl;

import edu.weather.datagetter.exception.DataHttpGetException;
import edu.weather.datagetter.service.DataGetterService;
import edu.weather.logger.service.WeatherAppLoggerService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DataGetterService implementation.
 */
/*@Component(name = "DataGetterServiceImpl", immediate = true)
@Service(value = DataGetterService.class)*/
@Component(
        name = "DataGetterServiceImpl",
        immediate = true,
        service = DataGetterService.class
)
public class DataGetterServiceImpl implements DataGetterService {

    private static final String WRONG_URL_MESSAGE = "Probably you specify wrong URL";
    private static final String CONNECTION_PROBLEM_MESSAGE = "Probably you have problems with your connection";
    private static final String READING_PAGE_PROBLEM_MESSAGE = "Problems while reading page html";
    private static final String GET_DATA_ERROR = "Error getting page html";

    @Reference
    private WeatherAppLoggerService logger;


    @Override
    public StringBuilder getDataFromUrl(String url) throws DataHttpGetException {
        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        StringBuilder htmlResponse = new StringBuilder();

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity httpEntity = response.getEntity();
            InputStream connectInputStream = httpEntity.getContent();
            InputStreamReader connectInputStreamReader = new InputStreamReader(connectInputStream);
            BufferedReader bufferedReader = new BufferedReader(connectInputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                htmlResponse.append(line);
            }
        } catch (UnknownHostException e) {
            logger.log(Level.WARNING, WRONG_URL_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR);
        } catch (ClientProtocolException e) {
            logger.log(Level.WARNING, CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR);
        } catch (IOException e) {
            logger.log(Level.WARNING, READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR);
        }
        return htmlResponse;
    }
}
