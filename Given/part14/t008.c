int esquare(int x){ return x*x;}
#include <math.h>
#include <stdlib.h>
#include <stdbool.h>
int esqrt(int x){ double y; if (x < 0) return 0; y = sqrt((double)x); return (int)y;}
#include <stdio.h>
bool isZero(int x){if(x == 0)return true; return false;}
int mod(int x, int y){if(isZero(y)){printf("mod(a,b) with b=0"); exit(1);} return (((x % y) + y) % y);}
int main() {
{
int
x_a
= -12345;
int
x_b
= -12345;
int
x_ab
= -12345;
x_a
=
999
;
x_ab
=
1888
;
printf("%d\n", 
x_a
);
printf("%d\n", 
x_ab
);
if(
