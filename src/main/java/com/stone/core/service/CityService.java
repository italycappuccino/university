package com.stone.core.service;

import com.stone.core.entity.City;

public interface CityService {

	City findCityByName(String name);

	City findCityByCode(String code);

}
