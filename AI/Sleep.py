# -*- coding: utf-8 -*-
"""
Created on Tue Sep 28 21:06:04 2021

@author: HSJ
"""

class Sleep:
    
    def __init__(self):
        self.id = ""
        self.start = ""
        self.end = ""
        self.elements = ""
        self.email = ""
        
    def setId(self,id):
        self.id = id
    
    def getId(self):
        return self.id
    
    def setStart(self,start):
        self.start = start
        
    def getStart(self):
        return self.start

    def setEnd(self,end):
        self.end = end
        
    def getEnd(self):
        return self.end
    
    def setElements(self,elements):
        self.elements = elements
    
    def getElements(self):
        return self.elements
    
    def setEmail(self,email):
        self.email = email
        
    def getEmail(self):
        return self.email