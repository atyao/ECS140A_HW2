var k j i sum rav

fa j := 1 to 3 ->
  sum := 0
  fa k := 1 to 20 ->
    sum := sum+k
    break 0 # should be ignored
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
