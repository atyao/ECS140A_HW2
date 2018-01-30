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
x_a
= -12345;
int
x_b
= -12345;
int
x_c
= -12345;
int
x_d
= -12345;
for(
x_a
=
1
;
x_a
<=
2
;
x_a
++)
{
{
}
}
for(
x_b
=
1
;
x_b
<=
3
;
x_b
++)
{
{
printf("%d\n", 
x_b
);
}
}
}
return 0; }
