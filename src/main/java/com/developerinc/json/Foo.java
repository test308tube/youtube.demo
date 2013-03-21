package com.developerinc.json;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.util.concurrent.ConcurrentHashMap;


public class Foo
{
	private final ConcurrentHashMap <String,ItemDTO> cmap = new ConcurrentHashMap(); 
	
  public static void main(String[] args) throws Exception
  {
	  
	 new Foo();
   
    
  }
  
  
  Foo(){
	  Gson gson = new Gson();
	    TypeDTO[] myTypes;
		try {
			
			myTypes = gson.fromJson(new FileReader("f:/c_source/chapter_3/input.json"), TypeDTO[].class);
		
			for (TypeDTO i : myTypes){
		    	for (ItemDTO iDTO : i.items){
		    		cmap.put(i.name, iDTO);
		    		
		    	}
		    } 
			
			ItemDTO idto = cmap.get("oracle_dev");
			if(idto != null)
				System.out.println(idto.name);
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
