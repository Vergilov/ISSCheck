import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {

        DataHandling dataHandling=new DataHandling();
        dataHandling.print();

        DataConventer conventer= new DataConventer();
        Date d1=conventer.epochConventer("1551301687");
        Date d2=conventer.epochConventer("1551299254");
        conventer.differenceTime(d1,d2);
        JSONHandling json = new JSONHandling();
        json.print();
    }
}
