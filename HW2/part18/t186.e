var repeat bigSum once rav

bigSum := 0

fa repeat := 1 to 2 ->
  var g sum rav
  sum := 0

  fa once := 1 to 1 ->
    var k sum rav

    sum := 0
    dump
    fa k := 1 to 10 ->
      sum := sum+k
      dump
    af
  af

  fa g := 1 to 10 ->
    sum := sum+g
    dump
  af

  bigSum := bigSum + sum

af

dump
