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
x_a
=
10
;
x_b
=
20
;
x_c
=
12
;
printf("%d\n", 
max(
x_a
,
x_b
)
);
printf("%d\n", 
max(
x_b
,
x_a
)
);
printf("%d\n", 
max(
x_a
,
x_c
)
);
printf("%d\n", 
max(
x_c
,
x_b
)
);
printf("%d\n", 
max(
x_a
+
x_b
,
x_b
-
x_c
)
);
printf("%d\n", 
max(
x_a
-
x_b
,
x_c
-
x_b
)
);
}
return 0; }
