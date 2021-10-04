package org.kpu.mood.service;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.kpu.mood.domain.SleepVO;
import org.kpu.mood.persistence.SleepDAO;
import org.kpu.mood.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
public class SleepServiceImpl implements SleepService {
	
	@Autowired
	private SleepDAO sleepDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public int insertSleep(SleepVO sleepVO) throws Exception {
		String ip = userDAO.readIP(sleepVO.getEmail());
	
		try {
			URL url = new URL("http://"+ip+"/warmwhite");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(2);
			conn.setReadTimeout(3);
			conn.setRequestMethod("GET");
			System.out.println("응답코드 : " + conn.getResponseCode());
			conn.disconnect();
		}catch (SocketTimeoutException e){
			System.out.println(ip+" mood light is off now");
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		return sleepDAO.insert(sleepVO);
	}
	public void updateSleep(SleepVO sleepVO) throws Exception {
		sleepDAO.update(sleepVO);
	}
}
