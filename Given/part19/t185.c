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
x_g
= -12345;
int
x_sum
= -12345;
x_sum
=
0
;
printf("+++ dump on line 10 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_repeat,1,0,"repeat");
printf("%12d %3d %3d %s\n", x_bigSum,1,0,"bigSum");
printf("%12d %3d %3d %s\n", x_k,7,1,"k");
printf("%12d %3d %3d %s\n", x_g,7,1,"g");
printf("%12d %3d %3d %s\n", x_sum,7,1,"sum");
printf("--- dump on line 10 of all levels end ---\n");
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
printf("+++ dump on line 13 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_repeat,1,0,"repeat");
printf("%12d %3d %3d %s\n", x_bigSum,1,0,"bigSum");
printf("%12d %3d %3d %s\n", x_k,7,1,"k");
printf("%12d %3d %3d %s\n", x_g,7,1,"g");
printf("%12d %3d %3d %s\n", x_sum,7,1,"sum");
printf("--- dump on line 13 of all levels end ---\n");
}
}
for(
x_g
=
1
;
x_g
<=
10
;
x_g
++)
{
{
x_sum
=
x_sum
+
x_g
;
printf("+++ dump on line 18 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_repeat,1,0,"repeat");
printf("%12d %3d %3d %s\n", x_bigSum,1,0,"bigSum");
printf("%12d %3d %3d %s\n", x_k,7,1,"k");
printf("%12d %3d %3d %s\n", x_g,7,1,"g");
printf("%12d %3d %3d %s\n", x_sum,7,1,"sum");
printf("--- dump on line 18 of all levels end ---\n");
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
printf("+++ dump on line 25 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_repeat,1,0,"repeat");
printf("%12d %3d %3d %s\n", x_bigSum,1,0,"bigSum");
printf("--- dump on line 25 of all levels end ---\n");
}
return 0; }
