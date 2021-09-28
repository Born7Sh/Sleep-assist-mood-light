# -*- coding: utf-8 -*-
"""
Created on Sun Sep 26 17:22:07 2021

@author: HSJ
"""

import requests, json,datetime
from SleepStatus import SleepStatus

from DBConnect import DBConnect

from Weather import Weather

from SleepReport import SleepReport

from CalendarList import CalendarList

from UserList import UserList

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
    
   # def setSStatus(self,ss):
    #    self.sleep_status = ss
      
    def setDB(self):
        self.db = DBConnect()
    
    def getDB(self):
        return self.db
    
    def setWeather(self,date):
        self.weather = Weather()
        
        self.weather.getWeather(self.db.getDB(), self.db.getCursor(),date)
        
    def getSleep(self,email):
        sql = "select * from sleep where email = %s ORDER BY id desc LIMIT %s"
        self.db.getCursor().execute(sql,(email,self.need_update_count))
        self.db.getDB().commit()
        self.sleep_list = self.db.getCursor().fetchall()
        #print(self.sleep_list[0])
      
    def makeSleepRepo(self,email):
        self.sleep_repo_list = []
        for i in range(self.need_update_count):
            #initialize score
            self.setScoreDef()
            #order by desc, insert old one first
            k = self.sleep_list[self.need_update_count-i-1]
            #print(self.need_update_count-i-1)
            sr = SleepReport()
            
            self.setSleepStatus(k[1])
            
            if k[2] == None:
                #print(k[1]+ datetime.timedelta(hours = 7))
                self.setWeather(k[1] + datetime.timedelta(hours = 7))
                self.timeCal(k[1],k[1] + datetime.timedelta(hours=7))
                self.timeToScore()
                self.weatherToScore()
                self.gyroToScore()
                self.CalendarToScore(email, k[1])
                #self.elementToScore(k[3])


            else:
                self.setWeather(k[2])
                self.timeCal(k[1],k[2])
                self.timeToScore()
                self.weatherToScore()
                self.gyroToScore()
                self.CalendarToScore(email, k[1])
                
            self.insertScore(id)
            #self.sleep_repo_list.append()
            
    def setSleepStatus(self,id):
        sql = "select * from sleep_status where sleep_id = %s ORDER BY id desc LIMIT 1;"
        self.db.getCursor().execute(sql,id)
        self.db.getDB().commit()
        #for Sleep_Status select
        sleep_status = self.db.getCursor().fetchone()
        if sleep_status == None:
            #print("NONE")
            self.sleep_status.setX(0)
            self.sleep_status.setY(0)
            self.sleep_status.setGyro(0)
            self.sleep_status.setSleepId(id)
        else:
            self.sleep_status.setX(sleep_status[1])
            self.sleep_status.setY(sleep_status[2])
            self.sleep_status.setGyro(sleep_status[3])
            self.sleep_status.setSleepId(sleep_status[4]) 
        
    def getSleepCount(self,email):
        sql = "select count(id) from sleep where email = %s;"
        self.db.getCursor().execute(sql,(email))
        self.db.getDB().commit()
        self.sleep_count = self.db.getCursor().fetchone()[0]
        #print(email,self.sleep_count)
        #print(self.sleep_count)
        sql = "select count(id) from sleep_report where email = %s"
        self.db.getCursor().execute(sql,(email))
        self.db.getDB().commit()
        self.sleep_repo_count = self.db.getCursor().fetchone()[0]
        #print(email,self.sleep_repo_count)
        self.need_update_count = self.sleep_count - self.sleep_repo_count
        
    def timeStrCal(self,start,end):
        format = '%Y-%m-%d %H:%M:%S'
        self.dt_sleep_start = datetime.datetime.strptime(start,format)
        self.dt_sleep_end = datetime.datetime.strptime(end,format)
        
        self.dt_sleep_time = self.dt_sleep_end - self.dt_sleep_start
        
        self.dt_sleep_time = self.dt_sleep_end - self.dt_sleep_start
        
        #Sleeping time to seconds => sleep_time 
        #ex)5 hour to 18000
        self.sleep_time = self.dt_sleep_time.seconds
    
    def timeCal(self,start,end):
        
        self.dt_sleep_time = end-start
        #Sleeping time to seconds => sleep_time 
        #ex)5 hour to 18000
        self.sleep_time = self.dt_sleep_time.seconds
    
    def timeToScore(self):
        time_score = 0
        if self.sleep_time >= 25200 and self.sleep_time <= 32400:
            self.score = self.score + 5*int((self.sleep_time-25200)/900)
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
        
    def elementToScore(self,elements):
        self.score = self.score
        
    def CalendarToScore(self,email,date):
        cl = CalendarList()
        cl.getCalList(self.db,date)
        
        cl.makeScore()
        
        self.score = self.score - cl.getScore()
        
    def insertScore(self,id):
        if self.score<3 :
            self.score = 3
        
        
    def makeScore(self,email):
        self.getSleepCount(email)
        self.getSleep(email)
        self.makeSleepRepo(email)
            
ss = SleepStatus()
ss.setX(0)
ss.setY(0)
ss.setGyro(3)

sd = SleepScore()
sd.setDB()

ul= UserList()

ul.setUserList(sd.getDB())

user_list = ul.getUserList()
for i in range(len(user_list)):
    sd.makeScore(user_list[i])
    
sd.getSleepCount("born7sh@gmail.com")
#sd.getSleep("born7sh@gmail.com")
#sd.makeScore("born7sh@gmail.com")
#sd.getSleep("born7sh@gmail.com")
#sd.setSStatus(ss)
"""
sd.timeCal("2021-09-24 22:00:00", "2021-09-25 18:00:00")
print(sd.sleep_time)
sd.timeToScore()
print(sd.getScore())
sd.gyroToScore()

print(sd.getScore())
"""

        
