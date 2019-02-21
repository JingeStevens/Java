/*	Math methods
 * Author:Wang Jinge
 * Date:  2/11/2019
 */
package Fraction;
public class Fraction {
    private int num, den;
    public Fraction(int n, int d){
        num = n;
        den = d;
    }
    public Fraction(){
        num = 0;
        den = 1;
    }
    
    public String toString(){   
        return num + "/" + den;
    }
    //add "+"
    public Fraction add(Fraction b){
        Fraction answer = new Fraction(this.num * b.den + b.num * this.den,this.den * b.den);
        return answer;
    }
    // times "*"
    public Fraction times(Fraction b) {
    	Fraction answer = new Fraction(this.num* b.num,this.den * b.den);
    	return answer;
    }
    //minus "-"
    public Fraction min(Fraction b) {
    	Fraction answer = new Fraction (this.num * b.den - this.den * b.num, this.den * b.den);
    	return answer;	
    }
    // opposite "-x"
    public Fraction negtive() {
    	this.num = -num;    //this.den = num;
    	return this;
    }
    // division "/"
    public Fraction division(Fraction b) {
    	Fraction answer = new Fraction(this.num * b.den, this.den * b.num);
    	return answer;
    	
    }
    
    public static void main(String args[]){
       
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1,3); 
        a.negtive();
        b.negtive();
        System.out.println("the sum is "+a.add(b));
        System.out.println("the product is " + a.times(b));
        System.out.println("the difference is " + a.min(b));
        System.out.println("the opposit number is "+ a +" and " + b );       
        System.out.println("the quotients is " + a.division(b));
 
    }    
}

