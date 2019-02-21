/* 
        Author:Wang Jinge
*/
int x;
int y;
void setup(){
size(500, 500);
x=50;
y=50;
}
void draw(){
background(234, 113, 107);
fill(0,0,255);
stroke(0,200,0);
strokeWeight(5);
ellipse(x, y, 100, 100);
textSize(20);
text("   moving circle. Cool!",x+30,y);
if(x<450 && x>=50 && y<=50){
  x+=5;}
else if(x>=450 && y>=50 && y<450){
  y+=5;
}
else if(x>50 && x<=450 && y>=450){
  x-=5;
}else if(x>=50 && y>50 && y<=450){
  y-=5;
}

}
