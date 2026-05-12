
package com.devisimmobilierfx.utilitaire;

import java.io.*;
import java.util.ArrayList;
import com.devisimmobilierfx.modele.*;

public class LoadManager {
    
    public static Projet chargerProjet(){
        Projet projet = new Projet();
        
        System.out.println("===== Chargement... =====");
        
        chargerRevetement();
        System.out.println();
        
        var maisons = chargerMaison();
        System.out.println();
        
        var immeubles = chargerImmeuble();
        System.out.println();
                
        var niveaux = chargerNiveau(immeubles);
        System.out.println();
        
        var appartements = chargerAppartement(niveaux);
        System.out.println();
        
        var pieces = chargerPiece(maisons, appartements);
        System.out.println();
        
        chargerPoint(pieces);
        System.out.println();
        
        for(Piece piece : pieces){
            piece.updateMur();
        }
        
        chargerRevMur(pieces);       
        chargerSol(pieces);
        chargerPlafond(pieces);
        chargerOuverture(pieces);
        
        for (Maison maison : maisons) {
            projet.addMaison(maison);
        }
        
        for (Immeuble immeuble : immeubles){
            projet.addImmeuble(immeuble);
        }  
        
        System.out.println("===== Chargement du projet effectue avec succes ! =====");
        
        return projet;
    }
    
    private static ArrayList<Maison> chargerMaison(){
        System.out.println("  Chargement des maisons...");
        var maisonList = new ArrayList<Maison>(); 
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/maison.csv"))){                      
            br.readLine();
            String ligne;
            while((ligne = br.readLine()) != null){
                String[] parties = ligne.split(";");
                int id = Integer.parseInt(parties[0]);

                Maison maison = new Maison(id);
                maison.setAdresse(parties[1]);
                maison.setHauteur(Float.parseFloat(parties[2]));
                
                maisonList.add(maison);
                GenerateurID.updateGenerateur(id);
            }
            
            System.out.println("  Chargement des maisons effectue");
            return maisonList;
        }
        catch (IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des maisons...");
            return maisonList;
        }
    }
    
    private static ArrayList<Immeuble> chargerImmeuble(){
        System.out.println("  Chargement des immeubles...");
        var immeubleList = new ArrayList<Immeuble>();
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/immeuble.csv"))){
            br.readLine();
            String ligne;
            while((ligne = br.readLine())!= null){
                String[] parties = ligne.split(";");
                int id = Integer.parseInt(parties[0]);

                Immeuble immeuble = new Immeuble(id);
                immeuble.setAdresse(parties[1]);
                
                immeubleList.add(immeuble);
                GenerateurID.updateGenerateur(id);
            }
            
            System.out.println("  Chargement des immeubles effectue");
            return immeubleList;
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des immeubles...");
            return immeubleList;
        }
    }
    
    private static ArrayList<Niveau> chargerNiveau(ArrayList<Immeuble> immeubleList){
        System.out.println("  Chargement des niveaux...");
        var niveauList = new ArrayList<Niveau>();
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/niveau.csv"))){
            br.readLine();
            String ligne;
            while((ligne = br.readLine())!= null){
                String[] parties = ligne.split(";");
                
                int id = Integer.parseInt(parties[0]);
                Niveau niveau = new Niveau(id);
                
                for (Immeuble immeuble : immeubleList){
                    if (immeuble.getId() == Integer.parseInt(parties[1])){
                        immeuble.addNiveau(niveau);
                        break;
                    }
                }
                
                niveau.setHauteur(Float.parseFloat(parties[2]));

                niveauList.add(niveau);
                GenerateurID.updateGenerateur(id);
            }
            
            System.out.println("  Chargement des niveaux effectue");
            return niveauList;
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des niveaux...");
            return niveauList;
        }
    }
    
    private static ArrayList<Appartement> chargerAppartement(ArrayList<Niveau> niveauList){
        System.out.println("  Chargement des appartements...");
         var appartList = new ArrayList<Appartement>();
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/appartement.csv"))){
            br.readLine();
            String ligne;
            while((ligne = br.readLine()) != null){
                String[] parties = ligne.split(";");
                int id = Integer.parseInt(parties[0]);
                
                Appartement appartement = new Appartement(id);
                
                for(Niveau niveau : niveauList){
                    if(niveau.getId() == Integer.parseInt(parties[1])){
                        niveau.addAppartement(appartement);
                        break;
                    }
                }
                
                appartList.add(appartement);
                GenerateurID.updateGenerateur(id);
            }
            
            System.out.println("  Chargement des appartements effectue");
            return appartList;
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des appartements...");
            return appartList;
        }
    }
    
    private static ArrayList<Piece> chargerPiece(ArrayList<Maison> maisons, ArrayList<Appartement> appartements){
        System.out.println("  Chargement des pieces...");
        var pieceList = new ArrayList<Piece>();
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/piece.csv"))){
            br.readLine();
            String ligne;
            
            while((ligne = br.readLine())!= null){
                String[] parties = ligne.split(";");
                int id = Integer.parseInt(parties[0]);
                int idLog = Integer.parseInt(parties[1]);
                
                Piece piece = new Piece(id);
                
                for(Maison maison : maisons){
                    if(maison.getId() == idLog){
                        maison.addPiece(piece);
                        break;
                    }
                }
                for(Appartement appartement : appartements){
                    if(appartement.getId() == idLog){
                        appartement.addPiece(piece);
                        break;
                    }
                }
                
                piece.setUsage(parties[2]);
                
                pieceList.add(piece);
                GenerateurID.updateGenerateur(id);               
            }
            
            System.out.println("  Chargement des pieces effectue");
            return pieceList;
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des pieces...");
            return pieceList;
        }
    }
    
    private static void chargerPoint(ArrayList<Piece> pieceList){
        System.out.println("  Chargement des sommets...");
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/point.csv"))){
            br.readLine();
            String ligne;
            while((ligne = br.readLine()) != null){
                String[] parties = ligne.split(";");
                int idPiece = Integer.parseInt(parties[0]);
                int index = Integer.parseInt(parties[1]);
                float x = Float.parseFloat(parties[2]);
                float y = Float.parseFloat(parties[3]);
                
                Point point = new Point(x, y);
                
                for(Piece piece : pieceList){
                    if(piece.getId() == idPiece){
                        piece.addSommet(index, point);
                        break;
                    }
                }
            }
            
            System.out.println("  Chargement des sommets effectue");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des sommets...");
        }
    }
    
    private static void chargerRevMur(ArrayList<Piece> pieces){
        System.out.println("  Chargement des revetements de mur...");
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/revetementmur.csv"))){
            br.readLine();
            String ligne;
            
            while((ligne = br.readLine())!= null){
                String[] parties = ligne.split(";");
                int idPiece = Integer.parseInt(parties[0]);
                int idRev = Integer.parseInt(parties[1]);
                int index = Integer.parseInt(parties[2]);
                
                if(idRev!=-1){
                    Revetement revetement = BanqueRevetement.get(idRev);
                    for(Piece piece : pieces){
                        if(piece.getId() == idPiece){
                            piece.getMurList().get(index).setRevetement(revetement);
                            break;
                        }
                    }    
                }   
            }
            
            System.out.println("  Chargement des revetements de mur effectue");
        }
        catch (IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des revetements de mur...");
        }
    }
    
    public static void chargerRevetement(){
        System.out.println("  Chargement des revetements...");
        new File("sauvegarde/").mkdirs();
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/revetement.txt"))){
            br.readLine();
            String ligne;
            
            while((ligne = br.readLine()) != null){
                String[] parties = ligne.split(";");
                BanqueRevetement.ajouter(Integer.parseInt(parties[0]), parties[1], Float.parseFloat(parties[2]));
                BanqueRevetement.updateId(Integer.parseInt(parties[0]));
            }
            
            System.out.println("  Chargement des revetements effectue");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des revetemnets...");
        }
    }
    
    private static void chargerSol(ArrayList<Piece> pieces){
        System.out.println("  Chargement des sols...");
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/sol.csv"))){
            br.readLine();
            String ligne;
            
            while((ligne = br.readLine()) != null){
                String[] parties = ligne.split(";");
                int id = Integer.parseInt(parties[0]);
                int idPiece = Integer.parseInt(parties[1]);
                int idRevetement = Integer.parseInt(parties[2]);
                
                Sol sol = new Sol(id);
                System.out.println("Chargement sol - idSol:" + id + " idRev:" + idRevetement);

                
                if(idRevetement!= -1){
                    sol.setRevetement(BanqueRevetement.get(idRevetement));
                }
                
                for(Piece piece : pieces){
                    if(piece.getId() == idPiece){
                        piece.addSol(sol);
                        break;
                    }
                }
                GenerateurID.updateGenerateur(id);
            }
            
            System.out.println("  Chargement des sols effectue");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des sols...");
        }
    }
    
    private static void chargerPlafond(ArrayList<Piece> pieces){
        System.out.println("  Chargement des plafonds...");
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/plafond.csv"))){
            br.readLine();
            String ligne;
            
            while((ligne = br.readLine())!= null){
                String[] parties = ligne.split(";");
                int id = Integer.parseInt(parties[0]);
                int idPiece = Integer.parseInt(parties[1]);
                int idRevetement = Integer.parseInt(parties[2]);
                
                Plafond plafond = new Plafond(id);
                
                if(idRevetement!= -1){
                    plafond.setRevetement(BanqueRevetement.get(idRevetement));
                }
                
                for(Piece piece : pieces){
                    if(piece.getId() == idPiece){
                        piece.addPlafond(plafond);
                        break;
                    }
                }
                GenerateurID.updateGenerateur(id);
            }
            
            System.out.println("  Chargement des plafonds effectue");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des plafonds...");
        }
    }
    
    private static void chargerOuverture(ArrayList<Piece> pieces){
        System.out.println("  Chargement des ouvertures...");
        try(BufferedReader br = new BufferedReader(new FileReader("sauvegarde/ouverture.csv"))){
            br.readLine();
            String ligne;
            
            while((ligne = br.readLine())!= null){
                String[] parties = ligne.split(";");
                int id = Integer.parseInt(parties[0]);
                int idPiece = Integer.parseInt(parties[1]);
                int indexMur = Integer.parseInt(parties[2]);
                TypeOuverture type = TypeOuverture.valueOf(parties[3]);
                float longueur = Float.parseFloat(parties[4]);
                float largeur = Float.parseFloat(parties[5]);
                float hauteur = Float.parseFloat(parties[6]);
                
                Ouverture ouverture = new Ouverture(id);
                ouverture.setType(type);
                ouverture.setLongueur(longueur);
                ouverture.setLargeur(largeur);
                ouverture.setHauteur(hauteur);
                
                for(Piece piece : pieces){
                    if(idPiece == piece.getId()){
                        piece.getMurList().get(indexMur).addOuverture(ouverture);
                        System.out.println("Chargement ouverture - idPiece:" + idPiece + " indexMur:" + indexMur);
                    }
                }
                GenerateurID.updateGenerateur(id);
            }
            
            System.out.println("  Chargement des ouvertures effectue");
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("(!) Erreur lors du chargement des ouvertures...");
        }
    }
}
