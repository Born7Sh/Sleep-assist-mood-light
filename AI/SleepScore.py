# -*- coding: utf-8 -*-
"""
Created on Sun Sep 26 17:22:07 2021

@author: HSJ
"""

import requests, json,datetime
import SleepStatus


class SleepScore:
    
    
    def __init__(self):
        self.score = 50
        self.humidity = 0
        self.temperature = 0
        self.sleep_status = SleepStatus()
        
        
        
        
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
        if self.sleep_time >= 25200 or self.sleep_time <= 32400:
            self.score = self.score + 5*int(self.sleep_time/900)
        elif self.sleep_time < 25200: 
            self.score = self.score - 50 + 3*int(self.sleep_time/1800)
        elif self.sleep_time > 32400:
            if 3*int(self.sleep_time/900) >= 24:
                time_score = 24
            self.score = self.score - time_score
            
    def weatherToScore(self):
        if self.temperature < 17:
            self.score = self.score - int(17-self.temperature)
        elif self.temperature > 22:
            self.score = self.score - int(self.temperature-22)
        
        if self.humidity < 40 or self.humidity > 60:
            self.score = self.score - 3
            
    def gyroToScore(self):
        self.score = self.score - 3*(self.sleep_status.getGyro())
        
    def 
        
        
            
        
        


sl = SleepScore();

print(sl.timeCal("2021-09-04 07:00:00","2021-09-04 12:00:00"))
        