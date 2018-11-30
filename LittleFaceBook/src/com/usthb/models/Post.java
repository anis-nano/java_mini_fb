package com.usthb.models;
import java.util.List;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Post implements Serializable{
	
     private static int nbr_post;
	 private int num�ro_auto,nbr_partage;
     private String contenu;
	 Niveau_visb niveau_visb;
	 private boolean �pingl�e;
	 private Reaction r�action;
	 public enum Niveau_visb{priv�,ami,listeAmis,groupe}
	
	 private HashMap<String,StringBuilder> table_commentaire= new HashMap<String, StringBuilder>() ;
     private List<Reaction> list_R�action=new ArrayList<Reaction>();

     Post(String contenu,Niveau_visb niveau_visb,boolean �pingl�e)
     {this.contenu=contenu; this.niveau_visb=niveau_visb; this.�pingl�e=�pingl�e;
      nbr_post++; this.num�ro_auto=nbr_post;
     }
   

    void incNbr_partage()
   {
	nbr_partage++;
	
   }
   void setEpingl�e(boolean b)
   {this.�pingl�e=b;
   }
   
  void setNiveau_visibilit�(Niveau_visb niv)
  {
  this.niveau_visb=niv;
  }
 
public int getNum�ro_auto() {
	return num�ro_auto;
}


public void setNum�ro_auto(int num�ro_auto) {
	this.num�ro_auto = num�ro_auto;
}


public Niveau_visb getNiveau_visb() {
	return niveau_visb;
}


public void setNiveau_visb(Niveau_visb niveau_visb) {
	this.niveau_visb = niveau_visb;
}


public boolean is�pingl�e() {
	return �pingl�e;
}


public void set�pingl�e(boolean �pingl�e) {
	this.�pingl�e = �pingl�e;
}


public Reaction getR�action() {
	return r�action;
}


public void setR�action(Reaction r�action) {
	this.r�action = r�action;
}


public HashMap<String, StringBuilder> getTable_commentaire() {
	return table_commentaire;
}


public void setTable_commentaire(HashMap<String, StringBuilder> table_commentaire) {
	this.table_commentaire = table_commentaire;
}


public List<Reaction> getList_R�action() {
	return list_R�action;
}


public void setList_R�action(List<Reaction> list_R�action) {
	this.list_R�action = list_R�action;
}
boolean getEpingl�e()
{
	return this.�pingl�e;
}

public void ajouteCommentaire(String username,StringBuilder commentaire)
{
	if(this.table_commentaire.containsKey(username))
		this.table_commentaire.get(username).append(" "+commentaire);
	 this.table_commentaire.put(username,commentaire);
	 
}



void afficher()
{
	System.out.println("num_post: "+this.num�ro_auto+"\nPost: "+contenu+"\nniveau_visibilit�: "+niveau_visb+
			"\nnbr_partage: "+nbr_partage);
	System.out.println("R�action :");
	if(!this.list_R�action.isEmpty())
	{
		for(Reaction rea:this.list_R�action)
		   rea.afficher();
			}
	System.out.print("commentaires: ");
	if(!(this.table_commentaire.isEmpty()))
	{for(String cl�:this.table_commentaire.keySet())
	{System.out.print(cl�+": "+this.table_commentaire.get(cl�)+",");
	}
	}
	
}

   void SetR�action(String username,R�action r�action)
    {
	 this.r�action=new Reaction(username,r�action);
    }	

	public void ajout�_reaction(Reaction rect)
	{
		this.list_R�action.add(rect);
	}
	public void add_commentaire(String username,StringBuilder commentaire)
	{
		this.table_commentaire.put(username,commentaire);
	}
	public int get_num�ro_auto()
	{
	return this.num�ro_auto;
	}
	
}



	
	
	


