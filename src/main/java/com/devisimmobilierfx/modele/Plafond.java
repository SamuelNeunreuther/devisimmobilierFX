
package com.devisimmobilierfx.modele;


public class Plafond extends Paroi{

    public Plafond() {
        super();
    }

    public Plafond(int id) {
        super(id);
    }

    @Override
    public float surface(){
        return this.getPiece().surface();
    }   
}
