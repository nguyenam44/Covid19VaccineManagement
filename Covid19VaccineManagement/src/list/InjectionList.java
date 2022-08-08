/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import dto.Injection;
import java.util.ArrayList;
import java.util.Collections;
import validation.MyLib;
import validation.file.Encryption;
import validation.file.FactoryFile;

/**
 *
 * @author macos
 */
public class InjectionList {

    ArrayList<Injection> list;
    StudentList stdList;
    VaccineList vacList;
    FactoryFile file;
    int count = 0;

    public InjectionList() throws Exception {
        list = new ArrayList<>();
        stdList = new StudentList();
        vacList = new VaccineList();
        file = new FactoryFile();
        file.readInjectionFromBinaryFile(list);
    }

    /**
     *
     * @return -1 nếu list=null,list!=null thì return list.size
     */
    public int getSize() {
        if (list == null) {
            return -1;
        }
        return list.size();
    }

    /**
     *
     * @param idInjec
     * @return vị trí Injection tìm thấy, ngược lại return null
     */
    private Injection findInjecByIdInjec(String idInjec) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param idStd
     * @return vị trí Injection tìm thấy, ngược lại return null
     */
    private Injection findInjecByIdStd(String idStd) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdStd().equalsIgnoreCase(idStd)) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * thêm thông tin mũi tiêm thứ nhất
     *
     * @param idStd
     */
    private void addFirstInjection(String idStd) {
        String idInjec;
        String idVac;
        while (true) {
            idInjec = MyLib.getAString("Input injection id: ", "Injection id following IJxxxx format, x is the digits!", "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
            if (list.isEmpty()) {
                break;
            } else if (findInjecByIdInjec(idInjec) == null) {
                break;
            } else {
                System.out.println("The id injection is duplicated! Try again.");
            }
        }
        while (true) {
            idVac = MyLib.getAString("Input vaccine id: ", "Vaccine id following VAxxx, x is the digits!", "^[v,V]{1}[a,A]{1}[0-9]{3}$").toUpperCase().trim();
            if (vacList.findVaccineById(idVac) != null) {
                break;
            } else {
                System.out.println("Vaccine with id " + idVac + " is not existed! Try again.");
            }
        }
        String firstPlace = MyLib.getAString("Input 1st injection place: ", "Place is characters! Cannot Blank.");
        String firstDate = MyLib.getDate("Input 1st injaction Date(dd/mm/yyyy): ");

        list.add(new Injection(idInjec, idStd, idVac, firstPlace, firstDate, " ", " "));
        System.out.println("Add 1st injection successfully!");
    }

    /**
     * thêm thông tin cả hai lần tiêm
     *
     * @param idStd
     */
    private void addAllInjection(String idStd) {
        String idInjec;
        String idVac;
        while (true) {
            idInjec = MyLib.getAString("Input injection id: ", "Injection id following IJxxxx format, x is the digits!", "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
            if (list.isEmpty()) {
                break;
            } else if (findInjecByIdInjec(idInjec) == null) {
                break;
            } else {
                System.out.println("The id injection is duplicated! Try again.");
            }

        }
        while (true) {
            idVac = MyLib.getAString("Input vaccine id: ", "Vaccine id following VAxxx, x is the digits!", "^[v,V]{1}[a,A]{1}[0-9]{3}$").toUpperCase().trim();
            if (vacList.findVaccineById(idVac) != null) {
                break;
            } else {
                System.out.println("Vaccine with id " + idVac + " is not existed! Try again.");
            }
        }
        String firstPlace = MyLib.getAString("Input 1st injection place: ", "Place is characters! Cannot blank.");
        String firstDate = MyLib.getDate("Input 1st injection date(dd/mm/yyyy): ");
        String secondPlace = MyLib.getAString("Input 2nd injection place: ", "Place is characters! Cannot blank.");
        String secondDate;
        while (true) {
            secondDate = MyLib.getDate("Input 2nd injection date: ");
            if (MyLib.checkDistance2Date(firstDate, secondDate, 28, 84) == true) {
                break;
            } else {
                System.out.println("The 2nd injection must be given 4 to 12 weeks after the 1st injection!");
            }
        }
        list.add(new Injection(idInjec, idStd, idVac, firstPlace, firstDate, secondPlace, secondDate));
        System.out.println("Add successfully! This student had been completed 2 injection.");
    }

    /**
     * thêm thông tin mũi tiêm thứ hai
     *
     * @param idStd
     * @param firstDate
     */
    private void addSecondInfection(String idStd, String firstDate) {
        String secondDate;
        String secondPlace = MyLib.getAString("input 2nd injection place: ", "Place is characters! Cannot Blank.");
        while (true) {
            secondDate = MyLib.getDate("Input 2nd injection date: ");
            if (MyLib.checkDistance2Date(firstDate, secondDate, 28, 84) == true) {
                break;
            } else {
                System.out.println("The 2nd injection must be given 4 to 12 weeks after the 1st injection!");
                System.out.println("This student's 1st date injection is: " + firstDate);
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdStd().equalsIgnoreCase(idStd)) {
                list.get(i).setSecondPlace(secondPlace);
                list.get(i).setSecondDate(secondDate);
            }
        }
        System.out.println("Add successfully! This student had been completed 2 injection.");
    }

    /**
     * thêm một Injection mới
     */
    public void addNewInjection() {
        while (true) {
            if (stdList.getSize() <= 0) {
                System.out.println("The student list is empty! Cannot add new Injection.");
                return;
            }
            if (vacList.getSize() <= 0) {
                System.out.println("The vaccine list is empty!  Cannot add new Injection");
                return;
            }
            String idStd;
            while (true) {
                idStd = MyLib.getAString("Input student id: ", "Student id following SExxxx, x is the digits!", "^[s,S]{1}[e,E]{1}[0-9]{4}$").toUpperCase().trim();
                if (stdList.findStudentById(idStd) != null) {
                    break;
                } else {
                    System.out.println("Student with " + idStd + " does not exist!");
                }
            }
            Injection inj = null;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).getIdStd().equalsIgnoreCase(idStd)) {
                    inj = list.get(i);
                }
            }
            if (inj != null) {
                if (inj.getFirstPlace() != null && !" ".equals(inj.getSecondPlace())) {
                    System.out.println("This student had been completed 2 injection!");
                } else {
                    if (MyLib.isContinue("This student had the first injection. Do you want to ADD 2nd injection(yes/no): ")) {
                        addSecondInfection(idStd, inj.getFirstDate());
                        count++;
                    } else {
                        System.out.println("You choose to keep the original information.");
                    }
                }
            } else {
                System.out.println("\n==>This student has not been injected. ADD new Injection:");
                System.out.println("1. Add 1st injection");
                System.out.println("2. Add both 2 injection");
                System.out.println("3. Exit to main menu");
                int choice = MyLib.getChoice("Input your choice: ", "Choice only [1-3]", 1, 3);
                switch (choice) {
                    case 1 -> {
                        System.out.println("\n==> You choose ADD 1st injection.");
                        addFirstInjection(idStd);
                        count++;
                    }
                    case 2 -> {
                        System.out.println("\n==> You choose ADD both 2 injection.");
                        addAllInjection(idStd);
                        count++;
                    }
                    case 3 -> {
                        System.out.println("\n==> You choose BACK to main menu.");
                        if (count > 0) {
                            if (MyLib.isContinue("Do you want to STORE the new added to file(yes/no)? ")) {
                                writeInjectionToBinaryFile();
                                Encryption.writeEncryptionToFile();
                                return;
                            } else {
                                System.out.println("This new injection have not been store to file! ");
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            }
            if (!MyLib.isContinue("Do you want to continue ADD new injection(yes/no)? ")) {
                if (count > 0) {
                    if (MyLib.isContinue("Do you want to STORE the new added to file(yes/no)? ")) {
                        writeInjectionToBinaryFile();
                        return;
                    } else {
                        System.out.println("The new injection have not been store to file.");
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    /**
     * cập nhật thông tin mũi tiêm thứ nhất
     *
     * @param idInjec
     */
    private void updateFirstInjection(String idInjec) {
        int size = list.size();
        String firstPlace = MyLib.getAString("input new 1st injection place: ", "Place is characters! Cannot Blank.");
        Injection inj = findInjecByIdInjec(idInjec);
        String firstDate;
        while (true) {
            firstDate = MyLib.getDate("Input new 1st injection date: ");
            if (" ".equals(inj.getSecondDate())) {
                break;
            }
            if (!" ".equals(inj.getSecondDate())) {
                if (MyLib.checkDistance2Date(firstDate, inj.getSecondDate(), 28, 84) == true) {
                    break;
                } else {
                    System.out.println("The 1nd injection must be given 4 to 12 weeks BEFORE the 2st injection!");
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).setFirstPlace(firstPlace);
                list.get(i).setFirstDate(firstDate);
            }
        }
        System.out.println("Update the 1st injection successfully!");
        System.out.println("\n==>The injection with id " + idInjec + " AFTER update.");
        System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).outputInjection();
            }
        }
    }

    /**
     * Cập nhật thông tin mũi tiêm thứ hai
     *
     * @param idInjec
     */
    private void updateSecondInjection(String idInjec) {
        String secondDate;
        Injection inj = findInjecByIdInjec(idInjec);
        String secondPlace = MyLib.getAString("input new 2nd injection place: ", "Place is characters! Cannot Blank.");
        while (true) {
            secondDate = MyLib.getDate("Input new 2nd injection date: ");
            if (MyLib.checkDistance2Date(inj.getFirstDate(), secondDate, 28, 84) == true) {
                break;
            } else {
                System.out.println("The 2nd injection must be given 4 to 12 weeks after the 1st injection!");
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).setSecondPlace(secondPlace);
                list.get(i).setSecondDate(secondDate);
            }
        }
        System.out.println("Update the 2nd injection successfully! This student had been completed 2 injection.");
        System.out.println("\n==> Student with id injection " + idInjec + " AFTER update.");
        System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).outputInjection();
            }
        }
    }

    /**
     * cập nhật thông tin cả hai mũi tiêm
     *
     * @param idInjec
     */
    private void updateBothInjection(String idInjec) {
        String firstPlace = MyLib.getAString("Input new 1st injection place: ", "Place is characters! Cannot blank.");
        String firstDate = MyLib.getDate("Input new 1st injection date(dd/mm/yyyy): ");
        String secondPlace = MyLib.getAString("Input 2nd new injection place: ", "Place is characters! Cannot blank.");
        String seccondDate;
        while (true) {
            seccondDate = MyLib.getDate("Input new 2nd injection date: ");
            if (MyLib.checkDistance2Date(firstDate, seccondDate, 28, 84) == true) {
                break;
            } else {
                System.out.println("The 2nd injection must be given 4 to 12 weeks after the 1st injection!");
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).setFirstPlace(firstPlace);
                list.get(i).setFirstDate(firstDate);
                list.get(i).setSecondPlace(secondPlace);
                list.get(i).setSecondDate(seccondDate);
            }
        }
        System.out.println("Update successfully! This student had been completed 2 injection.");
        System.out.println("\n==>The injection with id " + idInjec + " AFTER update.");
        System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).outputInjection();
            }
        }
    }

    /**
     * cập nhật id vaccine
     *
     * @param idInjec
     */
    private void updateVacId(String idInjec) {
        String idVac;
        while (true) {
            idVac = MyLib.getAString("Input new vaccine id: ", "Vaccine id following VAxxx, x is the digits!", "^[v,V]{1}[a,A]{1}[0-9]{3}$").toUpperCase().trim();
            if (vacList.findVaccineById(idVac) != null) {
                break;
            } else {
                System.out.println("Vaccine with id " + idVac + " is not existed! Try again.");
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).setIdVac(idVac);
            }
        }
        System.out.println("Update vaccine ID successfully!");
        System.out.println("\n==>The injection with id " + idInjec + " AFTER update.");
        System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).outputInjection();
            }
        }
    }

    /**
     * cập nhật id student
     *
     * @param idInjec
     */
    private void updateStudentId(String idInjec) {
        String idStd;
        while (true) {
            idStd = MyLib.getAString("Input new student id: ", "Student id following SExxxx, x is the digits!", "^[s,S]{1}[e,E]{1}[0-9]{4}$").toUpperCase().trim();
            Injection inj = findInjecByIdStd(idStd);
            if (stdList.findStudentById(idStd) != null) {
                if (inj == null) {
                    break;
                } else if (findInjecByIdInjec(idInjec).getIdStd().equalsIgnoreCase(idStd)) {
                    break;
                } else {
                    System.out.println("The student with id student " + idStd + " existed in injection list!");
                }
            } else {
                System.out.println("Student with id " + idStd + " is not existed! Try again.");
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).setIdStd(idStd);
            }
        }
        System.out.println("Update student id successfully!");
        System.out.println("\n==>The injection with id " + idInjec + " AFTER update.");
        System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                list.get(i).outputInjection();
            }
        }
    }

    /**
     * cập nhật thông tin Injection bằng idInjecc
     */
    public void updateInjection() {
        while (true) {
            String idInjec = MyLib.getAString("Input injection id: ", "Injection id following IJxxxx format, x is the digits!", "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
            Injection inj = findInjecByIdInjec(idInjec);
            int size = list.size();
            if (inj == null) {
                System.out.println("The injection with id " + idInjec + " does not exist.");
                return;
            } else {
                System.out.println("\n==>The injection with id " + idInjec + " BEFORE update.");
                System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
                for (int i = 0; i < size; i++) {
                    if (list.get(i).getIdInjec().equalsIgnoreCase(idInjec)) {
                        list.get(i).outputInjection();
                    }
                }
                System.out.println("\n==>Select the information you want to UPDATE:");
                System.out.println("1. Update student ID");
                System.out.println("2. Update vaccine ID");
                System.out.println("3. Update 1st injection information");
                System.out.println("4. Update 2nd injection information");
                System.out.println("5. Update both injection information");
                System.out.println("6. Exit to main menu");
                int choice = MyLib.getChoice("Input your choice:", "Choice only[1-6]", 1, 6);
                switch (choice) {
                    case 1 -> {
                        System.out.println("\n==> You choose UPDATE student ID.");
                        updateStudentId(idInjec);
                        count++;
                    }
                    case 2 -> {
                        System.out.println("\n==> You choose UPDATE vaccine ID.");
                        updateVacId(idInjec);
                        count++;
                    }
                    case 3 -> {
                        System.out.println("\n==> You choose UPDATE 1st injection information.");
                        updateFirstInjection(idInjec);
                        count++;
                    }
                    case 4 -> {
                        System.out.println("\n==> You choose UPDATE 2nd injection information.");
                        updateSecondInjection(idInjec);
                        count++;
                    }
                    case 5 -> {
                        System.out.println("\n==> You choose UPDATE both injection information.");
                        updateBothInjection(idInjec);
                        count++;
                    }
                    case 6 -> {
                        System.out.println("\n==> You choose BACK to main menu.");
                        return;
                    }
                }
            }
            if (MyLib.isContinue("Do you want to STORE the new updated to file(yes/no)? ")) {
                writeInjectionToBinaryFile();
                return;
            } else {
                System.out.println("This updated information have not been store to file! ");
                return;
            }
        }
    }

    /**
     * xoá một Injection bằng idInjec
     */
    public void removeInjection() {
        String idInjec = MyLib.getAString("Input injection id: ", "Injection id following IJxxxx format, x is the digits!", "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
        Injection inj = findInjecByIdInjec(idInjec);
        if (inj == null) {
            System.out.println("The injection with id " + idInjec + " does not exist.");
        } else {
            if (MyLib.isContinue("Are you sure want to REMOVE this injection(yes/no)? ") == true) {
                list.remove(inj);
                count++;
                System.out.println("Remove injection successfully!");
                if (MyLib.isContinue("Do you want to STORE to file(yes/no)? ")) {
                    writeInjectionToBinaryFile();
                } else {
                    System.out.println("The updated injection have not been store to file.");
                }
            } else {
                System.out.println("The injection information has not been removed.");
            }
        }
    }

    /**
     * tìm kiếm một Injection theo idStd
     */
    private void searchInjectionByIdStd() {
        String idStd = MyLib.getAString("Input student id: ", "Student id following SExxxx, x is the digits!", "^[s,S]{1}[e,E]{1}[0-9]{4}$").toUpperCase().trim();

        Injection inj = findInjecByIdStd(idStd);
        if (inj == null) {
            System.out.println("\nStudent with id student " + idStd + " has not been injected.");
        } else {
            System.out.println("\n==>Information of injection with id student: " + idStd);
            System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).getIdStd().equalsIgnoreCase(idStd)) {
                    list.get(i).outputInjection();
                }
            }
        }
    }

    /**
     * tìm kiếm injection theo tên SV
     */
    private void searchInjectionByNameStd() {
        String nameStd = MyLib.getAString("Input student name: ", "Student name is only chararters!");
        ArrayList<String> arr = stdList.findStudentByNameReturnID(nameStd);
        int size = list.size();
        int arrSize = arr.size();
        int count = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < arrSize; j++) {
                if (list.get(i).getIdStd().equalsIgnoreCase(arr.get(j))) {
                    System.out.println("\nInformation of injection with student name: " + stdList.findStudentById(arr.get(j)).getNameStd().toUpperCase().trim());
                    System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
                    list.get(i).outputInjection();
//                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                    count++;
                }
            }
        }
        if (count == 0) {
            System.out.println("\nHave no injection exists with student name: " + nameStd);
        }
        if (count == 1) {
            System.out.println("Total " + count + " injection with student name: " + nameStd);
        }
        if (count > 1) {
            System.out.println("Total " + count + " injections with student name: " + nameStd);
        }
    }

    /**
     * tìm kiếm injection
     */
    public void searchInjection() {
        System.out.println("1. Search by studentID");
        System.out.println("2. Search by student name");
        System.out.println("3. Exit to main menu");
        int choice = MyLib.getChoice("Input your choice: ", "Only choice [1-3]", 1, 3);
        switch (choice) {
            case 1 -> {
                System.out.println("\n==>You choose SEARCH by studentID.");
                searchInjectionByIdStd();
            }
            case 2 -> {
                System.out.println("\n==>You choose SEARCH by student name.");
                searchInjectionByNameStd();
            }
            case 3 ->
                System.out.println("\n==>You choose BACK to main menu.");
        }
    }

    /**
     * in danh sách Injection ra màn hình
     */
    private void showInjectionList() {
        System.out.println("\n                                                       === INJECTION LIST ===");
        System.out.println("|   ID   | ID STUDENT |  ID VACCINE |     FIRST PLACE      |   FIRST DATE   |     SECOND PLACE     |   SECOND DATE  |      STATUS     |");
        Collections.sort(list);
        list.forEach(injection -> {
            injection.outputInjection();
        });
        if (list.size() == 1) {
            System.out.println("Total " + list.size() + " injection.");
        }
        if (list.size() > 1) {
            System.out.println("Total " + list.size() + " injections.");
        }
    }

    /**
     * show danh sách theo nhu cầu
     */
    public void show() {
        System.out.println("1. Show injection list");
        System.out.println("2. Show student list");
        System.out.println("3. Show vaccine list");
        System.out.println("4. Exit to main menu");
        int choice = MyLib.getChoice("Input your choice: ", "Only choice [1-4]", 1, 4);
        switch (choice) {
            case 1 -> {
                System.out.println("\n==>You choose SHOW injection list.");
                showInjectionList();
            }
            case 2 -> {
                System.out.println("\n==>You choose SHOW student list.");
                stdList.printStd();
            }
            case 3 -> {
                System.out.println("\n==>You choose SHOW vaccine list.");
                vacList.printVac();
            }
            case 4 ->
                System.out.println("\n==>You choose BACK to main menu.");

        }
    }

    /**
     * ghi file injection.dat
     */
    public void writeInjectionToBinaryFile() {
        file.writeInjectionToBinaryFile(list);
        Encryption.writeEncryptionToFile();
        count = 0;
    }

    /**
     *
     * @return count>0
     */
    public boolean checkSave() {
        return count > 0;
    }

}
