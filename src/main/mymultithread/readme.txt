用来创建现成的三种方式：
1、通过继承Thread来创建线程
2、通过实现Runable来创建线程
3、通过使用Callable和Future来创建线程


三种方式的对比（1；2、3）：
继承Thread：
    a、优势是编写简单，如果需要访问当前线程，只需要用this即可
    b、劣势是java是单继承，线程类已经继承了Thread就不能在继承其他的父类

实现Runnable、Callable接口：
    a、优点：变相的多继承
    b、优点：多个线程可以共享同一个target对象，非常适合多个相同线程来处理同一份资源的情况，
       从而将cpu、代码和数据分开，形成清晰的模型，较好的体现了面向对象的思想
    c：缺点：编程复杂了点，要想访问当前线程，必须使用Thread.currentThread()方法

    modify by lwz