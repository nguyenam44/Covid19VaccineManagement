/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import dto.Student;
import java.util.ArrayList;
import java.util.Collections;
import validation.file.FactoryFile;

/**
 *
 * @author macos
 */
public class StudentList {

    ArrayList<Student> list;
    FactoryFile f;

    public StudentList() throws Exception {
        list = new ArrayList<>();
        f = new FactoryFile();
        f.readStdFromFile(list);

    }

    /**
     * @param id
     * @return vị trí Student tìm thấy, ngược lại return null
     */
    protected Student findStudentById(String id) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getIdStd().equalsIgnoreCase(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * in danh sách Student ra màn hình
     */
    public void printStd() {
        System.out.println("\n          === STUDENT LIST ===");
        System.out.println("| ID STUDENT |     NAME OF STUDENT     |");
        Collections.sort(list);
        list.forEach(student -> {
            student.outputStd();
        });
        System.out.println("Total " + this.getSize() + " students.");
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
     * @param nameStd
     * @return danh sách id student có tên trùng với tên cần tìm
     */
    public ArrayList<String> findStudentByNameReturnID(String nameStd) {
        ArrayList<String> listId = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getNameStd().toUpperCase().contains(nameStd.toUpperCase())) {
                listId.add(list.get(i).getIdStd());
            }
        }
        return listId;
    }

}
