package com.stone.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.stone.core.common.BaseDAO;
import com.stone.core.dao.CityDAO;
import com.stone.core.entity.City;

@Repository
public class CityDAOImpl extends BaseDAO implements CityDAO {

	@Override
	public City findCityByName(String name) {
		return (City) this.getSqlMapClientTemplate().queryForObject(
				"City.findCityByName", name);
	}

	@Override
	public City findCityByCode(String code) {
		return (City) this.getSqlMapClientTemplate().queryForObject(
				"City.findCityByCode", code);
	}

}
