# -*- coding: utf-8 -*-
"""
Created on Mon Sep 27 15:19:14 2021

@author: HSJ
"""

import datetime
from DBConnect import DBConnect

class Weather:
    
   # def __init__(self): 
        
        
    def setWeather(self,weathers):
        self.datev = weathers[0][0]
        self.temperature = weathers[0][1]
        self.humidity = weathers[0][2]
        self.precipitation_type = weathers[0][3]  
        self.fine_dust10 = weathers[0][4]
        self.fine_dust2_5 = weathers[0][5]

    def getTemperature(self):
        return self.temperature
    
    def getPrec_type(self):
        return self.precipitation_type
    
    def getHumidity(self):
        return self.humidity

    def getDust25(self):
        return 15
    
    def getWeather(self,db,cursor):
        sql = """select * from current_weather where date_time = %s"""
        now = datetime.datetime.now()
        del_now = datetime.timedelta(hours = 1)
        now = now-del_now
        #nowstr = now.strftime('%Y-05-11 %H:00:00')
        nowstr = now.strftime('%Y-09-27 08:00:00')
        dt = nowstr.split()
        cursor.execute(sql,(dt[0]))
        self.weather = list(cursor.fetchall())
        db.commit()
        
        print(self.weather)
        self.setWeather(self.weather)
        
