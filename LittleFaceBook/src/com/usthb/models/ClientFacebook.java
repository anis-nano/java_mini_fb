package com.usthb.models;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.usthb.models.Post.Niveau_visb;



public class ClientFacebook {
	
	private String username;
	
     boolean afficher_page(Socket s,ObjectOutputStream out,ObjectInputStream in) throws IOException, ClassNotFoundException, InterruptedException
     
    {for(int i=0;i<50;i++)
    	System.out.println("\n");
    	 
    	 
    	 
    	 System.out.println("\"Bienvenue au réseau LittleFacebook :) !\\r\\n\"");
    	 System.out.println("1-connecter \n2-inscrire\n3-quit");
         Scanner e=new Scanner(System.in);
         int n=e.nextInt();
         boolean cn=true;
     
     
     switch (n)
     {case 1:this.connecter(s,out,in); 
     break;
     case 2:this.inscrire(s); 
     break;
     case 3:cn=false; System.exit(0);
     break;
     default:cn=true;
     break;
     }
     return cn;
     	
    }
     
     
     
	boolean affiche_menu(Socket s,Scanner sc1) throws IOException
	{int n;
	int return_to_client = 1;
	 
		System.out.println("\r\n \r\n"
				+ "Bienvenue au réseau LittleFacebook !\r\n" + 
				"Veuillez choisir un numéro d’opération à invoquer :");
		System.out.println("1. Visualiser le mur\r\n" + 
				           "2. Publier\r\n" + 
				           "3. Visualiser les notification\r\n"+
				           "4. Visualiser les invitations\r\n"+
				           "5. partager un post\r\n"+
				           "6. réagir à un post\r\n"+
				           "7. rajouter un commentaire\r\n" +
				           "8. envoyer une invitation\r\n" +
				           "9. reconnecter a un autre count\r\n" +
				           "10. déconnecter\r\n"+
				           "11. Visualiser mur de un user\r\n"
				   
				           );
		
     Scanner e=new Scanner(System.in);
	 n=e.nextInt(); 
	 
	switch (n)
	 {
	  case 1:this.visualiser_Mur(s);
	  break;
	  case 2: this.publier(s); 
	  break;
	  case 3:this.visualiser_notf(s);
	  break;
	  case 4:this.visualiser_invi(s);
	  break;
	  case 5:this.partager(s);
	  break;
	  case 6:this.réagir(s);
	  break;
	  case 10:this.déconnecter(s);
	  break;
	  case 7:this.ajouter_commentaire(s);
	  break;
	  case 8:this.envoyer_invitation(s);
	  break;
	  case 9:this.reconnecter(s);
	  break;
	  case 11:this.Visualiser_mur_de_user(s);
	  break;
	  default:
	  break;
	  
	 }
	 //to accept the result from the server
	 
	
	    if(sc1.hasNextInt())
		return_to_client=sc1.nextInt();
	    /*
	 if(return_to_client==-1)
	 {
		 return false; //deconnexion
	 }
	 */
	 
	 if(return_to_client==0)
	 { if(n==10) return false;
		 return true;
	 }

	 return true;
	 
	}

	public void reconnecter(Socket s) throws IOException
	{
		PrintStream p=new PrintStream(s.getOutputStream());
		p.println(9);
		
	}
	void connecter(Socket s,ObjectOutputStream out,ObjectInputStream in) throws IOException, ClassNotFoundException, InterruptedException
	{ 
	  
		
		
	  Scanner e=new Scanner(System.in);	
	  System.out.println("username: ");String username=e.next();
	  System.out.println("mot de passe: ");String mot_passe=e.next();
	  
	  PrintStream p=new PrintStream(s.getOutputStream());
	  int operation=1;
      p.println(operation);
      
	  out.writeObject(username);
	  out.flush();
	  
	  out.writeObject(mot_passe);
	  
	  TimeUnit.SECONDS.sleep(1);
	  out.flush();
       
	  String usr=(String) in.readObject();
	  if(usr.equals("failed")==false) this.username=usr;
	 
	  
	 }
	 
	
    void inscrire(Socket s) throws IOException
	{
	      PrintStream p=new PrintStream(s.getOutputStream());
	      p.println(2);
	   
	}
    
    
    void partager(Socket s) throws IOException  
    {PrintStream p=new PrintStream(s.getOutputStream());
     p.println(5);
    }
	
    
	void visualiser_Mur(Socket s) throws IOException 
	{  PrintStream p=new PrintStream(s.getOutputStream());
	   p.println(1);		
	}
	public void Visualiser_mur_de_user(Socket s) throws IOException
	{
		
		PrintStream p=new PrintStream(s.getOutputStream());
		   p.println(11);		
	}
	
	
	void visualiser_invi(Socket s) throws IOException
	{
		PrintStream p=new PrintStream(s.getOutputStream());
		p.println(4);
		
	}
	
	void visualiser_notf(Socket s) throws IOException
	{
		PrintStream p=new PrintStream(s.getOutputStream());
		   p.println(3);		
	}

	
	void déconnecter(Socket s) throws IOException
	{
	 PrintStream p=new PrintStream(s.getOutputStream());
	 p.println(10);
	}
	
	void publier(Socket s) throws IOException
	{
		PrintStream p=new PrintStream(s.getOutputStream());
        p.println(2);  
		
	}
	
	
	void envoyer_invitation(Socket s) throws IOException
	{
		PrintStream p=new PrintStream(s.getOutputStream());
        p.println(8);  
	  
	}
	
	
	void ajouter_commentaire(Socket s) throws IOException
	{
		PrintStream p=new PrintStream(s.getOutputStream());
		   p.println(7);
	}
	
	
	
	
	void réagir(Socket s) throws IOException
	{PrintStream p=new PrintStream(s.getOutputStream());
     p.println(6);  
	}
	//                **                    MAINNNNNN                    **
	public static void main(String Args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
	ClientFacebook cl=new ClientFacebook();
	
    Scanner e=new Scanner(System.in);
	//there's :simple socket
	//server socket
	        
	
	                 
    boolean cn_haut=true; //continue l'exécution
    
    
    boolean clear_screen=false;
    //the main  !! LOOOOOOOOOOOOOOOOOOOOOOOOP !!
    while(cn_haut)
    {
    Socket s=new Socket("127.0.0.1",120); //ip_adresse to connect to         //port number
    Scanner sc1=new Scanner(s.getInputStream());//to accept the result from the server
    //ou on va envoyer
    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
    ObjectInputStream in=new ObjectInputStream(s.getInputStream());
    
    boolean cn=true;//continue affichage la page_facebook
    boolean cn2=false;//continue affichage menu_client
    int return_to_client=0;
    
   
    while(cn)
    {   
    	if(clear_screen)for(int i=0;i<50;i++)
		 System.out.println("\n");
	 
    	
    	cl.afficher_page(s,out,in);
    	cn=false;
    	if(sc1.hasNextInt())
    	{
        return_to_client=sc1.nextInt();
    	}
    	
        if(return_to_client==1)//succes de la connexion 
        {//debut de menu facebook
          cn=false; cn2=true; clear_screen=false;
          while(cn2)
         {
        	  if(clear_screen)for(int i=0;i<50;i++)
    		 System.out.println("\n");
    	 
        	  
        	  
          cn2=cl.affiche_menu(s,sc1); // if(return_to_client==1)cn2=fOalse;
          cn=false;//pour on peut accéder a la grand loop
                   //et fermé les sockets et faire la reconnexion au serveur
         clear_screen=true;
         }
       // else if(return_to_client==1) cn=true;
       return_to_client=0;
       clear_screen=true;
   }
	  //ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
	 //out.close();
    
	 
	}
    
    s.close(); sc1.close(); in.close(); out.close(); sc1.close();
    }
    
    
	
}
	
}
