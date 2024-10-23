package Gym;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class writeToFile implements IFileWriter {

        public void writeAllMembersToFile(Collection<Person> members) {
            String gymMembersFilePath = "src/Gym/Files/GymMembersData.txt";
            int numMembers = members.size();
            int maxNameLength = Person.maxNameLength(members);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, false))) {

                for (Person person : members) {
                    writer.write(person.toStringList(maxNameLength));
                    writer.newLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("All members have been written to the file: " + gymMembersFilePath);
            System.out.println("Number of members: " +numMembers);
        }

        // rensar filen på medlemmar
        public static void clearAllMembers(){
            String gymMembersFilePath = "src/Gym/Files/GymMembersData.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath))) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("All members have been cleared from the file.");
        }

        // logga medlemamar
        public void logMemberCheckin(Person person){
            String gymMembersFilePath = "src/Gym/Files/GymMemberCheckinLog.txt";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String checkInTime = java.time.LocalDateTime.now().format(formatter);
            boolean activeMember = person.getMembershipStatus().equals("Active");

            if (!activeMember) {
                System.out.println("Member " +person.getFullName() +
                        " is not an active member.\n" +
                        "Please renew membership to check in.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, true))) {
                writer.write("Member " +person.getFullName() +" checked in: " + checkInTime);
                writer.newLine();
                System.out.println("Welcome " +person.getFullName() +"! checked in: " + checkInTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void writeMemberToFile(Person person) throws IOException {
    }
}
