import java.io.IOException;
import java.io.LineNumberReader;

public class DataHandling {
    private ConnectionWithJSON connectionWithCelestrak;

    public DataHandling() throws Exception{
        this.connectionWithCelestrak=new ConnectionWithJSON();
    }


    public void print() {
        try {
            LineNumberReader reader = new LineNumberReader(connectionWithCelestrak.getIn());
            String inputLine;
            while ((inputLine = reader.readLine()) != null && reader.getLineNumber() <= 3) {
                connectionWithCelestrak.getWriter().println(inputLine);
                System.out.println(inputLine);
            }
            connectionWithCelestrak.getIn().close();
            connectionWithCelestrak.getWriter().close();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
