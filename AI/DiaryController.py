# -*- coding: utf-8 -*-
"""
Created on Mon Sep 27 14:45:05 2021

@author: HSJ
"""

from DBConnect import DBConnect

import datetime

import networkx
import re

from textrank import TextRank

from konlpy.tag import Komoran

class RawSentence:
    def __init__(self, textIter):
        if type(textIter) == str: self.textIter = textIter.split('\n')
        else: self.textIter = textIter
        self.rgxSplitter = re.compile('([.!?:](?:["\']|(?![0-9])))')
 
    def __iter__(self):
        for line in self.textIter:
            ch = self.rgxSplitter.split(line)
            for s in map(lambda a, b: a + b, ch[::2], ch[1::2]):
                if not s: continue
                yield s
 
class RawSentenceReader:
    def __init__(self, filepath):
        self.filepath = filepath
        self.rgxSplitter = re.compile('([.!?:](?:["\']|(?![0-9])))')
 
    def __iter__(self):
        for line in open(self.filepath, encoding='utf-8'):
            ch = self.rgxSplitter.split(line)
            for s in map(lambda a, b: a + b, ch[::2], ch[1::2]):
                if not s: continue
                yield s
 
class RawTagger:
    def __init__(self, textIter, tagger = None):
        if tagger:
            self.tagger = tagger
        else :
            from konlpy.tag import Komoran
            self.tagger = Komoran()
        if type(textIter) == str: self.textIter = textIter.split('\n')
        else: self.textIter = textIter
        self.rgxSplitter = re.compile('([.!?:](?:["\']|(?![0-9])))')
 
    def __iter__(self):
        for line in self.textIter:
            ch = self.rgxSplitter.split(line)
            for s in map(lambda a,b:a+b, ch[::2], ch[1::2]):
                if not s: continue
                yield self.tagger.pos(s)
 
class RawTaggerReader:
    def __init__(self, filepath, tagger = None):
        if tagger:
            self.tagger = tagger
        else :
            from konlpy.tag import Komoran
            self.tagger = Komoran()
        self.filepath = filepath
        self.rgxSplitter = re.compile('([.!?:](?:["\']|(?![0-9])))')
 
    def __iter__(self):
        for line in open(self.filepath, encoding='utf-8'):
            ch = self.rgxSplitter.split(line)
            for s in map(lambda a,b:a+b, ch[::2], ch[1::2]):
                if not s: continue
                yield self.tagger.pos(s)

class DiaryController:
    
    def __init__(self):
        print("시작")
        self.description = ""
        self.keyw = ""
        
    def getDay(self,dbc,email):
        sql = """select description from diary where date = %s and email = %s"""
        now = datetime.datetime.now()
        del_now = datetime.timedelta(days = 1)
        now = now-del_now
        #nowstr = now.strftime('%Y-05-11 %H:00:00')
        nowstr = now.strftime('%Y-%m-%d 08:00:00')
        dt = nowstr.split()
        dbc.getCursor().execute(sql,(dt[0],email))
        #self.weather = list(cursor.fetchall())
        self.description = dbc.getCursor().fetchall()
        dbc.getDB().commit()
        
        #print(self.description)
        
        f= open("DayDiary.txt",'w+',encoding = 'utf-8')

        f.close()
        #car num
        f= open("DayDiary.txt",'a',encoding = 'utf-8')
        f.write(self.description[0][0] + '\n')
        f.close()
    
    
    def getLast(self,dbc,email):
        sql = """select description from diary where email = %s ORDER BY id DESC LIMIT 1;"""
        now = datetime.datetime.now()
        del_now = datetime.timedelta(days = 1)
        now = now-del_now
        #nowstr = now.strftime('%Y-05-11 %H:00:00')
        nowstr = now.strftime('%Y-%m-%d 08:00:00')
        dt = nowstr.split()
        dbc.getCursor().execute(sql,(email))
        #self.weather = list(cursor.fetchall())
        self.description = dbc.getCursor().fetchall()
        dbc.getDB().commit()
        
        print(self.description)
        
        f= open("DayDiary.txt",'w+',encoding = 'utf-8')

        f.close()
        #car num
        f= open("DayDiary.txt",'a+',encoding = 'utf-8')
        f.write(self.description[0][0] + '\n')
        f.close()
    
        
    def getWeek(self,dbc,email):
        sql = "select description from diary where email = %s ORDER BY id DESC LIMIT 7;"
        now = datetime.datetime.now()
        del_now = datetime.timedelta(days = 1)
        now = now-del_now
        #nowstr = now.strftime('%Y-05-11 %H:00:00')
        nowstr = now.strftime('%Y-%m-%d 08:00:00')
        dt = nowstr.split()
        dbc.getCursor().execute(sql,email)
        #self.weather = list(cursor.fetchall())
        self.description = dbc.getCursor().fetchall()
        dbc.getDB().commit()
        
        #print(self.description)
        
        f= open("WeekDiary.txt",'w',encoding = 'utf-8')

        f.close()
        for i in range(len(self.description)):
            #car num
            f= open("WeekDiary.txt",'a',encoding = 'utf-8')
            f.write(self.description[i][0] + '\n')
            f.close()
            
        
    def getMonth(self,dbc,email):
        sql = "select description from diary where email = %s ORDER BY id DESC LIMIT 30;"
        now = datetime.datetime.now()
        del_now = datetime.timedelta(days = 1)
        now = now-del_now
        #nowstr = now.strftime('%Y-05-11 %H:00:00')
        nowstr = now.strftime('%Y-%m-%d 08:00:00')
        dt = nowstr.split()
        dbc.getCursor().execute(sql,email)
        #self.weather = list(cursor.fetchall())
        self.description = dbc.getCursor().fetchall()
        dbc.getDB().commit()
        
        #print(self.description)
        
        f= open("MonthDiary.txt",'w',encoding = 'utf-8')

        f.close()
        for i in range(len(self.description)):
            #car num
            f= open("MonthDiary.txt",'a',encoding = 'utf-8')
            f.write(self.description[i][0] + '\n')
            f.close()
            
    def getDaySum(self):
        tr = TextRank()
        tagger = Komoran()
        stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV') ])
        tr.loadSents(RawSentenceReader('DayDiary.txt'), lambda sent: filter(lambda x:x not in stopword and x[1] in ('NNG', 'NNP', 'VV', 'VA'), tagger.pos(sent)))
        tr.build()
        ranks = tr.rank()
        #for k in sorted(ranks, key=ranks.get, reverse=True)[:100]:
           #print("\t".join([str(k), str(ranks[k]), str(tr.dictCount[k])]))
        #print(tr.summarize(0.2))
        return tr.summarize(0.3)
    
    def getWeekSum(self):
        tr = TextRank()
        tagger = Komoran()
        stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV') ])
        tr.loadSents(RawSentenceReader('WeekDiary.txt'), lambda sent: filter(lambda x:x not in stopword and x[1] in ('NNG', 'NNP', 'VV', 'VA'), tagger.pos(sent)))
        tr.build()
        ranks = tr.rank()
        #for k in sorted(ranks, key=ranks.get, reverse=True)[:100]:
           #print("\t".join([str(k), str(ranks[k]), str(tr.dictCount[k])]))
        #print(tr.summarize(0.2))
        return tr.summarize(0.5)
    
    def getMonthSum(self):
        tr = TextRank()
        tagger = Komoran()
        stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV') ])
        tr.loadSents(RawSentenceReader('MonthDiary.txt'), lambda sent: filter(lambda x:x not in stopword and x[1] in ('NNG', 'NNP', 'VV', 'VA'), tagger.pos(sent)))
        tr.build()
        ranks = tr.rank()
        #for k in sorted(ranks, key=ranks.get, reverse=True)[:100]:
           #print("\t".join([str(k), str(ranks[k]), str(tr.dictCount[k])]))
        #print(tr.summarize(0.2))
        return tr.summarize(0.38)
    
    def getDayKey(self):
        
        tr = TextRank(window=5, coef=1)
        stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV'), ('없', 'VV') ])
        tr.load(RawTaggerReader('DayDiary.txt'), lambda w: w not in stopword and (w[1] in ('NNG', 'NNP', 'VV', 'VA')))
        tr.build()
        kw = tr.extract(0.1)
        for k in sorted(kw, key=kw.get, reverse=True):
            #print(kw[k])
            if kw[k] >= 0.05:
                self.keyw = self.keyw +"," + k[0][0]
        return self.keyw
    def getWeekKey(self):
                
        tr = TextRank(window=5, coef=1)
        stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV'), ('없', 'VV') ])
        tr.load(RawTaggerReader('WeekDiary.txt'), lambda w: w not in stopword and (w[1] in ('NNG', 'NNP', 'VV', 'VA')))
        tr.build()
        kw = tr.extract(0.1)
        for k in sorted(kw, key=kw.get, reverse=True):
            #print(kw[k])
            if kw[k] >= 0.05:
                self.keyw = self.keyw + "," + k[0][0]
        return self.keyw
            
    def getMonthKey(self):
                
        tr = TextRank(window=5, coef=1)
        stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV'), ('없', 'VV') ])
        tr.load(RawTaggerReader('MonthDiary.txt'), lambda w: w not in stopword and (w[1] in ('NNG', 'NNP', 'VV', 'VA')))
        tr.build()
        kw = tr.extract(0.2)
        for k in sorted(kw, key=kw.get, reverse=True):
            #print(kw[k])
            if kw[k] >= 0.05:
                self.keyw = self.keyw + "," + k[0][0]
        return self.keyw
                
    def getSumId(self,dbc,email):
        sql = "select id from diary_user where email = %s;"
        dbc.getCursor().execute(sql,email)
        #self.weather = list(cursor.fetchall())
        self.description = ()
        self.description = dbc.getCursor().fetchall()
        dbc.getDB().commit()
        print(self.description[0][0])
        if len(self.description)==0:
            sql = "insert into diary_user(email,daySum,weekSum,monthSum,dayKey,weekKey,monthKey) values (%s,%s,%s,%s,%s,%s,%s);"
            dbc.getCursor().execute(sql,(email,"","","","","",""))
            dbc.getDB().commit()
        sql = "update diary_user set daySum = %s,weekSum = %s, monthSum = %s, dayKey = %s, weekKey = %s, monthKey = %s where id = %s"
        dbc.getCursor().execute(sql,(self.getDaySum(),self.getWeekSum(),self.getMonthSum(),self.getDayKey(),self.getWeekKey(),self.getMonthKey(),self.description[0][0]))
        dbc.getDB().commit()
            
                

db = DBConnect()

dc = DiaryController()
dc.getWeek(db,"born7sh@gmail.com")
dc.getMonth(db,"born7sh@gmail.com")
#print(dc.getMonthSum())
#dc. getSumId(db,"born7sh@gmail.com")
#dc.getLast(db,"born7sh@gmail.com")
print("키",dc.getMonthKey())
print("요약",dc.getMonthSum())
#dc.getWeek(db,"born7sh@gmail.com")
