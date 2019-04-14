package com.st.cart.service.impl;

import java.util.List;
import com.st.cart.dao.ProvinceDao;
import com.st.cart.dao.impl.ProvinceDaoImpl;
import com.st.cart.domain.Province;
import com.st.cart.service.ProvinceService;

public class ProvinceServiceImpl implements ProvinceService {

	private ProvinceDao provinceDao = new ProvinceDaoImpl();
	
	@Override
	public List<Province> getAll() {
		return provinceDao.getAll();
	}
}
