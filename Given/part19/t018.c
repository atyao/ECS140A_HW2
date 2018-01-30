#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#define max(x,y) (x)>(y)?(x):(y)
int esquare(int x){ return x*x;}
bool isZero(int x){if(x == 0)return true; return false;}
int mod(int x, int y){if(isZero(y)){printf("\nmod(a,b) with b=0\n"); exit(1);} return (((x % y) + y) % y);}
int esqrt(int x){ double y; if (x < 0) return 0; y = sqrt((double)x); return (int)y;}
int main() {
{
int
x_i
= -12345;
x_i
=
1
;
while(1){
if(
x_i
==
3
)
{
{
printf("%d\n", 
100
+
x_i
);
x_i
=
x_i
+
1
;
}
}
else
if(
x_i
<
5
)
{
{
printf("%d\n", 
x_i
);
x_i
=
x_i
+
1
;
}
}
else break;
}
}
return 0; }
