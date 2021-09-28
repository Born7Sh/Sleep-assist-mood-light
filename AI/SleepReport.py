# -*- coding: utf-8 -*-
"""
Created on Tue Sep 28 21:39:51 2021

@author: HSJ
"""

class SleepReport:
    
    def __init__(self):
        self.id = ""
        self.email = ""
        self.score = ""
        self.sleeping_time = ""
        self.date = ""
        
    def setId(self,id):
        self.id = id
        
    def getId(self):
        return self.id

    def setEmail(self,email):
        self.email = email
    
    def getEmail(self):
        return self.email
    
    def setScore(self,score):
        self.score = score
    
    def getScore(self):
        return self.score
    
    def setSleepTime(self,st):
        self.sleeping_time = st
        
    def getSleepTime(self):
        return self.sleeping_time
    
    def setDate(self,date):
        self.date = date
        
    def getDate(self):
        return self.date