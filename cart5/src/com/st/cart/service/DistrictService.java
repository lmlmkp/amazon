package com.st.cart.service;

import java.util.List;

import com.st.cart.domain.District;

public interface DistrictService {
	public List<District> getDistrictsByCityId(int cityId);
}
