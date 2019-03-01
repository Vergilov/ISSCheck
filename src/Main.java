public class Main {

    public static void main(String[] args) throws Exception {
        JSONHandling json = new JSONHandling();
        DataConventer dataConventer=new DataConventer();


        json.print();
        json.getLatitude();
        json.getLongitude();
        json.getTimestamp();
        System.out.println("*****************");
        System.out.println(dataConventer.epochConventer(json.getTimestamp()));


    }

}
