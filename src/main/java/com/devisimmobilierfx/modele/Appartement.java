
package com.devisimmobilierfx.modele;

import com.devisimmobilierfx.utilitaire.GenerateurID;
import java.util.ArrayList;

public class Appartement implements Logement {
    
    private Niveau niveau;
    private final ArrayList<Piece> pieceList = new ArrayList<>();
    
    private final int id;   
    
    public Appartement(){
        this.id = GenerateurID.genererId();
    }
    
    //Uniquement pour load de la sauvegarde
    public Appartement(int id){
        this.id = id;
    }
    
    @Override
    public void addPiece(Piece piece){
        this.verifierPieceNull(piece);
        if (piece.getLogement() != null){
            throw new IllegalStateException("Cette piece est deja dans un logement...");
        }
        
        this.pieceList.add(piece);
        piece.setLogement(this);
        System.out.println("Piece ("+piece.getId()+") ajoutee à l'appartement ("+this.getId()+")");
    }
    
    @Override
    public void removePiece(Piece piece){
        this.verifierPieceNull(piece);
        if (!pieceList.contains(piece)){
            throw new IllegalStateException("Cette piece n'est pas dans cet appartement...");
        }
        this.pieceList.remove(piece);
        piece.setLogement(null);
    }
    
    @Override
    public int getIndexPiece(Piece piece){ 
        this.verifierPieceNull(piece);
        if (!this.pieceList.contains(piece)){
            throw new IllegalStateException("Cette piece n'est pas dans cet appartement...");
        }
        return this.pieceList.indexOf(piece);
    }
    
    @Override
    public void verifierPieceNull(Piece piece){
        if (piece==null){
            throw new IllegalStateException("Piece null : erreur...");
        }
    }
    
    @Override
    public int getId(){
        return this.id;
    }
    
    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    
    @Override
    public float getHauteur(){
        return this.niveau.getHauteur();
    }

    public ArrayList<Piece> getPieceList() {
        return pieceList;
    }
    
    @Override
    public void afficher(){
        System.out.println("Appartement "+this.id);
    }
}
