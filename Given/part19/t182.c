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
printf("+++ dump on line 2 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_a,1,0,"a");
printf("%12d %3d %3d %s\n", x_b,1,0,"b");
printf("%12d %3d %3d %s\n", x_c,1,0,"c");
printf("--- dump on line 2 of all levels end ---\n");
x_a
=
10
;
x_b
=
20
;
printf("+++ dump on line 5 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_a,1,0,"a");
printf("%12d %3d %3d %s\n", x_b,1,0,"b");
printf("%12d %3d %3d %s\n", x_c,1,0,"c");
printf("--- dump on line 5 of all levels end ---\n");
x_a
=
x_b
;
x_c
=
44
;
printf("+++ dump on line 8 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_a,1,0,"a");
printf("%12d %3d %3d %s\n", x_b,1,0,"b");
printf("%12d %3d %3d %s\n", x_c,1,0,"c");
printf("--- dump on line 8 of all levels end ---\n");
printf("+++ dump on line 9 of all levels begin +++\n");
printf("%12d %3d %3d %s\n", x_a,1,0,"a");
printf("%12d %3d %3d %s\n", x_b,1,0,"b");
printf("%12d %3d %3d %s\n", x_c,1,0,"c");
printf("--- dump on line 9 of all levels end ---\n");
}
return 0; }
