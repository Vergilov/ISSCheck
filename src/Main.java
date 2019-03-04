import java.util.InputMismatchException;
import java.util.Scanner;

public class Main implements JSONCreator {
    private static MenuMethods menuMethods = new MenuMethods();


    public static void main(String[] args) throws Exception {

        menu();

    }


    private static void menu() throws Exception {
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        int action;
        System.out.println("\n********************************************************************************");
        System.out.println("WELCOME TO ISS ANALYST:");
        try {
        while (!quit) {
            System.out.println("Choose a option:" +
                    "\n 1: Add current record to Arraylist from JSON ISS Website" +
                    "\n 2: Show Current status of ISS" +
                    "\n 3: Print all records" +
                    "\n 4: Calculate Speed" +
                    "\n 5: Calculate distance from Start to End" +
                    "\n 6: Add result from ArrayList to file"+
                    "\n 0: Quit"+
            "\n********************************************************************************\n");

                action = in.nextInt();
                if (checkRange(action) || in.hasNextInt()) {
                    switch (action) {
                        case 1:
                            menuMethods.addJSONtoArray();
                            menuMethods.addToFile();
                            break;
                        case 2:
                            menuMethods.currentStatus();
                            break;
                        case 3:
                            System.out.println("Print all elements from Arraylist: ");
                            if (menuMethods.getArrayList().isEmpty()) {
                                System.out.println("ArrayList its Empty");
                            } else {
                                for (String element : menuMethods.getArrayList()) {
                                    System.out.println(element);
                                }
                            }
                            break;
                        case 4:
                            if (menuMethods.getArrayList().isEmpty() || menuMethods.getArrayList().size() == 1) {
                                System.out.println("Please add at least 2 records to Array first!");
                            } else {
                                menuMethods.calculateSpeedFromLastTwoPoints();
                            }

                            break;
                        case 5:
                            if (menuMethods.getArrayList().isEmpty() || menuMethods.getArrayList().size() == 1) {
                                System.out.println("Please add at least 2 records to Array first!");
                            } else {
                                menuMethods.calculateDistance();
                            }
                            break;
                        case 6:
                            if (menuMethods.getArrayList().isEmpty()) {
                                System.out.println("Please add input to Arraylist first");
                            } else {
                                menuMethods.saveToFile();
                            }
                            break;
                        case 0:
                            quit = true;
                            break;
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.print( " Wrong format!"+
                    "\n Type a Option from 0 to 5!");
            menu();
        }
    }

    private static boolean checkRange(int action) {
        boolean range = true;
        if (action < 0 || action > 6) {
            System.out.println("Type a Option from 0 to 6!");
            range = false;
        }
        return range;
    }
}
