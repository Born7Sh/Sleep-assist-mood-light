# -*- coding: utf-8 -*-
"""
Created on Mon Sep 27 11:30:19 2021

@author: HSJ
"""

import datetime

class Calendar:
    
    def __init__(self):
        self.start = ""
        self.title = ""
        self.description = ""
        self.end = ""
        self.email = ""
        self.score = 3
        
    def calByTime(self):
        format = '%Y-%m-%d %H:%M:%S'
        cal_time = datetime.datetime.strptime(self.end,format) - datetime.datetime.strptime(self.start,format)
        
        if cal_time.seconds <= 10800:
            self.score = self.score - 1
        elif cal_time.seconds >=10800:
            self.score = self.score + 1
        
    def calByDesc(self):
        if len(self.description) > 30 :
            self.score = self.score + 1
    
