//no, it will not compile ;) just a model goto usage example
//based on Stephen Prata's book ;)


/* some code*/
while( fun > 0 )
  {
  for( i = 1 ; i <= 100 ; i++)
    {
    oh so many instructions;
    fibonacciRecursive(i);
    if(trouble)
      {
      puts("Run! Save your life!");
      goto escape;
      }
    some time consuming lines of code'
    }
  yetAnotherThingToDo();
  }

escape:
puts("Huh, we are safe now")

/*some code*/
