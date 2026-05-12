
package com.devisimmobilierfx.utilitaire;

import com.devisimmobilierfx.modele.Revetement;
import java.util.HashMap;

public abstract class BanqueRevetement {
    
    private static int currentId = 0;
    private static final HashMap<Integer, Revetement> revetementMap = new HashMap<>();

    public static void ajouter(String type, float prixUnitaire){
        currentId++;        
        Revetement revetement = new Revetement(currentId, type, prixUnitaire);
        revetementMap.put(currentId, revetement);
    }
    
    protected static void ajouter(int id, String type, float prixUnitaire){
        Revetement revetement = new Revetement(id, type, prixUnitaire);
        revetementMap.put(id, revetement);
        updateId(id);
    }
    
    public static void modifier(int id, String type){
        var revetement = revetementMap.get(id);
        if(revetement!=null){
            revetement.setType(type);
        }
    }
    
    public static void modifier(int id, Float prixUnitaire){
        var revetement = revetementMap.get(id);
        if (revetement!=null){
            revetement.setPrixUnitaire(prixUnitaire);
        }
    }
    
    public static Revetement get(int id){
        return revetementMap.get(id);
    }
    
    public static void supprimer(int id){
        revetementMap.remove(id);
    }

    public static HashMap<Integer, Revetement> getAll() {
        return revetementMap;
    }
    
    public static void updateId(int id){
        if (id > currentId){
            currentId = id;
        }
    }
    
    public static void resetId(){
        currentId = 0;
    }
}
