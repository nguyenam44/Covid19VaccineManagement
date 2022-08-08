/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the esditor.
 */
package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import list.InjectionList;
import validation.MyLib;
import validation.file.Encryption;

/**
 *
 * @author macos
 */
public class Covid19VaccineManager {

    public static void main(String[] args) {
        int count=0;
        while (true) {
            if (Encryption.checkSum() == false) {
                System.err.println("Invalid encryption!!! File information has been changed.");
                if (MyLib.isContinue("Do you want to continue the program(yes/no)?") == true) {
                    System.out.println("You choose continue!");
                    count++;
                } else {
                    System.out.println("Encryption failed!");
                    return;
                }
            }
            try {
                int choice;
                InjectionList list = new InjectionList();
                do {
                    System.out.println();
                    if (list.checkSave() == true) 
                        System.out.println("WARNING!!! The changed data will be lost when you exit the program because you have not saved it to the file.");
                    System.out.println("Welcome to Covid-19 Vaccine Management - @ 2021 by <SE150925 - Nguyễn Văn Hải Nam>");
                    System.out.println("Select the options below: ");
                    System.out.println("1. Show information all students have been injected");
                    System.out.println("2. Add student's vaccine injection information");
                    System.out.println("3. Updating information of students' vaccine injection");
                    System.out.println("4. Delete student vaccine injection information");
                    System.out.println("5. Search for injection information");
                    System.out.println("6. Store data to file");
                    System.out.println("7. Store new information encryption MD5 to file ");
                    System.out.println("8. Others - Quit");
                    choice = MyLib.getChoice("Input your choice: ", "Choice only [1-8]", 1, 8);
                    switch (choice) {
                        case 1 -> {
                            if (list.getSize() <= 0) {
                                System.out.println("The injection list is empty!");
                                break;
                            }
                            System.out.println("\n==> You choose SHOW information all students have been injected.");
                            list.show();
                        }
                        case 2 -> {
                            System.out.println("\n==> You choose ADD student's vaccine injection information.");
                            list.addNewInjection();
                        }
                        case 3 -> {
                            if (list.getSize() <= 0) {
                                System.out.println("The injection list is empty!");
                                break;
                            }
                            System.out.println("\n==>You choose UPDATE information of students' vaccine injection.");
                            list.updateInjection();
                        }
                        case 4 -> {
                            if (list.getSize() <= 0) {
                                System.out.println("The injection list is empty!");
                                break;
                            }
                            System.out.println("\n==>You choose DELETE student vaccine injection information.");
                            list.removeInjection();
                        }
                        case 5 -> {
                            if (list.getSize() <= 0) {
                                System.out.println("The injection list is empty!");
                                break;
                            }
                            System.out.println("\n==>You choose SEARCH for injection information.");
                            list.searchInjection();
                        }
                        case 6 -> {
                            if (list.getSize() <= 0) {
                                System.out.println("The injection list is empty!");
                                break;
                            }
                            System.out.println("\n==>You choose STORE data to file");
                            list.writeInjectionToBinaryFile();
                        }
                        case 7 -> {
                            System.out.println("\n==>You choose STORE new information encryption MD5 to file ");
                            Encryption.writeEncryptionToFile();
                            System.out.println("The new encryption MD5 was succesfully written to a file");
                        }
                        case 8 -> {
                            if (list.checkSave() == true) {
                                if (MyLib.isContinue("Do you want to save the changed data to the file BEFORE quit the program(yes/no): ")) {
                                    list.writeInjectionToBinaryFile();
                                } else {
                                    System.out.println("The changed data have not been save to file.");
                                }
                            }
                            System.out.println("\nTHANK YOU AND GOOD BYE!!!");
                            return;
                        }
                    }
                } while (choice > 0 && choice < 8);
            } catch (Exception ex) {
                Logger.getLogger(Covid19VaccineManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}