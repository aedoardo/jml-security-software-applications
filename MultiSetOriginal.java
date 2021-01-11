
/* JML Exercise: 
   
   This class implements a MultiSet of integers, using an array.

   Add JML specs to this class, and if necessary modify the code, to stop openJML from complaining.


   The only JML keywords needed are
      requires
      invariant 
      ensures 
  
   If you want, you can also use
      non_null
   as abbreviation.


   While developing your specs, it may be useful to use the keyword
      assert
   to add additional assertions in source code, to find out what 
   JML can - or cannot - prove at a given program point.
  
*/

class MultiSet {

  int[] elements;
  int n;

 MultiSet(int[] input) {
    n = input.length;
    elements = new int[n];
    arraycopy(input, 0, elemnts, 0, n);
  }

  MultiSet() {
    n = 0;
    elements = new int[0];
  }

  void removeOnce(int elt) {
    for (int i = 0; i <= n; i++) {  
      if (elements[i] == elt ) {
         n--;
         elements[i] = elements[n];
         return;
      }
    }
  }

  /* the next method should remove ALL occurrences of the parameter int elt.
   */

  void removeAll(int elt) {
    for (int i = 0; i <= n; i++) {   
      if (elements[i] == elt ) {
         n--;
         elements[i] = elements[n];
      }
    }
  }

  int getCount(int elt) {
    int count = 0;
    for (int i = 0; i <= n; i++) 
      if (elements[i] == elt) count++; 
    return count;
  }

  /* Warning: you may have a hard time checking the method "add" below.
   */

  void add(int elt) {
    if (n == elements.length) {
       int[] new_elements = new int[2*n]; 
       arraycopy(elements, 0, new_elements, 0, n);
       elements = new_elements;
    }
    elements[n]=elt;
    n++;
  }

  void add(MultiSet b) {
    int[] new_elements = new int[n + b.n];
    arraycopy(elements, 0, new_elements, 0, n);
    arraycopy(b.elements, 0, new_elements, n+1, b.n);
    elements = new_elements; 
  }

  void add(int[] a) {
    this.add(new MultiSet(a));
  }

  MultiSet(MultiSet b) {
    this.add(b);    
  }


  private static void arraycopy(int[] src,
                                int   srcOff,
                                int[] dest,
                                int   destOff,
                                int   length) {
    for( int i=0 ; i<length; i++) {
       dest[destOff+i] = src[srcOff+i];
    }
  }
  
}
