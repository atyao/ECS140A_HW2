var repeat bigSum rav

bigSum := 0

fa repeat := 1 to 2 ->

  var k g sum rav

  sum := 0
  dump
  fa k := 1 to 10 ->
    sum := sum+k
    dump
  af

  fa g := 1 to 10 ->
    sum := sum+g
    dump
  af

  bigSum := bigSum + sum

af

dump
