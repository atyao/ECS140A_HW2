var repeat bigSum once einmal rav

bigSum := 0

fa repeat := 1 to 2 ->
  var g sum rav
  sum := 0

  fa once := 1 to 1 ->
    dump 1
    fa einmal := 1 to 1 ->
      var k rav
      fa k := 1 to 2 ->
        print repeat  print k
        dump 2
      af
    af
  af

  fa g := 1 to 10 ->
    sum := sum+g
    dump 1
  af

  bigSum := bigSum + sum

af

dump
