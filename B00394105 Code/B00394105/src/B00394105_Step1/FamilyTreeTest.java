package B00394105_Step1;

/**
 *
 * @author Lewis
 */
public class FamilyTreeTest {
    
    public static void main(String[] args) {
        // Inputs for the names of the Ancestor Patriarch or Matriarch, and the name of their Partner for matching nodes
        // -------------------------------------------------------------------------------------------------------------------------
        // name equals string input value
        // partnerName equals string input value
        String name = Input.getString("What is the Ancestors name: ");
        String partnerName = Input.getString("What is there Partners name: ");
        
        // Inputs are then passed to the FamilyTree class
        // -------------------------------------------------------------------------------------------------------------------------
        // Class variable equals new class using string inputs
        FamilyTree familyTree = new FamilyTree(name, partnerName);
        
        // Sets up a menu that users can use to access features related to the program requirements
        // -------------------------------------------------------------------------------------------------------------------------
        // 0 input will quit the program and print a message
        // 1 input will take the user to case 1
        // 2 input will take the user to case 2
        // If option input does not equal one of the given options then display a message and resend option
        Integer option;
        do {
            System.out.println("-------------------------------------");
            System.out.println("0: Quit program");
            System.out.println("1: Add child to current");
            System.out.println("2: Display Family Tree");
            option = Input.getInteger("Input menu choice: ");
            while(option != 0 && option != 1 && option != 2){
                System.out.println("Invalid menu option...");
                option = Input.getInteger("Input menu choice: ");
            }
            System.out.println("-------------------------------------");
            
            switch (option) {
                case 0:
                    // Case 0 will simply print a message then quit
                    // -------------------------------------------------------------------------------------------------------------------------
                    System.out.println("Quitting Program...");
                    break;
                case 1:
                    // Case 1 takes an input for the ancestors child name which will be implemented in the FamilyTree class
                    // -------------------------------------------------------------------------------------------------------------------------
                    /* Algorithm
                    name equals string input value
                    Try to put name value through class function compareName
                    Print message confirming uniqueness if no exceptions encountered
                    Send the name value through class function addChild
                    If encounter is caught then print message stating lack of uniqueness
                    Break to finish
                    */
                    name = Input.getString("Input the child's name: ");
                    try{
                        familyTree.addChild(name);
                        System.out.println("Child added...");
                    } catch(FamilyTree.FamilyFoundException e){
                        System.out.println("Child name already exists...");
                    }
                    break;
                case 2:
                    // Case 2 will print the familyTree nodes, ancestor & partner along with their children
                    // -------------------------------------------------------------------------------------------------------------------------
                    /* Algorithm
                    Call on class to print toString messages on screen
                    Call on class to print flippedDetails message on screen
                    Break when finished
                    */
                    System.out.println(familyTree);
                    System.out.println(familyTree.flippedDetails());
                    break;
            }
        } while (option != 0);
    }
}
