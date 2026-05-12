
package com.devisimmobilierfx.modele;

import com.devisimmobilierfx.utilitaire.GenerateurID;
import java.util.ArrayList;

public class Niveau {
        
    private Immeuble immeuble;
    private final ArrayList<Appartement> appartementList = new ArrayList<>();
    
    private float hauteur;
    
    private final int id;

    public Niveau() {
        this.id = GenerateurID.genererId();
    }

    public Niveau(float hauteur) {
        this.id = GenerateurID.genererId();
        this.hauteur = hauteur;
    }
    
    public Niveau(int id){
        this.id = id;
    }
    
    public void addAppartement(Appartement appartement){
        if(appartement.getNiveau() != null){
            throw new IllegalStateException("Cet appartement appartient deja a un niveau...");
        }        
        this.appartementList.add(appartement);
        appartement.setNiveau(this);
        System.out.println("Appartement ("+appartement.getId()+") ajoutee au niveau ("+this.getId()+")");
    }
    
    public void removeAppartement(Appartement appartement){
        if(appartement ==null){
            throw new IllegalStateException("appartement null : erreur...");
            //pour eviter les erreurs de syntaxe
        }
        
        if(!this.appartementList.contains(appartement)){
            throw new IllegalStateException("L'appartement n'est pas dans ce niveau...");
        }
        this.appartementList.remove(appartement);
        
        appartement.setNiveau(null);
    }
    
    public int getNombreAppartement(){
        return this.appartementList.size();
    }
    
    public int getIndexAppartement(Appartement appartement){
        return this.appartementList.indexOf(appartement)+1;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }
    
    public int getId(){
        return this.id;
    }

    public ArrayList<Appartement> getAppartementList() {
        return appartementList;
    }
    
    public void afficher(){
        System.out.println("Niveau "+this.id);
        System.out.println("    - Hauteur : "+this.hauteur+"m");
        System.out.println();
    }
}
