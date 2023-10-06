# JAVA REFLECTION

- Language and JVM feature that gives us Runtime access to information about our application's classes, and objects.
- Those features are available to us through the `java.lang.reflect` (Reflection API) package.


## getClass()
- Returns the runtime class of an object.
- The returned Class object is the object that is locked by static synchronized methods of the represented class.

## Object Creation

- class.getDeclaredConstructor()
  - Returns all the constructors declared by the class. (public, protected, default (package) access, and private constructors)
- class.getConstructor()
  - Returns only the public constructors declared by the class.