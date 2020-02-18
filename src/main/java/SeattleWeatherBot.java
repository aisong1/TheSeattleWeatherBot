import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;

import java.io.IOException;



public class SeattleWeatherBot {

    public static void main(String[] args) throws TwitterException, IOException, APIException {

        Twitter twitter = TwitterFactory.getSingleton();

        OWM owm = new OWM(System.getenv("OWM_API_KEY"));
        CurrentWeather cwd = owm.currentWeatherByCityId(5809844);
        System.out.println(cwd.getMainData().getTemp());


        /*ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey("7f2f84004262bae12e55ae03e9071e0d"))
                .location(new GeoCoordinates(new Longitude(-122.303200), new Latitude(47.655548))).build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = client.forecast(request);
        Double currentInC = forecast.getCurrently().getTemperature();
        Double currentInF = (currentInC*(9/5)) + 32;

        System.out.println(currentInF); */

        String message = "The current weather in Seattle is " + cwd.getMainData();
        System.out.println(message);
        // Status status = twitter.updateStatus(message);
        // System.out.println("Successfully updated the status to [" + status.getText() + "].");

    }


}
