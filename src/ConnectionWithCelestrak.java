import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ConnectionWithCelestrak {
    private URL url;
    private BufferedReader in;


    public ConnectionWithCelestrak() throws Exception {
        this.url = new URL("http://www.celestrak.com/NORAD/elements/stations.txt");
        this.in = new BufferedReader(new InputStreamReader(url.openStream()));
    }

    public void print() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }

}
