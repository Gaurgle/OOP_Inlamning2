package Gym;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DataInput {

    // relative path
    protected File gymMembersData = new File("src/Gym/Data.txt");
    protected HashMap<Long, Person> gymMembersDataList = new HashMap<>();


    public DataInput() throws FileNotFoundException {
        if (!gymMembersData.exists()) {
            throw new FileNotFoundException("Filen hittades ej.");
        }
        try {
            // kopplar en scanner till filen
            Scanner sc = new Scanner(gymMembersData);
            while (sc.hasNextLine()) {
                if (sc.hasNextLine()) {
                    String pNrName = sc.nextLine();
                    String lastPaymentDate = sc.nextLine();

                    // splitta och parse. lastPaymentDate behöver inte parsas eller splittas.
                    String[] parts = pNrName.split(",\\s*");
                    Long pNr = Long.parseLong(parts[0]);
                    String fullName = parts[1];

                    // lägger in nytt personobjekt i persons-listan
                    gymMembersDataList.put(pNr, new Person(pNr, fullName, lastPaymentDate, ""));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}