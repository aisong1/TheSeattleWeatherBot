import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import io.github.cdimascio.dotenv.Dotenv;

public class SeattleWeatherBot {

    public static void main(String[] args) {

        try {
            // Create Twitter instance
            Twitter twitter = TwitterFactory.getSingleton();

            // Get environment variables
            Dotenv dotenv = Dotenv.load();

            OWM owm = new OWM(dotenv.get("OWM_API_KEY"));
            owm.setUnit(OWM.Unit.IMPERIAL);
            CurrentWeather cwd = owm.currentWeatherByCityId(5809844);

            double rain = (cwd.hasRainData()) ? cwd.getRainData().getPrecipVol3h() : 0;

            // owm.setUnit(OWM.Unit.METRIC);
            // double cels = cwd.getMainData().getTemp();
            // double celsMin = cwd.getMainData().getTempMin();
            // double celsMax = cwd.getMainData().getTempMax();

            String dateTime = "[" + cwd.getDateTime().toString() + "] ";
            String currTemp = "It is currently " + Math.round(cwd.getMainData().getTemp()) + "˚F. ";
            String minMaxTemp = "Today will have a max of " + Math.round(cwd.getMainData().getTempMax()) + "˚F and a min of "
                    + Math.round(cwd.getMainData().getTempMin()) + "˚F. ";
            String rain3H = "There has been " + rain + " mm of rain in the last three hours.";
            String message = dateTime + currTemp + minMaxTemp + rain3H;
            Status status = twitter.updateStatus(message);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
