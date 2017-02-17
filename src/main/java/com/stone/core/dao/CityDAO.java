package com.stone.core.dao;

import com.stone.core.entity.City;

public interface CityDAO {

	City findCityByName(String name);

	City findCityByCode(String code);

}
