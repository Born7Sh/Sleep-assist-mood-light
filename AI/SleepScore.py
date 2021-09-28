# -*- coding: utf-8 -*-
"""
Created on Sun Sep 26 17:22:07 2021

@author: HSJ
"""

import requests, json,datetime
from SleepStatus import SleepStatus

from DBConnect import DBConnect

from Weather import Weather

class SleepScore:
    
    
    def __init__(self):
        self.score = 50
        self.humidity = 0
        self.temperature = 0
        self.sleep_status = SleepStatus()
        
    def setScoreDef(self):
        self.score = 50
        
    def getScore(self):
        return self.score
    
    def setSStatus(self,ss):
        self.sleep_status = ss
      
    def setDB(self):
        self.db = DBConnect()
        
    def setWeather(self):
        self.weather = Weather()
        
        self.weather.getWeather(self.db.getDB(), self.db.getCursor())
        
    def getSleep(self,email):
        sql = "select * from sleep where email = %s ORDER BY id desc LIMIT %s"
        self.db.getCursor().execute(sql,(email,self.need_update_count))
        self.db.getDB().commit()
        self.sleep_list = self.db.getCursor().fetchall()
        print(self.sleep_list)
        print(len(self.sleep_list))
        #for i in range(self.sleep_count - self.sleep_repo_count):
            
            
        
    def getSleepCount(self,email):
        sql = "select count(id) from sleep where email = %s;"
        self.db.getCursor().execute(sql,(email))
        self.db.getDB().commit()
        self.sleep_count = self.db.getCursor().fetchone()[0]
        print(self.sleep_count)
        sql = "select count(id) from sleep_report where email = %s"
        self.db.getCursor().execute(sql,(email))
        self.db.getDB().commit()
        self.sleep_repo_count = self.db.getCursor().fetchone()[0]
        print(self.sleep_repo_count)
        self.need_update_count = self.sleep_count - self.sleep_repo_count
        
    def timeCal(self,start,end):
        format = '%Y-%m-%d %H:%M:%S'
        self.dt_sleep_start = datetime.datetime.strptime(start,format)
        self.dt_sleep_end = datetime.datetime.strptime(end,format)
        
        self.dt_sleep_time = self.dt_sleep_end - self.dt_sleep_start
        
        #Sleeping time to seconds => sleep_time 
        #ex)5 hour to 18000
        self.sleep_time = self.dt_sleep_time.seconds
    
    def timeToScore(self):
        time_score = 0
        if self.sleep_time >= 25200 and self.sleep_time <= 32400:
            self.score = self.score + 5*int(self.sleep_time/900)
            #print("잘잠")
        elif self.sleep_time < 25200: 
            self.score = self.score - 50 + 3*int(self.sleep_time/1800)
            #print("못잠")
        elif self.sleep_time > 32400:
            #print("많이잠")
            if 3*int(self.sleep_time/900) >= 24:
                time_score = 24
            self.score = self.score - time_score
            
    def weatherToScore(self):
        if self.weather.getTemperature() < 17:
            self.score = self.score - int(17-self.weather.getTemperature())
        elif self.weather.getTemperature() > 22:
            self.score = self.score - int(self.weather.getTemperature()-22)
        
        if self.weather.getHumidity() < 40 or self.weather.getHumidity() > 60:
            self.score = self.score - 3
            
    def gyroToScore(self):
        self.score = self.score - 3*(self.sleep_status.getGyro())
        
    def elementToScore(self):
        self.score = self.score
        
    def insertScore(self):
        print("db")
        
        
            
ss = SleepStatus()
ss.setX(0)
ss.setY(0)
ss.setGyro(3)

sd = SleepScore()
sd.setDB()
sd.getSleepCount("born7sh@gmail.com")
sd.getSleep("born7sh@gmail.com")
#sd.setSStatus(ss)
"""
sd.timeCal("2021-09-24 22:00:00", "2021-09-25 18:00:00")
print(sd.sleep_time)
sd.timeToScore()
print(sd.getScore())
sd.gyroToScore()

print(sd.getScore())
"""

        
