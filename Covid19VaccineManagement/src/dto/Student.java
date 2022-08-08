/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author macos
 */
public class Student implements Serializable, Comparable<Student> {

    String idStd;
    String nameStd;

    public Student() {
    }

    public Student(String idStd, String nameStd) {
        this.idStd = idStd;
        this.nameStd = nameStd;
    }

    public String getIdStd() {
        return idStd;
    }

    public String getNameStd() {
        return nameStd;
    }

    public void setIdStd(String idStd) {
        this.idStd = idStd;
    }

    public void setNameStd(String nameStd) {
        this.nameStd = nameStd;
    }

    /**
     * in thông tin student ra màn hình
     */
    public void outputStd() {
        System.out.printf("|   %-6s   | %-24s|\n", idStd, nameStd);
    }

    /**
     *
     * @param o
     * @return id student theo thứ tự tăng dần
     */
    @Override
    public int compareTo(Student o) {
        return this.getIdStd().compareTo(o.getIdStd());
    }
}
