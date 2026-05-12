
package com.devisimmobilierfx;

import com.devisimmobilierfx.modele.*;
import com.devisimmobilierfx.utilitaire.*;

public class DevisImmobilierFX {

    public static void main(String[] args) {

    }
    
    public static void test1(){
        System.out.println("=== DEBUT DU TEST ===");
        System.out.println();
        System.out.println("Chargement des revetements");
        LoadManager.chargerRevetement();
        System.out.println();
        System.out.println("Creation d'objets : ");
        
        Maison m1 = new Maison(2.30f);
        m1.setAdresse("4 impasse du Lavoir, 67370, Willgottheim");
        
        Piece p1 = new Piece();
        p1.setUsage("salon");
        p1.addSommet(new Point(0,0));
        p1.addSommet(new Point(0,2));
        p1.addSommet(new Point(2,2));
        p1.addSommet(new Point(2,0));
        Sol s1 = new Sol();
        s1.setRevetement(BanqueRevetement.get(5));
        p1.addSol(s1);
        Plafond pl1 = new Plafond();
        pl1.setRevetement(BanqueRevetement.get(16));
        p1.addPlafond(pl1);
        for(Mur mur : p1.getMurList()){
            mur.setRevetement(BanqueRevetement.get(13));
        }
        m1.addPiece(p1);
        
        System.out.println("cout de p1 : "+p1.cout()+" euros");
        
        Immeuble i1 = new Immeuble("11 rue de Geispolsheim, 67000, Strasbourg");
        Niveau n1 = new Niveau(3.00f);
        i1.addNiveau(n1);
        
        Appartement a1 = new Appartement();
        n1.addAppartement(a1);
        
        Piece p2 = new Piece();
        p2.setUsage("chambre");
        p2.addSommet(new Point(3,5));
        p2.addSommet(new Point(5,5));
        p2.addSommet(new Point(5,3));
        p2.addSommet(new Point(3,3));
        Sol s2 = new Sol();
        s2.setRevetement(BanqueRevetement.get(7));
        p2.addSol(s2);
        Plafond pl2 = new Plafond();
        pl2.setRevetement(BanqueRevetement.get(2));
        p2.addPlafond(pl2);
        for(Mur mur : p2.getMurList()){
            mur.setRevetement(BanqueRevetement.get(14));
        }        
        Ouverture o1 = new Ouverture(TypeOuverture.FENETRE);
        p2.getMurList().get(0).addOuverture(o1);
        
        a1.addPiece(p2);
        System.out.println("cout de p2 : "+p2.cout()+" euros");
        
        Projet p = new Projet();
        p.addImmeuble(i1);
        p.addMaison(m1);
        
        System.out.println();
        System.out.println("=== Sauvegarde... ===");
        System.out.println();
        SaveManager.sauvegarderProjet(p);
        
        System.out.println();
        System.out.println("===Chargement... ===");
        System.out.println();
        LoadManager.chargerProjet();
    }
    
    public static void test2(){
        Projet p = LoadManager.chargerProjet();
        p.afficher();
    }
}
