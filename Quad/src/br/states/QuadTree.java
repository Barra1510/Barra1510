package br.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Console;
import java.util.ArrayList;
//import java.awt.event.KeyEvent;
import java.util.Random;


import br.Boids;

import br.Game;
//import br.Passageiro;
//import br.ThreadProcessor;

public class QuadTree implements State {

	int x, y, width, height;
	int max_boids;
	int amount = 5000;
	
	//Rectangle quadTree = new Rectangle(x, y, width, height);
	Boolean hasLeaf; 
	
	QuadTree northwest, southwest, southeast, northeast, root;
	
	
	ArrayList<Boids> boids = new ArrayList<Boids>(amount);
	Boids boid;	
	
	int randomico;
	float speed = 2f;	
	
	
	public QuadTree(int _x, int _y, int _width, int _height, QuadTree _root)
	{
		this.x = _x;
		this.y = _y;
		this.root = _root;
		this.width = _width;
		this.height = _height;
		this.boids = new ArrayList<Boids>(amount);
		this.hasLeaf = false;
		this.northwest = null;
        this.southwest = null;
        this.southeast = null;
        this.northeast = null;

        this.max_boids = 20;
	}
	
	@Override
	public void init() {
			//NoQuadMODE();
			QuadMODE();		
	}
	
	public boolean IsInside(Boids boid){
        if(boid.x >= this.x 
            & boid.x <= this.x + this.width)
            {
                if(boid.y >= this.y 
                    & boid.y <= this.y + this.height)
            {
                return true;
            }
        }
        return false;
    }
	
	 public void AddFromLeaf(Boids boid){
	        if(!this.AddToLeaf(boid)){   
	        	if(this.root != null)
	        		this.root.AddFromLeaf(boid);
	        	else {
	        		System.out.println(boid.x + "." + boid.y + ":" + this.y);
	        		if(boid.x <= 0)
	        			boid.x = 1;
	        		if(boid.y <= 0)
	        			boid.y = 1;
	        		if(boid.x >= Game.width-100)
	        			boid.x = Game.width-101;
	        		if(boid.y >= Game.height-100)
	        			boid.y = Game.height-101;
	        		this.AddBoid(boid);
	        	}
	        		
	        }
	    }
	 
	 public boolean AddToLeaf(Boids b)
	    {
	        if(this.northwest.IsInside(b)){
	            this.northwest.AddBoid(b);
	            return true;
	        }

	        if(this.southwest.IsInside(b)){
	            this.southwest.AddBoid(b);
	            return true;
	        }

	        if(this.southeast.IsInside(b)){
	            this.southeast.AddBoid(b);
	            return true;
	        }

	        if(this.northeast.IsInside(b)) {
	            this.northeast.AddBoid(b);
	            return true;
	        }
	        
	        return false;
	    }
	 
	 public void AddBoid(Boids boid){		 
	        if(!this.hasLeaf){
	            if(this.boids.size() >= this.max_boids){           
	                this.Divide(boid);	                
	                this.boids = new ArrayList<Boids>();	                
	                return;
	            }           	            
	            this.boids.add(boid);
	        }
	        else{
	            if(!this.AddToLeaf(boid))
	            	System.out.println(boid.x + "." + boid.y);
	        }
	    }
	 
	 public void Divide(Boids boid){
		 	//
	        this.hasLeaf = true;
	        
	        int w = this.width / 2;
	        int h = this.height / 2;
	        int x = this.x;
	        int y = this.y;

	        this.northwest = new QuadTree(x, y, w, h, this);
	        y += h;
	        this.southwest = new QuadTree(x, y, w, h, this);
	        x += w;
	        this.southeast = new QuadTree(x, y, w, h, this);
	        y -= h;
	        this.northeast = new QuadTree(x, y, w, h, this);

	        for (int i = 0; i < this.boids.size(); i++) {	            
	            this.AddToLeaf(this.boids.get(i));
	        }

	        this.AddToLeaf(boid);

	    }
	 
	 public void UpdateBoids(){		 	
	        if(this.root != null){
	            for (int i = this.boids.size() - 1; 
	                i >=0 ; i--) 
	            {
	                Boids boid = this.boids.get(i);
	                if(!this.IsInside(boid)){
	                    this.root.AddFromLeaf(boid);
	                    this.boids.remove(i);
	                    
	                }
	            }
	        }

	        for (int i = 0; i < this.boids.size(); i++) 
	        {
	            Boids boid = this.boids.get(i);

	            for (int j = i + 1; j < this.boids.size(); j++) 
	            {	                
	                if(boid.Colision(this.boids.get(j)))
	                    break;
	            }

	            boid.Update();
	            
	        }
	        
	    }
	 
	 public int GetAmountBoids(){
	        if(this.hasLeaf){
	            int amount = 
	            this.northwest.GetAmountBoids() +
	            this.southwest.GetAmountBoids() +
	            this.southeast.GetAmountBoids() +
	            this.northeast.GetAmountBoids();
	            return amount;
	        }
	        else{
	            return this.boids.size();
	        }
	    }	 
	 
	 @Override
		public void update() {
			if(this.hasLeaf){
	            int amount = 
	            this.northwest.GetAmountBoids() +
	            this.southwest.GetAmountBoids() +
	            this.southeast.GetAmountBoids() +
	            this.northeast.GetAmountBoids();

	            if(amount > 0){
	                this.northwest.update();
	                this.southwest.update();
	                this.southeast.update();
	                this.northeast.update();
	            }
	            else{
	                this.hasLeaf = false;
	            }
	           
	        }
	        else{	        	    
	            this.UpdateBoids();
	            
	        }
			
		}	
	 
	

	@Override
	public void Draw(Graphics g, Color cor) {
		
		g.setColor(cor); 
        g.drawRect(this.x, this.y, 
            this.width, this.height); 	
        
        
        if(this.hasLeaf)
        {
        	northwest.Draw(g, Color.RED);        	
            southwest.Draw(g, Color.GREEN);
            southeast.Draw(g, Color.BLUE);
            northeast.Draw(g, Color.CYAN);
        }
        
        else {       
        
        	for(int i = 0; i < boids.size(); i++)
        	{        		
        		this.boids.get(i).Draw(g, cor);
        	}
        }
        
        
	}	
	
	public void NoQuadMODE(){
	    for (int i = 0; i < amount; i++) {
	        int x = (int) Math.floor(Math.random() * (Game.width-100));
	        int y = (int) Math.floor(Math.random() * (Game.width/2-100));
	        boids.add(new Boids(x , y, 5,5));
	    }
	}
	
	public void QuadMODE(){
	    for (int i = 0; i < amount; i++) {
	        int x = (int) Math.floor(Math.random() * (Game.width-100));
	        int y = (int) Math.floor(Math.random() * (Game.width/2-100));
	        this.AddBoid(new Boids(x , y, 5, 5));
	        
	        
	    }
	       
	}

}
