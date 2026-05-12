
package com.devisimmobilierfx.modele;

import com.devisimmobilierfx.utilitaire.Point;
import com.devisimmobilierfx.utilitaire.Segment;

public class Mur extends Paroi{

    private Ouverture ouverture;
    
    private Segment position;
    
    public Mur(){
        super();
    }
    
    public Mur (Segment position){
        super();
        this.position = position;
    }
    
    public Mur(Point p1, Point p2){
        super();
        this.position = new Segment(p1, p2);
    }

    public Mur(int id) {
        super(id);
    }
        
    public void addOuverture(Ouverture ouverture){
        this.verifierNull(ouverture);
        if(this.ouverture!=null){
            throw new IllegalStateException("Cette ouverture est deja utilisee autre part...");
        }
        ouverture.setMur(this);
        this.ouverture = ouverture;
    }
    
    public void removeOuverture(){
        if (this.ouverture == null){
            throw new IllegalStateException("Ce mur n'a pas d'ouverture...");            
        }
        this.ouverture.setMur(null);
        this.ouverture = null;
    }
    
    public void verifierNull(Ouverture ouverture){
        if(ouverture == null){
            throw new IllegalStateException("Ouverture null : erreur...");
        }
    }

    public Segment getPosition() {
        return position;
    }

    public void setPosition(Segment position) {
        this.position = position;
    }
    
    public void setPosition(Point p1, Point p2){
        this.position = new Segment(p1, p2);
    }

    public Ouverture getOuverture() {
        return ouverture;
    }
    
    public boolean contains(Point p){
        return (this.position.getP1().equals(p) || this.position.getP2().equals(p));
    }
    
    public boolean hasOuverture(){
        return this.ouverture != null;
    }
    
    @Override
    public float surface(){
        return this.position.longueur() * super.piece.getLogement().getHauteur();
    }
}
