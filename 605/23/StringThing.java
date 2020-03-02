class StringThing {

    public static void method(String id, String literal, String aNewString)    {
        System.out.println(id + ". " + (literal == aNewString ));
    }
    public static void main( String args[] ) {

        String aString = "654"; // Generates "654" inside of string pool.
        String bString = "654"; // Refers to "654" inside of string pool.
        String cString = "6" + "54"; // Concatenates "65" and "4", therefore referring to "654" inside of string pool.

        // Note: For following explanations, "654" is in string pool and not eligible for garbage collection until end
        //       of program. It will not be generated again by any of these statements, because it already exists.

        /*
           Output explanation:
           Strings generated:
           Garbage collection:
         */
        /*
           Output explanation: "false". Compiler evaluates from left to right, so "I. 654" does not have same memory
                               address as "654".
           Strings generated: One literal is generated, "I. 654".
           Garbage collection: "I. 654" is eligible for garbage collection inside of string pool.
         */
        System.out.println("I. " +     bString == aString   );
        /*
           Output explanation: "II. true". bString and aString refer to same literal "654" inside of string pool.
           Strings generated: "II. true"
           Garbage collection: "II. true" will be eligible for garbage inside of string pool.
         */
        System.out.println("II.    " +     ( bString == aString )   );
        /*
           Output explanation: "III. false" Compiler evaluates from left to right, so "654"+[empty string] is interned
                               in string pool. This is not the same location as "654".
           Strings generated: "654"+[empty string] and "III. false" are generated.
           Garbage collection: "654"+[empty string] and "III. false" is eligible for garbage collection inside of string
                               pool.
         */
        System.out.println("III    " +     ( bString  + "" == aString )   );
        /*
           Output explanation: "IV. false" Compiler evaluates from left to right, so parenthesis do not change anything
                               in comparison to II.
           Strings generated: "654"+[empty string] and "IV. false"
           Garbage collection: "654"+[empty string] and "IV. false" are eligible for garbage collection in string pool.
         */
        System.out.println("IV.    " +     ( ( bString  + "" ) == aString )   );
        /*
           Output explanation: "V. false" [empty string]+"654" is stored in different memory address than "654"
           Strings generated: [empty string]+"654" and "V. false" are generated.
           Garbage collection: [empty string]+"654" and "V. false" are eligible for garbage collection inside of string
                               pool.
         */
        System.out.println("V. " +     ( ( "" + bString  ) == aString )   );
        /*
           Output explanation: "false". "a. 654" is not in the same memory address as "654".
           Strings generated: "a. 654" is generated.
           Garbage collection: "a. 654 is eligible for garbage collection inside of string pool.
         */
        System.out.println("a. " +     "654" == aString   );
        /*
           Output explanation: True. Compiler follows order of operations and evaluates parenthesis. It sees string
                               first and concatenates the following integer. So "654" is the result and thus is in the
                               memory location that aString refers to.
           Strings generated: "b. true" is generated.
           Garbage collection: "b. true" will be eligible for garbage collection in string pool.
         */
        System.out.println("b. " +   ( "65" + 4 == aString ) );
        /*
           Output explanation: Compiler follows order of operations, so 6*54 = 324. Then first element of expression is
                               string so following elements will concatenated into string. Result will be "c. 654324"
           Strings generated: "c. 654324"
           Garbage collection: "c. 654324" is eligible for garbage collection inside of string pool.
         */
        System.out.println("c. " +   aString  + 6 * 54 );
        /*
           Output explanation: Compiler evaluates left to right. Because first element is string, following elements
                               will be concatenated together. Result will be "d. 6544654"
           Strings generated: "d. 6544654"
           Garbage collection: "d. 6544654" is eligible for garbage collection inside of string pool.
         */
        System.out.println("d. " +   654 + 4 + aString  );
        /*
           Output explanation: Compile follows order of operation, starting with parenthesis. 654+8=658. Then first
                               element of expression is string, so following elements will be concatenated together.
                               Result will be "e. 658654"
           Strings generated: "e. 658654"
           Garbage collection: "e. 658654" is eligible for garbage collection inside of string pool.
         */
        System.out.println("e. " +   ( 654 + 4 ) + aString   );
        /*
           Output explanation: Compiler follows order of operations, starting with parenthesis. 654 - 5 = 649, then
                               concatenated with empty string and therefore concatenated with every other element.
                               Result will be "f. 6494654"
           Strings generated: "f. 6494654"
           Garbage collection: "f. 6494654" will be eligible for garbage collection inside of string pool.
         */
        System.out.println("f. " +   ( 654 - 5 + "" +  4 + aString )  );
        /*
           Output explanation: Compiler follows order of operations, so 654 * 4 = 2616. Then first element of expression
                               is string, so following elements will be concatenated. Result will be "g. 2616654"
           Strings generated: "g. 2616654"
           Garbage collection: "g. 2616654" will be eligible for garbage collection inside of string pool.
         */
        System.out.println("g. " +   654 * 4 + aString  );
        /*
           Output explanation: Compiler follows order of operations, so 654 / 4 = 654. Then first element of expression
                               is string, so following elements will be concatenated. Result will be "h. 654654"
           Strings generated: "g. 654654"
           Garbage collection: "g. 654654" will be eligible for garbage collection inside of string pool.
         */
        System.out.println("h. " +   654 / 1 + aString  );
        /*
           Output explanation: Compiler follows order of operation starting with parenthesis. 654 - 0 = 654. Elements
                               are then concatenated together. Result will be "i. 654654".
           Strings generated: "i. 654654"
           Garbage collection: "i. 654654" will be eligible for garbage collection inside of string pool.
         */
        System.out.println("i. " +   ( 654 - 0 )  + aString  );

        /*
           Output explanation: Compiler starts with parenthesis. aString refers to "654" inside of string pool, so this
                               will evaluate to true. Result will be "j. true".
           Strings generated: "j. true"
           Garbage collection: "j. true" will be eligible for garbage collection inside of string pool.
         */
        System.out.println("j. " +     ( "654" == aString )   );
        /*
           Output explanation: Start with parenthesis. "65"+"4" is concatenated to "654" and thus shares same memory
                               location as "654". Result will be "g. true".
           Strings generated: "g. true"
           Garbage collection: "g. true" will be eligible for garbage collection inside of string pool.
         */
        System.out.println("g. " +     ( "65" + "4" == "654"  )   );
        /*
           Output explanation: Starts with parenthesis. cString refers to "654" inside of string pool, so this evaluates
                               to true. Result will be "h. true"
           Strings generated: "h. true"
           Garbage collection: "h. true" will be eligible for garbage collection inside of string pool.
         */
        System.out.println("h. " +     ( "654" == cString )   );
        /*
           Output explanation: Concatenates "x" and "yz" to "xyz", in the same memory location as "xyz". They are equal
                               so result will be "1. true"
           Strings generated: "1", "xyz", and "1. true"
           Garbage collection: all strings generated here will be eligible for g.c. in string pool
         */
        method("1", "xyz", "x" + "yz");
        /*
           Output explanation: Concatenates "x" and "y" and "z" to "xyz". Result will be "2. true"
           Strings generated: "2", "xyz", and "2. true"
           Garbage collection: all strings generated here will be eligible for g.c. in string pool
         */
        method("2", "xyz", "x" + "y" +"z");
        /*
           Output explanation: "6" and "5" and 4" are concatenated together into "654". Then compiler follows order of
                               operations, so 5 * 6 = 30. Then "6" and 30 and 4 are concatenated together into "6304".
                               Result will be "3. false"
           Strings generated: "3", "6304" and "3. false"
           Garbage collection: all strings here will be eligible for g.c. in string pool
         */
        method("3", "6" + "5" + "4", "6" + 5 * 6 + 4);
        /*
           Output explanation: "x" is a string object. Strings computed by concatenation at runtime are newly created
                               and therefore distinct. Result will be "4. false"
           Strings generated: "4", "xyz", "yz" and String object containing "x"
           Garbage collection: all string here will eligible for g.c, even string object because it is out of scope
         */
        method("4", "xyz", new String("x") + "yz" ); //
        /*
           Output explanation: Compiler starts with parenthesis. 4 - 6 = -2. Then elements will be concatenated
                               together. "654" is not in the same memory address as "6-24". Result will be "5. false"
           Strings generated: "5", "6-24", "5. false"
           Garbage collection: all strings generated here will be eligible for g.c. in string pool
         */
        method("5", "6" + "5" + "4", "6" + (4 - 6)  + 4);
        /*
           Output explanation: "Compiler reads left to right, "6" and 5 and 4 will be concatenated together, so it is
                               equal to "654". Result will be "6. true."
           Strings generated: "6" and "6. true" ("654" is already in string pool)
           Garbage collection: Strings generated here will be eligible for g.c. in string pool
         */
        method("6", "6" + "5" + "4", "6" + 5 + 4);
    }
}