import pickle
from flask import Flask, jsonify
import pyrebase
import parselmouth
from parselmouth.praat import call, run_file
import glob
import pandas as pd
import numpy as np
import scipy
from scipy.stats import binom
from scipy.stats import ks_2samp
from scipy.stats import ttest_ind
import os
import json
import urllib
import flask
import subprocess



config={
    "apiKey": "AIzaSyDeFLJFr2aqcAK40nZOmBtEDNYij49yyAk",
    "authDomain": "healdon-916dd.firebaseapp.com",
    "databaseURL": "https://healdon-916dd.firebaseio.com",
    "projectId": "healdon-916dd",
    "storageBucket": "healdon-916dd.appspot.com",
    "messagingSenderId": "756073662506",
    "appId": "1:756073662506:web:2f4cb2e5e93f1d4b9d1b53"
}
firebase=pyrebase.initialize_app(config)


model = pickle.load(open('model.pkl','rb'))

# app
app = Flask(__name__)

# routes
@app.route('/', methods=['POST'])

def predict():
    print("server called")
    # get data
    data = flask.request.get_json(force=True)
    data.update((x, [y]) for x, y in data.items())
    data_df = pd.DataFrame.from_dict(data)
    
    u_id="Utkarsh5470" 
    lang=data_df["language"]
    filename=data_df["filename"]
    print("Data retrived")
    
    storage_path="audio/"+str(filename)+".mp3"
    try:
        storage = firebase.storage()
        storage.child(storage_path).download("downloaded.mp3")
    except:
        print("Download Failed")
    
    c=os.getcwd()
           
    subprocess.call(['ffmpeg', '-i', c+'/downloaded.mp3',
                   'converted.wav'])

   
    print("Successfully Converted .mp3 into .wav")
    
    p="converted"
   

    ar_rate=articulation_rate(p,c)
    rate_sph=rate_of_speech(p,c)
    no_pause=number_of_pauses(p,c)
    speak_dur=speaking_duration(p,c)
    org_dur=original_duration(p,c)

    data=[ar_rate,rate_sph,no_pause,speak_dur,org_dur,lang]
    df = pd.DataFrame([data],columns=['articulation_rate', 'rate_of_speech', 'number_of_pauses', 'speaking_duration', 'original_duration', 'language'])
    print("data frame created")
    # predictions
    result = model.predict(df)
    print("model output=",result)
    
    

    output = {'model_prediction':str(int(result[0])),
              'articulation rate': str(ar_rate),
             'rate of speech': str(rate_sph),
             'number of pauses': str(no_pause),
             'speking duration':str(speak_dur),
             'original duration': str(org_dur)}
    json_data = json.dumps(output).encode()
    
    request = urllib.request.Request("https://healdon-916dd.firebaseio.com/users/"+u_id+"/test/voice.json", data=json_data, method="PATCH")
    try:
        loader = urllib.request.urlopen(request)
    except urllib.error.URLError as e:
        message = json.loads(e.read())
        
    # return data
    return jsonify(results=output)

######################################################

def articulation_rate(m,p):
    sound=p+"/"+m+".wav"
    sourcerun=p+"/myspsolution.praat"
    path=p+"/"
    try:
        objects= run_file(sourcerun, -20, 2, 0.3, "yes",sound,path, 80, 400, 0.01, capture_output=True)
        print (objects[0]) # This will print the info from the sound object, and objects[0] is a parselmouth.Sound object
        z1=str( objects[1]) # This will print the info from the textgrid object, and objects[1] is a parselmouth.Data object with a TextGrid inside
        z2=z1.strip().split()
        z3=int(z2[3]) # will be the integer number 10
        z4=float(z2[3]) # will be the floating point number 8.3
        print ("articulation_rate=",z3,"# syllables/sec speaking duration")
    except:
        z3=0
        print ("Try again the sound of the audio was not clear")
    return z3;

def rate_of_speech(m,p):
    sound=p+"/"+m+".wav"
    sourcerun=p+"/myspsolution.praat"
    path=p+"/"
    try:
        objects= run_file(sourcerun, -20, 2, 0.3, "yes",sound,path, 80, 400, 0.01, capture_output=True)
        print (objects[0]) # This will print the info from the sound object, and objects[0] is a parselmouth.Sound object
        z1=str( objects[1]) # This will print the info from the textgrid object, and objects[1] is a parselmouth.Data object with a TextGrid inside
        z2=z1.strip().split()
        z3=int(z2[2]) # will be the integer number 10
        z4=float(z2[3]) # will be the floating point number 8.3
        print ("rate_of_speech=",z3,"# syllables/sec original duration")
    except:
        z3=0
        print ("Try again the sound of the audio was not clear")
    return z3;

def number_of_pauses(m,p):
    sound=p+"/"+m+".wav"
    sourcerun=p+"/myspsolution.praat"
    path=p+"/"
    try:
        objects= run_file(sourcerun, -20, 2, 0.3, "yes",sound,path, 80, 400, 0.01, capture_output=True)
        print (objects[0]) # This will print the info from the sound object, and objects[0] is a parselmouth.Sound object
        z1=str( objects[1]) # This will print the info from the textgrid object, and objects[1] is a parselmouth.Data object with a TextGrid inside
        z2=z1.strip().split()
        z3=int(z2[1]) # will be the integer number 10
        z4=float(z2[3]) # will be the floating point number 8.3
        print ("number_of_pauses=",z3)
    except:
        z3=0
        print ("Try again the sound of the audio was not clear")
    return z3;

def speaking_duration(m,p):
    sound=p+"/"+m+".wav"
    sourcerun=p+"/myspsolution.praat"
    path=p+"/"
    try:
        objects= run_file(sourcerun, -20, 2, 0.3, "yes",sound,path, 80, 400, 0.01, capture_output=True)
        print (objects[0]) # This will print the info from the sound object, and objects[0] is a parselmouth.Sound object
        z1=str( objects[1]) # This will print the info from the textgrid object, and objects[1] is a parselmouth.Data object with a TextGrid inside
        z2=z1.strip().split()
        z3=int(z2[3]) # will be the integer number 10
        z4=float(z2[4]) # will be the floating point number 8.3
        print ("speaking_duration=",z4,"# sec only speaking duration without pauses")
    except:
        z4=0
        print ("Try again the sound of the audio was not clear")
    return z4;


def original_duration(m,p):
    sound=p+"/"+m+".wav"
    sourcerun=p+"/myspsolution.praat"
    path=p+"/"
    try:
        objects= run_file(sourcerun, -20, 2, 0.3, "yes",sound,path, 80, 400, 0.01, capture_output=True)
        print (objects[0]) # This will print the info from the sound object, and objects[0] is a parselmouth.Sound object
        z1=str( objects[1]) # This will print the info from the textgrid object, and objects[1] is a parselmouth.Data object with a TextGrid inside
        z2=z1.strip().split()
        z3=int(z2[3]) # will be the integer number 10
        z4=float(z2[5]) # will be the floating point number 8.3
        print ("original_duration=",z4,"# sec total speaking duration with pauses")
    except:
        z4=0
        print ("Try again the sound of the audio was not clear")
    return z4;

if __name__ == '__main__':
    app.run(port = 5000, debug=True,use_reloader=False)
