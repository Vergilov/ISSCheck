import java.util.Date;

public class DataConventer {
    private DataHandling dataHandling;

    public DataConventer() throws Exception {
        this.dataHandling = new DataHandling();
    }

    public Date epochConventer(String date) {
        Date expiry = new Date(Long.parseLong(date) * 1000);
        System.out.println(expiry);
        return expiry;
    }

    public void differenceTime(Date d1,Date d2){


        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        System.out.println("Time in seconds: " + diffSeconds + " seconds.");
        System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        System.out.println("Time in hours: " + diffHours + " hours.");
    }
}
