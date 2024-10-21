package Gym;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataInputTest {

    private final String oldDate = "1900-01-01";
    private final String today = LocalDate.now().toString();
    private final HashMap<Long, Person> testMembers = new HashMap<>();
    private final Person testMember = new Person(
            9003135555L,
            "Andreas Roos",
            "2021-12-12",
            "N/A");

    @Test
    public void testFileExist() {
        File gymMembersData = new File("src/Gym/Data.txt");
        assertTrue(gymMembersData.exists());
    }

    @Test
    public void testFileIsNotEmpty() {
        File gymMembersData = new File("src/Gym/Data.txt");
        assertNotEquals(0, gymMembersData.length());
    }

    @Test
    public void testSplit() throws FileNotFoundException {
        File gymMembersData = new File("src/Gym/Data.txt");
        Scanner sc = new Scanner(gymMembersData);

        // kollar om fullständig information finns för medlem
        assertTrue(sc.hasNextLine());
        assertTrue(sc.hasNextLine());
    }

    @Test
    public void testLastPaymentDate() {
        assertEquals(today, LocalDate.now().toString());
        assertNotEquals(today, oldDate);
    }

    @Test
    public void testCheckMembership() {
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
    public void testWriteMemberToFile() throws IOException {
        testMembers.put(testMember.getpNr(), testMember);

        // Skriver
        String testFilePath = "src/Gym/DataTest.txt";
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