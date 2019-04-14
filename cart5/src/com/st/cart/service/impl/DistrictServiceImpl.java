package com.st.cart.service.impl;

import java.util.List;

import com.st.cart.dao.DistrictDao;
import com.st.cart.dao.impl.DistrictDaoImpl;
import com.st.cart.domain.District;
import com.st.cart.service.DistrictService;

public class DistrictServiceImpl implements DistrictService{
	private  DistrictDao DistrictDao = new DistrictDaoImpl();

	@Override
	public List<District> getDistrictsByCityId(int cityId) {
		return DistrictDao.getDistrictsByCityId(cityId);
	}}
