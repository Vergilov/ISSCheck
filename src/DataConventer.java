import java.util.Date;

public class DataConventer {
    private DataHandling dataHandling;

    public DataConventer() throws Exception {
        this.dataHandling = new DataHandling();
    }

    public void epochConventer(String date) {
        Date expiry = new Date(Long.parseLong(date) * 1000);
        System.out.println(expiry);
    }
}
