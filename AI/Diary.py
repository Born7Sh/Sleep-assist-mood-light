# -*- coding: utf-8 -*-
"""
Created on Sun Sep 26 19:40:30 2021

@author: HSJ
"""

class Diary:
    
    def __init__(self):
        self.date = ""
        self.description = ""
        self.email = ""
        
    def setDate(self,date):
        self.date = date
        
    def getDate(self):
        return self.date
    
    def setDescription(self,des):
        self.description = des
        
    def getDescription(self):
        return self.description
    
    def setEmail(self,email):
        self.email = email
        
    def getEmail(self):
        return self.email