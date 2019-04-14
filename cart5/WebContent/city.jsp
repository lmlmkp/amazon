<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript">
			$(function(){
				var province = $("#province");
				var city = $("#city");
				var district = $("#district");
				
				$.ajax({
					url:"cascade", //请求的地址
					type:"get", //表示的是请求的方式
					data: {tag:1}, //表示前端发送到后台的数据
					dataType:"json", //表示服务器这边返回的数据类型
					success: function(_data){ //
						// [{id:1, name:"beijing"}, {}]
						for(var i = 0; i < _data.length; i++) {
							province.append("<option value='" + _data[i].id + "'>" + _data[i].name + "</option>");
						}
					}
				});
				
				console.log(34);
				
				province.change(function() {
					$("#city > option:gt(0)").remove();
					$("#district > option:gt(0)").remove();
					var provinceId = province.val();
					$.get("cascade", {tag:2, provinceId:provinceId}, function(_data){
						for(var i = 0; i < _data.length; i++) {
							city.append("<option value='" + _data[i].id + "'>" + _data[i].name + "</option>");
						}
					}, "json");
				});
				
				city.change(function() {
					$("#district > option:gt(0)").remove();
					var cityId = city.val();
					$.get("cascade", {tag:3, cityId:cityId}, function(_data){
						for(var i = 0; i < _data.length; i++) {
							district.append("<option value='" + _data[i].id + "'>" + _data[i].name + "</option>");
						}
					}, "json");
				});
			});
		</script>
	</head>
<body>
	省份: <select id="province">
			<option value="-1">请选择省份</option>
	     </select>
	城市: <select id="city">
			<option value="-1">请选择城市</option>
	     </select>
	地区: <select id="district">
			<option value="-1">请选择地区</option>
	     </select>
</body>
</html>