# The Seattle Weather Bot

An application to tweet daily, automated weather updates for the city of Seattle, WA.

Built with Java, Maven, and Heroku. Written with IntelliJ.

APIs: 
- [Twitter4J](http://twitter4j.org/en/index.html) 
- [OpenWeatherMap](https://openweathermap.org/api) (OWM)
  - Navigated using Ashutosh Kumar Singh's handy [OWM JAPIs](https://bitbucket.org/aksinghnet/owm-japis/src/master/)

[Check it out here!](https://twitter.com/bot_seattle)

# Project Status

Completed. Fully running and automated since Feb 25, 2020. 

Only reported issue is that the posted conditions are *consistently inaccurate*. Current temperatures along with highs and lows for the day can be off by ±10 ˚F at a time. 

You might be asking yourself what the point of an inaccurate weather bot is and to that I say *most* (if not all) other weather APIs I've seen report inaccuracies to a certain degree. In other words, improving the accuracy is out of my hands. I'm always open to suggestions if you know of a better (and free) API alternative I can use!

Possible future update would be to implement an option for users to direct message the account with a city name or latitude and longitude coordinates and return a weather summary for the specified location. 

# Setup and Installation

You can use the framework I have to set up your very own Twitter weather bot.

1. Create a new folder to store your project in. 
2. Make an account with [Heroku](https://signup.heroku.com/), if you don't already have one.
3. Open Terminal, navigate to the folder you created and clone the repo using `git clone`. If you're unfamiliar with git, check out this amazing Git-how-to graphic [here](https://rogerdudler.github.io/git-guide/). 
4. Create new accounts with Twitter and OWM.
    1. Apply for a [developer profile with Twitter](https://developer.twitter.com/en.html) using your newly created account and generate [API keys](https://developer.twitter.com/en/apps).
    2. Access your OWM API keys by logging in and navigating [here](https://home.openweathermap.org/api_keys).
5. Make sure you have a working version of [Maven](https://maven.apache.org/install.html) installed. OS X usually comes with Maven pre-installed: check `mvn -version` in Terminal or run `java` to initiate install. 

## Running locally
1. Go to the following block in `SeattleWeatherBot.java`.
```java
ConfigurationBuilder cb = new ConfigurationBuilder();
cb.setDebugEnabled(true)
  .setOAuthConsumerKey(System.getenv("consumerKey"))
  .setOAuthConsumerSecret(System.getenv("consumerSecret"))
  .setOAuthAccessToken(System.getenv("accessToken"))
  .setOAuthAccessTokenSecret(System.getenv("accessTokenSecret"));
TwitterFactory tf = new TwitterFactory(cb.build());
Twitter twitter = tf.getInstance();

OWM owm = new OWM(System.getenv("OWM_API_KEY"));
```
Replace all `System.getenv("...")` with the API keys generated in steps 4i and 4ii.
 
2. Run `$ mvn package` in Terminal.
3. Make sure your `JAVA_HOME` is defined correctly, making such changes as 
```nano
export JAVA_HOME=$(/usr/libexec/java_home)
export PATH=$JAVA_HOME/jre/bin:$PATH
```
to your `.bash_profile` using `$ nano .bash_profile`. After adding the necessary changes, save the changes with `cmd+o`, hitting `enter` and then exiting the file with `cmd+x`.

4. Run `$ sh target/bin/weather`
5. If it runs properly, the output will say 
```
[current date-time] It is currently (temp) ˚F. Today will have a max of (max) ˚F and a min of (min) ˚F. There has been (rain) mm of rain in the last three hours.
Successfully updated the status to [" (above) "].
```
and you can move onto the next section. Else, run any errors through this handy [tool](google.com).

## Automating on Heroku
1. Revert code back to `System.getenv("...")` in `SeattleWeatherBot.java`.
2. Follow [this](https://devcenter.heroku.com/articles/run-non-web-java-processes-on-heroku). Quick notes: 
    2. The `pom.xml` is already made for you.
    3. **After creating your Heroku app but before deploying the code**, visit the app by going to your Heroku [dashboard](dashboard.heroku.com). Add your unique API keys as "Config Vars" in your app's Settings. Name your Vars with the name it is given in `System.getenv("match-with-this-name-here")`. For example, my Twitter app's consumer key is saved as "consumerKey" in Heroku so that it matches with `System.getenv("consumerKey")`.
    4. Skip the "Scaling worker processes" section and go to "One-off dynos" since we want Heroku to "manully" input and run `heroku run "sh target/bin/weather"` every morning. 
    5. Schedule your one off dyno to run at a specific time or interval of time with Heroku's [Scheduler](https://elements.heroku.com/addons/scheduler) addon. (Limited if you want to schedule using a unique or odd schedule. Consider using a [custom clock process](https://devcenter.heroku.com/articles/scheduled-jobs-custom-clock-processes) instead.)
3. Enjoy your bot!

Stuck? Read up on the **documentations** for [Twitter4J](http://twitter4j.org/javadoc/index.html) and [OWM JAPIs](https://bitbucket.org/aksinghnet/owm-japis/src/master/docs/). Still stuck? Shoot me an [email](andrewsong61@gmail.com). Always happy to help :)

# Screenshots 

![Homepage](/img/screenshot1.jpg?raw=true "Main page") 
![Tweet thread](/img/screenshot2.jpg?raw=true "Tweet thread") 
![Tweet example](/img/screenshot3.jpg?raw=true "Tweet example")


# Reflection

This was a one month long project built during my senior year at the University of Washington. Project goals included creating a project outside of classes and interacting with APIs and their documentations. 

In the beginning, the goal was to build an application that tweeted the current temperature, the highs and lows for the day, and the percent chance of rain. After all, the Emerald City isn't known to have lots of rain for nothing. However, due to limitations of OWM, percent chance of rain is not available so I instead opted to report the amount of rain (mm) fallen in the past 3 hours. I originally wanted the app to run on my local machine, for fun, until I came across Heroku and thought to automate it. 

Some of the main challenges I ran into were Authentication and Cloud Services. I had to spend a couple of weeks figuring out how to use API keys so that they could be run both locally and on Heroku. It was also my first time using .gitignore to hide my API keys so that they can be protected from malicious intent. Further, once I had the code up and running locally, it was another challenge to figure out how to use Heroku's [Scheduler](https://elements.heroku.com/addons/scheduler) addon to automate the process. 

The reason I chose to write this project in Java was because Java is the language I was most familiar with. I initially thought that this familiarity would make the project easier to develop, but the difficulties I faced pushed my understanding to the limit and made resolving issues that much more rewarding. 

