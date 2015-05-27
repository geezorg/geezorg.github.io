/* define number addresses */
#define ONE             0x1369
#define TEN             ONE + 9
#define HUNDRED         TEN + 9
#define TENTHOUSAND     HUNDRED + 1
typedef unsigned short  UCHAR;

#include <stdio.h>  /* imports "NULL */

 /**+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+/
/+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+/
//
//  arabtof (number)
//
//  Convert a string of ASCII digits into Ethiopic numerals.
//
//  number   -is a pointer to an ASCII string with a numeric sequence to be
//            convereted  ("1998" , "4,321" , ... )
//
//+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+/
/+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+**/


UCHAR*
ArabicToEthiopic (number)
  char* number;
{

UCHAR *ethioNumString, *returnString;
register int i, j, m, n;
char asciiOne, asciiTen;
UCHAR gezOne, gezTen, sep;
int place, pos;


  if ( number == NULL )
    return ( NULL );

  /*  simple integers only,
   *  reject if punctuation present:
   */
  for (i = 0; number[i] ; i++ )  
    if ( !(isdigit(number[i]) 
         || number[i] == '.'
         || number[i] == ',') )
      return ( NULL );

  n = strlen ( number ) - 1;

  if ( n == 0 ) {
    /* Only one digit, don't bother with the loop,
     * map onto Ethiopic "ones" and return:
     * */
    returnString = (UCHAR *) malloc ( 2 * sizeof (UCHAR) );
    returnString[0] = number[0] + ONE - '1';
    returnString[1] = '\0';
    return ( returnString );
  }
  else if ( (n % 2) == 0 ) {
     /* precondition the string to always have the leading
      * tens place populated, this avoids at least one check
      * in the loop:
      */
     char* tmp = (char *)malloc ( (n+2)*sizeof(char) );
     sprintf ( tmp, "0%s", number );
     number = tmp;
     n++;
  }


  /* make a big unicode digit holder: */
  ethioNumString = (UCHAR *) malloc ( (4*n+1) * sizeof (UCHAR) );

  m = 0;

  for ( place = n; place >= 0; place-- )
    {

      /* initialize values: */
      gezOne   = gezTen = 0x0;

      /* set ascii values: */
      asciiTen = number[n-place]; 
      place--;
      asciiOne = number[n-place]; 

      /* set ethiopic values: */
      if ( asciiOne != '0' )
        gezOne = asciiOne + (ONE - '1');  /* map onto Ethiopic "ones" */

      if ( asciiTen != '0' )
        gezTen = asciiTen + (TEN - '1');  /* map onto Ethiopic "tens" */

      /* pos indicates if our grouping subscript is even or odd       */
      pos = ( place % 4 ) / 2;

      /* find a separator, if any, to follow Ethiopic ten and one     */
      sep
      = ( place )
        ? ( pos )
          ? ( gezOne || gezTen )
            ? HUNDRED
            : 0x0
          : TENTHOUSAND
        : 0x0
      ;


      /* we want to clear ONE when it is superfluous  */
      if ( ( gezOne == ONE ) && gezTen == 0x0 )      /* one without a leading ten  */
        if ( ( sep == HUNDRED ) || (place+1) == n )  /* following (100) or leading */
          gezOne = 0x0;                              /* the sequence               */

      /* put it all together: */
      if ( gezTen )
        ethioNumString[m++] = gezTen;

      if ( gezOne )
        ethioNumString[m++] = gezOne;

      if ( sep )
        ethioNumString[m++] = sep;

    }

  returnString = ( UCHAR * ) malloc ( (m+1) * sizeof (UCHAR) );

  for (i=0; i<m; i++)
    returnString[i] = ethioNumString[i];

  returnString[m] = '\0';

  free ( ethioNumString );

  return ( returnString );

}
