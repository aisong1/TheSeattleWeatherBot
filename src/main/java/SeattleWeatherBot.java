import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import twitter4j.conf.ConfigurationBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SeattleWeatherBot {

    public static void main(String[] args) {

        try {

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("qhq4CdE6fnyYLVuI2ao5zi5it")
                    .setOAuthConsumerSecret("7WhT6FP0IqgyBUrsOBFM3FOKvFNMazvABscgLJlpDLNC3jrioh")
                    .setOAuthAccessToken("1146196880725966848-Y236kqwMFaLmH7CGX675UNo1NdQb7i")
                    .setOAuthAccessTokenSecret("GFzknaZa5WvtJecKv7kgzSUqLuX4NufUqNUW4HuZ1SwhU");
            /* cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(System.getenv("consumerKey"))
                    .setOAuthConsumerSecret(System.getenv("consumerSecret"))
                    .setOAuthAccessToken(System.getenv("accessToken"))
                    .setOAuthAccessTokenSecret(System.getenv("accessTokenSecret")); */
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            //OWM owm = new OWM(System.getenv("OWM_API_KEY"));
            OWM owm = new OWM("e26a2a9e5da4b9f33506d24dac1c0d90");
            owm.setUnit(OWM.Unit.IMPERIAL);
            CurrentWeather cwd = owm.currentWeatherByCityId(5809844);

            // Check if weather information is available
            double rain;
            if (cwd.hasRainData() && cwd.getRainData().getPrecipVol3h() != null) {
                rain = cwd.getRainData().getPrecipVol3h();
            } else {
                rain = 0;
            }

            // Set from UTC (used on Heroku servers) to Seattle local time, PST.
            LocalDateTime now = LocalDateTime.now().plusHours(-9);
            ZoneId id = ZoneId.of("America/Los_Angeles");
            ZonedDateTime zonedDateTime = ZonedDateTime.of(now, id);
            String formattedDate = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm").format(zonedDateTime);

            // Update tweet
            String dateTime = "[" + formattedDate + " PST] ";
            String currTemp = "It is currently " + Math.round(cwd.getMainData().getTemp()) + "˚F. ";
            String minMaxTemp;
            minMaxTemp = "Today will have a max of " + Math.round(cwd.getMainData().getTempMax()) + "˚F and a min of "
                    + Math.round(cwd.getMainData().getTempMin()) + "˚F. ";
            String rain3H = "There has been " + rain + " mm of rain in the last three hours.";
            String message = dateTime + currTemp + minMaxTemp + rain3H;
            System.out.println(message);
            //Status status = twitter.updateStatus(message);
            //System.out.println("Successfully updated the status to [" + status.getText() + "].");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
