# CSE4334TermProject

**Summary**
This project is a basic music recommender system used to practice the concepts of data mining.  A basic text search feature is implemented using a Support Vector Machine, Genre classification is done by Naive Bayes classification, and music artist recommendation is done by item-based recommendation.  The user is able to enter a text query into the app which is sent to the server, hosted by pythonanywere, to be worked on.

**Deployment**
Deployment is done via Android Studio.
1. Import the files into Android Studio.
2. Connect the phone to the computer via USB.
3. Select the phone and run the application.
4. The app will be installed onto the phone, click the icon to use the app.

**Phase I**
The first phase of the development is to implement the search feature using support vector machine. SVM involves creating a hyperplane and margins for the classes to properly find the text most similar to the entered query. I used the dataset from lastfm that contains various artists, their tagged genres, and the number of users that have them tagged. For this project I am creating a Android phone app that has the functionality hosted on a flask server via pythonanywhere.

During this phase of the project I was having problems with the search taking too much time to find results, and that the app would crash while trying to get the search results. I am currently looking for methods that would make run time for the search more efficient, so that I can implement them for the later phases. The problem with the app crashing was that the write could not complete without blocking. I was able to temporarily stop the problem by reducing the size of the dataset; however, that is not a permanent solution and I am looking into a way to fix it. My contribution over the references is making the program work for the artist data instead of the generic example in the code.

**Phase II**
For the second phase of development I was able to find some methods that could reduce the time for searches by storing some of the values on the server itself. The primary part of this phase is implementing a text classifier to find the genre based on user input. I have currently been trying to implement either Naives Bayes or k-nearest neighbors, and see which one has the best results.  Naive Bayes classification works by converting the data into frequency tables that are used to find the posterior probabilitie of the genres to accurately predict which genre the entered query is.

I am still having some issues with proper classification which might be due to the dataset or the algorithm not being properly written. Also, depending on the changes I make to the code, the app starts to crash again when the user tries the functions on the app. My contribution over the references is making the program work for the artist data instead of the generic example in the code.

**Phase III**
The third phase of development involved finding a method to give recommendations to the user of genres of music they may prefer. The algorithm looks at the information of the user then the type of music they listen to. This creates an idea of what type of music to suggest to users with similar characteristics.  The algorithm uses a matrix fitting the information into the matrix as a 0 or 1 then calculates the similarity to give a recommendation.

The biggest problem with this phase is trying to get proper recommendations and to tailor the information toward music artist. There is not really any examples of what to do for this type of recommendation, so trying to rewrite code to fit my data set has been a challenge. My contribution over the references is making the program work for the artist data instead of the generic example in the code.

**References**
https://github.com/mnielsen/VSM/blob/master/vsm.py
https://www.analyticsvidhya.com/blog/2017/09/understaing-support-vector-machine-example-code/
https://www.analyticsvidhya.com/blog/2017/09/naive-bayes-explained/
https://www.analyticsvidhya.com/blog/2018/06/comprehensive-guide-recommendation-engine-python/
https://github.com/BhaskarTrivedi/QuerySearch_Recommentation_Classification
