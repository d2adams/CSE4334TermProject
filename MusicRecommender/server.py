from flask import Flask, request,jsonify
import search as ts
import naiveBayes as nb
import recommendation as cr


app = Flask(__name__)

SearchObj = ts.search()
FileHandlingObj = SearchObj.getFileReadObj()
naivebObj = nb.navieBayes()
RecObj = cr.recommendation()


@app.route('/')
def WelcomeToDataMining():
   return 'Welcome To Data Mining'


@app.route('/search/',methods=['POST'])
def searchText():
    req_data = request.get_json()
    print(req_data)
    if 'searchString' in req_data:
        searchQ = req_data['searchString']

    ResultData = SearchQuery(searchQ)
    if ResultData == []:
        ResultData = {"Artist": ["NA","NA","NA","NA","NA"],
            "description": ["NA", "NA" ,"NA","NA" ,"NA"]}
    print("Search Result : method :searchText File :DataMiningServer.py")
    print(ResultData)
    return jsonify(ResultData)

@app.route('/classification/',methods=['POST'])
def classificationdata():
    req_data = request.get_json()
    print(req_data)
    if 'classification' in req_data:
        searchQ = req_data['classification']

    ResultData = ClassificationT(searchQ)
    if ResultData == []:
        ResultData = {"Artist": ["NA", "NA", "NA", "NA", "NA"],
                      "Genre": ["NA", "NA", "NA", "NA", "NA"]}
    print("classificationdata : method :classificationdata File :server.py")
    print(ResultData)
    return jsonify(ResultData)


@app.route('/recommendation/',methods=['POST'])
def recommendationdata():
    req_data = request.get_json()
    print(req_data)
    if 'Recommendation' in req_data:
        searchQ = req_data['Recommendation']

    ResultData = Recommendation(searchQ)
    if ResultData == []:
        ResultData = {"Artist": ["NA", "NA", "NA", "NA", "NA"]}
    print("Recommendation : method :Recommendation File :server.py")
    print(ResultData)
    return jsonify(ResultData)


def InitialiseSearchObject():
    print("Initialising search Object")
    SearchObj.Read_and_initialise_document()
    print("Initialising term frequency")
    SearchObj.Calculating_Document_frequency()
    print("Search Initialise")

def InitializeClassificationObject():
    print("Initialising Classification Object")
    naivebObj.setFileReadObj(FileHandlingObj)
    naivebObj.Initialize()
    naivebObj.CalculateClassProbability()
    print("Classification Initialise")

def InitializeRecommendationSystem():
    RecObj.Initialize()
    RecObj.CreateModel()
    RecObj.CalculateSimilarity()
    RecObj.Predict()

def SearchQuery(query):
    print("Inside Search Query Server: server.py")
    return SearchObj.Search(query)

def ClassificationT(query):
    print("Inside ClassificationT Server: server.py")
    PredictedClass = naivebObj.CalculateTermProbablity(query)
    print(PredictedClass)
    return PredictedClass

def Recommendation(query):
    print("Inside Recommendation Server: server.py")
    recommendated_data = RecObj.GetPredictedArtist()
    print(recommendated_data)
    return recommendated_data


InitialiseSearchObject()
InitializeClassificationObject()
InitializeRecommendationSystem()

if __name__ == '__main__':
   app.run()
