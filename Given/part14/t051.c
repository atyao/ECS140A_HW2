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
