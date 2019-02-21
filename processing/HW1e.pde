/*
        Author: Wang Jinge
*/
void setup() {
  //The first method:from 1 to 1/99
  int i;
  double sum1 = 0;
  
  for(i = 1; i < 100; ++i)
  {
    sum1 = sum1 + 1.0/i;
  }
  println("sum1 = " + sum1);
  //The second method: from 1/99 to 1
  int j;
  double sum2 = 0;
  for (j = 99; j >=1 ; j--){
    sum2 = sum2 + 1.0/j;
  }
    println("sum2 = " + sum2);
//Difference
   double d;
  d = sum1 - sum2;
  println(" d = "+ d);
  exit();
}
