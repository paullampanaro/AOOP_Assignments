// Code provided by instructor for assignment unless otherwise stated.
class Prime {

  public static boolean isPrime(int n) {
	
	for ( int index = 2; index < n; index ++ ) {
		if ( n % index  == 0 )
			 return false;
	}

	return true;
  }
  
  /**
   * This method is similar to provided isPrime method, but goes a step further.
   * When a non-prime number is found, check the resulting divisors to see if 
   * they are prime. If they are, then two prime factorial's have been found.
   *
   * @param n	Number to check
   * 
   * @author Paul Lampanaro
   */
  public static void integerFactorization(int n) {
	
	for ( int index = 2; index < n; index ++ ) {
		if ( n % index == 0 ) {
			// store the divisor
			int other = n / index;
			if(isPrime(other) && isPrime(index)) {
				System.out.println("The sum of all primes for " + n + ": " + 
						(other + index) + " (" + other + " + " + index + ")");
				break;
			}
		}
	}
  }
  
  public static void main( String args[] ) {
    for ( int index = 2; index <= 20; index ++ ) {
    	// call revised method here
    	integerFactorization(index);
    }
  }
}
