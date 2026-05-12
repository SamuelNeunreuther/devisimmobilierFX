
package com.devisimmobilierfx.modele;


public class Sol extends Paroi{

    public Sol() {
        super();
    }

    public Sol(int id) {
        super(id);
    }
 
    @Override
    public float surface(){
        return this.getPiece().surface();
    }
}
