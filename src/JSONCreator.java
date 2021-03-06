import org.json.JSONObject;

import java.util.Scanner;

public interface JSONCreator {


    static String JSONasString() throws Exception {
        ConnectionWithJSON json = new ConnectionWithJSON();
        Scanner scan = new Scanner(json.getIn());
        String string = "";
        while (scan.hasNext()) {
            string += scan.nextLine();
        }
        scan.close();
        return string;
    }

    static JSONObject buildJSON() throws Exception  {
        String str=JSONasString();
        JSONObject obj=new JSONObject(str);
        if (!obj.getString("message").equals("success")) {
            System.out.println("Something wrong!");
            return null;
        }
        return obj;
    }

}
