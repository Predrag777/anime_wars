from flask import Flask, request, jsonify
import numpy as np
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
import pandas as pd
from tensorflow.keras.models import load_model
import re

app = Flask(__name__)


@app.route('/api/receive', methods=['GET'])

def AI_answer():
    #load neural network
    #model = tf.keras.models.load_model('anime_wars_model/model_AI_V3.h5')


    arg1 = float(request.args.get('arg1'))/1000
    arg2 = float(request.args.get('arg2'))/800
    arg3 = float(request.args.get('arg3'))/1000
    arg4 = float(request.args.get('arg4'))/800
    arg5 = float(request.args.get('arg5'))/100
    arg6 = float(request.args.get('arg6'))/100

    model = load_model('anime_wars_model/model_AI_V2.h5')

    new_input = np.array([[arg1,arg2,arg3,arg4,arg5,arg6]])


    prediction = model.predict(new_input)

    predicted_class = np.argmax(prediction, axis=1)[0]

    action_map = {1: 'right', 2: 'left', 3: 'jump', 4: 'attack', 5: 'other'}
    predicted_action = action_map[predicted_class]
    print(predicted_action)
    return jsonify({"message": predicted_action})



if __name__ == '__main__':
    app.run(debug=True, port=5000)

