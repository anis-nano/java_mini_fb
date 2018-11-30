package com.usthb.models;

public class Reaction {
	
	    
	    private Réaction réaction;
	    private String username;
	    int num_post_de_reaction;
	    
	    Reaction(String username,Réaction réaction)
	    {this.username=username; this.réaction=réaction;
	    }
	    
	    public Réaction get_reaction()
	    {
	    	return réaction;
	    }
	    public String get_username()
	    {
	    	return this.username;
	    }
	    void afficher()
	    {
	    	System.out.println(username+": "+this.réaction);
	    }
	    
	    public void set_num_post_de_reaction(int num_auto)
	    {
	    	this.num_post_de_reaction=num_auto;
	    }
	    
	   
}
