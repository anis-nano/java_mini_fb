package com.usthb.models;
import java.util.List;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Post implements Serializable{
	
     private static int nbr_post;
	 private int numéro_auto,nbr_partage;
     private String contenu;
	 Niveau_visb niveau_visb;
	 private boolean épinglée;
	 private Reaction réaction;
	 public enum Niveau_visb{privé,ami,listeAmis,groupe}
	
	 private HashMap<String,StringBuilder> table_commentaire= new HashMap<String, StringBuilder>() ;
     private List<Reaction> list_Réaction=new ArrayList<Reaction>();

     Post(String contenu,Niveau_visb niveau_visb,boolean épinglée)
     {this.contenu=contenu; this.niveau_visb=niveau_visb; this.épinglée=épinglée;
      nbr_post++; this.numéro_auto=nbr_post;
     }
   

    void incNbr_partage()
   {
	nbr_partage++;
	
   }
   void setEpinglée(boolean b)
   {this.épinglée=b;
   }
   
  void setNiveau_visibilité(Niveau_visb niv)
  {
  this.niveau_visb=niv;
  }
 
public int getNuméro_auto() {
	return numéro_auto;
}


public void setNuméro_auto(int numéro_auto) {
	this.numéro_auto = numéro_auto;
}


public Niveau_visb getNiveau_visb() {
	return niveau_visb;
}


public void setNiveau_visb(Niveau_visb niveau_visb) {
	this.niveau_visb = niveau_visb;
}


public boolean isÉpinglée() {
	return épinglée;
}


public void setÉpinglée(boolean épinglée) {
	this.épinglée = épinglée;
}


public Reaction getRéaction() {
	return réaction;
}


public void setRéaction(Reaction réaction) {
	this.réaction = réaction;
}


public HashMap<String, StringBuilder> getTable_commentaire() {
	return table_commentaire;
}


public void setTable_commentaire(HashMap<String, StringBuilder> table_commentaire) {
	this.table_commentaire = table_commentaire;
}


public List<Reaction> getList_Réaction() {
	return list_Réaction;
}


public void setList_Réaction(List<Reaction> list_Réaction) {
	this.list_Réaction = list_Réaction;
}
boolean getEpinglée()
{
	return this.épinglée;
}

public void ajouteCommentaire(String username,StringBuilder commentaire)
{
	if(this.table_commentaire.containsKey(username))
		this.table_commentaire.get(username).append(" "+commentaire);
	 this.table_commentaire.put(username,commentaire);
	 
}



void afficher()
{
	System.out.println("num_post: "+this.numéro_auto+"\nPost: "+contenu+"\nniveau_visibilité: "+niveau_visb+
			"\nnbr_partage: "+nbr_partage);
	System.out.println("Réaction :");
	if(!this.list_Réaction.isEmpty())
	{
		for(Reaction rea:this.list_Réaction)
		   rea.afficher();
			}
	System.out.print("commentaires: ");
	if(!(this.table_commentaire.isEmpty()))
	{for(String clé:this.table_commentaire.keySet())
	{System.out.print(clé+": "+this.table_commentaire.get(clé)+",");
	}
	}
	
}

   void SetRéaction(String username,Réaction réaction)
    {
	 this.réaction=new Reaction(username,réaction);
    }	

	public void ajouté_reaction(Reaction rect)
	{
		this.list_Réaction.add(rect);
	}
	public void add_commentaire(String username,StringBuilder commentaire)
	{
		this.table_commentaire.put(username,commentaire);
	}
	public int get_numéro_auto()
	{
	return this.numéro_auto;
	}
	
}



	
	
	


