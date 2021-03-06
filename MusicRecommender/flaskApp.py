from flask import Flask, request,jsonify
import search as ts
import classification as clf


app = Flask(__name__)

SearchObj = ts.CTextSearch()
FileHandlingObj = SearchObj.getFileReadObj()
ClassificationObj = clf.CClassification()


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

    ResultData = ClassificationT()
    if ResultData == []:
        ResultData = {"Artist": ["NA", "NA", "NA", "NA", "NA"],
                      "Genre": ["NA", "NA", "NA", "NA", "NA"]}
    print("classificationdata : method :classificationdata File :DataMiningServer.py")
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
    ClassificationObj.setFileReadObj(FileHandlingObj)
    ClassificationObj.Initialize()
    ClassificationObj.CreateTraingData()
    ClassificationObj.TrainingClassification()
    print("Classification Initialise")

def SearchQuery(query):
    print("Inside Search Query Server: DataMiningServer.py")
    return SearchObj.Search(query)

def ClassificationT():
    print("Inside ClassificationT Server: DataMiningServer.py")
    [data, actual_label, index] = ClassificationObj.getTestData()
    #print(data)
    print(actual_label)
    PredictedClass = ClassificationObj.PredictedClass(data, index)
    return PredictedClass

InitialiseSearchObject()
InitializeClassificationObject()

if __name__ == '__main__':
   app.run()
