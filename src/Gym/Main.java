package Gym;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            DataInput dataInput = new DataInput();
            DataLogic dataLogic = new DataLogic(dataInput);

            dataLogic.checkMembership();

        } catch (FileNotFoundException e) {
            System.out.println("filen hittades ej" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}