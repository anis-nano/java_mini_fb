package com.usthb.models;

public class Reaction {
	
	    
	    private R�action r�action;
	    private String username;
	    int num_post_de_reaction;
	    
	    Reaction(String username,R�action r�action)
	    {this.username=username; this.r�action=r�action;
	    }
	    
	    public R�action get_reaction()
	    {
	    	return r�action;
	    }
	    public String get_username()
	    {
	    	return this.username;
	    }
	    void afficher()
	    {
	    	System.out.println(username+": "+this.r�action);
	    }
	    
	    public void set_num_post_de_reaction(int num_auto)
	    {
	    	this.num_post_de_reaction=num_auto;
	    }
	    
	   
}
