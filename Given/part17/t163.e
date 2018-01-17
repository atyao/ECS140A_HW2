var k sum rav

sum := 0
fa k := 1 to 20 ->
  sum := sum+k
  if sum > 30 ->
    print 888
    break
    print 54321
  fi
  print k
af
print sum
