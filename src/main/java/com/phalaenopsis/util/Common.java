package com.phalaenopsis.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Common {
	public static final Logger logger = LoggerFactory.getLogger(Common.class);
	public static void responseOutWithJson(HttpServletResponse response, String username, Long userid) {		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JSONObject responseJSONObject = new JSONObject();
		try {
			responseJSONObject.put("username", username);
			responseJSONObject.put("id", userid);		
			out = response.getWriter();
			out.append(responseJSONObject.toString());			
			logger.debug("return : "+responseJSONObject.toString());
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
