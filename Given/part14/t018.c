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
x_i
= -12345;
x_i
=
1
;
while(1){
if(
x_i
==
3
)
{
{
printf("%d\n", 
100
+
x_i
);
x_i
=
x_i
+
1
;
}
}
else
if(
x_i
<
5
)
{
{
printf("%d\n", 
x_i
);
x_i
=
x_i
+
1
;
}
}
else break;
}
}
return 0; }
