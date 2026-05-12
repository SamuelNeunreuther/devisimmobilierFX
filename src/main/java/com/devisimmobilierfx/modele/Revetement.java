
package com.devisimmobilierfx.modele;

public class Revetement {
    
    private int id;
    private String type;
    private float prixUnitaire;

    public Revetement(int id, String type, float prixUnitaire ) {
        this.id = id;
        this.type = type;
        this.prixUnitaire = prixUnitaire;
    }

    public int getIdRev() {
        return id;
    }

    public void setIdRev(int idRev) {
        this.id = idRev;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    
    @Override
    public String toString(){
        return id+" - "+type+" ("+prixUnitaire+" euro/m2)";
    }
}
