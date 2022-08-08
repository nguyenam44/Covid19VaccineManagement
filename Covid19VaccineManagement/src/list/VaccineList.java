/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import dto.Vaccine;
import java.util.ArrayList;
import java.util.Collections;
import validation.file.FactoryFile;

/**
 *
 * @author macos
 */
public class VaccineList {

    ArrayList<Vaccine> list;
    FactoryFile f;

    public VaccineList() throws Exception {
        list = new ArrayList<>();
        f = new FactoryFile();
        f.readVacFromFile(list);
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
     * in danh sách Vaccine ra màn hình
     */
    public void printVac() {
        System.out.println("\n      === VACCINE LIST ===");
        System.out.println("| ID VACCINE  |  NAME OF VACCINE  |");
        Collections.sort(list);
        list.forEach(vaccine -> {
            vaccine.outputVaccine();
        });
        System.out.println("Total " + this.getSize() + " vaccines.");
    }

    /**
     *
     * @param id
     * @return vị trí Vaccine tìm thấy, ngược lại return null
     */
    protected Vaccine findVaccineById(String id) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdVac().equalsIgnoreCase(id)) {
                return list.get(i);
            }
        }
        return null;
    }

}
