/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author hammad
 */

@WebListener
public class TopupServiceIntegrationStatusExecutor implements ServletContextListener{

    private ScheduledExecutorService scheduler;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        MasaryManager.getInstance().logger.info("Starting ServiceStatusExecutor...");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        TopupServiceIntegrationStatus serviceStatus = new TopupServiceIntegrationStatus();
        scheduler.scheduleAtFixedRate(serviceStatus, 2, 60, TimeUnit.SECONDS);
        sce.getServletContext().setAttribute("orangeIntegrationStatus", serviceStatus.orangeserviceStatus);
        sce.getServletContext().setAttribute("etisalatIntegrationStatus", serviceStatus.etisalatserviceStatus);
        sce.getServletContext().setAttribute("vodafoneIntegrationStatus", serviceStatus.vodafoneserviceStatus);
        sce.getServletContext().setAttribute("vodafoneIntegrationStatus", serviceStatus.moneyTransferServiceStatus);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        MasaryManager.getInstance().logger.info("Closing ServiceStatusExecutor...");
        scheduler.shutdown();
    }
    
}
