package Gym;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class writeToFile implements IFileWriter {

        public static void writeToGymMembers(Person person) {
            String gymMembersFilePath = "src/Gym/GymMembersData.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, true))) {
                writer.write(person.toStringList());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public void writeAllMembersToFile(Collection<Person> members) {
            String gymMembersFilePath = "src/Gym/GymMembersData.txt";
            clearAllMembers();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, true))) {
                for (Person person : members) {
                    writer.write(person.toStringList());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("All members have been written to the file.");
        }

        // rensar filen på medlemmar
        public static void clearAllMembers(){
            String gymMembersFilePath = "src/Gym/GymMembersData.txt";
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String checkInTime = java.time.LocalDateTime.now().format(formatter);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath))) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("All members have been cleared from the file.");
        }

        // logga medlemamar
        public void logMemberCheckin(Person person){
            String gymMembersFilePath = "src/Gym/GymMemberCheckinLog.txt";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String checkInTime = java.time.LocalDateTime.now().format(formatter);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, true))) {
                writer.write("Member: " +person.getFullName() +" checked in: " + checkInTime);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void writeMemberToFile(Person person) throws IOException {

    }
}
