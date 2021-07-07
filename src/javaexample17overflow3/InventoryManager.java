
package javaexample17overflow3;

import java.util.concurrent.atomic.AtomicInteger;


public class InventoryManager {
   
    private final AtomicInteger itemsInInventory =
      new AtomicInteger(100);
 
  public final void nextItem() {
    while (true) {
      int old = itemsInInventory.get();
      if (old == Integer.MAX_VALUE) {
        throw new ArithmeticException("Integer overflow");
      }
      int next = old + 1; // Increment
      if (itemsInInventory.compareAndSet(old, next)) {
        break;
      }
    } // End while
  } // End nextItem()
}
/*
 
 This compliant solution uses the get() and compareAndSet() methods provided by AtomicInteger to guarantee 
 successful manipulation of the shared value of itemsInInventory. This solution has the following characteristics:

(*) The number and order of accesses to itemsInInventory remain unchanged from the noncompliant code example.
(*) All operations on the value of itemsInInventory are performed on a temporary local copy of its value.
(*) The overflow check in this example is performed in inline code rather than encapsulated in a method call. 
This is an acceptable alternative implementation. The choice of method call versus inline code should be 
made according to your organization's standards and needs.

// -----------------------------------------------------------------------------
The two arguments to the compareAndSet() method are the expected value of the variable when the method is invoked 
and the intended new value. The variable's value is updated only when the current value and the expected value 
are equal [API 2006] (refer to VNA02-J. Ensure that compound operations on shared variables are atomic for more details).

 */