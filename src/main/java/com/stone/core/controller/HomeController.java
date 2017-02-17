package com.stone.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stone.core.entity.City;
import com.stone.core.service.CityService;

@Controller
@Slf4j
public class HomeController {
	final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
			.create();

	@Resource
	private CityService cityService;

	@RequestMapping(value = "/queryCity", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryCity(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			String cityCode = request.getParameter("cityCode");
			City city = cityService.findCityByCode(cityCode);
			map.put("city", city);
		} catch (Exception e) {
			log.error(
					"queryCity failed. parameters="
							+ gson.toJson(request.getParameterMap()), e);
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		log.info(gson.toJson(map));
		return gson.toJson(map);
	}

	@RequestMapping(value = "/queryCityWithJsonp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryCityWithJsonp(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			String cityCode = request.getParameter("cityCode");
			City city = cityService.findCityByCode(cityCode);
			if (city == null) {
				map.put("success", false);
				map.put("msg", "no matched info");
			} else {
				map.put("city", city);
			}
		} catch (Exception e) {
			log.error(
					"queryCity failed. parameters="
							+ gson.toJson(request.getParameterMap()), e);
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		log.info(gson.toJson(map));
		String callback = request.getParameter("callback");
		String result = callback + "(" + gson.toJson(map) + ")";
		return result;
	}

}
