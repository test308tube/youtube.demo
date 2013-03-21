package com.developerinc.xml;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;


public class XMLConfig {
	
	final static Logger logger = LoggerFactory.getLogger(XMLConfig.class);
	
	public static void main(String [] args){
	
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		 
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	    // print logback's internal status
	    StatusPrinter.print(lc);
	    
		try {
			XMLConfiguration xmlconfig = new XMLConfiguration("/resources/config.xml");
			xmlconfig.setExpressionEngine(new XPathExpressionEngine());
			
			
			
			
//			for(String items : list){
//				System.out.println(items);
//			}
//			
//			String k = xmlconfig.getString("//database[1]/name");        
//			System.out.println("Next " + k);
			
			 SubnodeConfiguration c2 =
				 xmlconfig.configurationAt("databases");
			 
			
			 Iterator <String> iter = c2.getKeys();
			while (iter.hasNext()){
				String s = iter.next();
				int cnt = 1;
				//System.out.println(s);
				if(s.equals("database/name")){
					
					String [] str =xmlconfig.getStringArray("databases/database/name");
					for (String k : str){
						logger.debug(" -- " + k);
						
					}
					
					ArrayList <String> listitem = (ArrayList)c2.getList(s);
					for(String item : listitem){
						Iterator list = xmlconfig.getKeys("databases/database[" + cnt++ + "]");
						while (list.hasNext()){
							String newitem = (String) list.next();
							System.out.println(xmlconfig.getString(newitem));
							
						}
						//System.out.println(cnt + " " + item);
						//System.out.println(xmlconfig.getString("databases/database[" + cnt++ + "]/UserName"));
					}
					//System.out.println(c2.getList(s));
					listitem.clear();
					listitem = null;
					
//					System.out.println(s);
				}
			
			}
			
			 
			// 127.0.0.1
			
			
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new XMLConfig();
	}
	
	XMLConfig(){
		
	}
}