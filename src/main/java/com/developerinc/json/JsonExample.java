package com.developerinc.json;

import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.google.gson.*;


import java.io.*;
import java.util.*;

public class JsonExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JsonExample();
	}
	
	JsonExample(){
		JSONParser parser = new JSONParser();
		 
		try {
	 
			Object obj = parser.parse(new FileReader("f:/c_source/chapter_3/test.json"));
	 
			JSONObject jsonObject = (JSONObject) obj;
	 
			//String name = (String) jsonObject.g
			//System.out.println(name);
	 
			
	 
			// loop array
			
//			Iterator<String> iterator = msg.iterator();
//			while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
