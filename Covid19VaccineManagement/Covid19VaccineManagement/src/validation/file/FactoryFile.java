/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation.file;

import dto.Injection;
import dto.Student;
import dto.Vaccine;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author macos
 */
public class FactoryFile {

    private final String stdFileName = "student.dat";
    private final String vacFileName = "vaccine.dat";
    private final String injecFileName = "injection.dat";
    private final String injecFileTxt = "injection.txt";

    /**
     * đọc file student.dat
     *
     * @param list
     * @throws Exception
     *
     */
    public void readStdFromFile(ArrayList<Student> list) throws Exception {
        ObjectInputStream ois = null;
        try ( FileInputStream fis = new FileInputStream(stdFileName)) {
            ois = new ObjectInputStream(fis);
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Student) {
                    list.add((Student) obj);

                }
            }
            fis.close();
            ois.close();
        } catch (EOFException e) {

        } catch (IOException ex) {
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }

    /**
     * gi danh sách Student vào file student.dat
     */
    public void writeStdToFile() {
        try {
            try ( FileOutputStream file = new FileOutputStream(stdFileName);  ObjectOutputStream oStream = new ObjectOutputStream(file)) {
                ArrayList<Student> list = new ArrayList<>();
                list.add(new Student("SE1501", "Hoang Chi Cuong"));
                list.add(new Student("SE1502", "Hoang Minh Thuan"));
                list.add(new Student("SE1503", "Ho Dang Khoa"));
                list.add(new Student("SE1504", "Le Quoc Khanh"));
                list.add(new Student("SE1505", "Nguyen Dang Nam"));
                list.add(new Student("SE1506", "Nguyen Huu Phuc "));
                list.add(new Student("SE1507", "Nguyen Huynh Minh Khoi "));
                list.add(new Student("SE1508", "Nguyen Phuoc Tho"));
                list.add(new Student("SE1509", "Nguyen Van Hai Nam"));
                list.add(new Student("SE1510", "Bui Khoi Nguyen"));

                for (Student obj : list) {
                    oStream.writeObject(obj);
                }
            }
            System.out.println("Write student to file successfully!");
        } catch (IOException ex) {
        }
    }

    /**
     * đọc file vaccine.dat
     *
     * @param list
     * @throws Exception
     */
    public void readVacFromFile(ArrayList<Vaccine> list) throws Exception {
        ObjectInputStream ois = null;
        try ( FileInputStream fis = new FileInputStream(vacFileName)) {
            ois = new ObjectInputStream(fis);
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Vaccine) {
                    list.add((Vaccine) obj);
                }
            }
            fis.close();
            ois.close();
        } catch (EOFException e) {
        } catch (IOException ex) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }

    /**
     * ghi danh sách Vaccine vào file vaccine.dat
     */
    public void writeVacToFile() {
        try {
            try ( FileOutputStream file = new FileOutputStream(vacFileName);  ObjectOutputStream oStream = new ObjectOutputStream(file)) {
                ArrayList<Vaccine> list = new ArrayList<>();
                list.add(new Vaccine("VA001", "AstraZeneca"));
                list.add(new Vaccine("VA002", "SPUTNIK V"));
                list.add(new Vaccine("VA003", "Vero Cell"));
                list.add(new Vaccine("VA004", "Pfizer"));
                list.add(new Vaccine("VA005", "Moderna"));
                list.add(new Vaccine("VA006", "Janssen"));
                list.add(new Vaccine("VA007", "CoviVac"));
                list.add(new Vaccine("VA008", "CoronaVac"));
                list.add(new Vaccine("VA009", "Abdala"));
                list.add(new Vaccine("VA010", "Sputnik Light"));
                for (Vaccine obj : list) {
                    oStream.writeObject(obj);
                }
            }
            System.out.println("Write vaccine to file successfully!");
        } catch (IOException ex) {
        }
    }

    /**
     * ghi danh sách Injection vào file ịnection.dat
     *
     * @param list
     */
    public void writeInjectionToBinaryFile(ArrayList<Injection> list) {

        try {
            try ( FileOutputStream file = new FileOutputStream(injecFileName);  ObjectOutputStream oStream = new ObjectOutputStream(file)) {
                for (Injection obj : list) {
                    oStream.writeObject(obj);
                }
            }
            System.out.println("Store to file successfully!");
        } catch (IOException ex) {
        }
    }

    /**
     * ghi danh sách Injection vào file injection.txt
     *
     * @param list
     */
    public void writeInjectionToFile(ArrayList<Injection> list) {
        if (list.isEmpty()) {
            System.out.println("Injection list is empty! Nothing to save!\n");
            return;
        }
        try {
            File f = new File(injecFileTxt);
            try ( FileWriter fw = new FileWriter(f);  PrintWriter pw = new PrintWriter(fw)) {
                Collections.sort(list);
                list.forEach(injection -> {
                    pw.println(injection.toString());
                });
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * đọc file injection.dat
     *
     * @param list
     * @throws Exception
     */
    public void readInjectionFromBinaryFile(ArrayList<Injection> list) throws Exception {
        ObjectInputStream ois = null;
        try ( FileInputStream fis = new FileInputStream(injecFileName)) {
            ois = new ObjectInputStream(fis);
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Injection) {
                    list.add((Injection) obj);
                }
            }
            fis.close();
            ois.close();
        } catch (EOFException e) {
        } catch (IOException ex) {
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }

    public static void main(String[] args) {
        FactoryFile f = new FactoryFile();
        f.writeStdToFile();
        f.writeVacToFile();
    }
}
