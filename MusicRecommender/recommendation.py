import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import pairwise_distances

class recommendation():
    def __init__(self):
        self.usercols = []
        self.userdes = 0
        self.ratingcols = []
        self.ratings = 0
        self.itemcol = []
        self.item = 0
        self.totaluser = 0
        self.totalartist = 0
        self.item_matrix = 0
        self.usersimilarity = 0
        self.itemsimilarity = 0
        self.prdiction = []

    def Initialize(self):
        self.usercols = ['user_id', 'age', 'sex', 'occupation', 'zip_code']
        self.ratingcols = ['user_id', 'artist_id', 'rating', 'unix_timestamp']
        self.userdes = pd.read_csv('u.user', sep='|', names=self.usercols,encoding='latin-1')
        self.ratings = pd.read_csv('u.data', sep='\t', names=self.ratingcols,encoding='latin-1')
        self.itemcol = ['artist_id', 'artist_name' ,'rock', 'pop', 'metal', 'hiphop', 'rap', 'electronic', 'folk', 'country']
        self.item = pd.read_csv('u.item', sep='|', names=self.itemcol,encoding='latin-1')
        #print(self.item.head())
        #print(self.ratings.head())

    def CreateModel(self):
        self.totaluser = self.ratings.user_id.unique().shape[0]
        self.totalartist = self.ratings.artist_id.unique().shape[0]
        self.item_matrix = np.zeros((self.totaluser+1, self.totalartist))
        self.item_matrix[1, :] = np.random.randint(6, size=self.totalartist)
        for line in self.ratings.itertuples():
            self.item_matrix[line[1] , line[2]-1 ] = line[3]

    def CalculateSimilarity(self):
        self.usersimilarity = pairwise_distances(self.item_matrix, metric='cosine')
        self.itemsimilarity = pairwise_distances(self.item_matrix.transpose(), metric='cosine')

    def Predict(self, type='user'):
        if type == 'user':
            mean_user_rating = self.item_matrix.mean(axis=1)
            ratings_diff = (self.item_matrix - mean_user_rating[:, np.newaxis])
            self.prdiction = mean_user_rating[:, np.newaxis] + self.usersimilarity.dot(ratings_diff) / np.array(
                [np.abs(self.usersimilarity).sum(axis=1)]).transpose()
        elif type == 'item':
            self.prdiction = self.item_matrix.dot(self.itemsimilarity) / np.array([np.abs(self.itemsimilarity).sum(axis=1)])

    def GetPredictedArtist(self):
        retrive_data = {}
        artist_name = []

        for index in range(5):
            maxindex = np.argmax(self.prdiction[1,:])
            self.prdiction[1, maxindex] = 0
            artist_name.append(self.item[self.item.artist_id==maxindex]['artist_title'].iloc[0])

        retrive_data.update({"Artist":artist_name})
        return retrive_data
