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
x_sum
= -12345;
x_sum
=
0
;
printf("+++ dump on line 4 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_k,1,0,"k");
printf("%12d %3d %3d %s\n", x_sum,1,0,"sum");
printf("--- dump on line 4 of all levels end ---\n");
for(
x_k
=
1
;
x_k
<=
10
;
x_k
++)
{
{
x_sum
=
x_sum
+
x_k
;
printf("+++ dump on line 7 of level 1 begin +++\n");
printf("--- dump on line 7 of level 1 end ---\n");
}
}
}
return 0; }
