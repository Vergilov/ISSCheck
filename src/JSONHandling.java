import org.json.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class JSONHandling {
    private ConnectionWithJSON json;
    private BufferedReader fr;

    public JSONHandling() throws Exception {
        this.json = new ConnectionWithJSON();
        this.fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Vergilov\\Desktop\\ISS.json"))));
    }

    public void print() {
        String line = "";
        try {
            JSONObject obj = new JSONObject(fr.readLine());
            while ((line = fr.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("*********");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
