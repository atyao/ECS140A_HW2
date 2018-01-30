cp ../part10/*.java .
make 
java e2c $1.e > $1.c
gcc $1.c 
./a.out