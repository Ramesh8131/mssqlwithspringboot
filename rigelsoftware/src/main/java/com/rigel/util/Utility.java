package com.rigel.util;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utility {
	
	 private static final String USER_AGENT = "Mozilla/5.0";

	 public static void sendHttpGETRequest() throws IOException {
		    String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAeW9wbWFpbC5jb20iLCJleHAiOjE2ODk2MjA3NTUsImlhdCI6MTY4ODIzODM1NX0.RbMimfDRxQetkuUTBDEEMz-9FeakAhtJ-zL_EfQ_5eUnd0PMqKgo60PKC1zi78ES5wI3vXE1vOLgf3AMnYj2fQ";
	        URL obj = new URL("http://localhost:4444/api/genarateReport");
	        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
	        httpURLConnection.setRequestMethod("GET");
	        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
	        httpURLConnection.setRequestProperty("Authorization", "Bearer "+token);
	        int responseCode = httpURLConnection.getResponseCode();
	        System.out.println("GET Response Code :: " + responseCode);
	        if (responseCode == HttpURLConnection.HTTP_OK) { // success
	            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();

	            while ((inputLine = in .readLine()) != null) {
	                response.append(inputLine);
	            } in .close();

	            // print result
	            System.out.println(response.toString());
	        } else {
	            System.out.println("GET request not worked");
	        }

	        for (int i = 1; i <= 8; i++) {
	            System.out.println(httpURLConnection.getHeaderFieldKey(i) + " = " + httpURLConnection.getHeaderField(i));
	        }

	    }
}
