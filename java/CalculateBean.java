/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author furkan y
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author PELIN
 */
@ManagedBean(name = "cal")
@SessionScoped
public class CalculateBean {

    private String adet="0";
    private String agirlik="0";
    private String mesafe="0";
    private String sonuc="";

    
    public void setAdet(String adet) {
        this.adet = adet;
    }

    public String getAdet() {
        return adet;
    }

    public void setAgirlik(String agirlik) {
        this.agirlik = agirlik;
    }

    public String getAgirlik() {
        return agirlik;
    }

    public void setMesafe(String mesafe) {
        this.mesafe = mesafe;
    }

    public String getMesafe() {
        return mesafe;
    }

 


    public String getSonuc() {
        int b=Integer.parseInt(getAgirlik())*Integer.parseInt(getMesafe())*Integer.parseInt(getAdet());
       sonuc= String.valueOf(b);
        return sonuc+ "$";
    }
}
