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
x_n
= -12345;
for(
x_k
=
5
;
x_k
<=
1
;
x_k
++)
if( 
x_k
!=
3
)
{
{
printf("%d\n", 
x_k
);
}
}
printf("%d\n", 
x_k
);
x_n
=
10
;
for(
x_k
=
x_n
-
4
;
x_k
<=
x_n
-
2
;
x_k
++)
{
{
printf("%d\n", 
x_k
);
}
}
printf("%d\n", 
x_n
);
printf("%d\n", 
x_k
);
for(
x_n
=
1
;
x_n
<=
4
;
x_n
++)
{
{
printf("%d\n", 
1000
+
x_n
);
for(
x_k
=
x_n
*
x_n
;
x_k
<=
x_n
*
2
;
x_k
++)
{
{
printf("%d\n", 
200
+
x_k
);
}
}
for(
x_k
=
x_n
*
x_n
;
x_k
<=
x_n
;
x_k
++)
{
{
printf("%d\n", 
300
+
x_k
);
}
}
}
}
printf("%d\n", 
x_n
);
printf("%d\n", 
x_k
);
printf("%d\n", 
8888
);
for(
x_n
=
1
;
x_n
<=
4
;
x_n
++)
{
{
printf("%d\n", 
1000
+
x_n
);
for(
x_k
=
x_n
*
x_n
;
x_k
<=
x_n
*
2
;
x_k
++)
{
{
printf("%d\n", 
200
+
x_k
);
x_k
=
x_n
*
8
;
}
}
x_n
=
77
;
for(
x_k
=
x_n
*
x_n
;
x_k
<=
x_n
;
x_k
++)
{
{
printf("%d\n", 
300
+
x_k
);
}
}
}
}
printf("%d\n", 
x_n
);
printf("%d\n", 
x_k
);
}
return 0; }
