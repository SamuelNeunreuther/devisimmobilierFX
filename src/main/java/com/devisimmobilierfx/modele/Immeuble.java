
package com.devisimmobilierfx.modele;

import java.util.ArrayList;

public class Immeuble extends Batiment{
    
    private final ArrayList<Niveau> niveauList = new ArrayList<>();
    
    public Immeuble(){
        super();
    }
    
    public Immeuble(String adresse){
        super(adresse);
    }
    
    //Uniquement pour load de la sauvegarde
    public Immeuble(int id){
        super(id);
    }
    
    public void addNiveau(Niveau niveau){
        if(niveau.getImmeuble() != null){
            throw new IllegalStateException("Ce niveau appartient deja a un immeuble...");
        }        
        this.niveauList.add(niveau);
        niveau.setImmeuble(this);
        System.out.println("Niveau ("+niveau.getId()+") ajoutee à l'immeuble ("+this.getId()+")");
    }
    
    public void removeNiveau(Niveau niveau){
        if(niveau==null){
            throw new IllegalStateException("niveau null : erreur...");
            //pour eviter les erreurs de syntaxe
        }
        
        if(!this.niveauList.contains(niveau)){
            throw new IllegalStateException("Le niveau n'est pas dans cet immeuble...");
        }
        this.niveauList.remove(niveau);
        
        niveau.setImmeuble(null);
    }
    
    public int getEtage(Niveau niveau){
        if(niveau==null){
            throw new IllegalStateException("niveau null : erreur...");
            //pour eviter les erreurs de syntaxe
        }
        int i = this.niveauList.indexOf(niveau);
        
        if (i == -1){
            throw new IllegalStateException("Le niveau n'est pas dans cet immeuble...");
            //indexOf() return -1 si niveau n'est pas dans la liste
        }       
        return i;        
    }
    
    public int getNbNiveau(){
        return this.niveauList.size();
    }

    public ArrayList<Niveau> getNiveauList() {
        return niveauList;
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
        System.out.println("Immeuble "+this.id);
        System.out.println("    - Adresse : " + a);
    }
}
