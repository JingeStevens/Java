/*
        Author: Wang Jinge
*/
void setup() {
  size(800, 800);
  background(0);
  noStroke();
}
void draw() {
  background(0);
  fill(250, 233, 77);  
  //final int n = 4;//n*n matrix
  final int n = 8;
  int m = 800 / n;//length of the side
  int x = 0; 
  int y = 0; 
  for (int i = 0;i < n; i++){
    for (int j = 0; j <= n; j++){
      stroke(255, 0, 0);
      strokeWeight(3);
      rect(j * m,y,m,m);
      x += m;
    }   
    stroke(255, 0, 0);
    strokeWeight(3);
    rect(x, i * m ,m,m);
    y += m;
  }
}
