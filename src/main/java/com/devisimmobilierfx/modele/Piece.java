
package com.devisimmobilierfx.modele;

import com.devisimmobilierfx.utilitaire.GenerateurID;
import com.devisimmobilierfx.utilitaire.Point;
import java.util.ArrayList;

public class Piece {
    
    private Logement logement;
    private final ArrayList<Mur> murList = new ArrayList<>();
    private final ArrayList<Point> sommets = new ArrayList<>();
    private Plafond plafond;
    private Sol sol;
    
    private String usage;
    
    private final int id;
    
    public Piece(){
        this.id = GenerateurID.genererId();
    }
    
    public Piece(int id){
        this.id = id;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }
    
    public float surface(){
        float a=0;
        int n = sommets.size();
        if (n<3){
            throw new IllegalStateException("Une piece doit avoir au moins 3 sommets...");
        }
        for (int i = 0; i < n; i++){
            a+= (sommets.get(i).getX()*sommets.get((i+1)%n).getY() - sommets.get((i+1)%n).getX()*sommets.get(i).getY());
        }
        return Math.abs(a)/2f;
    }
    
    public float cout(){
        float c = 0;
        //cout de revetement
        int n = murList.size();
        for (int i=0; i<n; i++){
            c += murList.get(i).cout();
        }
        c+= this.plafond.cout();
        c+= this.sol.cout();
        return c;
    }
    
    public void addSommet (int index, Point sommet){
        for (Point point : sommets){
            if(sommet.equals(point)){
                throw new IllegalStateException("Ce sommet a deja ete ajoute...");
            }
        }
        sommets.add(index, sommet); 
        updateMur();
    }
    
    public void addSommet (Point sommet){
        for (Point point : sommets){
            if(sommet.equals(point)){
                throw new IllegalStateException("Ce sommet a deja ete ajoute...");
            }
        }
        sommets.add(sommet); 
        updateMur();
    }
    
    public void removeSommet(Point sommet){
        int index = sommets.indexOf(sommet);
        
        if (index == -1) {
            throw new IllegalStateException("Sommet inexistant...");
        }
        
        sommets.remove(index);
        updateMur();
    }
    
    public void updateMur(){
        murList.clear();
        
        int n = sommets.size();
        for (int i = 0; i<n; i++){
            Mur mur = new Mur(sommets.get(i), sommets.get((i+1)%n));
            this.addMur(mur);
        }
    }
    
    public void addMur(Mur mur){
        this.verifierNull(mur);
        if(mur.getPiece() != null){
            throw new IllegalStateException("Ce mur est deja utilise autre part...");
        }
        this.murList.add(mur);
        mur.setPiece(this);
    }
    
    public void removeMur(Mur mur){
        this.verifierNull(mur);
        if(!this.murList.contains(mur)){
            throw new IllegalStateException("Le mur n'est pas dans cette piece...");
        }
        this.murList.remove(mur);
        mur.setPiece(null);
    }
    
    public void addSol(Sol sol){
        this.verifierNull(sol);
        if(sol.getPiece()!=null){
            throw new IllegalStateException("Ce sol est deja utilise autre part...");
        }
        this.sol=sol;
        sol.setPiece(this);
    }
    
    public void removeSol(){
        if(this.sol==null){
            throw new IllegalStateException("Cette piece n'a pas de sol...");
        }
        sol.setPiece(null);
        this.sol=null;       
    }
    
    public void addPlafond(Plafond plafond){
        this.verifierNull(plafond);
        if(plafond.getPiece()!=null){
            throw new IllegalStateException("Ce plafond est deja utilise autre part...");
        }
        this.plafond=plafond;
        plafond.setPiece(this);
    }
    
    public void removePlafond(){
        if(this.plafond==null){
            throw new IllegalStateException("Cette piece n'a pas de plafond...");
        }
        plafond.setPiece(null);
        this.plafond=null;
    }
        
    public void verifierNull(Paroi paroi){
        if(paroi == null){
            throw new IllegalStateException("Mur null : erreur...");
        }
    }
    
    public int getIdPiece(){
        return this.logement.getIndexPiece(this);
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Plafond getPlafond() {
        return plafond;
    }

    public Sol getSol() {
        return sol;
    }
    
    public int getId(){
        return this.id;
    }

    public ArrayList<Point> getSommets() {
        return sommets;
    }

    public ArrayList<Mur> getMurList() {
        return murList;
    }
    
    public void afficher(){
        String s;
        if(sommets.size()>=3){
            s=this.surface()+"m2";
        }
        else{
            s="NA";
        }
        System.out.println("Piece "+this.id);
        System.out.println("    - Usage : "+this.usage);
        System.out.println("    - Surface : "+s);
    }
}
