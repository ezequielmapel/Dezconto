package com.dezconto.dezconto_prot.cuponclasses;

import java.util.Calendar;

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


    public static boolean cupomVencimento(Item itemPos){
        // VERIFICANDO SE O CUPOM ESTÁ VENCIDO
        String[] vencimento = itemPos.getDataVenc().split("-");
        int[] dataVenc = new int[2];
        int[] dataHoje = new int[2];
        dataHoje[0] =  Calendar.getInstance().get(Calendar.MONTH)+1;
        dataHoje[1] = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1;


        for( int i = 0; i<vencimento.length; i++){
            dataVenc[i] = Integer.parseInt(vencimento[i]);
            i++;
        }

        return dataHoje[0] == dataVenc[0] && dataHoje[1] == dataVenc[1];
    }


    public static int DiaDoCupomVencer(Item itemPos){
        String[] vencimento = itemPos.getDataVenc().split("-");
        int[] dataVenc = new int[2];
        int[] dataHoje = new int[2];
        int[] distVenc = new int[2];
        dataHoje[0] =  Calendar.getInstance().get(Calendar.MONTH)+1;
        dataHoje[1] = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1;


        for( int i = 0; i<vencimento.length; i++){
            dataVenc[i] = Integer.parseInt(vencimento[i]);
            i++;
        }

        return dataVenc[0];

    }
}
