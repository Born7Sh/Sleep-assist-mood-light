# -*- coding: utf-8 -*-
"""
Created on Mon Sep 27 14:46:32 2021

@author: HSJ
"""
from DBConnect import DBConnect

class UserList:
    
    def __init__(self):
        self.user_list = []
    
    
    def setUserList(self,db):
        sql = "select username from user;"
        db.getCursor().execute(sql)
        db.getDB().commit()
        
        user_list = db.getCursor().fetchall()
        
        for i in range(len(user_list)):
            self.user_list.append(user_list[i][0])
        
    def getUserList(self):
        return self.user_list

    