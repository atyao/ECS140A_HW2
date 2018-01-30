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
x_k
= -12345;
int
x_g
= -12345;
int
x_h
= -12345;
x_g
=
1
;
x_h
=
1
;
for(
x_k
=
1
;
x_k
<=
20
;
x_k
++)
{
{
int
x_newg
= -12345;
printf("%d\n", 
x_h
);
x_newg
=
x_h
;
x_h
=
x_g
+
x_h
;
x_g
=
x_newg
;
}
}
}
return 0; }
