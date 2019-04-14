package com.st.cart.dao;

import java.util.List;

import com.st.cart.domain.City;

public interface CityDao {
	public List<City> getCitysByProvinceId(int provinceId);
}
