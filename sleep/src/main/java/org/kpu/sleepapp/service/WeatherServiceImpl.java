package org.kpu.sleepapp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kpu.sleepapp.domain.ServiceKey;
import org.kpu.sleepapp.domain.WeatherVO;
import org.kpu.sleepapp.persistence.WeatherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WeatherService")
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	private WeatherDAO weatherDAO;

	public void getNowWeather() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));

		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);
		
		LocalTime now = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
		
		String tempTime = now.format(formatter);
		System.out.println(tempTime);
		// JSON데이터를 요청하는 URLstr을 만듭니다.
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
		// 홈페이지에서 받은 키
		// 다른곳을 서비스키 이동시켜야 함.
		ServiceKey servicekey = new ServiceKey();
		String serviceKey = servicekey.getWeatherAPI();
		String pageNo = "1";
		String numOfRows = "225"; // 한 페이지 결과 수
		String data_type = "JSON"; // 타입 xml, json 등등 ..
		String baseDate = tempDate; // "20200821"이런식으로 api에서 제공하는 형식 그대로 적으시면 됩니당.
		
		String baseTime = tempTime+"30"; // API 제공 시간을 입력하면 됨
		System.out.println(baseTime);
		String nx = "59"; // 위도
		String ny = "125"; // 경도

		// 전날 23시 부터 153개의 데이터를 조회하면 오늘과 내일의 날씨를 알 수 있음
		WeatherVO TW = new WeatherVO();
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(data_type, "UTF-8")); /* 타입 */	
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="+ URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
		urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="+ URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
		urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // 경도
		urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")+"&"); // 위도
	
	
		 /* GET방식으로 전송해서 파라미터 받아오기*/
		URL url = new URL(urlBuilder.toString());
		// 어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
		System.out.println(url);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String result = sb.toString();
		System.out.println("결과: " + result);
		// 문자열을 JSON으로 파싱합니다. 마지막 배열형태로 저장된 데이터까지 파싱해냅니다.
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
		JSONObject parse_response = (JSONObject) jsonObj.get("response");
		JSONObject parse_header = (JSONObject) parse_response.get("header");
		JSONObject parse_body = (JSONObject) parse_response.get("body");// response 로 부터 body 찾아오기
		JSONObject parse_items = (JSONObject) parse_body.get("items");// body 로 부터 items 받아오기
		// items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다.
		JSONArray parse_item = (JSONArray) parse_items.get("item");
																	
		JSONObject obj,obj1;
		String category,resultCode; // 기준 날짜와 기준시간을 VillageWeather 객체에 저장합니다.
		
		//List<WeatherVO> datalist = new ArrayList<WeatherVO>();
		obj1 = (JSONObject) parse_header;
		resultCode = (String) obj1.get("resultCode");
		System.out.println(resultCode);
		if(resultCode.equals("00")) {
			for (int i = 0; i < parse_item.size(); i++) {
				obj = (JSONObject) parse_item.get(i); // 해당 item을 가져옵니다.
				category = (String) obj.get("category"); // item에서 카테고리를 검색해옵니다.
				// 검색한 카테고리와 일치하는 변수에 문자형으로 데이터를 저장합니다.
				
				switch (category) {
				case "REH":
					TW.setHumidity(Integer.parseInt((String) obj.get("obsrValue")));
					break;
				case "T1H":
					TW.setTemperature(Float.parseFloat((String) obj.get("obsrValue")));
					break;
				case "PTY":
					TW.setPrecipitation_type(Integer.parseInt((String) obj.get("obsrValue")));
				}
				
			}	
			DateFormat sdFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			String tempDate1 = sdFormat1.format(nowDate);
			
			TW.setDatetime(tempDate1+" "+tempTime+":"+"00"+":"+"00");
			weatherDAO.insertNow(TW);
		}
	}
	
	public void getTomWeather() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));

		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);
		
		LocalTime now = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
		
		String tempTime = now.format(formatter);
		System.out.println(tempTime);
		// JSON데이터를 요청하는 URLstr을 만듭니다.
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";
		// 홈페이지에서 받은 키
		// 다른곳을 서비스키 이동시켜야 함.
		ServiceKey servicekey = new ServiceKey();
		String serviceKey = servicekey.getWeatherAPI();
		String pageNo = "1";
		String numOfRows = "225"; // 한 페이지 결과 수
		String data_type = "JSON"; // 타입 xml, json 등등 ..
		String baseDate = tempDate; // "20200821"이런식으로 api에서 제공하는 형식 그대로 적으시면 됩니당.
		
		String baseTime = "12"+"30"; // API 제공 시간을 입력하면 됨
		System.out.println(baseTime);
		String nx = "59"; // 위도
		String ny = "125"; // 경도

		// 전날 23시 부터 153개의 데이터를 조회하면 오늘과 내일의 날씨를 알 수 있음
		WeatherVO TW = new WeatherVO();
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(data_type, "UTF-8")); /* 타입 */	
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="+ URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
		urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="+ URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
		urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // 경도
		urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")+"&"); // 위도
	
	
		 /* GET방식으로 전송해서 파라미터 받아오기*/
		URL url = new URL(urlBuilder.toString());
		// 어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
		System.out.println(url);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String data= sb.toString();
		
		// Json parser를 만들어 만들어진 문자열 데이터를 객체화 
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(data); 
		// response 키를 가지고 데이터를 파싱 
		JSONObject parse_response = (JSONObject) obj.get("response"); 
		// response 로 부터 body 찾기
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
		// body 로 부터 items 찾기 
		JSONObject parse_items = (JSONObject) parse_body.get("items");

		// items로 부터 itemlist 를 받기 
		JSONArray parse_item = (JSONArray) parse_items.get("item");
			
			
			
		JSONObject weather = new JSONObject();
		Object fcstTime;
		Object category;
		Object value;
		String fcstTime1, category1, value1;
		/**
		 * parse_item.size()/10 : 몇 개의 시간 데이터를 받아 왔는지
		 * 받아온 데이터의 갯수 / 시간당 데이터 갯수 = 
		 */
		int dataSize = parse_item.size()/60;
		
		//6시간의 기상예보를 담을 리스트
		List<WeatherVO> weatherList = new ArrayList<WeatherVO>();
		
		for(int i=0;i<dataSize;i++) {
			for(int k=0;k<60;k++) {
				WeatherVO weatherVO = new WeatherVO();
				weather = (JSONObject) parse_item.get((i*dataSize)+k);
				fcstTime = weather.get("fcstTime");
				category = weather.get("category");
				value = weather.get("fcstValue");
				fcstTime1 = (String)fcstTime;
				category1 = (String)category;
				value1 = (String)value;
				
				//PTY의 값과 시간 넣기
				if(category1.equals("PTY")) {
					weatherVO.setDatetime(fcstTime1);
					weatherVO.setPrecipitation_type(Integer.parseInt(value1));
					weatherList.add(weatherVO);
				}
				//온도 값 넣기
				if(category1.equals("T1H")) {
					for(int j=0;j<6;j++) {
						if(weatherList.get(j).getDatetime().equals((String)fcstTime1)) {
							weatherList.get(j).setTemperature(Float.parseFloat(value1));
						}
					}
				}
				//습도 값 넣기
				if(((String)category).equals("REH")) {
					for(int j=0;j<6;j++) {
						if(weatherList.get(j).getDatetime().equals((String)fcstTime1)) {
							weatherList.get(j).setHumidity(Integer.parseInt(value1));
						}
					}
				}
				//check
				System.out.println(fcstTime+" : " +category+"  "+value);
			}
		}
		
		DateFormat sdFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate1 = new Date();
		String tempDate1 = sdFormat1.format(nowDate1);
		
		//정상적으로 작동하는지 체크
//		for(WeatherVO i : weatherList) {
//			System.out.println(i.getDatetime()+" "+i.getHumidity()+" "+i.getPrecipitation_type()+" "+i.getTemperature());
//		}
		//DB의 DATETIME에 맞추기 위해 수정.
		for(WeatherVO i : weatherList) {
			String temp;
			if(!(i.getDatetime().substring(0, 2).equals("00"))) {
				temp = tempDate1+" "+i.getDatetime().substring(0,2)+":00:00";
				i.setDatetime(temp);
			}else {
				Calendar cal = Calendar.getInstance();
				cal.setTime(nowDate1);
				cal.add(Calendar.DATE, 1);
				String tomorrow = sdFormat1.format(cal.getTime());
				i.setDatetime(tomorrow+" "+i.getDatetime().substring(0,2)+":00:00");
			}
			weatherDAO.insertForecast(i);		
		}

	}
}
