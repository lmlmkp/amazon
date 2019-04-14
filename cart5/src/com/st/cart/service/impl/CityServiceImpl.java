package com.st.cart.service.impl;

import java.util.List;
import com.st.cart.dao.CityDao;
import com.st.cart.dao.impl.CityDaoImpl;
import com.st.cart.domain.City;
import com.st.cart.service.CityService;

public class CityServiceImpl implements CityService {

	private CityDao cityDao = new CityDaoImpl(); 
	
	@Override
	public List<City> getCitysByProvinceId(int provinceId) {
		return cityDao.getCitysByProvinceId(provinceId);
	}
}
