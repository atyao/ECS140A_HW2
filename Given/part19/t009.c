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
x_k
= -12345;
int
x_long
= -12345;
int
x_n
= -12345;
x_i
=
25
;
while(1){
if(
10
!=
x_i
)
{
{
if(
20
==
x_i
)
{
{
x_k
=
20
-
x_i
;
while(1){
if(
x_k
)
{
{
printf("%d\n", 
x_k
);
x_k
=
x_k
+
1
;
}
}
else break;
}
}
}
else
if(
15
==
x_i
)
{
{
x_k
=
15
-
x_i
;
x_n
=
5
;
while(1){
if(
x_k
)
{
{
x_long
=
10
*
x_k
;
printf("%d\n", 
x_long
);
x_k
=
x_k
+
1
;
}
}
else
if(
x_n
)
{
{
printf("%d\n", 
x_n
);
x_n
=
0
;
}
}
else break;
}
printf("%d\n", 
x_long
);
}
}
else
{
{
x_k
=
10
-
x_i
;
while(1){
if(
x_k
)
{
{
printf("%d\n", 
x_k
);
x_k
=
x_k
+
1
;
}
}
else break;
}
}
}
x_i
=
x_i
-
1
;
}
}
else break;
}
printf("%d\n", 
x_i
);
}
return 0; }
