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
public class Injection implements Comparable<Injection>, Serializable {

    String idInjec;
    String idStd;
    String idVac;
    String firstDate;
    String secondDate;
    String firstPlace;
    String secondPlace;

    public Injection() {
    }

    public Injection(String idInjec, String idStd, String idVac, String firstPlace, String firstDate, String secondPlace, String secondDate) {
        this.idInjec = idInjec;
        this.idStd = idStd;
        this.idVac = idVac;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
    }

    public String getIdInjec() {
        return idInjec;
    }

    public String getIdStd() {
        return idStd;
    }

    public String getIdVac() {
        return idVac;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setIdInjec(String idInjec) {
        this.idInjec = idInjec;
    }

    public void setIdStd(String idStd) {
        this.idStd = idStd;
    }

    public void setIdVac(String idVac) {
        this.idVac = idVac;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    /**
     * in thông tin Injection ra màn hình
     */
    public void outputInjection() {
        if (secondPlace == null && secondDate == null) {
            secondPlace = " ";
            secondDate = " ";
        }
        System.out.printf("| %-7s|   %-6s   |    %-5s    | %-21s|   %-10s   | %-21s|   %-10s   |  %-13s  |\n", idInjec, idStd, idVac,
                firstPlace, firstDate, secondPlace, secondDate, !" ".equals(getSecondPlace()) ? "  Completed" : "Not Completed");
    }

    /**
     * @return thông tin Injection ra màn hình
     */
    @Override
    public String toString() {
        if (" ".equals(secondPlace) && " ".equals(secondDate)) {
            secondPlace = "null";
            secondDate = "null";
        }
        return idInjec + "-" + idStd + "-" + idVac + "-" + firstPlace + "-" + firstDate + "-" + secondPlace + "-" + secondDate;
    }

    /**
     *
     * @param o
     * @return id injection theo thứ tự tăng dần
     */
    @Override
    public int compareTo(Injection o) {
        return this.getIdInjec().compareTo(o.getIdInjec());
    }

}
