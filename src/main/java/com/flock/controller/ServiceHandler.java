package com.flock.controller;

import java.net.Socket;
import java.net.URL;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flock.common.Global;
import com.flock.controller.ServiceHandler;

@RestController
public class ServiceHandler {

	private static final Logger log = LoggerFactory.getLogger(ServiceHandler.class);

	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	public ResponseEntity<String> service(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ResponseEntity<String>(ServiceHandler.sendGet().toJSONString(), HttpStatus.OK);

	}
	
	
	/**
	 * 
	 * getStatus method to check services are active or not
	 * 
	 * @return
	 * @throws Exception
	 */
	
	@SuppressWarnings("unchecked")
	private static JSONArray sendGet() throws Exception {

		log.info("*********Inside sendGet Method*********\n");

		JSONArray jArray = new JSONArray();
		int port;

		String hostIp = "";
		for (Entry<Object, Object> entry : Global.serviceUrls) {
			String url = (String) entry.getValue();
			String serviceName = (String) entry.getKey();
			URL url_obj = new URL(url);
			port = url_obj.getPort();
			hostIp = url_obj.getHost();
			String var;
			String color = "";
			try {
				Socket soc = new Socket(hostIp, port);
				var = "Active";
			} catch (Exception e) {
				var = "Not Active";
				color = "#ff9999";
			}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("name", serviceName);
			jsonObj.put("url", hostIp);
			jsonObj.put("status", var);
			jsonObj.put("port", port);
			jsonObj.put("color", color);

			jArray.add(jsonObj);
		}
		return jArray;
	}

}
