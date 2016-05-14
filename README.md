# BOFA---Android-Coding-Exercise
Bank of America - Android Coding Exercise

1. What error conditions will you encounter? How should these be handled?
    - I was facing misbehaviours or error scenarios when the auto complete suggestion item clicked. Basically SearchView Widget delivers new Intent with selected suggestion to the activity. This has been handled by changing lanch mode of the MainActivity
    - I was facing exceptions when parsing google place details response. For example, "formatted-phone" attribute is optional and will not be available for some places. So this has been handled by catching JSON Exceptions in the parant response class "RestfulResponse"  
    
2. Where could the user experience break? How will you prevent this?
    - If there is no back navigation in the action bar of Second Activity "Place Details", the user will experience break at that point. This has been prevented by implementing "Intuitive Back Navigation" 
    
3. What other improvements can be made?
    - If we provide search history as a suggestion when user perform search, the user experience will be much better and 'Google AutoComplete API' hits can be reduced
