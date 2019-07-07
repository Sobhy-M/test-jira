package com.masary.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masary.database.manager.MasaryManager;

public class PerformanceFilter implements Filter
{

	@Override
	public void destroy()
	{
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        String requestURI = request.getRequestURI();
        
        long startTime = System.currentTimeMillis();
     
        chain.doFilter(request, response);
       
        long elapsed = System.currentTimeMillis() - startTime;
        
        if(elapsed >= 5000 )
        {
        	String message = "[PERFORMANCE] URI: " + requestURI + " ELAPSED TIME: " + elapsed + " ms."; 
            MasaryManager.logger.error(message);
        }
        
	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		
	}

}
