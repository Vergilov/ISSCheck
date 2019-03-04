import org.json.JSONObject;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;

public class MenuMethods {
    private ArrayList<String> arrayList;
    private DataConventer dataConventer;

    public MenuMethods() {
        this.arrayList = new ArrayList<>();
        this.dataConventer = new DataConventer();
    }


    public void calculateSpeed() {
        JSONObject oneBeforeLast = new JSONObject(arrayList.get(arrayList.size() - 2));
        JSONObject last = new JSONObject(this.arrayList.get(arrayList.size() - 1));
        JSONHandling oneBeforeLast1 = new JSONHandling(oneBeforeLast);
        JSONHandling last1 = new JSONHandling(last);

        Date oneBeforeLastDate = dataConventer.epochConventer(oneBeforeLast1.getTimestamp());
        Date lastDate = dataConventer.epochConventer(last1.getTimestamp());

        double result = (distance(last1.getLatitude(), oneBeforeLast1.getLatitude(), last1.getLongitude(), oneBeforeLast1.getLongitude())) / (dataConventer.differenceTime(oneBeforeLastDate, lastDate));
        System.out.format("%.2f KM/H", result);
    }

    public void calculateDistance() {
        JSONObject first = new JSONObject(arrayList.get(0));
        JSONHandling first1 = new JSONHandling(first);

        JSONObject lastElement = new JSONObject(arrayList.get(arrayList.size() - 1));
        JSONHandling lastElement1 = new JSONHandling(lastElement);

        double result = distance(lastElement1.getLatitude(), first1.getLatitude(), lastElement1.getLongitude(), first1.getLongitude());
        System.out.format("%.2f KM", result);
    }



    private double distance(double lat1, double lat2, double lon1,
                           double lon2) {
        //Haversine_formula
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2) + Math.pow(0.0, 2);

        return (Math.sqrt(distance)) / 1000; //return distance in KM
    }

    public void addJSONtoArray() throws Exception {
        arrayList.add(JSONCreator.buildJSON().toString());
        System.out.println("SUCCESSFUL");
    }

    public void currentStatus() throws Exception {
        System.out.println(JSONCreator.buildJSON().toString());
    }

    public void addToFile() throws Exception {
        ConnectionWithJSON connectionWithJSON = new ConnectionWithJSON();
        try {
            LineNumberReader reader = new LineNumberReader(connectionWithJSON.getIn());
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                connectionWithJSON.getWriter().println(inputLine);
                System.out.println(inputLine);
            }
            connectionWithJSON.getIn().close();
            connectionWithJSON.getWriter().close();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<String> getArrayList() {
        return arrayList;
    }
}
