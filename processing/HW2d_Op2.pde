/*
      Author: Wang Jinge
      Function animation(sin,cos)
      Date:19/2/3
*/
float x,y,z,myFunction;
void setup(){
  size(1000, 1000);
  background(0);
  x = 0;
}

void draw(){
  translate(100, height / 2);
  y =  sin(radians(15*x))*100;                             
  z =  cos(radians(13*x))*100;   
  myFunction = sin(radians(x)) * 150;       //*100 because the animation is too small
  x+= 1;   
  //x+= 0.1;    
  //x+= PI;   
  //x+= PI/10;        (different stepping)
  //first function
  stroke(255);//white
  strokeWeight(2);
  point(x, y);
  //second function
  stroke(255,0,0);//red
  strokeWeight(2);
  point(x, z);  
  //myFunction   :myFunction is the perfect sin function animation :)
  stroke(0,100,200);//blue
  strokeWeight(2);
  point(x, myFunction);
  fill(255);
  line(0,0,600,0);
  line(0,-height/3,0,height/3);  //coordinate
  }
