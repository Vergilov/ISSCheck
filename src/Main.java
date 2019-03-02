import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main implements JSONCreator {
    public static ArrayList<String> arrayList = new ArrayList<>();
    public static DataConventer dataConventer = new DataConventer();


    public static void main(String[] args) throws Exception {
        menu();
    }


    public static void menu() throws Exception {
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        int action;


        System.out.println("WELCOME TO ISS ANALYST:");
        while (!quit) {
            System.out.println("Choose a option:" +
                    "\n 1: ADD current record to Arraylist from JSON ISS Website" +
                    "\n 2: Show Current status of ISS" +
                    "\n 3: Print all records" +
                    "\n 4: Calculate Speed" +
                    "\n 5: Calculate distance from Start to End" +
                    "\n 0: Quit");
            action = in.nextInt();
            if (checkRange(action)) {
                switch (action) {
                    case 1:
                        addJSONtoArray();
                        break;
                    case 2:
                        currentStatus();
                        break;
                    case 3:
                        System.out.println("Print all elements from Arraylist: ");
                        for (String element : arrayList) {

                            System.out.println("**");
                            System.out.println(element);
                        }
                        break;
                    case 4:
                        if (arrayList.isEmpty() || arrayList.size() == 1) {
                            System.out.println("Please add at least 2 records to Array first!");
                        } else {
                            calculateSpeed();
                        }
                        break;
                    case 5:
                        if (arrayList.isEmpty() || arrayList.size() == 1) {
                            System.out.println("Please add at least 2 records to Array first!");
                        } else {
                            calculateDistance();
                        }
                        break;
                    case 0:
                        quit = true;
                        break;
                }
            }
        }
    }

    public static void calculateSpeed() {
        JSONObject last = new JSONObject(arrayList.get(arrayList.size() - 1));
        JSONHandling last1 = new JSONHandling(last);
        JSONObject prelast = new JSONObject(arrayList.get(arrayList.size() - 2));
        JSONHandling prelast1 = new JSONHandling(prelast);
        Date prelastDate = dataConventer.epochConventer(prelast1.getTimestamp());
        Date lastDate = dataConventer.epochConventer(last1.getTimestamp());
        double result = (distance(last1.getLatitude(), prelast1.getLatitude(), last1.getLongitude(), prelast1.getLongitude())) / (dataConventer.differenceTime(prelastDate, lastDate));
        System.out.format("%.2f KM/H \n", result);
    }

    public static void calculateDistance() {
        JSONObject first = new JSONObject(arrayList.get(0));
        JSONHandling first1 = new JSONHandling(first);
        JSONObject lastElement = new JSONObject(arrayList.get(arrayList.size() - 1));
        JSONHandling lastElement1 = new JSONHandling(lastElement);

        double result = distance(lastElement1.getLatitude(), first1.getLatitude(), lastElement1.getLongitude(), first1.getLongitude());
        System.out.format("%.3f KM \n", result);
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters


        distance = Math.pow(distance, 2) + Math.pow(0.0, 2);

        return (Math.sqrt(distance)) / 1000; //KM
    }

    public static void addJSONtoArray() throws Exception {
            arrayList.add(JSONCreator.buildJSON().toString());
            System.out.println("SUCCESSFUL");
    }

    public static void currentStatus() throws Exception {
        System.out.println(JSONCreator.buildJSON().toString());
    }

    public static boolean checkRange(int action) {
        boolean range = true;
        if (action < 0 || action > 5) {
            System.out.println("Type a Option from 0 to 5!");
            range = false;
        }
        return range;
    }
}
