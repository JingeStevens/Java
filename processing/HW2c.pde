/*
    Author:Wang Jinge  
    Sum
    Date:19/2/3
*/
void setup(){
  for( long n = 1; n <= 100000; n *= 10){       // use long because if use int, it will be "infinite"
    float sum = 0;
    for (long i = 1; i <= n; i++)
      sum += 1.0 / (i*i);


    float sum2 = 0;
    for (long i = n; i >= 1; i--)
      sum2 += 1.0 / (i*i);
    
    println(n, sum, sum2, sqrt(6 * sum));
  } 
  exit();
}
