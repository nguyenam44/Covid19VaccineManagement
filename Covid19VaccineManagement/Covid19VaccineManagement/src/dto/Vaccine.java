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
public class Vaccine implements Serializable, Comparable<Vaccine> {

    String idVac;
    String nameVac;

    public Vaccine() {
    }

    public Vaccine(String idVac, String nameVac) {
        this.idVac = idVac;
        this.nameVac = nameVac;
    }

    public String getIdVac() {
        return idVac;
    }

    public String getNameVac() {
        return nameVac;
    }

    public void setIdVac(String idVac) {
        this.idVac = idVac;
    }

    public void setNameVac(String nameVac) {
        this.nameVac = nameVac;
    }

    /**
     * in thông tin Vaccine ra màn hình
     */
    public void outputVaccine() {
        System.out.printf("|    %-5s    | %-18s|\n", idVac, nameVac);
    }

    /**
     *
     * @param o
     * @return id vaccine theo thứ tự tăng dần
     */
    @Override
    public int compareTo(Vaccine o) {
        return this.getIdVac().compareTo(o.getIdVac());
    }

}
