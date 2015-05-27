public class ArabicToEthiopic {

	private static final char ETHIOPIC_ONE          = 0x1369;
	private static final char ETHIOPIC_TEN          = 0x1372;
	private static final char ETHIOPIC_HUNDRED      = 0x137B;
	private static final char ETHIOPIC_TEN_THOUSAND = 0x137C;
	
	public static String Numeral ( String asciiNumberString ) {
		int n = asciiNumberString.length() - 1;
		
		 if ( (n%2) == 0 ) {
		 	asciiNumberString = "0" + asciiNumberString;
		 	n++;
		 }
		 
		 String ethioNumberString = "";
		 char asciiOne, asciiTen, ethioOne, ethioTen;
	 
		 for ( int place = n; place >= 0; place-- ) {
		 	asciiOne = asciiTen = ethioOne = ethioTen = 0x0;

			asciiTen = asciiNumberString.charAt( n - place );
			place--;
			asciiOne = asciiNumberString.charAt( n - place );
			
			 if ( asciiOne != '0' )
			 	ethioOne = (char)((int)asciiOne + ( ETHIOPIC_ONE - '1' ));
				
			if ( asciiTen != '0' ) 
				ethioTen = (char)((int)asciiTen + ( ETHIOPIC_TEN - '1'));
			
			int pos = ( place % 4 ) / 2;
			
			char sep = ( place != 0 )
			          ? ( pos != 0 )
			             ? ( ( ethioOne != 0x0 ) || ( ethioTen != 0x0 ) )
			                ? ETHIOPIC_HUNDRED
			                :  0x0
			             : ETHIOPIC_TEN_THOUSAND
			          :  0x0
			        ;

			if ( ( ethioOne == ETHIOPIC_ONE ) && ( ethioTen == 0x0 ) && ( n > 1 ) )
			  if ( ( sep == ETHIOPIC_HUNDRED ) || ( (place + 1) == n ) )
			    ethioOne = 0x0;
			    

			if ( ethioTen != 0x0 )
			  ethioNumberString += String.valueOf ( ethioTen );
			if ( ethioOne != 0x0 )
			  ethioNumberString += String.valueOf ( ethioOne );			  
			if ( sep != 0x0 )
			  ethioNumberString += String.valueOf ( sep );				
		 }
		 
		 return ( ethioNumberString );
	}
	
	public static void main(String[] args) {
		String Numbers[] = {
			"1",
			"10",
			"100",
			"1000",
			"10000",
			"100000",
			"1000000",
			"10000000",
			"100000000",
			"100010000",
			"100100000",
			"100200000",
			"100110000",
			"1",
			"11",
			"111",
			"1111",
			"11111",
			"111111",
			"1111111",
			"11111111",
			"111111111",
			"1111111111",
			"11111111111",
			"111111111111",
			"1111111111111",
			"1",
			"12",
			"123",
			"1234",
			"12345",
			"7654321",
			"17654321",
			"51615131",
			"15161513",
			"10101011",
			"101",
			"1001",
			"1010",
			"1011",
			"1100",
			"1101",
			"1111",
			"10001",
			"10010",
			"10100",
			"10101",
			"10110",
			"10111",
			"100001",
			"100010",
			"100011",
			"100100",
			"101010",
			"1000001",
			"1000101",
			"1000100",
			"1010000",
			"1010001",
			"1100001",
			"1010101",
			"101010101",
			"10000",         // እልፍ
			"100000",        // አእላፍ
			"1000000",       // አእላፋት
			"10000000",      // ትእልፊት
			"100000000",     // ትእልፊታት
			"1000000000",
			"10000000000",
			"100000000000",  // እልፊት
			"1000000000000", // ምእልፊታት
			"100010000",
			"100010100",
			"101010100",
			"3",
			"30",
			"33",
			"303",
			"3003",
			"3030",
			"3033",
			"3300",
			"3303",
			"3333",
			"30003",
			"30303",
			"300003",
			"303030",
			"3000003",
			"3000303",
			"3030003",
			"3300003",
			"3030303",
			"303030303",
			"333333333"
		};

		System.out.println ( "<html>\n<head>\n  <meta http-equiv=\"content-type\" content=\"text-html; charset=utf-8\">\n  <title>Ge'ez Number Samples</title>\n</head>\n<body bgcolor=\"#fffffh\">\n\n" );
		
		System.out.println ( "<h1>Ge'ez Number Samples</h1>\n<dir>\n<table border=1>" );
		int end = Numbers.length;		
		for ( int i = 0; i < end; i++ ) {
			System.out.println ( "  <tr>\n    <th>" + (i+1)  + "</th>\n    <td>" + Numbers[i] + "</td>\n    <td>" + Numeral ( Numbers[i] ) + "</td>\n  </tr>" );
		}

		System.out.println ( "</table>\n</dir>\n" );
		System.out.println ( "</body>\n</html>" );
	}
}
