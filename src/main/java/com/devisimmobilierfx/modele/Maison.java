
package com.devisimmobilierfx.modele;

import java.util.ArrayList;

public class Maison extends Batiment implements Logement{
    
    private final ArrayList<Piece> pieceList = new ArrayList<>();
    
    private float hauteur;
    
    public Maison(){
        super();
    }
    
    public Maison(String adresse){
        super(adresse);
    }
    
    public Maison(float hauteur){
        super();
        this.hauteur = hauteur;
    }
    
    public Maison(String adresse, float hauteur){
        super(adresse);
        this.hauteur = hauteur;
    }
    
    //Uniquement pour load de la sauvegarde
    public Maison(int id){
        super(id);
    }
    
    @Override
    public void addPiece(Piece piece){
        this.verifierPieceNull(piece);
        if (piece.getLogement() != null){
            throw new IllegalStateException("Cette piece est deja dans un logement...");
        }
        
        this.pieceList.add(piece);
        piece.setLogement(this);
        System.out.println("Piece ("+piece.getId()+") ajoutee à la maison ("+this.getId()+")");
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
    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    public ArrayList<Piece> getPieceList() {
        return pieceList;
    }
    
    @Override
    public void afficher(){
        String a;
        if(this.adresse ==null){
            a = "NA";
        }
        else{
            a = this.adresse;
        }
        
        System.out.println("Maison "+this.getId());
        System.out.println("    - Adresse : "+this.adresse);
        System.out.println("    - Hauteur : "+this.hauteur+"m");
        
    }
}
