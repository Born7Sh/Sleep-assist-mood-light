package org.kpu.mood.service;

import java.net.HttpURLConnection;
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
		URL url = new URL("http://"+ip+"/warmwhite");
		System.out.println(url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		System.out.println("응답코드 : " + conn.getResponseCode());

		conn.disconnect();
		return sleepDAO.insert(sleepVO);
	}
	public void updateSleep(SleepVO sleepVO) throws Exception {
		sleepDAO.update(sleepVO);
	}
}
