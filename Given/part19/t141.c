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
mod(
x_k
,
5
)
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
mod(
x_k
,
0
-
5
)
);
}
}
printf("%d\n", 
888
);
printf("%d\n", 
mod(
8
,
5
)
);
printf("%d\n", 
mod(
19
,
7
)
);
printf("%d\n", 
mod(
1000
,
mod(
2000
,
1009
)
)
);
x_k
=
0
-
2000
;
printf("%d\n", 
x_k
%
33
);
printf("%d\n", 
x_k
%
(
0
-
33
)
);
printf("%d\n", 
mod(
x_k
,
33
)
);
printf("%d\n", 
mod(
x_k
,
0
-
33
)
);
printf("%d\n", 
mod(
19
,
11
)
);
printf("%d\n", 
mod(
8
,
5
)
*
mod(
19
,
7
)
*
mod(
mod(
1000
,
mod(
2000
,
1009
)
)
+
mod(
19
,
11
)
,
5
)
);
printf("%d\n", 
mod(
19
,
7
)
);
printf("%d\n", 
mod(
mod(
8
,
5
)
*
mod(
19
,
7
)
*
mod(
mod(
1000
,
mod(
2000
,
1009
)
)
+
mod(
19
,
11
)
,
5
)
+
74
,
mod(
19
,
7
)
)
);
printf("%d\n", 
777
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
mod(
5
,
x_k
)
);
}
}
printf("%d\n", 
666
);
}
return 0; }
