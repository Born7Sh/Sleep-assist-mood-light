# -*- coding: utf-8 -*-
"""
Created on Thu Jul 29 13:18:21 2021

@author: HSJ
"""
import textrank

tr = textrank()
print('Load...')
from konlpy.tag import Komoran
tagger = Komoran()
stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV') ])
tr.loadSents(RawSentenceReader('test2.txt'), lambda sent: filter(lambda x:x not in stopword and x[1] in ('NNG', 'NNP', 'VV', 'VA'), tagger.pos(sent)))
print('Build...')
tr.build()
ranks = tr.rank()
for k in sorted(ranks, key=ranks.get, reverse=True)[:100]:
    print("\t".join([str(k), str(ranks[k]), str(tr.dictCount[k])]))
print(tr.summarize(0.2))