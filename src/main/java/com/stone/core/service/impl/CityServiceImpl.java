package com.stone.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stone.core.dao.CityDAO;
import com.stone.core.entity.City;
import com.stone.core.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Resource
	private CityDAO cityDAO;

	@Override
	public City findCityByName(String name) {
		return cityDAO.findCityByName(name);
	}

	@Override
	public City findCityByCode(String code) {
		return cityDAO.findCityByCode(code);
	}

}
