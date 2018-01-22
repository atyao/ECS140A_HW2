var k j sum rav

fa j := 1 to 3 ->
  sum := 0
  fa k := 1 to 20 ->
    sum := sum+k
    if sum > 30 ->
      print 888
      break
      print 12345
      print 54321
    fi
    print k
  af
  print sum
  break # makes having a loop loopy, but a good test.
  print 12345
  print 54321
af
print j

print 1111
k := 1
do k < 10 ->
  print k
  if k > 3 -> break fi
  k := k+1
od
print k

print 2222
k := 1
do k < 10 ->
  print k
  if k > 3 -> break fi
  k := k+1
[] k < 20 ->
  print 999
od
print k

print 3333
k := 1
do k < 10 ->
  print k
  k := k+1
[] k < 20 ->
  print k
  if k > 15 -> break fi
  k := k+2
od
print k

print 4444
k := 11
do k < 10 ->
  print k
  break
  k := k+1
[] k < 20 ->
  print k
  if k > 15 -> break fi
  k := k+2
od
print k

print 5555
k := 1
do k < 10 ->
  print k
  if k > 5 -> break fi
  k := k+2
[] k < 20 ->
  print k
  k := k+1
od
print k

print 6666
j := 1
do j <= 20 ->
  k := 11
  do k < 10 ->
    print k
    break
    k := k+1
  [] k < 20 ->
    print k
    if k > 15 -> break fi
    k := k+2
  od
  print k
  if j > 1 -> break fi
  j := j+1
od
print k
print j

