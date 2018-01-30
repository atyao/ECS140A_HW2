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
x_i
=
25
;
while(1){
if(
x_i
!=
10
)
{
{
if(
x_i
<
20
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
<
0
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
x_i
<
15
)
{
{
x_k
=
15
-
x_i
;
while(1){
if(
x_k
<
0
)
{
{
int
x_long
= -12345;
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
else break;
}
printf("%d\n", 
