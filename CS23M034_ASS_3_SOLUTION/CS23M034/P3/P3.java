import syntaxtree.*;
import visitor.*;



public class P3 {
   public static void main(String [] args) {
      try {
         Node root = new TACoJavaParser(System.in).Goal();
         //System.out.println("Program parsed successfully ");


         Info ClassInformation = new Info<>();
         root.accept(ClassInformation); // Your assignment part is invoked here.


         Create programCreation= new Create<>();
         root.accept(programCreation,ClassInformation);

         System.out.println(programCreation.ans);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}
