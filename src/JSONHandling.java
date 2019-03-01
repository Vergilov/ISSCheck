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

    private JSONObject buildJSON()  {
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


    public Long getTimestamp() {
        Integer res = obj.getInt("timestamp");
        return Long.valueOf(res);
    }

    public Double getLatitude(){
        return Double.parseDouble(obj.getJSONObject("iss_position").getString("latitude"));
    }
    public Double getLongitude(){
        return Double.parseDouble(obj.getJSONObject("iss_position").getString("longitude"));
    }
}
