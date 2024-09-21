public class EthiopicToInteger {

	public boolean isEthiopicNumber(char ethNumber) {
		return ( ('፩' <= (int)ethNumber) && ((int)ethNumber <= '፼') );
	}

	/*
	 *  AB፼AB፻AB፼AB፻AB  =>  AB፻^5 + AB፻^4 + AB፻^3 + AB፻^2 + AB፻^1 + AB፻^0
	 * 
	 */
	public int convert (String ethiopic) {

		/* Confirm that we have a valid number string */
		if( (ethiopic == null) || (ethiopic.length() == 0) )
			return ( -1 ); // failure code


		int numberLength = ethiopic.length();
		for ( int i=0; i < numberLength; i++ ) {
			if ( !isEthiopicNumber ( ethiopic.charAt(i) ) ) {
				return ( -1 );
			}
		}


		/*
		 *  Read right to left to get the orders of 10 right
		 */
		int numout=0, power=0;
		int firstNumber = numberLength - 1;
		int A=0, B=0;
		boolean sawMeto = false;
		
		for( int i = firstNumber; i>=0; i-- )
		{
			int X = (int)ethiopic.charAt(i);

			if ( X == '፻' || X == '፼' ) {
				if( X == '፼' ) {
					if( sawMeto ) {
						if ( A == 0 ) {
							A = 1;
						}
						//we may have skipped a B, so add A:
						numout += A*(int)Math.pow((double)100, (double)(power));							
					} else {
						//we may have skipped a B, so add A:
						numout += A*(int)Math.pow((double)100, (double)(power));						
					}
					sawMeto = false;
					power++;
				} else {
					numout += A*(int)Math.pow((double)100, (double)(power));
					power++;
					sawMeto = true;
				}
				
				if( numberLength == 1 ) {
					numout = (int)Math.pow((double)100, (double)power);
					return numout;
				}
				
				if( i == 0 ) {
					numout += (int)Math.pow((double)100, (double)power);
				}
				
				A = 0;
				continue;
			}

			if ( X >= '፲' ) {
				B = (X - '፱') * 10;
				numout += (B + A) * (int)Math.pow((double)100, (double)power);
				A = B = 0;
				sawMeto = false;
			}
			else {
				A = (X - '፩') + 1;
				if( i == 0 ) {
					numout +=  A * (int)Math.pow((double)100, (double)power);
				}
			}
		}

		return numout ;

	}
	
	public static void main(String[] args) {
		String[] numbers = {
			"፩",
			"፪",
			"፫",
			"፬",
			"፭",
			"፮",
			"፯",
			"፰",
			"፱",
			"፲",
			"፲፩",
			"፲፫",
			"፳",
			"፴",
			"፻", 
			"፲፻",
			"፼",
			"፭፼",
			"፶፼",
			"፲፩",
			"፳፪",
			"፴፫",
			"፵፬",
			"፶፭",
			"፷፮",
			"፸፯",
			"፹፰",
			"፺፱", 
			"፪፻",
			"፪፻፯",
			"፳፻",
			"፳፻፯",
			"፳፪፻፳፪",
			"፲፱፻፹፯", 
			"፻፺፱",
			"፺፱፻፺፱", 
			"፼፺፱፻፺፱",
			"፺፱፻፺፱፼፺፱፻፺፱",
			"፲፼",
			"፻፼",
			"፲፻፼",
			"፼፼",
			"፻፩",	
			"፻፲",
			"፻፲፩",			
			"፼፻",
			"፼፻፲",
			"፼፻፲፩",
			"፲፩፻፲፩",
			"፼፲፩፻፲፩",
			"፲፩፼፲፩፻፲፩",
			"፲፩፻፲፩፼፲፩፻፲፩",
			"፲፻፲፼፲፻፲", // 10101010
			"፻፩፼፻፩",  //  1010101
			"፪፻፪፼፪፻፪", //  2020202
			"፼፩",
			"፼፲",
			"፼፻፩"		
		};

		EthiopicToInteger e2i = new EthiopicToInteger();

		for(String number: numbers) {
			System.out.println( number + " => " + e2i.convert(number) );
		}
	}

}
