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
int
x_j
= -12345;
for(
x_i
=
1
;
x_i
<=
4
;
x_i
++)
{
{
printf("%d\n", 
x_i
);
}
}
for(
x_i
=
2
;
x_i
<=
5
;
x_i
++)
{
{
for(
x_j
=
x_i
;
x_j
<=
5
;
x_j
++)
{
{
printf("%d\n", 
x_i
*
100
+
x_j
);
}
}
}
}
for(
x_i
=
2
;
x_i
<=
9
;
x_i
++)
{
{
for(
x_j
=
1
;
x_j
<=
5
;
x_j
++)
if( 
x_i
!=
x_j
)
{
{
printf("%d\n", 
x_i
*
100
+
x_j
);
}
}
}
}
for(
x_j
=
10
;
x_j
<=
3
;
x_j
++)
{
{
printf("%d\n", 
2000
+
x_j
);
}
}
}
return 0; }
