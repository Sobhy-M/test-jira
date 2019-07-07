package com.masary.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.masary.database.manager.MasaryManager;

public class HttpRequestParametersExtractor {

    public Map<String, String> parseRequestParameters(HttpServletRequest request) throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();

        try {
            String[] parametersArray = body.split("&");
            for (String row : parametersArray) {
                String parameterName = null;
                String parameterValue = null;

                String[] parameterValues = row.split("=");

                try {
                    parameterName = parameterValues[0];
                } catch (Exception e) {
                    MasaryManager.logger.error("Parameter name is not presented");
                }

                try {
                    parameterValue = parameterValues[1];
                } catch (Exception e) {
                    MasaryManager.logger.error("Parameter value is not presented");
                }
                if (!parameters.containsKey(parameterName)) {
                    parameters.put(parameterName, parameterValue);
                }

            }
        } catch (Exception e) {
            throw e;
        }

        return parameters;
    }
}
