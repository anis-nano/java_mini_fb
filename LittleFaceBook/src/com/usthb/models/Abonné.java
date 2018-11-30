package com.usthb.models;
import java.util.List;
import java.util.Scanner;

import com.usthb.models.Post.Niveau_visb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Abonné implements Serializable {
	static int nbr_abonné; 
	private int numéro_sq;
	private boolean online;
	
    public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getCatégorie() {
		return catégorie;
	}
	public void setCatégorie(String catégorie) {
		this.catégorie = catégorie;
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
	public String getPrénom() {
		return prénom;
	}
	public void setPrénom(String prénom) {
		this.prénom = prénom;
	}
	public String getSpécialité() {
		return spécialité;
	}
	public void setSpécialité(String spécialité) {
		this.spécialité = spécialité;
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
	private String catégorie;
	private String date_naiss;
    private String nom,prénom,spécialité,fonction,niveau;
    private String  username;
    private StringBuilder mot_passe;
     
	 
     private List<String> list_amis=new ArrayList<>();
      List<Notification> list_notif=new ArrayList<>();
      List<Invitation> list_invi=new ArrayList<>();
   //  List<Post> list_Post=new ArrayList<>();
    
    private HashMap<String,Post> list_post=new HashMap<String,Post>();
     
     
     
     
	
	Abonné(String nom,String prénom,String catégorie,String date_naiss,
			String username,
			StringBuilder mot_passe,
			String spécialité
			,String fonction,String niveau)
	
	{nbr_abonné++; this.numéro_sq=nbr_abonné; 
	 this.nom=nom; this.prénom=prénom; this.username=username; this.mot_passe=mot_passe;
	 this.spécialité=spécialité; this.fonction=fonction; this.niveau=niveau;
	 this.catégorie=catégorie; this.date_naiss=date_naiss;
	 
	}
	public Post get_post(int numéro_auto)
	{
            return this.list_post.get(Integer.toString(numéro_auto));
	}
	public void add_post(Post post)
	{
		this.list_post.put(Integer.toString(post.get_numéro_auto()),post);
	}
	
}

//LES CALSSES DE NOTIFICATION ET INVIATION
class Notification{
	String information;
	String type;
    private Etat_notf état;
    
    public void set_état(Etat_notf état)
    {
    	this.état=état;
    }
    
	Notification(String information,String type)
	{ this.type=type;  this.information=information;
		this.état=Etat_notf.NOLUE;
	}
	
	
	void affiche()
	{System.out.println(information+" "+type+" "+état);	
	}
	Etat_notf get_état()
	{
		return état;
	}
}


class Invitation{
	private String username;
	private String message;
	
	
	
	
	private Etat_invi état;
	
	public void set_état(Etat_invi état)
	{
		this.état=état;
	}
	
	Invitation(String username,String message)
	{set_état(Etat_invi.ENINSTANCE);
	
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
	public Etat_invi get_état()
	{
		return état;
	}
	
	
	
	
	
}
