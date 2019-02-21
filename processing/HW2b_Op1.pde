/*
    Author: Wang Jinge  
    Koch Snowflake
    date 19/2/4
*/

void setup() {
  size(800, 800);
}

void draw() {
  background(255);
  snowflake(new PVector(0, height-250), new PVector(width, height-250), new PVector(width/2, 0));
}

void koch(PVector p1, PVector p2, int deep) {
  // distance between two points
  float d = dist(p1.x, p1.y, p2.x, p2.y);
  // If the distance is too short
  if (d < 4) {
    line(p1.x, p1.y, p2.x, p2.y);
    return;
  }

  PVector mid1 = PVector.lerp(p1, p2, 1.0/3);
  PVector mid2 = PVector.lerp(p1, p2, 2.0/3);
  PVector midonLine = PVector.lerp(p1, p2, 0.5);
  PVector target = PVector.sub(mid2, mid1);
  target.rotate(PI/3);
  target.add(mid1);

  if (frameCount < 60*deep) {
    float dt = frameCount%(60);
    PVector mid = PVector.lerp(midonLine, target, dt/60.0);
    line(p1.x, p1.y, mid1.x, mid1.y);
    line(mid.x, mid.y, mid1.x, mid1.y);
    line(mid.x, mid.y, mid2.x, mid2.y);
    line(p2.x, p2.y, mid2.x, mid2.y);
  } else {
    koch(p1, mid1, deep+1);
    koch(mid1, target, deep+1);
    koch(target, mid2, deep+1);
    koch(mid2, p2, deep+1);
  }
}

void snowflake(PVector p1, PVector p2, PVector p3) {
  koch(p1, p2, 1);
  koch(p2, p3, 1);
  koch(p3, p1, 1);
}
