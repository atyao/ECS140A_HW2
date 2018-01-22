var k rav

fa k := 0-10 to 10 ->
    print k
    print mod(k,5)
af

print 999

fa k := 0-10 to 10 ->
    print k
    print mod(k,0-5)
af

print 888
print mod(8,5)
print mod(19,7)
print mod(1000,mod(2000,1009))

k := 0-2000
print k%33
print k%(0-33)
print mod(k,33)
print mod(k,0-33)

print mod(19,11)
print mod(8,5)*mod(19,7)*mod(mod(1000,mod(2000,1009))+mod(19,11),5)
print mod(19,7)

print mod(mod(8,5)*mod(19,7)*mod(mod(1000,mod(2000,1009))+mod(19,11),5)+74,
          mod(19,7))

print 777

# this loop will cause program (when k=0) to terminate with "mod 0" error
fa k := 0-10 to 10 ->
    print k
    print mod(5,k)
af

print 666
