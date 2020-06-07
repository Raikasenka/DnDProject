classes:
	javac *.java
	javac */*.java

test: classes
	java DnDTest

clean:
	rm -rf *.class
