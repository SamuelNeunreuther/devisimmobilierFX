
package com.devisimmobilierfx.modele;

import com.devisimmobilierfx.utilitaire.GenerateurID;
import com.devisimmobilierfx.utilitaire.TypeOuverture;

public class Ouverture {
      
    private Mur mur;
    
    private TypeOuverture type;
    private float longueur;
    private float largeur;
    private float hauteur;
    
    private final int id;

    public Ouverture() {
        this.id = GenerateurID.genererId();
    } 

    public Ouverture(TypeOuverture type) {
        this.type = type;
        this.id = GenerateurID.genererId();
    }
    
    //Uniquement pour load de la sauvegarde
    public Ouverture(int id) {
        this.id = id;
    }
    
    public Mur getMur() {
        return mur;
    }

    public void setMur(Mur mur) {
        this.mur = mur;
    }
    
    public int getId(){
        return this.id;
    }

    public TypeOuverture getType() {
        return type;
    }

    public void setType(TypeOuverture type) {
        this.type = type;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }
    
    public void afficher(){
        System.out.println("Ouverture "+this.id);
        System.out.println("    - Type : "+this.type);
        System.out.println("    - Dimensions : "+this.longueur+"m*"+this.largeur+"m a "+this.hauteur+"m du sol");
    }
}
