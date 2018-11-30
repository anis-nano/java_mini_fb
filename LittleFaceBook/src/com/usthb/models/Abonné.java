package com.usthb.models;
import java.util.List;
import java.util.Scanner;

import com.usthb.models.Post.Niveau_visb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Abonn� implements Serializable {
	static int nbr_abonn�; 
	private int num�ro_sq;
	private boolean online;
	
    public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getCat�gorie() {
		return cat�gorie;
	}
	public void setCat�gorie(String cat�gorie) {
		this.cat�gorie = cat�gorie;
	}
	public String getDate_naiss() {
		return date_naiss;
	}
	public void setDate_naiss(String date_naiss) {
		this.date_naiss = date_naiss;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	public boolean getOnline()
	{
		return online;
	}
	public String getPr�nom() {
		return pr�nom;
	}
	public void setPr�nom(String pr�nom) {
		this.pr�nom = pr�nom;
	}
	public String getSp�cialit�() {
		return sp�cialit�;
	}
	public void setSp�cialit�(String sp�cialit�) {
		this.sp�cialit� = sp�cialit�;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public StringBuilder getMot_passe() {
		return mot_passe;
	}
	public void setMot_passe(StringBuilder mot_passe) {
		this.mot_passe = mot_passe;
	}
	public List<String> getList_amis() {
		return list_amis;
	}
	public void setList_amis(List<String> list_amis) {
		this.list_amis = list_amis;
	}
	public List<Notification> getList_notif() {
		return list_notif;
	}
	public void setList_notif(List<Notification> list_notif) {
		this.list_notif = list_notif;
	}
	public List<Invitation> getList_invi() {
		return list_invi;
	}
	public void setList_invi(List<Invitation> list_invi) {
		this.list_invi = list_invi;
	}
	public HashMap<String, Post> getList_post() {
		return list_post;
	}
	public void setList_post(HashMap<String, Post> list_post) {
		this.list_post = list_post;
	}
	private String cat�gorie;
	private String date_naiss;
    private String nom,pr�nom,sp�cialit�,fonction,niveau;
    private String  username;
    private StringBuilder mot_passe;
     
	 
     private List<String> list_amis=new ArrayList<>();
      List<Notification> list_notif=new ArrayList<>();
      List<Invitation> list_invi=new ArrayList<>();
   //  List<Post> list_Post=new ArrayList<>();
    
    private HashMap<String,Post> list_post=new HashMap<String,Post>();
     
     
     
     
	
	Abonn�(String nom,String pr�nom,String cat�gorie,String date_naiss,
			String username,
			StringBuilder mot_passe,
			String sp�cialit�
			,String fonction,String niveau)
	
	{nbr_abonn�++; this.num�ro_sq=nbr_abonn�; 
	 this.nom=nom; this.pr�nom=pr�nom; this.username=username; this.mot_passe=mot_passe;
	 this.sp�cialit�=sp�cialit�; this.fonction=fonction; this.niveau=niveau;
	 this.cat�gorie=cat�gorie; this.date_naiss=date_naiss;
	 
	}
	public Post get_post(int num�ro_auto)
	{
            return this.list_post.get(Integer.toString(num�ro_auto));
	}
	public void add_post(Post post)
	{
		this.list_post.put(Integer.toString(post.get_num�ro_auto()),post);
	}
	
}

//LES CALSSES DE NOTIFICATION ET INVIATION
class Notification{
	String information;
	String type;
    private Etat_notf �tat;
    
    public void set_�tat(Etat_notf �tat)
    {
    	this.�tat=�tat;
    }
    
	Notification(String information,String type)
	{ this.type=type;  this.information=information;
		this.�tat=Etat_notf.NOLUE;
	}
	
	
	void affiche()
	{System.out.println(information+" "+type+" "+�tat);	
	}
	Etat_notf get_�tat()
	{
		return �tat;
	}
}


class Invitation{
	private String username;
	private String message;
	
	
	
	
	private Etat_invi �tat;
	
	public void set_�tat(Etat_invi �tat)
	{
		this.�tat=�tat;
	}
	
	Invitation(String username,String message)
	{set_�tat(Etat_invi.ENINSTANCE);
	
	this.username=username;

	this.message=message;
	}
	String getUsername()
	{
		return this.username;
	}
	
	
	void affiche()
	{
		System.out.println("Invitation de la part :"+username+": "+message);
	}
	
	
	public static void main(String[] args)
	{
		
	}
	public Etat_invi get_�tat()
	{
		return �tat;
	}
	
	
	
	
	
}
