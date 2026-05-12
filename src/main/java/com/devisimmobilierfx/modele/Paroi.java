
package com.devisimmobilierfx.modele;

import com.devisimmobilierfx.utilitaire.GenerateurID;

public abstract class Paroi {
    
    protected Piece piece;
    protected Revetement revetement;
    
    private final int id;
    
    public Paroi(){
        this.id = GenerateurID.genererId();
    }
    
    public Paroi(int id){
        this. id = id;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Revetement getRevetement() {
        return revetement;
    }

    public void setRevetement(Revetement revetement) {
        this.revetement = revetement;
    }
    
    public float surface(){
        return -1;
    }
    
    public float cout(){
        return this.surface()*this.revetement.getPrixUnitaire();
    }
    
    public int getId(){
        return this.id;
    }
    
    public void afficher() {
    System.out.println(getClass().getSimpleName() + " : " + this.id);
    if (this.revetement != null) {
        System.out.println("    Revetement : " + this.revetement.getType());
    } else {
        System.out.println("    Revetement : aucun");
    }
}
}
