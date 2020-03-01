package proiect1;

import java.lang.Math; // importing java.lang package

public class PlayerStatus {
	
	private String nickname; //un sir de caractere, ce semnifica numele jucatorului
	public int score; // un numar intreg, ce reprezinta scorul jucatorului
	public int lives; // un numar intreg, ce simbolizeaza numarul de vieti al jucatorului
	public int health;//: un numar intreg, intre 0 si 100, ce reprezinta procentul de viata ramas
	public String weaponInHand; // un sir de caractere ce simbolizeaza arma jucatorului
	public boolean changeWeapon; // var creata de mine... nush daca e neaparat nevoie de ea.. vedem
	public static final String weapon_knife = "knife";
	public static final String weapon_sniper = "sniper";
	public static final String weapon_kalashnikov = "kalashnikov";
	
	private double positionX; //: coordonata OX a pozitiei jucatorului, o valoare numerica reala
	private double positionY; //: coordonata OY a pozitiei jucatorului, o valoare numerica reala
	protected static String gameName; //: numele jocului --> toti jucatorii fac parte din acelasi joc. Prin urmare, valoarea campului va fi unica pentru toate obiectele.

	
	public void initPlayer(String nickname) { //: va initializa doar numele jucatorului (restul campurilor vor avea valoarea implicita a tipului.
		
	}
		
	public void initPlayer(String nickname, int lives) { //: va initializa campurile aferente: nickname respectiv lives(restul campurilor vor avea valoarea implicita a tipulu
		
		
	}
	public void initPlayer(String nickname, int lives, int score) { }//: comportamentul va fi unul asemanator, initializand campurile cu acelasi nume ca parametrii.
	
	public void findArtifact(int artifactCode) 
	{
		if (isPerfectNumber(artifactCode)) 
		{
			score += 5000;
			lives++;
			health = 100;
			return;
		} 

		if (isPrimeNumber(artifactCode)) 
		{
			score += 1000;
			lives += 2;
			health += 25;
			if (health > 100) health = 100;
			return;
		}
		
		if (isComposedNumber(artifactCode))
		{
			score -= 3000; 
			health -= 25; 
			if (health <= 0) 
			{	lives -= 1;
			health = 100; 
			}
			return;
		} 
		score += artifactCode;
	}
	
	
	
	private static int costArma(String weapon) {
		
		/* knife - cost 1000 puncte,
		sniper- cost 10000 puncte,
		kalashnikov - cost 20000 puncte 
		*/
		if(weapon.equals(weapon_knife)) return 1000;
		if(weapon.equals(weapon_sniper)) return 1000;
		if(weapon.equals(weapon_kalashnikov)) return 20000;
		
		return Integer.MAX_VALUE; //max 2,147,483,647...
	}
	
	
	public boolean setWeaponInHand(String weaponProp) { 
		if (
				weaponProp.equals(weapon_kalashnikov) 
					|| 
				weaponProp.equals(weapon_knife) 
					|| 
				weaponProp.equals(weapon_sniper)
		){
			int cost= costArma(weaponProp);
			if (score >= cost) {
				score -= cost;
				weaponInHand = weaponProp;
				return true;
			}
		} 
		
		return false;
	}
	
	
	public String getWeaponInHand() {
		return weaponInHand;
	}
	
	public void setGameName(String newGameName) {
		    gameName = newGameName;
		  }
	 
	public String getGameName() {
	    return gameName;
	  }
	
	
	public double getX() {
	    return positionX;
	  }
	public double getY() {
	    return positionY;
	  }
	
	public void setX(double valoare) { 	//dam o valoare positionX
		positionX = valoare;
	}
	public void setY(double valoare) { 	//dam o valoare positionY
		positionY = valoare;
	}
	
	
	 
	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		setY(positionY);
	}
	
	private void setNickname(String nick) {
		nickname = nick;
	}
	public String getNickname() {
		return nickname; 
	}
	
	public double probability() {
		return (3 * this.health + this.score / 1000.0) / 4.0 ;
	}
	
	
	public int putereArma(double dist) {
		// pentru arma detinuta returneaza putere
		if (dist > 1000)  {
			
		if (this.weaponInHand.equals(weapon_sniper)) return 3;
		if (this.weaponInHand.equals(weapon_kalashnikov)) return 2;
		if (this.weaponInHand.equals(weapon_knife)) return 1;
	
		} else {
			
			if (this.weaponInHand.equals(weapon_sniper)) return 2;
			if (this.weaponInHand.equals(weapon_kalashnikov)) return 3;
			if (this.weaponInHand.equals(weapon_knife)) return 1;
		}
		
		return 0;  //arma fara putere
	}
	
	
	public boolean shouldAttackOponent(PlayerStatus oponent) {
		if (weaponInHand.equals(oponent.weaponInHand)) { 
			if (this.probability() > oponent.probability()) {  // compara probabilitatea in cazul in care armele sunt identice
				return true;
			} 
			else return false;
		} 
		    double distanta = this.distance(oponent);   ///double distanta = oponent.distance(this);
			if (this.putereArma(distanta) > oponent.putereArma(distanta) ) {  //sniper > kalashnikov > knife
			return true;
			} else { 
				return false;
			}
	} 
	
	//private double distance(double valueX, double  valueY) {
	//	return Math.sqrt(Math.pow(positionX - valueX, 2) + Math.pow(positionY - valueY,2));
	//}
	
	private double distance(PlayerStatus oponent) {
		return	Math.sqrt(
					Math.pow(this.positionX - oponent.positionX, 2) 
						+ 
					Math.pow(this.positionY - oponent.positionY,2)
				);
	}
	
	
	//verifica PERFECT
	public boolean isPerfectNumber(int number) {
		int sum = 0;
		for (int i=1; i<number; ++i) {
			if (number % i == 0) {
				sum +=i;
			}
		}
		if (sum == number) {
			return true;
			} else {
			return false;
			}
	}
	//////////////////
	
	//verifica PRIME
	public boolean isPrimeNumber(int number) {
		
		for (int i=2; i<=number/2; ++i) {
			if (number % i == 0) {
			      return false;
			    } 
		}
		return true;
	}
	//////////////////
	
	///CONDITIE SPECIALA este numar par si suma cifrelor sale este divizibila cu 3 atunci obiectul respectiv este o capcana.
	public boolean isComposedNumber(int number) { // 
		
		
		return true;
	}
	///////////////////
	
}
