
package com.devisimmobilierfx.utilitaire;

public abstract class GenerateurID {
    
    private static int currentId = 0;
    
    public static int genererId(){
        currentId++;
        return currentId;
    }
    
    public static void updateGenerateur(int id){
        if(id > currentId){
            currentId = id;
        }
    }
    
    public static void reset(){
        currentId = 0;
    }
}
