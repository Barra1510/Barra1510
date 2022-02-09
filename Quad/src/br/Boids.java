package br;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boids {
	
	public int x, y, width, height;
	float speed, speedX, speedY;
	
	public Boids(int _x, int _y, int _width, int _height) 
	{
		this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.speed = 1;
        this.speedX = this.speed;
        this.speedY = this.speed;
	}	
	
	
	
	
	 public boolean Check_Collision (Boids boid) {	       
	        if (this.x < boid.x && this.x + this.width > boid.x && 
	        		this.y < boid.y && this.y + this.height > boid.y) 
	        {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 public void Inverse(){
	        if(Math.random() > 0.5)
	        this.speedX =  -this.speedX;
	        if(Math.random() > 0.5)
	        this.speedY = -this.speedY;
	    }
	 
	 public boolean Colision(Boids boid){
	        if(this.Check_Collision(boid)){
	            this.Inverse();
	            boid.Inverse();
	            return true;
	        }
	        return false;
	    }
	 public void Update () {  
	        
	        if(this.x + this.width > Game.width-100)
	            this.speedX = -this.speed;
	        else if(this.x - this.width < 0)
	            this.speedX = this.speed;

	        if(this.y + this.height > Game.height-100)
	            this.speedY = -this.speed;
	        else if(this.y - this.height < 0)
	            this.speedY = this.speed;

	        this.x += this.speedX;
	        this.y += this.speedY;

	        
	    }
	 public void Draw(Graphics g, Color color)
	 {
		 g.setColor(color);		
		 g.fillRect(x, y, width, height);	
	 }
}
