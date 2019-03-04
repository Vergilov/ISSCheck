import java.util.Date;

public class DataConventer {


    public DataConventer() {
    }

    public Date epochConventer(Long date) {
        Date expiry = new Date(date * 1000);
        System.out.println(expiry);
        return expiry;
    }

    public double differenceTime(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (double) diff / (60 * 60 * 1000); //in hours because of KM/H final output
    }

    //Helping methods
    public void differenceTimeInSeconds(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        System.out.println("Time in seconds: " + diffSeconds + " seconds.");
    }

    public void differenceTimeInMinutes(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        System.out.println("Time in minutes: " + diffMinutes + " minutes.");

    }

    public void differenceTimeInHours(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        long diffHours = diff / (60 * 60 * 1000);
        System.out.println("Time in hours: " + diffHours + " hours.");
    }

}
