package com.st.cart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.cart.domain.City;
import com.st.cart.domain.District;
import com.st.cart.domain.Province;
import com.st.cart.service.CityService;
import com.st.cart.service.DistrictService;
import com.st.cart.service.ProvinceService;
import com.st.cart.service.impl.CityServiceImpl;
import com.st.cart.service.impl.DistrictServiceImpl;
import com.st.cart.service.impl.ProvinceServiceImpl;

public class CacadeServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	private ProvinceService provinceService = new ProvinceServiceImpl(); 
	private CityService cityService = new CityServiceImpl(); 
	private DistrictService districtService = new DistrictServiceImpl(); 
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tag = req.getParameter("tag");
		if("1".equals(tag)) {
			getPronvice(req, resp);
		}else if("2".equals(tag)) {
			getCitys(req, resp);
		}else if("3".equals(tag)) {
			getDistricts(req, resp);
		}
	}
	
	private void getPronvice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Province> list = provinceService.getAll();
		outJson(resp, list);
	}
	
	private void getCitys(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String provinceId = req.getParameter("provinceId");
		List<City> list = cityService.getCitysByProvinceId(Integer.parseInt(provinceId));
		outJson(resp, list);
	}
	
	private void getDistricts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cityId = req.getParameter("cityId");
		List<District> list = districtService.getDistrictsByCityId(Integer.parseInt(cityId));
		outJson(resp, list);
	}
}
