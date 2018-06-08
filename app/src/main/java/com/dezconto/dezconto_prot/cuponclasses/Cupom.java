package com.dezconto.dezconto_prot.cuponclasses;

/**
 * Created by brhue on 07/06/2018.
 */

public class Cupom {
    private String nomeCupom;
    private String  desCupom;
    private String  qtdCupom;
    private String  valCupom;
    private String  validadeCupom;
    private String  validadePromo;
    private String idCupom;
    private boolean value;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
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

    public String getDesCupom() {
        return desCupom;
    }

    public void setDesCupom(String desCupom) {
        this.desCupom = desCupom;
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

    public String getValidadeCupom() {
        return validadeCupom;
    }

    public void setValidadeCupom(String validadeCupom) {
        this.validadeCupom = validadeCupom;
    }

    public String getValidadePromo() {
        return validadePromo;
    }

    public void setValidadePromo(String validadePromo) {
        this.validadePromo = validadePromo;
    }

    public Cupom(String idCupom, boolean value){
        this.idCupom = idCupom;
        this.value = value;

    }

    public Cupom(String nomeCupom, String desCupom, String qtdCupom, String valCupom, String validadeCupom, String validadePromo){
        this.nomeCupom = nomeCupom;
        this.desCupom = desCupom ;
        this.qtdCupom = qtdCupom;
        this.valCupom = valCupom;
        this.validadeCupom = validadeCupom;
        this.validadePromo = validadePromo;
    }
}
