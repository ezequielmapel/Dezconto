package com.dezconto.dezconto_prot.cuponclasses;

/**
 * Created by brhue on 28/04/2018.
 */

public class Item{
    private String fotoLoja;
    private String idCupom;
    private String nomeCupom;
    private String qtdCupom;
    private String valCupom;
    private String validadePromo;
    private String catCupom;
    private String validadeCupom;
    private String desCupom;

    public String getFotoLoja() {
        return fotoLoja;
    }

    public void setFotoLoja(String fotoLoja) {
        this.fotoLoja = fotoLoja;
    }

    public String getIdCupom() {
        return idCupom;
    }

    public void setIdCupom(String idCupom) {
        this.idCupom = idCupom;
    }


    public String getNomeCupom() {
        return nomeCupom;
    }

    public void setNomeCupom(String nomeCupom) {
        this.nomeCupom = nomeCupom;
    }

    public String getQtdCupom() {
        return qtdCupom;
    }

    public void setQtdCupom(String qtdCupom) {
        this.qtdCupom = qtdCupom;
    }

    public String getValCupom() {
        return valCupom;
    }

    public void setValCupom(String valCupom) {
        this.valCupom = valCupom;
    }

    public String getValidadePromo() {
        return validadePromo;
    }

    public void setValidadePromo(String validadePromo) {
        this.validadePromo = validadePromo;
    }

    public String getCatCupom() {
        return catCupom;
    }

    public void setCatCupom(String catCupom) {
        this.catCupom = catCupom;
    }

    public String getValidadeCupom() {
        return validadeCupom;
    }

    public void setValidadeCupom(String validadeCupom) {
        this.validadeCupom = validadeCupom;
    }

    public String getDesCupom() {
        return desCupom;
    }

    public void setDesCupom(String desCupom) {
        this.desCupom = desCupom;
    }





    public Item(){

    }

    public Item(String nomeCupom, String qtdCupom, String valCupom, String validadePromo, String catCupom, String validadeCupom, String desCupom, String fotoLoja, String idCupom){
        this.nomeCupom = nomeCupom;
        this.qtdCupom = qtdCupom;
        this.valCupom = valCupom;
        this.validadeCupom = validadeCupom;
        this.catCupom = catCupom;
        this.validadePromo = validadePromo;
        this.desCupom = desCupom;
        this.fotoLoja = fotoLoja;
        this.idCupom = idCupom;
    }

}
