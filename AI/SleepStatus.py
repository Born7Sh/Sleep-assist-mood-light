# -*- coding: utf-8 -*-
"""
Created on Sun Sep 26 19:36:43 2021

@author: HSJ
"""

class SleepStatus:
    
    def __init__(self):
        self.x = 0
        self.y = 0
        self.gyro_sensor = 0
        self.sleep_id = 0
        self.time = "09:00"
    
    def setX(self,x):
        self.x = x
        
    def getX(self):
        return self.x
    
    def setY(self,y):
        self.y = y
        
    def getY(self):
        return self.y
    
    def setGyro(self,gyro):
        self.gyro_sensor = gyro
        if self.gyro_sensor > 2000:
            self.gyro_sensor = 2000
        
    def getGyro(self):
        return self.gyro_sensor
    
    def setSleepId(self,s):
        self.sleep_id = s
        
    def getSleepId(self):
        return self.sleep_id
    
    def setTime(self,time):
        self.time = time
        
    def getTime(self):
        return self.time
    
    
        