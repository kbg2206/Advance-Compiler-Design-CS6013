import syntaxtree.*;
import visitor.*;

public class P1 {
   public static void main(String [] args) {
      try {
         Node root = new BuritoJavaParser(System.in).Goal();
         //System.out.println("Program parsed successfully");
         
         Test_depth x =new Test_depth();
         root.accept(x); // Your assignment part is invoked here.
         if(x.ans==false)
         {
               System.out.println("No uninitialized variables.");
         }
         else
         {
               System.out.println("Uninitialized variable found.");   
         }
         //x.printResult();
         //System.out.println();
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 



