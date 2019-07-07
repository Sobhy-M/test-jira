package com.masary.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author tamer
 *
 * SystemSettingsUtil is used to provide access to system settings which is
 * located at a properties file 'config.properties' in System's class path
 */
public class SystemSettingsUtil
{

    private static SystemSettingsUtil instance = null;

    public static SystemSettingsUtil getInstance()
    {
        if (instance == null)
        {
            instance = new SystemSettingsUtil();
        }// end if
        
        return instance;
    }// end of method getInstance

    public String loadProperty(String propertyName)
    {
        String propertyValue = null;
        
        try
        {
            Properties configs = new Properties();
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null)
            {
                configs.load(inputStream);
                propertyValue = configs.getProperty(propertyName);
                inputStream.close();
            }// end if
        }// end try
        catch (Exception e)
        {
            e.printStackTrace();
        }// end catch

        return propertyValue;
    }// end of method loadProperty
    
}// end of class SystemSettingsUtil