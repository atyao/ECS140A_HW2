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
x_repeat
= -12345;
int
x_bigSum
= -12345;
x_bigSum
=
0
;
for(
x_repeat
=
1
;
x_repeat
<=
2
;
x_repeat
++)
{
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
printf("+++ dump on line 11 of level 0 begin +++\n");
printf("%12d %3d %3d %s\n", x_repeat,1,0,"repeat");
printf("%12d %3d %3d %s\n", x_bigSum,1,0,"bigSum");
printf("--- dump on line 11 of level 0 end ---\n");
printf("+++ dump on line 12 of level 1 begin +++\n");
printf("%12d %3d %3d %s\n", x_k,8,1,"k");
printf("%12d %3d %3d %s\n", x_sum,8,1,"sum");
printf("--- dump on line 12 of level 1 end ---\n");
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
printf("+++ dump on line 15 of level 0 begin +++\n");
printf("%12d %3d %3d %s\n", x_repeat,1,0,"repeat");
printf("%12d %3d %3d %s\n", x_bigSum,1,0,"bigSum");
printf("--- dump on line 15 of level 0 end ---\n");
printf("+++ dump on line 16 of level 1 begin +++\n");
printf("%12d %3d %3d %s\n", x_k,8,1,"k");
printf("%12d %3d %3d %s\n", x_sum,8,1,"sum");
printf("--- dump on line 16 of level 1 end ---\n");
}
}
x_bigSum
=
x_bigSum
+
x_sum
;
}
}
}
return 0; }
