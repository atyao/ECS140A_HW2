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
for(
x_k
=
0
-
10
;
x_k
<=
10
;
x_k
++)
{
{
printf("%d\n", 
x_k
);
printf("%d\n", 
x_k
%
5
);
}
}
printf("%d\n", 
999
);
for(
x_k
=
0
-
10
;
x_k
<=
10
;
x_k
++)
{
{
printf("%d\n", 
x_k
);
printf("%d\n", 
x_k
%
5
%
2
);
}
}
printf("%d\n", 
300
+
23
%
5
+
6
%
4
);
printf("%d\n", 
10
*
4
%
6
*
11
);
printf("%d\n", 
100
%
9
%
3
);
}
return 0; }
