# -*- coding: utf-8 -*-
"""
Created on Mon Sep 27 12:22:54 2021

@author: HSJ
"""

class User:
    
    def __init__(self):
        self.email = ""
        
    def setEmail(self,email):
        self.email = email
    
    def getEmail(self):
        return self.email