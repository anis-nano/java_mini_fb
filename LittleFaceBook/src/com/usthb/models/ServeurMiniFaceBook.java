package com.usthb.models;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.usthb.models.Post.Niveau_visb;



public class ServeurMiniFaceBook {
	private HashMap<String,Abonné> baseAbonnés=new HashMap<String, Abonné>();
	 
void inscrireeAbonné(Socket ss,Abonné abonné)throws IOException, ClassNotFoundException, InterruptedException
{
	   
	   PrintStream p=new PrintStream(ss.getOutputStream());
	   int return_client=0;
	   if(!baseAbonnés.containsKey(abonné.getUsername()))
	   {  this.baseAbonnés.put(abonné.getUsername(),abonné);
	      System.out.println("inscription terminé avec success");
	      TimeUnit.SECONDS.sleep(3);
	      p.println(return_client);
	      
	   }
	   else {System.out.println("inscription n'pas terminé avec success");
	   TimeUnit.SECONDS.sleep(3);
		   p.println(return_client);
	   }
	   
}
String username_connecté;

Abonné read_info_inscripition()
{
	Scanner e=new Scanner(System.in);
	
    String catégorie;
    String date_naiss;
    String nom,prénom,spécialité,fonction,niveau;
    String username;
    StringBuilder mot_passe;
	System.out.println("donner un username: ");username=e.nextLine();
	System.out.println("donner un mot de passe: ");mot_passe=new StringBuilder(e.nextLine());
	//lire les donnés
	System.out.println("completer la liste des information suivant:");
	System.out.println("nom: ");nom=e.next();
	
	System.out.println("prénom:");prénom=e.next();
	System.out.println("date de naissance: ");date_naiss=e.nextLine();
	System.out.println("catégorie (homme ou femmme):" ); catégorie=e.next();
			
   System.out.println("spécialité: ");spécialité=e.next();
   System.out.println("niveau: ");niveau=e.next(); 
   System.out.println("fonction:");fonction=e.next();
   
   Abonné abonné=new Abonné(nom,prénom,catégorie,date_naiss,
			username,
			mot_passe,
			spécialité
			,fonction,niveau);
   return abonné;
}




void supprimerAbonné(String username)
{  if(baseAbonnés.containsKey(username))
		baseAbonnés.remove(username);
}


public boolean seConnecter(ServerSocket s1,Socket ss,ObjectInputStream in,ObjectOutputStream out) throws ClassNotFoundException, IOException, InterruptedException
{   boolean b=false;
	
	
    //s1=new ServerSocket(98);
    //ss=s1.accept();
    String sss="no_mot_de_passe";
    String username="no_user_name";

   try {
        username=(String) in.readObject();
   }catch(Exception e1)
   {
	   System.out.println("not recevied username  ");
   }
	
	try {
     sss=(String) in.readObject();
	}
	catch(Exception e)
	{   System.out.println("this just happenes sometimes password not recevied !");
	}
	
	
	StringBuilder mot_passe=new StringBuilder(sss);
	
	
	if(baseAbonnés.containsKey(username))
{ 
	if(mot_passe.toString().equals(baseAbonnés.get(username).getMot_passe().toString()))
	{ System.out.println("vous avez bien connecté :) !"); 
        b=true;
       this.username_connecté=username; 
      affiche_Mur(username,0);
      TimeUnit.SECONDS.sleep(4);
		
		
    }
	
	else {
      System.out.println("le mot de pas est incorrect");
      TimeUnit.SECONDS.sleep(3);
      System.out.println("resseyez svp il reste 4 chances");
      int i=4;
      while(b==false && i!=0)
      {
      Scanner e=new Scanner(System.in);	
	  System.out.println("username: "); username=e.nextLine();
	  System.out.println("mot de passe: "); mot_passe=new StringBuilder(e.nextLine());
	  if(mot_passe.toString().equals(baseAbonnés.get(username).getMot_passe().toString()))
	  {
		  b=true;
		  System.out.println("vous avez bien connecté :) !"); 
		 this.username_connecté=username;
		 this.baseAbonnés.get(username).setOnline(true);
		  affiche_Mur(username,0);
	  }
	  i--;
	  
	  
	  
	  
      }
      
	}
	
	
}
	
	

	else System.out.println("le username est pas dans le serveur MiniFaceBook");
	
	if(b==true)
	{
		
		  out.writeObject(username);
	}
	else
	{
		username="falied";
		
         try
         {
		  out.writeObject(username);
         }catch(Exception p3)
         {
        	 System.out.println("not sended ligne 172");
         }
         
	}
	
	
	   PrintStream p=new PrintStream(ss.getOutputStream());
	   
	   
	int return_client;
	if(!(b)) {
		return_client=0;
		p.println(return_client); 

		return false;
	}
	else {
		return_client=1;
		p.println(return_client);
		return true;
	}
	
}


void affiche_Mur(String username,int clear_screen) throws InterruptedException, IOException
{
	if(clear_screen==1)
	{
	for(int i=0;i<50;i++)
	{
		System.out.println("\n");
	}
	}
	
	//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	//it didn't work :(
System.out.println("profile: "+username);
Abonné a=this.baseAbonnés.get(username);
for(Notification n: a.list_notif)
	if(n.get_état().equals(Etat_notf.NOLUE)) n.affiche();
for(Invitation i: a.list_invi)
	if(i.get_état().equals(Etat_invi.ENINSTANCE)) i.affiche();

this.affiche_posts(username);
}





void affiche_posts(String username)
{
Abonné a=this.baseAbonnés.get(username);
for(Post p:a.getList_post().values())
{
	
	if(p.getEpinglée()==true) {
		System.out.println("\n");
		p.afficher();
	}
}
for(Post p:a.getList_post().values())
{
	if(p.getEpinglée()==false) 
		{System.out.println("\n");
	   	p.afficher();
		}
}

}

void rechercher(String username) throws InterruptedException, IOException
{
	if(this.baseAbonnés.containsKey(username))
		this.affiche_Mur(username,0);
	else System.out.println("ERORR l'abonné n'exist pas");
}


public void épnigler(Post post)
{
	post.setEpinglée(true);
}

public void désépinlger(Post post)
{
	post.setEpinglée(false);
}

//continue this//////////////////////////////////////////////
public void réagir(String username,Reaction rect,int numéro_auto)
{
 if(this.baseAbonnés.get(username).getList_post().containsKey(Integer.toString(numéro_auto)))
 {
 this.baseAbonnés.get(username).get_post(numéro_auto).ajouté_reaction(rect);
 System.out.println("réaction ajouté a "+username+" avec success !");
 if(!username.equals(rect.get_username()))
 {Notification nt=new Notification("votre amis"+rect.get_username()+"a reagir a votre post","reaction");
	 this.baseAbonnés.get(username).list_notif.add(nt);	 
 }
 }
 else System.out.println("ERORR: la publication n'existe pas !__!");
}

public void rajouter_commentaire(String username_1,int numéro_auto,String username,StringBuilder commentaire)
{
	this.baseAbonnés.get(username_1).get_post(numéro_auto).add_commentaire(username,commentaire);
	if(!(username_1.equals(username)))
	{Notification notf=new Notification(username_1+"a commenté a votre post num "+numéro_auto,
			"commentaire"
			);
		this.baseAbonnés.get(username).list_notif.add(notf);
	}
	
}

public void partager(String username_1,String username,int numéro_auto)
{
	this.baseAbonnés.get(username).add_post(this.baseAbonnés.get(username_1).get_post(numéro_auto));
	
}



void publier(String username,Post post)
{
	this.baseAbonnés.get(username).add_post(post);

Notification nt=new Notification("votre amis "+username+" a publier",
		"publication");	
	
for(String amis: this.baseAbonnés.get(username).getList_amis())
{
	
   this.baseAbonnés.get(amis).list_notif.add(nt);
}
	
}



public void reconnecter() throws InterruptedException, IOException
{
Scanner e=new Scanner(System.in);	
System.out.println("\n");
System.out.println("username: ");String username=e.next();
System.out.println("mot de passe: ");StringBuilder mot_passe=new StringBuilder(e.next());
boolean b = false;


if(baseAbonnés.containsKey(username))
{ 
	if(mot_passe.toString().equals(baseAbonnés.get(username).getMot_passe().toString()))
	{  
        b=true;
       this.username_connecté=username; 
      affiche_Mur(username,1);
      System.out.println("vous avez bien connecté :) !");
      TimeUnit.SECONDS.sleep(4);
      
		
    }
	
	else {
      System.out.println("le mot de pas est incorrect");
      TimeUnit.SECONDS.sleep(3);
      System.out.println("resseyez svp il reste 4 chances");
      int i=4;
      while(b==false && i!=0)
      {
     	
	  System.out.println("username: "); username=e.next();
	  System.out.println("mot de passe: "); mot_passe=new StringBuilder(e.next());
	  if(mot_passe.toString().equals(baseAbonnés.get(username).getMot_passe().toString()))
	  {
		  b=true;
		 this.username_connecté=username;
		 this.baseAbonnés.get(username).setOnline(true);
		  affiche_Mur(username,1);
		  System.out.println("vous avez bien connecté :) !"); 
	  }
	  i--;
	  
	  
	  
	  
      }
      
	}
	
	
}

  else System.out.println("le username est pas dans le serveur MiniFaceBook");
}

Post read_post()
{ System.out.println("\n");
	Niveau_visb niveau_visb=Niveau_visb.listeAmis;
	System.out.println("saiser les infos de post");
    Scanner e=new Scanner(System.in);
    System.out.println("contenu:");String contenu=e.nextLine();
    System.out.println("choisir le numéro correspondent a le Niveau de visbilité:");
    System.out.println("1-privé 2-ami 3-ListeAmis 4-groupe ");
    int v=e.nextInt();
    
    switch (v)
    {case 1:niveau_visb=Niveau_visb.privé;
     break;
     case 2:niveau_visb=Niveau_visb.ami;
     break;
     case 3:niveau_visb=Niveau_visb.listeAmis;
     break;
     case 4:niveau_visb=Niveau_visb.groupe;
     break;
    }
    
    boolean epl=false;
    System.out.println("épinglée (taper oui ou non):");String ep=e.next();
    switch (ep)
    {case "oui":epl=true;
     break;
     case "non":epl=false;
     break;
     default: epl=false;
     break;
    }
    

	Post post=new Post(contenu,niveau_visb,epl);
    return post;	
	//serveur.publier(username,post);
    
}

void affiche_notf(String username)
{ if(!(this.baseAbonnés.get(username).list_notif.isEmpty()))
	for(Notification nt:this.baseAbonnés.get(username).list_notif)
	{
		nt.affiche();
	}
else System.out.println("Notifications: il y'pas d'notifications pour le moment");
}

void affiche_les_invitations(String username)
{
	if(!(this.baseAbonnés.get(username).list_invi.isEmpty()))	
	{
	for(Invitation invi:this.baseAbonnés.get(username).list_invi)
	{
	invi.affiche(); 
	System.out.println("pour accepter taper -1 sinon taper -2");
	Scanner e=new Scanner(System.in);
	int réponse=e.nextInt();
	if(réponse==1)
	{
		this.baseAbonnés.get(username).getList_amis().add(invi.getUsername());
	System.out.println("tu est maintent amis avec i "+invi.getUsername()+" :)");
	}
	
	}
	
	}
	else System.out.println("Invitations: il y'a pas d'invitations pour le moment");
	
}


void partager_client()
{   String username;
    Scanner e=new Scanner(System.in);
    System.out.println("vous pouvez partager la publication de qui :");
	String username_1=e.next();
	System.out.println("le num de la publication svp :");
	int num_auto=e.nextInt();
	
	System.out.println("taper 1 si tu peut partager a votre mur 2 sinon:");
	int n=e.nextInt();
	switch(n)
	{
	case 1:username=this.username_connecté;
	break;
    case 2:
	System.out.println("vous pouvez partager a qui ");
	username=e.next();
	{
	if(this.si_amie(username_connecté, username))
    this.partager(username_1,username,num_auto);
	}
    break;
    }
}
private boolean si_amie(String username,String username2)
{
if(this.baseAbonnés.get(username).getList_amis().contains(username2)) return true;
else return false;
}

public void deconnecter(String username)
{
//this.baseAbonnés.get(username).getOnline()=dec;
	this.baseAbonnés.get(username).setOnline(false);
}

void add_client_comment()
{System.out.println("\n");
Scanner e=new Scanner(System.in);
System.out.println("vous pouvez commenter a qui");
String username_1=e.next();
System.out.println("a quelle publication (numéro de pup)");
int num_post=e.nextInt();
System.out.println("votre commentaire :");
String s=e.next(); 
StringBuilder commentaire=new StringBuilder(s);
this.rajouter_commentaire(username_1,num_post,this.username_connecté, commentaire);	
}

void envoyer_client_invitation()
{System.out.println("\n");
   	Scanner e=new Scanner(System.in);
	 System.out.println("a qui vous pouvez envoyer l'invitation");
	 String username_2=e.next();
	 System.out.println("qulle que mots dans le message d'invitation: ");
	 String message=e.next();
	 Invitation invi=new Invitation(this.username_connecté,message);
	 
	 this.ajouter_invitation(invi, username_2);
}


void ajouter_invitation(Invitation invi,String username_2)
{
	this.baseAbonnés.get(username_2).list_invi.add(invi);
}


Reaction read_reaction()
{System.out.println();
Reaction rec;
Scanner e=new Scanner(System.in);
System.out.println("a qui vous pouvez réagir (username)");
String username_1=e.next();
System.out.println("a quelle publication(numéro de pub)");
int numéro_auto=e.nextInt();
System.out.println("choiser votre réaction ");
System.out.println("1-jaime 2-jadore 3-triste 4-gai 5-encolére;\r\n");

int n=e.nextInt();
switch (n)
{case 1:rec=new Reaction(this.username_connecté,Réaction.jaime);
break;
case 2:rec=new Reaction(this.username_connecté,Réaction.jadore);
break;
case 3:rec=new Reaction(this.username_connecté,Réaction.triste);
break;
case 4:rec=new Reaction(this.username_connecté,Réaction.gai);
break;
case 5:rec=new Reaction(this.username_connecté,Réaction.encolére);
break; 
default:rec=new Reaction(this.username_connecté,Réaction.none);
break;
}
rec.set_num_post_de_reaction(numéro_auto);
return rec;
}
public void view_mur() throws InterruptedException, IOException
{ System.out.println("\n");
	System.out.println("donner le nom de username");
	Scanner e=new Scanner(System.in);
	String user=e.next();
	if(this.baseAbonnés.containsKey(user))
	{
		this.affiche_Mur(user,1);
		
	}
	else System.out.println("ce username n'existe pas dans le littilefacebook ");
}

//method pour ajouter 2 users pour test le projet 
public static void add_2_users_for_testing(ServeurMiniFaceBook serveur)

{
	  String catégorie;
	   String date_naiss;
	   String nom,prénom,spécialité,fonction,niveau;
	   String username;
	   StringBuilder mot_passe;
	   username="test_user";
	   mot_passe=new StringBuilder("test");
				//lire les donnés
		 nom="shadow";	
		 prénom="find";date_naiss="25/03/1998";
		 catégorie="homme";spécialité="sleep";niveau="fac"; fonction="code";
	 
	  Abonné abonné=new Abonné(nom,prénom,catégorie,date_naiss,
				username,
				mot_passe,
				spécialité
				,fonction,niveau);
	  
	//Post(String contenu,Niveau_visb niveau_visb,boolean épinglée);
	  Post p=new Post("test",Post.Niveau_visb.ami,false);
	  
	 
	  serveur.baseAbonnés.put(abonné.getUsername(),abonné);
	  serveur.publier(username,p);
	  username="test_user2";
	   mot_passe=new StringBuilder("test2");
		

		//lire les donnés

		 nom="project";	
		 prénom="testing";date_naiss="25/06/1998";
		 catégorie="homme";spécialité="info";niveau="fac"; fonction="code";
	 
	  Abonné abonné2=new Abonné(nom,prénom,catégorie,date_naiss,
				username,
				mot_passe,
				spécialité
				,fonction,niveau);
	 serveur.baseAbonnés.put(username, abonné2);
	  
}
 //          ***                            MAIN                                ***
 public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
  {
	 ServeurMiniFaceBook serveur=new ServeurMiniFaceBook();
	 
	 
	 add_2_users_for_testing(serveur);
	 //user1=test_user  password:test
	 //user2=test_user2 password:test2
	 
	 
	 
	 
	 Post p;
	 Scanner simple_sc;
	 ServerSocket s1;
	 Scanner sc;
	 ObjectInputStream in=null;
	 PrintStream p2;
	 ObjectOutputStream out=null;
	 Socket ss;
	 int operation=0;
	 int operation2;
	 boolean cn;
	 boolean no_return=false;
	boolean cn_haut=true;
  while(cn_haut)
  {
	s1=new ServerSocket(120);
 ss=s1.accept();
	 sc=new Scanner(ss.getInputStream());//get's input stream
	in=new ObjectInputStream(ss.getInputStream());
   
	 p2=new PrintStream(ss.getOutputStream()); //pour retourne au client
	 out = new ObjectOutputStream(ss.getOutputStream());
	 simple_sc=new Scanner(System.in);
    //get's input stream
	boolean connecté=false;
	int r;//retoure
	if(sc.hasNextInt())
		no_return=false;
    operation=sc.nextInt();
	
	cn=true;
	
	switch (operation)
	{case 1:connecté=serveur.seConnecter(s1,ss,in,out);
	no_return=false; 
	if(connecté)
	{
		
	while(cn)
	{
    operation=0;
	no_return=true;
    operation2=sc.nextInt(); //la répose de client
	switch(operation2)
	{case 1:serveur.affiche_Mur(serveur.username_connecté,1);
	break;
	case 2:p=serveur.read_post();
	serveur.publier(serveur.username_connecté,p);	
	break;
	case 3:serveur.affiche_notf(serveur.username_connecté);
	break;
	case 4:serveur.affiche_les_invitations(serveur.username_connecté);
	break;
	case 5:serveur.partager_client();
	break;
	case 6:
	Reaction rec=serveur.read_reaction();
    serveur.réagir(serveur.username_connecté,rec,rec.num_post_de_reaction);
    break;
	case 7:serveur.add_client_comment();
	break;
	case 8:serveur.envoyer_client_invitation();
	break;
	case 9:serveur.reconnecter();
	break;
	case 11:serveur.view_mur();
	break;
	case 10:
	serveur.deconnecter(serveur.username_connecté);
	connecté=false; cn=false; 
	System.out.println("deconnexion.... !!");
	break;
	default:
	break;
	}
	
	if(no_return&&(operation!=1 && operation!=2))
	{
	System.out.println("\ntab 0 to return");
	   TimeUnit.SECONDS.sleep(2);
	   
	//try
	//{
	r=simple_sc.nextInt();//0
    p2.println(r);//return to client
	//}
	//catch(Exception e)
	//{
	//p2.println(0);	
		//}
	
	}
	
	}
	}
	break;
	case 2:serveur.inscrireeAbonné(ss,serveur.read_info_inscripition());
	no_return=false; 
	break;
	default:
    break;
	
	}
	 ss.close(); s1.close();  sc.close(); p2.close(); in.close();
	   
	
	}
	
	
	  
  }
	  //in.close();
 
}






