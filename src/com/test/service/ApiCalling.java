package com.test.service;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiCalling {
	public static void main(String[] args) {
	
	// Change this to a valid token:
    //connection.setRequestProperty("Authorization", "Bearer <TOKEN ID>");
	//:8443/cbs-service/api/v1/reverseFundTransfer

		try {

			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "Habibur Rahman");
			map.put("email", "habib1@gmail.com");
			map.put("phone", "01559026095");

			String postUrl = "http://localhost:8078/emp";
			String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjM4OTYyMzAyMDgyMTUzNDUsImlhdCI6MTY0NTQ1NzI4OX0.fGD-uE_C6LoEVixTB4_V-MB_asU8XhDNyuruT_nW43c";

			postReverseTxnRequest(map, postUrl, token);
			//postReverseTxnRequest(map, postUrl);

		} catch (Exception e) {
			System.out.println("Custom Error");
		}
	}	

	public static void postReverseTxnRequest(Map<String, String> params, String urlParam) {
		try {

			URL url = new URL(urlParam);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String strParams = convertMapToString(params);

			OutputStream os = conn.getOutputStream();
			os.write(strParams.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void postReverseTxnRequest(Map<String, String> params, String urlParam, String token) {
		try {

			URL url = new URL(urlParam);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+token);

			String strParams = convertMapToString(params);

			OutputStream os = conn.getOutputStream();
			os.write(strParams.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	private static String convertMapToString(Map<String, String> params) {
		if (params.size() > 0) {
			StringBuilder mapAsString = new StringBuilder();
			mapAsString.append("{");
			for (Map.Entry<String, String> entry : params.entrySet()) {
				mapAsString.append("\"");
				mapAsString.append(entry.getKey());
				mapAsString.append("\"");
				mapAsString.append(":");
				mapAsString.append("\"");
				mapAsString.append(entry.getValue());
				mapAsString.append("\"" + ",");
			}
			mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
			return mapAsString.toString();
		} else {
			return "";
		}
	}	
	
}
