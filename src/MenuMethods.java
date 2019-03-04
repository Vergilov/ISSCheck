import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public class MenuMethods implements JSONDataOutput, DataConventer {
    private List<String> arrayList;

    public MenuMethods() {
        this.arrayList = new ArrayList<>();
    }


    public void calculateSpeedFromLastTwoPoints() {
        JSONObject oneBeforeLastJSONObject = createJSONOBject(arrayList.get(arrayList.size() - 2));
        JSONObject lastJSONObject = createJSONOBject(this.arrayList.get(arrayList.size() - 1));

        double oneBeforeLastLatidue = JSONDataOutput.getLatitude(oneBeforeLastJSONObject);
        double oneBeforeLastLongtitude = JSONDataOutput.getLongitude(oneBeforeLastJSONObject);
        double lastLatitude = JSONDataOutput.getLatitude(lastJSONObject);
        double lastLongtitude = JSONDataOutput.getLongitude(lastJSONObject);
        Date oneBeforeLastDate = DataConventer.epochConventer(JSONDataOutput.getTimestamp(oneBeforeLastJSONObject));
        Date lastDate = DataConventer.epochConventer(JSONDataOutput.getTimestamp(lastJSONObject));


        double result = (distance(lastLatitude, oneBeforeLastLatidue, lastLongtitude, oneBeforeLastLongtitude)) / (DataConventer.differenceTime(oneBeforeLastDate, lastDate)); // distance/ diffrenceTime
        System.out.println((int) result + " KM/H");
    }


    public void calculateDistance() {
        JSONObject first = createJSONOBject(arrayList.get(0));
        double firstLatitude = JSONDataOutput.getLatitude(first);
        double firstLongitude = JSONDataOutput.getLongitude(first);

        JSONObject last = createJSONOBject(arrayList.get(arrayList.size() - 1));
        double lastLatitude = JSONDataOutput.getLatitude(last);
        double lastLongitude = JSONDataOutput.getLatitude(last);

        double result = distance(lastLatitude, firstLatitude, lastLongitude, firstLongitude);
        System.out.format("%.2f KM", result);
    }

    private JSONObject createJSONOBject(String obj) {
        return new JSONObject(obj);
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

    public List<String> getArrayList() {
        return arrayList;
    }

    public void saveToFile() throws Exception {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File desktop = new File(System.getProperty("user.home"), "/Desktop");
            StringBuilder time = new StringBuilder(timeStamp + "ISS.txt");
            File output = new File(desktop, time.toString());
            PrintWriter pw = new PrintWriter(output);
            for (String inputLine : arrayList) {
                pw.println(inputLine);
            }
            pw.close();
            System.out.println(output.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}