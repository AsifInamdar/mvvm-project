# mvvm-project
This repository shows how to use MVVM architecture in an Android application
The app calls an API to login and fetch a user list and show in the Recyclerview.
Retrofit is used to make the API calls, Room DB is used to store the users locally and LiveData is used to observe the changes.
ViewBinding and DataBinding is also used here.
The user list is a lazy list which loads 10 items at a time and loads 10 more items when the scroll reaches the end of Recyclerview.
