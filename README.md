3. OOP
Materials
OOP
Lecture 3
Lecture 4
Reflection
Reflections Lib
Faker
VideoLectures
03.oop.u1. Classes & Object
03.oop.u2. Reflections. ENUM
03.oop.u3. OOP principles
03.oop.u4. Equals&HashCodes. Abstract Classes
03.oop.u5. Wrapper Classes
03.oop.u6. Interfaces
Task #3
Before start creating source code, read carefully all materials about OOP. It is not only 3 principles for interview;) Store functionality should be based on above principles. Classes to create:

Product with such attributes as [name, rate, price]
Category classes with the name attribute, for each store category [bike, phone, milk] and products list
Store - class that should handle category list
RandomStorePopulator - utility class that will populate out store/category with fake data using Faker lib
StoreApp - class with main method to execute our store scenario. When invoke main method, application should init store with categories and products and pretty print this data. Also, categories should be read dynamically (at runtime), from base category package using reflections lib.
