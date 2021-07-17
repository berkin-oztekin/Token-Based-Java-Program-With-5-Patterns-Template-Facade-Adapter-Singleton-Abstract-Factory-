# Token-Based-Java-Program-With-5-Patterns-Template-Facade-Adapter-Singleton-Abstract-Factory-
Hello coders.This is mine Software Architecture project..
I aim to that implement the 5 different pattern on the Java 
This program Token Based Program smilation. I only focused on patterns usages.

Briefly Explanations
--------------------
Adapter Pattern : I thought that all computer has OS(operating systems) so we need to adapt the different USB card to computer.If I use this pattern ,I can add the new Smart Card
to computers easily
--------------------.
Abstract Factory
-------------------
I used Abstract Factory pattern to produce SmartCard reader and USBtoken reader and their common method “cardInstertion”. 
We have an abstract reader factory class to make an insertion method. Then two reader factories extend this abstract class and 
change the body of method. Finally in buildReader class, we create our reader and add them into the port array “x”. We call
abstract factory objects on cardInstertion methods where there are SmartCard and USBstick classes

-------------------
We created an OS interface and wrote the methods that will be adapted. This means the OS is 
the target class. We used the SmartCard class as an adapter class. Then we adapted the OS to 
SmartCard on the OSToSmartcard class.
-------------------
Template Pattern
-------------------
When we provide the connection of SmartCard and USBStick , we need to 
respectively implement SmartCard and USBStick methods .In this situation we used 
Template Pattern.
-------------------
Why do we need template patterns? This implementation is like a recipe. We have to cook 
here so we need to use vegetables but SmartCard and USBStick have different recipes so we 
used different vegetables (methods).
Facade Pattern
-----------------
There are some common operations on writing and reading processes on both USBToken and 
SmartCard. We created a template class and their methods to keep common methods and 
operations. For example, encrypting operations for writing process and decrypting processes 
for reading processes are common. So we keep these methods on template design pattern.
Until this part everything is okay however the code looks very complicated. We need to make 
the code more understandable . While we were thinking , we decided to add a Facade Pattern.
Facade pattern works like a Tour Guide. The guide can tell us where we will go and how we 
will go. We create SmartCard and USBStick objects into the Access Class (Façade Class) and 
we implement the open() interface in the same class. In that way we adapted everything to the 
open() interface. 
-------------------
Singleton Pattern
-------------------
When all the steps are completed , SmartCard or USBStick which is adapted on Operating System 
add into our array list and we use sockets as that. Then, we thought of the Singleton Pattern .
The reason why we think that pattern is, we assume that all processes need to be implemented 
on one specific object. Also, we can check if the object has been created or not .Also we use 
the “lazy initialization approach”. It is a singleton pattern approach which means that our 
singleton object is created if we want it to be created and also it checks to the same object if it 
has been created or not. If it has not been created it provides an object is created.
