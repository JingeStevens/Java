/*
          Author:Wang Jinge      
          Date:  2/8/2019
*/

PShape earth;
PShape moon;
void setup(){
  size(1000,1000,P3D);
  noStroke();
  background(0);
  PImage imageEarth = loadImage("earth.jpg");
  earth = createShape(SPHERE,earthDiameter);
  earth.setTexture(imageEarth);
  PImage imageMoon = loadImage("moon.jpg");
  moon = createShape(SPHERE,moonDiameter);
  moon.setTexture(imageMoon);
}

float orbit_1 = 0.0; //earth
float orbit_2 = 0.0; //moon
int earthDiameter = 160;
int moonDiameter = 40;
int zoom = -500;
void draw (){
  background(0);
  translate(width/2,height/2,zoom);
  //draw earth
  pushMatrix();
    rotateZ(radians(23.5));
    rotateY(orbit_1);
    orbit_1 += 0.05;
    shape(earth);
  popMatrix();
    
   //draw moon 
    rotateZ(radians(5.6));
    rotateY(orbit_2);
    translate(earthDiameter*4,0,0); 
    rotateY(-orbit_2);
    orbit_2 += 0.05/28.5;
    shape(moon);
}
