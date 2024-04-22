import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeatherAssignment {

    public static boolean isValidStateCode(final String stateCode) {
        return stateCode != null && stateCode.trim().length() == 2;
    }

    public static void filterDataOnStateCode(final String inpStateCode) throws IOException {
        final List<String> outputLst = new ArrayList<>();
        final String outputFileName = "filtered.txt";
        int matchCount = 0;
        try {
            if (isValidStateCode(inpStateCode)) {
                BufferedReader reader = new BufferedReader(new FileReader("WXL.txt"));
                String st;
                while ((st = reader.readLine()) != null) { // filtering data on state code
                    st = st.trim();
                    if (st.length() >= 64) {
                        String stationIdentifier = st.substring(0,3);
                        String stateCode = st.substring(62,64);
                        String stationCity = st.substring(22,62);
                        if (isValidStateCode(stateCode) && stateCode.equals(inpStateCode)) {
                            matchCount ++;
                            outputLst.add(stationIdentifier + " " + stationCity);
                        }
                    }
                }

                // writing filtered data to output file.
                FileWriter writer = new FileWriter(outputFileName);
                if (!outputLst.isEmpty()) {
                    outputLst.forEach(line -> {
                        try {
                            writer.write(line + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    writer.write("RECORDS MATCHING "+ inpStateCode + ": " +outputLst.size());
                }
                else {
                    writer.write("NO MATCHING FOUND for "+ inpStateCode);
                }
                writer.close();
                reader.close();
            } else {
                FileWriter writer = new FileWriter(outputFileName);
                writer.write("INVALID STATE CODE: "+ inpStateCode);
                writer.close();
            }
        }
        catch (IOException ex) {
            System.out.println("Exception occurred " + ex.getMessage());
        }
        System.out.println("Program completed..");
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a state code to filter on: ");
        String stateCode = in.next();
        filterDataOnStateCode(stateCode);
        in.close();
    }
}
