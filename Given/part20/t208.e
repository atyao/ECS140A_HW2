var x y rav

x := 2
do x < 1000 ->
  EXCNT
  y := 3
  do y < 400 ->
     EXCNT
     if x + y > 444 ->
        EXCNT
        print x print y
	dump
	stop
     fi
     EXCNT
     y := y+3
  od
  EXCNT
  x := x+2
od
EXCNT
dump
