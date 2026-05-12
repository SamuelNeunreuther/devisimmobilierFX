
package com.devisimmobilierfx.modele;

import com.devisimmobilierfx.utilitaire.GenerateurID;

public abstract class Batiment {
    
    protected final int id;
    
    protected String adresse;
    
    public Batiment(){
        this.id = GenerateurID.genererId();
    }
    
    public Batiment(String adresse){
        this.adresse = adresse;
        this.id = GenerateurID.genererId();
    }
    
    //Uniquement pour load de la sauvegarde
    public Batiment(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        if (adresse == null){
            throw new IllegalStateException("Ce batiment n'a pas d'adresse...");
        }
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public void afficher(){
        
    }
}
