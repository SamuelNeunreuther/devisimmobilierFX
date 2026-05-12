
package com.devisimmobilierfx.utilitaire;

import com.devisimmobilierfx.modele.*;
import java.util.ArrayList;

public class Projet {
    
    private final ArrayList<Maison> maisonList;
    private final ArrayList<Immeuble> immeubleList;

    public Projet() {
        maisonList = new ArrayList<>();
        immeubleList = new ArrayList<>();
    }
    
    public void addMaison(Maison maison){
        maisonList.add(maison);
    }
    
    public void addImmeuble(Immeuble immeuble){
        immeubleList.add(immeuble);
    }
    
    public ArrayList<Maison> getMaisonList() {
        return maisonList;
    }

    public ArrayList<Immeuble> getImmeubleList() {
        return immeubleList;
    }
    
    public void afficher(){
        System.out.println("- Liste de maisons :");
        for(Maison maison : this.maisonList){
            maison.afficher();
            System.out.println("    - Liste de pieces :");
            for(Piece piece : maison.getPieceList()){
                piece.afficher();
                System.out.println("    - Sol :");
                piece.getSol().afficher();
                System.out.println("    - Plafond :");
                piece.getPlafond().afficher();
                System.out.println("    - Liste de murs :");
                for(Mur mur : piece.getMurList()){
                    mur.afficher();
                    if(mur.hasOuverture()){
                        System.out.println("    - Ouverture :");
                        mur.getOuverture().afficher();
                    }
                }
            }
        }
        System.out.println("    - Liste d'immeubles :");
        for (Immeuble immeuble : this.immeubleList){
            immeuble.afficher();
            System.out.println("    - Liste de niveaux :");
            for(Niveau niveau : immeuble.getNiveauList()){
                niveau.afficher();
                System.out.println("    - Liste d'appartements :");
                for(Appartement a : niveau.getAppartementList()){
                    a.afficher();
                    System.out.println("    - Liste de pieces :"); 
                    for(Piece piece : a.getPieceList()){
                        piece.afficher();
                        System.out.println("    - Sol :");
                        piece.getSol().afficher();
                        System.out.println("    - Plafond :");
                        piece.getPlafond().afficher();
                        System.out.println("    - Liste de murs :");
                        for(Mur mur : piece.getMurList()){
                            mur.afficher();
                            if(mur.hasOuverture()){
                                System.out.println("    - Ouverture :");
                                mur.getOuverture().afficher();
                            }
                        }
                    }
                }
            }
        }
    }
}
