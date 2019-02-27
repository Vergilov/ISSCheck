
public class Main {

    public static void main(String[] args) throws Exception {

        DataHandling dataHandling=new DataHandling();
        dataHandling.print();

        DataConventer conventer= new DataConventer();
        conventer.epochConventer("1551298737");
    }
}
