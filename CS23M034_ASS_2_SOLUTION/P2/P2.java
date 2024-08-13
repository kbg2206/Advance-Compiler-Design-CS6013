import java.util.Map;
import java.util.Vector;

import syntaxtree.*;
import visitor.*;

public class P2 {
   public static void main(String [] args) {
      try {
         Node root = new BuritoJavaParser(System.in).Goal();

         Return_Type R=new Return_Type<>();
         root.accept(R);
         Variable t= new Variable();
         root.accept(t,R);
    
         Test_depth IR=new Test_depth();     
         root.accept(IR,t);

        //  for(String key:Table.keySet())
        //  {
        //     System.out.println(key);
        //     for(String k1:Table.get(key).keySet())
        //     {
        //         System.out.println(k1+" -> "+Table.get(key).get(k1));
        //     }
        //     System.out.println();
        //  }
         System.out.println(IR.ans);
         //System.out.println(t);
         
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}
