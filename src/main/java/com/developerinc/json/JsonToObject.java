package com.developerinc.json;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


class JsonToObject
{
	final Gson gson = new Gson();
	private final ConcurrentHashMap <String,ItemURL> cmap = new ConcurrentHashMap<String,ItemURL>(); 
	TypeDataSource [] myTypes;
	
  public static void main(String[] args) throws Exception
  {
	  
	 new JsonToObject();
   
    
  }
  
  private TypeDataSource [] getDataSource(){
	  try {
		myTypes = gson.fromJson(new FileReader("f:/c_source/chapter_3/datasource.json"), TypeDataSource[].class);
	} catch (JsonSyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonIOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return myTypes; 
  }
  
  
  private JsonToObject(){
	  	
	    TypeDataSource [] tempDataSource;
		try {
			tempDataSource = gson.fromJson(new FileReader("f:/c_source/chapter_3/datasource.json"), TypeDataSource[].class);
			myTypes = tempDataSource;
			
			for (TypeDataSource i : myTypes){
				
		    	for (ItemURL iDTO : i.urls){
		    	
		    		cmap.put(iDTO.getName(), iDTO);
		    		System.out.println(iDTO.getName());
		    	}
		    } 
				
			
			ItemURL iurl = cmap.get("oracle_dev");
			System.out.println("Server: " + iurl.getType());
			
			for (Entry<String, ItemURL> iu : cmap.entrySet()){
				
				System.out.println(iu.getValue().getType());
			}
			
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
  }
}

