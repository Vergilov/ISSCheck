import org.json.*;

import java.util.Scanner;


public class JSONHandling implements JSONCreator{

    private JSONObject obj;

    public JSONHandling() throws Exception {
        this.obj= JSONCreator.buildJSON() ;
    }

    public JSONHandling(JSONObject json){
        this.obj= json ;
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
