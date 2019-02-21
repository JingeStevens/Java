/**
  Author:Wang Jinge    
  Sierpinski Triangle
  Date:19/2/3
 **/

int level = 0;

void sierpinski(int x1, int y1, int x2, int y2, int x3, int y3, 
  int level) {
  if (level == 0) {
    fill(0);
    triangle(x1, y1, x2, y2, x3, y3);
    return;
  }

  int nx1=(x1+x2)/2;
  int ny1=(y1+y2)/2;
  int nx2=(x2+x3)/2;
  int ny2=(y2+y3)/2;
  int nx3=(x1+x3)/2;
  int ny3=(y1+y3)/2;

  fill(255);
  triangle(nx1, ny1, nx2, ny2, nx3, ny3);

  sierpinski(x1, y1, nx1, ny1, nx3, ny3, level-1);
  sierpinski(nx1, ny1, x2, y2, nx2, ny2, level-1); 
  sierpinski(nx3, ny3, nx2, ny2, x3, y3, level-1);
}


void setup() {
  size(800, 800);
  frameRate(1);  // control the speed of animation
}

void draw() {
  background(255);
  sierpinski(0, height, width/2, 0, width, height, level);
  level++;
  if (level == 8){
    level = 0;
    }
}
