
package com.devisimmobilierfx.modele;


public interface Logement {
    
    void addPiece(Piece piece);
    void removePiece(Piece piece);
    void verifierPieceNull(Piece piece);
    int getIndexPiece(Piece piece);
    int getId();
    float getHauteur();
    void afficher();
    //int nbPiece(); 
    //Piece getPiece(int index);
    
}
