import org.json.*;

import java.util.Scanner;


public class JSONHandling{

    private ConnectionWithJSON json;
    private JSONObject obj;
    public JSONHandling() throws Exception {
        this.json =new ConnectionWithJSON();
        this.obj= buildJSON();
    }


    private String JSONasString() {
        Scanner scan = new Scanner(json.getIn());
        String string = "";
        while (scan.hasNext()) {
            string += scan.nextLine();
        }
        scan.close();
        return string;
    }

    public JSONObject buildJSON()  {
        String str=JSONasString();
        JSONObject obj=new JSONObject(str);
        if (!obj.getString("message").equals("success")) {
            return null;
        }
        return obj;
    }


    public void print() {
        if(this.obj.isEmpty()){
            System.out.println("JSON is Empty");
        } else {
            System.out.println(obj.toString());
        }
    }


    public void getTimestamp() {
        int res = obj.getInt("timestamp");
        System.out.println(res);
    }

    public void getLatitude(){
        String result = obj.getJSONObject("iss_position").getString("latitude");
        System.out.println(result);
    }
    public void getLongitude(){
        String result = obj.getJSONObject("iss_position").getString("longitude");
        System.out.println(result);
    }
}
