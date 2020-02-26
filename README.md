# The Seattle Weather Bot

An application to tweet daily, automated weather updates for the city of Seattle, WA.

Built with Java, Maven, and Heroku. Written with IntelliJ.

APIs: [Twitter4J](http://twitter4j.org/en/index.html), [OpenWeatherMap](https://openweathermap.org/api).

[Check it out here!](https://twitter.com/bot_seattle)

# Project Status

Completed. 

Fully running and automated since Feb 26, 2020. Possible future update would be to implement an option for 
users to direct message the account with latitude and longitude coordinates or a city name and return a weather summary 
for the specified location. 

# Screenshots 

![Screenshot 1](/img/screenshot1.jpg?raw=true "Screenshot 1")


# Reflection

This was a two month long project built during my senior year at the University of Washington. Project goals included creating a project outside of classes and interacting with APIs and their documentations. 

In the beginning, the goal was to build an application that tweeted the current temperature, the highs and lows for the day, and the percent chance of rain. After all, the Emerald City isn't known to have lots of rain for nothing. I originally wanted the app to run on my local machine, for fun, until I came across Heroku and thought to automate it. 

Some of the main challenges I ran into were Authentication and Cloud Services. I had to spend a couple of weeks figuring out how to use API keys so that they could be run both locally and on Heroku. It was also my first time using .gitignore to hide my API keys so that they can be protected from malicious intent. Further, once I had the code up and running locally, it was another challenge to figure out how to use Heroku's [Scheduler](https://elements.heroku.com/addons/scheduler) addon to automate the process. 

The reason I chose to write this project in Java was because Java is the language I was most familiar with. I initially thought that this familiarity would make the project easier to develop, but the difficulties I faced pushed my understanding to the limit and made resolving issues that much more rewarding. 

