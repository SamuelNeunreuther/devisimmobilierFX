
package com.devisimmobilierfx.utilitaire;

import com.devisimmobilierfx.modele.*;
import java.util.ArrayList;
import java.io.*;

public class SaveManager {
    
    public static void sauvegarderProjet(Projet projet){
        System.out.println("===== Sauvegarde... =====");
        System.out.println();
        
        new File("sauvegarde/").mkdirs();
        
        sauvegarderMaison(projet.getMaisonList());
        System.out.println();
        
        sauvegarderImmeuble(projet.getImmeubleList());
        System.out.println();
        
        ArrayList<Niveau> niveaux = new ArrayList<>();
        ArrayList<Appartement> appartements = new ArrayList<>();
        ArrayList<Piece> pieces = new ArrayList<>();
        
        for (Immeuble immeuble : projet.getImmeubleList()){
            niveaux.addAll(immeuble.getNiveauList());
        }       
        sauvegarderNiveau(niveaux);
        System.out.println();
        
        for (Niveau niveau : niveaux){
            appartements.addAll(niveau.getAppartementList());
        }
        sauvegarderAppartement(appartements);
        System.out.println();
        
        for (Appartement appartement : appartements){
            pieces.addAll(appartement.getPieceList());
        }
        for (Maison maison : projet.getMaisonList()){
            pieces.addAll(maison.getPieceList());
        }
        sauvegarderPiece(pieces);
        System.out.println();
        
        sauvegarderPoint(pieces);
        System.out.println();
        
        sauvegarderRevMur(pieces);
        System.out.println();
        
        sauvegarderSol(pieces);
        System.out.println();
        
        sauvegarderPlafond(pieces);
        System.out.println();
        
        sauvegarderOuverture(pieces);
        System.out.println();
        
        System.out.println("===== Sauvegarde du projet effectuee avec succes ! =====");
        System.out.println();
    }
    
    public static void reset(){
        String[] fichiers = {
            "sauvegarde/maison.csv",
            "sauvegarde/immeuble.csv",
            "sauvegarde/niveau.csv",
            "sauvegarde/appartement.csv",
            "sauvegarde/piece.csv",
            "sauvegarde/point.csv",
            "sauvegarde/revetementmur.csv",
            "sauvegarde/sol.csv",
            "sauvegarde/plafond.csv",
            "sauvegarde/ouverture.csv"
        };
        
        for(String fichier : fichiers){
            new File(fichier).delete();
        }
    }
    
    private static void sauvegarderMaison(ArrayList<Maison> maisonList){ 
        System.out.println("  Sauvegarde des maisons...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/maison.csv"))){            
            bw.write("id;adresse;hauteur");
            for (Maison maison : maisonList){
                bw.newLine();
                String ligne = maison.getId()+";"+maison.getAdresse()+";"+maison.getHauteur();
                bw.write(ligne);
                System.out.println("    sauvegarde : maison "+ligne);
            }
            
            System.out.println("  Sauvegarde des maisons effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des maisons...");
        }
    }
    
    private static void sauvegarderImmeuble(ArrayList<Immeuble> immeubleList){
        System.out.println("  Sauvegarde des immeubles...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/immeuble.csv"))){            
            bw.write("id;adresse");
            for (Immeuble immeuble : immeubleList){
                bw.newLine();
                String ligne = immeuble.getId()+";"+immeuble.getAdresse();
                bw.write(ligne);
                System.out.println("    sauvegarde : immeuble "+ligne);
            }
            
            System.out.println("  Sauvegarde des immeubles effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des immeubles...");
        }
    }
    
    private static void sauvegarderNiveau(ArrayList<Niveau> niveauList){
        System.out.println("  Sauvegarde des niveaux...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/niveau.csv"))){            
            bw.write("id;idImmeuble;hauteur");
            for (Niveau niveau : niveauList){
                bw.newLine();
                String ligne = niveau.getId()+";"+niveau.getImmeuble().getId()+";"+niveau.getHauteur();
                bw.write(ligne);
                System.out.println("    sauvegarde : niveau "+ligne);
            }
            
            System.out.println("  Sauvegarde des niveaux effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des niveaux...");
        }
    }
    
    private static void sauvegarderAppartement(ArrayList<Appartement> appartementList){
        System.out.println("  Sauvegarde des appartements...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/appartement.csv"))){            
            bw.write("id;idNiveau");
            for (Appartement appartement : appartementList){
                bw.newLine();
                String ligne = appartement.getId()+";"+appartement.getNiveau().getId();
                bw.write(ligne);
                System.out.println("    sauvegarde : appartement "+ligne);
            }
            
            System.out.println("  Sauvegarde des appartements effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des appartements...");
        }
    }
    
    private static void sauvegarderPiece(ArrayList<Piece> pieceList){
        System.out.println("  Sauvegarde des pieces...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/piece.csv"))){
            bw.write("id;idLogement;usage");
            for (Piece piece : pieceList){
                bw.newLine();
                String ligne = piece.getId()+";"+piece.getLogement().getId()+";"+piece.getUsage();
                bw.write(ligne);
                System.out.println("    sauvegarde : piece "+ligne);
            }
            
            System.out.println("  Sauvegarde des pieces effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des pieces...");
        }
    }
    
    private static void sauvegarderPoint(ArrayList<Piece> pieceList){
        System.out.println("  Sauvegarde des sommets...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/point.csv"))){
            bw.write("idPiece;index;x;y");
            for (Piece piece : pieceList){
                int index = 0;
                for (Point point : piece.getSommets()){
                    bw.newLine();
                    String ligne = piece.getId()+";"+index+";"+point.getX()+";"+point.getY();
                    bw.write(ligne);
                    System.out.println("    sauvegarde : sommet "+ligne);
                    
                    index++;
                }
            }
            
            System.out.println("  Sauvegarde des sommets effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des sommets de pieces...");
        }
    }
    
    private static void sauvegarderRevMur(ArrayList<Piece> pieceList){
        System.out.println("  Sauvegarde des revetements de mur...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/revetementmur.csv"))){
            bw.write("idPiece;idRevetement;index");
            for (Piece piece : pieceList){
                int index = 0;
                for(Mur mur : piece.getMurList()){
                    int idRev;
                    if(mur.getRevetement()!= null){
                        idRev = mur.getRevetement().getIdRev();
                    }
                    else{
                        idRev = -1;
                    }
                    bw.newLine();
                    String ligne = piece.getId() +";"+ idRev +";"+ index;
                    bw.write(ligne);
                    System.out.println("    sauvegarde : revetement de mur "+ligne);
                    
                    index++;
                }
            }
            
            System.out.println("  Sauvegarde des revetements de mur effectuee");
        }
        catch (IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des revetements des murs...");
        }
    }
    
    public static void sauvegarderRevetement(){
        System.out.println("  Sauvegarde des revetements...");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/revetement.txt"))){
            bw.write("id;type;prixUnitaire");
            for(Revetement revetement : BanqueRevetement.getAll().values()){
                bw.newLine();
                String ligne = revetement.getIdRev()+";"+
                        revetement.getType()+";"+
                        revetement.getPrixUnitaire();
                bw.write(ligne);
                System.out.println("    sauvegarde : revetement "+ligne);
            }
            
            System.out.println("  Sauvegarde des revetements effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des revetements...");
        }
    }
    
    private static void sauvegarderSol(ArrayList<Piece> pieceList){
        System.out.println("  Sauvegarde des sols...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/sol.csv"))){
            bw.write("id;idPiece;idRevetement");
            for (Piece piece : pieceList){
                int idRev;
                if(piece.getSol().getRevetement() != null){
                    idRev = piece.getSol().getRevetement().getIdRev();
                    
                }
                else{
                    idRev = -1;
                }
                bw.newLine();
                String ligne = piece.getSol().getId()+";"+ piece.getId()+";"+ idRev;
                bw.write(ligne);
                System.out.println("    sauvegarde : sol "+ligne);
            }
            
            System.out.println("  Sauvegarde des sols effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des sols...");
        }
    }
    
    private static void sauvegarderPlafond(ArrayList<Piece> pieceList){
        System.out.println("  Sauvegarde des plafonds...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/plafond.csv"))){
            bw.write("id;idPiece;idRevetement");
            for (Piece piece : pieceList){
                int idRev;
                if(piece.getPlafond().getRevetement() != null){
                    idRev = piece.getPlafond().getRevetement().getIdRev();
                }
                else{
                    idRev = -1;
                }
                bw.newLine();
                String ligne = piece.getPlafond().getId()+";"+ piece.getId()+";"+ idRev;
                bw.write(ligne);
                System.out.println("    sauvegarde : plafond "+ligne);
            }
            
            System.out.println("  Sauvegarde des plafonds effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des plafonds...");
        }
    }
    
    private static void sauvegarderOuverture(ArrayList<Piece> pieceList){
        System.out.println("  Sauvegarde des ouvertures...");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("sauvegarde/ouverture.csv"))){
            bw.write("id;idPiece;indexMur;type;longueur;largeur;hauteur");
            for (Piece piece : pieceList){
                int index = 0;
                for (Mur mur : piece.getMurList()){
                    if (mur.hasOuverture()){
                        bw.newLine();
                        String ligne = mur.getOuverture().getId()+";"+
                                piece.getId()+";"+
                                index+";"+
                                mur.getOuverture().getType()+";"+
                                mur.getOuverture().getLongueur()+";"+
                                mur.getOuverture().getLargeur()+";"+
                                mur.getOuverture().getHauteur();
                        bw.write(ligne); 
                        System.out.println("    sauvegarde : ouverture "+ligne);
                    }
                    index++;
                }
            }
            
            System.out.println("  Sauvegarde des ouvertures effectuee");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors de la sauvegarde des ouvertures...");
        }
    }
}
