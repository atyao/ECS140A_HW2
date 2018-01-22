var k sume sumo rav
EXCNT
sume := 0
sumo := 0
fa k := 1 to 1000 ->
  EXCNT
  if mod(k,2) = 0 ->
    sume := sume+k
    EXCNT
  else ->
    sumo := sumo+k
    EXCNT
  fi
  EXCNT
  if mod(k,10) = 0 ->
    dump
    EXCNT
  fi
  if sume > 100 ->
    dump
    EXCNT
    stop
  fi
af
EXCNT
