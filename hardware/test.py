import speech_recognition as sr
import os
from mpyg321.mpyg321 import MPyg321Player
from gtts import gTTS


r = sr.Recognizer()

with sr.AudioFile("test.wav")as source:
    audio_text = r.record(source)

print(audio_text)

text = r.recognize_google(audio_text,language = "ko-KR")
print(text)
if text == '안녕하세요':
    tts = gTTS( text='안녕하세요. 반가워요. ', lang='ko', slow=False )
    filename= 'ex_ko.mp3'
    tts.save(filename) 
    os.system('mpg321 -q '+filename)
