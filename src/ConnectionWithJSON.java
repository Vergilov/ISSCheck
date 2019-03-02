import java.io.*;
import java.net.URL;

public class ConnectionWithJSON {
    private final URL url = new URL("http://api.open-notify.org/iss-now.json");
    private BufferedReader in;
    private PrintWriter writer;


    public ConnectionWithJSON() throws Exception {
        this.in = new BufferedReader(new InputStreamReader(url.openStream()));
        this.writer = new PrintWriter(new FileWriter(new File("C:\\Users\\Vergilov\\Desktop\\ISS.json"), true));
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getWriter() {
        return writer;
    }
}

