var k j i sum rav

fa j := 1 to 3 ->
  sum := 0
  fa k := 1 to 20 ->
    sum := sum+k
    break 2
    if sum > 30 ->
      print 888
      fa i := 78 to 79 ->
	break 4 # should be ignored
        print i
	break 0 # should be ignored
      af
      break 1
    fi
    print k
  af
  print sum
  fa k := 1 to 20 ->
    if k = 9 ->
      break 1
    fi
  af
  print k
  if j>1 ->
    if sum > 30 ->
      break 1
    fi
  fi
af
print j
break # illegal, but should be ignored
print 7654321
print k
print sum
print i

i := 1
do i < 20 ->
  j := 30
  do j > 9 ->
    k := 12
    do k < 18 ->
       if i + k > 28 ->
         print 8888
         break 3
       fi
       k := k+2
    od
    j := j-1
  od
  print i
  i := i+2
od
print i
print j
print k

i := 1
do i < 20 ->
  j := 30
  do j > 9 ->
    k := 12
    do k < 18 ->
       if i + k > 38 ->
         print 8888
         break 2
       fi
       k := k+2
    od
    j := j-1
  od
  print i
  i := i+4
od
print i
print j
print k

i := 1
do i < 20 ->
  fa j := 10 to 30 ->
    k := j+2
    do k < 18 ->
       if i + k > 38 ->
         print 8888
         break 2
       fi
       k := k+2
    od
  af
  print i
  i := i+4
od
print i
print j
print k

