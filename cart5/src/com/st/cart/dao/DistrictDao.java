package com.st.cart.dao;

import java.util.List;

import com.st.cart.domain.District;

public interface DistrictDao {
	public List<District> getDistrictsByCityId(int cityId);
}
