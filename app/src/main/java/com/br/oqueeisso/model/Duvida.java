package com.br.oqueeisso.model;

import java.io.Serializable;
import java.util.Date;


public class Duvida implements Serializable{

    private int id;
    private String duvida;
    private byte[] resource;
    private Date dataDuvida;
    private String usrCad;

    public String getUsrCad() {
        return usrCad;
    }

    public void setUsrCad(String usrCad) {
        this.usrCad = usrCad;
    }

    public Date getDataDuvida() {
        return dataDuvida;
    }

    public void setDataDuvida(Date dataDuvida) {
        this.dataDuvida = dataDuvida;
    }

    public byte[] getResource() {
        return resource;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    public String getDuvida() {
        return duvida;
    }

    public void setDuvida(String duvida) {
        this.duvida = duvida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
