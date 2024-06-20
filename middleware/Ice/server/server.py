import signal
import sys
import Ice
from math import sqrt

Ice.loadSlice('./calculator.ice')
import Demo

class CalcI(Demo.Calc):
    def add(self, a, b, current):
        print(a+b)
        return a+b

    def norm(self, vector, current):
        print(sqrt(vector.x**2+vector.y**2))
        return sqrt(vector.x**2+vector.y**2)

    def addVectors(self, vectors, current):
        x, y = 0, 0
        for vector in vectors:
            x += vector.x
            y += vector.y
        print(x, y)
        return Demo.Vector(x, y)



with Ice.initialize(sys.argv) as communicator:
    adapter = communicator.createObjectAdapterWithEndpoints(
        "SimpleCalcAdapter", "default -h localhost -p 10000")
    adapter.add(CalcI(), Ice.stringToIdentity("SimpleCalc"))
    adapter.activate()
    communicator.waitForShutdown()
