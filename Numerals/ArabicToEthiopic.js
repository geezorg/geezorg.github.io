function ArabicToEthiopic(number) {
    var numberString = number.toString();
    var numberLength = numberString.length - 1
        
    if ( (numberLength % 2) == 0 ) {
        numberString = "0" + numberString;
        numberLength++; 
    }
         
    var ethioNumberString = "";
     
    for( var place = numberLength; place >= 0; place-- ) {
            var asciiOne, asciiTen, ethioOne=null, ethioTen=null;

            asciiTen = numberString.charAt( numberLength - place );
            place--;
            asciiOne = numberString.charAt( numberLength - place );
            
            if ( asciiOne != "0" ) {
                ethioOne = String.fromCharCode( "፩".charCodeAt(0) + (asciiOne.charCodeAt(0) - "1".charCodeAt(0) ) );
            }
                
            if ( asciiTen != "0" ) {
                ethioTen = String.fromCharCode( "፲".charCodeAt(0) + ( asciiTen.charCodeAt(0) - "1".charCodeAt(0) ) );
            }
            
            var pos = (place % 4) / 2;
            
            var sep=null;
            if ( place != 0 ) {
                if ( pos == 0 ) {
                    sep = "፼";
                }
                else if ( (ethioOne != null) || (ethioTen != null) ) {
                    sep = "፻";
                }
            }

            if ( (ethioOne == "፩") && !(ethioTen) && (numberLength > 1) ) {
                if ( (sep == "፻") || ((place + 1) == numberLength) ) {
                    ethioOne = null;
                }
            }
            
            if ( ethioTen ) { ethioNumberString += ethioTen; }
            if ( ethioOne ) { ethioNumberString += ethioOne; }
            if ( sep )      { ethioNumberString += sep;      }

    }
         
    return ethioNumberString;
}


var Numbers = [
			1,
			10,
			100,
			1000,
			10000,
			100000,
			1000000,
			10000000,
			100000000,
			100010000,
			100100000,
			100200000,
			100110000,
			1,
			11,
			111,
			1111,
			11111,
			111111,
			1111111,
			11111111,
			111111111,
			1111111111,
			11111111111,
			111111111111,
			1111111111111,
			1,
			12,
			123,
			1234,
			12345,
			7654321,
			17654321,
			51615131,
			15161513,
			10101011,
			101,
			1001,
			1010,
			1011,
			1100,
			1101,
			1111,
			10001,
			10010,
			10100,
			10101,
			10110,
			10111,
			100001,
			100010,
			100011,
			100100,
			101010,
			1000001,
			1000101,
			1000100,
			1010000,
			1010001,
			1100001,
			1010101,
			101010101,
			10000,         // እልፍ
			100000,        // አእላፍ
			1000000,       // አእላፋት
			10000000,      // ትእልፊት
			100000000,     // ትእልፊታት
			1000000000,
			10000000000,
			100000000000,  // እልፊት
			1000000000000, // ምእልፊታት
			100010000,
			100010100,
			101010100,
			3,
			30,
			33,
			303,
			3003,
			3030,
			3033,
			3300,
			3303,
			3333,
			30003,
			30303,
			300003,
			303030,
			3000003,
			3000303,
			3030003,
			3300003,
			3030303,
			303030303,
			333333333
];

for(var i in Numbers) {
	print( Numbers[i] + " ⇒ " + ArabicToEthiopic(Numbers[i]) );
}
