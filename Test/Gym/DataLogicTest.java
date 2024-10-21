package Gym;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class DataLogicTest {

    @Test
    public void testFindMemberByNameOrId() throws FileNotFoundException {
        DataInput dataInput = new DataInput();
        DataLogic dataLogic = new DataLogic(dataInput, new writeToFile());

        // testperson
        Person expectedPerson = new Person(
                9003135555L,
                "Andreas Roos",
                "2021-12-12",
                "Active");
        dataInput.gymMembersDataList.put(expectedPerson.getpNr(), expectedPerson);

        // search by name
        Person foundPerson = dataLogic.findMemberByNameOrId("Andreas Roos");
        assertEquals(expectedPerson, foundPerson);

        // search by id nr
        foundPerson = dataLogic.findMemberByNameOrId("9003135555");
        assertEquals(expectedPerson, foundPerson);

        // search for non-existing person
        assertNull(dataLogic.findMemberByNameOrId("xxxx"));
    }

    @Test
    public void testCheckForMembership() throws FileNotFoundException {
        DataInput dataInput = new DataInput();
        DataLogic dataLogic = new DataLogic(dataInput, new writeToFile());

        // testperson
        Person testPerson = new Person(
                9003135555L,
                "Andreas Roos",
                "2021-12-12",
                "Active");
        dataInput.gymMembersDataList.put(testPerson.getpNr(), testPerson);

        String inputName = "Andreas Roos";
        Person foundPerson = dataLogic.findMemberByNameOrId(inputName);

        assertNotNull(foundPerson);
        assertEquals("Inactive", testPerson.getMembershipStatus());

        // check for expired membership
        testPerson.setLastPaymentDate("2021-01-01");
        dataLogic.checkMembership();
        assertEquals("Inactive", testPerson.getMembershipStatus());
    }
}
