package com.st.cart.service;

import java.util.List;
import com.st.cart.domain.City;

public interface CityService {
	
	public List<City> getCitysByProvinceId(int provinceId);
}
