# -*- coding: utf-8 -*-
"""
Created on Tue Sep 28 22:42:19 2021

@author: HSJ
"""

from Calendar import Calendar

from DBConnect import DBConnect

import datetime

class CalendarList:
    
    def __init__(self):
        self.cal_list = []
        self.score = 0
        
    def getCalList(self,db,date):
        sql = "select * from calendar where start BETWEEN %s AND %s or end BETWEEN %s AND %s"
        date1 = date
        date2 = date + datetime.timedelta(days=1)
        date1 = str(date1.date())
        date2 = str(date2.date())
        db.getCursor().execute(sql,(date1,date2,date1,date2))
        db.getDB().commit()
        
        self.cal_list = db.getCursor().fetchall()
        
    def makeScore(self):
        for i in range(len(self.cal_list)):
            self.score = self.score + self.calToScore(self.setCal(self.cal_list[i]))
            
    def setCal(self,cal):
        cals = Calendar()
        cals.setStart(cal[1])
        cals.setTitle(cal[2])
        cals.setDesc(cal[3])
        cals.setEnd(cal[4])
        cals.setEmail(cal[5])
        return cals
    
    def calToScore(self,cal):
        cal.calByTime()
        cal.calByDesc()
        return cal.getScore()
    
    def getScore(self):
        return self.score

db = DBConnect()

cl = CalendarList()

print(datetime.datetime.now()-datetime.timedelta(days=4))
cl.getCalList(db, datetime.datetime.now()-datetime.timedelta(days=4))


cl.makeScore()

print(cl.getScore())
