import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import Gym.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataInputTest {

    private final String oldDate = "1900-01-01";
    private final String today = LocalDate.now().toString();
    private final String testFilePath = "src/Gym/DataTest.txt";

    private HashMap<Long, Person> testMembers = new HashMap<>();
    private Person testMember = new Person(9003135555L, "Andreas Roos", "2021-12-12", lastPaymentDate);

    @Test
    public void fileExist() {
        File gymMembersData = new File("src/Gym/Data.txt");
        assertTrue(gymMembersData.exists());
    }

    @Test
    public void fileIsNotEmpty() {
        File gymMembersData = new File("src/Gym/Data.txt");
        assertNotEquals(0, gymMembersData.length());
    }

    @Test
    public void splitTest() throws FileNotFoundException {
        File gymMembersData = new File("src/Gym/Data.txt");
        Scanner sc = new Scanner(gymMembersData);

        // kollar om fullständig information finns för medlem
        assertTrue(sc.hasNextLine());
        assertTrue(sc.hasNextLine());
    }

    @Test
    public void lastPaymentDateTest() {
        assertEquals(today, LocalDate.now().toString());
        assertNotEquals(today, oldDate);
    }

    @Test
    public void checkMembershipTest() {
        String memberDate = testMember.getLastPaymentDate();

        assertNotEquals(memberDate, LocalDate.now().toString());
        assertEquals(memberDate, "2021-12-12");

        if (memberDate.equals(LocalDate.now().toString())) {
            System.out.println("Medlemskapet är aktivt.");
        } else {
            System.out.println("Medlemskapet har gått ut.");
        }

    }

    @Test
    public void writeToFileTest() throws IOException {
        testMembers.put(testMember.getpNr(), testMember);

        // Skriver
        BufferedWriter writer = Files.newBufferedWriter(Path.of(testFilePath));
        for (Person _ : testMembers.values()) {
            writer.write(testMember + "\n");
            writer.flush();
        }
        writer.close();

        String expected = testMember.toString() + "\n";
        String actual = Files.readString(Path.of(testFilePath));

        assertEquals(expected, actual);
    }
}