import org.json.*;


public class JSONHandling implements JSONCreator{

    private JSONObject obj;

    public JSONHandling(JSONObject json){
        this.obj= json ;
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
