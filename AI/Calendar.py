# -*- coding: utf-8 -*-
"""
Created on Mon Sep 27 11:30:19 2021

@author: HSJ
"""

import datetime

from DBConnect import DBConnect

class Calendar:
    
    def __init__(self):
        self.start = datetime.datetime.now()
        self.title = ""
        self.description = ""
        self.end = datetime.datetime.now()
        self.email = ""
        self.score = 3
    
    def setStart(self,start):
        self.start = start
        
    def getStart(self):
        return self.start
    
    def setTitle(self,title):
        self.title = title
        
    def setDesc(self,desc):
        self.description = desc
        
    def getDesc(self):
        return self.description
    
    def setEnd(self,end):
        self.end = end
        
    def getEnd(self):
        return self.end
    
    def setEmail(self,email):
        self.email = email
        
    def getEmail(self):
        return self.email
    
    def setScore(self,score):
        self.score = score
        
    def getScore(self):
        return self.score
    
    def calByTime(self):
        #format = '%Y-%m-%d %H:%M:%S'
        #cal_time = datetime.datetime.strptime(self.end,format) - datetime.datetime.strptime(self.start,format)
        
        cal_time = self.end - self.start
        
        if cal_time.seconds <= 10800:
            self.score = self.score - 1
        elif cal_time.seconds >=10800:
            self.score = self.score + 1
        
    def calByDesc(self):
        if len(self.description) > 30 :
            self.score = self.score + 1

