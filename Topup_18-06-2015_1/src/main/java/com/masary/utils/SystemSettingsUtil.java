package com.masary.utils;

import com.masary.common.CONFIG;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

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

    public static Logger logger = Logger.getLogger(CONFIG.APP_LOG);

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
            Properties baseConfigs = new Properties();
            Properties configs = new Properties();
            
            String baseFileName = "config.properties";
            
            InputStream baseInputStream = getClass().getClassLoader().getResourceAsStream(baseFileName);
            
            String propFileName = null;
            
            if (baseInputStream != null)
            {
                baseConfigs.load(baseInputStream);
                propFileName = baseConfigs.getProperty("config.properties.name");
                baseInputStream.close();
            }// end if
            
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
            logger.error("error in getting property value " + e.getMessage() , e);
        }// end catch

        return propertyValue;
    }// end of method loadProperty
    
}// end of class SystemSettingsUtil