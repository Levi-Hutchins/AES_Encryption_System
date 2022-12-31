CC=g++
CFLAGS=-c -Wall 
LDFLAGS=
SOURCES= test.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=runme

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
