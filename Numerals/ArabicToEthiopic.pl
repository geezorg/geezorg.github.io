#!/usr/bin/perl -w

binmode(STDIN, ":utf8");
binmode(STDOUT, ":utf8");
binmode(STDERR, ":utf8");
use utf8;
use strict;
use open ':encoding(utf-8)';


my @testNumbers = (
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
	"10000",          # Ilf
	"100000",         # aIlaf
	"1000000",        # aIlafat
	"10000000",       # tIlfit
	"100000000",      # tIlfitat
	"1000000000",
	"10000000000",
	"100000000000",   # mIlfit
	"1000000000000",  # mIlfitat
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
	"333333333",
);



sub convert
{
my $number = $_[0];

	my $n = length ( $number ) - 1;

	unless ( $n % 2 ) {
		#
		#  Add dummy leading 0 to precondition the number for
		#  the algorithm and reduce one logic test within the
		#  for loop
		#
		$number = "0$number";
		$n++;
	}

	my @aNumberString = split ( //, $number );
	my $eNumberString = "";


	#
	#  read number from most to least significant digits:
	#
	for ( my $place = $n; $place >= 0; $place-- ) {
		#
		#  initialize values to emptiness:
		#
		my ($aTen, $aOne) = ( 0, 0);  #    ascii ten's and one's place
		my ($eTen, $eOne) = ('','');  # ethiopic ten's and one's place


		#
		#  populate our tens and ones places from the number string:
		#
		$aTen = $aNumberString[$n-$place]; $place--;
		$aOne = $aNumberString[$n-$place];


		#
		#  map to corresponding Ethiopic character addresses:
		#
		$eTen = chr( ord('፲') + ord($aTen) - ord('1') )  if( $aTen );
		$eOne = chr( ord('፩') + ord($aOne) - ord('1') )  if( $aOne );


		#
		#  pos tracks our 'pos'ition in a sequence of 4 digits
		#  to help determine what separator we need between
		#  a grouping of tens and ones.
		#
		my $pos = ( $place % 4 ) / 2;  # make even/odd 


		#
		#  find a separator, if any, to follow ethiopic ten and one:
		#
		my $sep
		= ( $place )
		  ? ( $pos ) # odd
		    ? ( ($eTen ne '') || ($eOne ne '') )
		      ? '፻'
		      : ''
		    : '፼'
		  : ''
		;


		#
		#  if $eOne is an Ethiopic '፩' we want to clear it under
		#  under special conditions.  These ellision rules could be
		#  combined into a single big test but gets harder to read
		#  and manage:
		#
		if ( ( $eOne eq '፩' ) && ( $eTen eq '' ) && ( $n > 1 ) ) {
			if ( $sep eq '፻' ) {
				#
				#  A superflous implied ፩ before ፻
				#
				$eOne = '';
			}
			elsif ( ($place+1) == $n ) {   # recover from initial $place--
				#
				#  ፩ is the leading digit.
				#
				$eOne = '';
			}
		}


		#
		#  put it all together and append to our output number:
		#
		$eNumberString .= "$eTen$eOne$sep";	
	}

	$eNumberString;
}



main:
{

	my $count = 1;;
	foreach ( @testNumbers ) {
		printf "%0i) ", $count++;	
		print "$_ => ", convert($_), "\n";

	}
	print "\n\n";
	print "See the Convert::Ethiopic::Lite::Number package at CPAN\n";

}
