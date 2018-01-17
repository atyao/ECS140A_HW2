var k j sum rav

fa j := 1 to 3 ->
  sum := 0
  fa k := 1 to 20 ->
    sum := sum+k
    if sum > 30 ->
      print 888
      break
    fi
    print k
  af
  print sum
af
print j
