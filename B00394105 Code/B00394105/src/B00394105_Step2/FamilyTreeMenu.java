package B00394105_Step2;

/**
 *
 * @author Lewis
 */
public class FamilyTreeMenu {
    public static void main(String[] args){
        // String input for the name of the Ancestor, will be passed into FamilyTree
        // class familyTree, using the FamilyTree class, will equal new FamilyTree class, passing name input through constructor
        // While the name input is blank, print a message and resend input prompt
        String name = Input.getString("What is the Ancestors name: ");
        while(name.isEmpty()){
            System.out.println("Invalid name entry...");
            name = Input.getString("What is the Ancestors name: ");
        }
        FamilyTree familyTree = new FamilyTree(name);
        
        // Menu with options available to the user, accessed with integer input
        /*
            0 input will print a message and quit program
            1 input will prompt input for Partner attribute, asking for who to add Partner to
            2 input will prompt input for Child attribute, asking for who to add Child to and only if they have a Partner
            3 input will print entire Family Tree
            4 input will prompt input for Family Member name, then print there Family Tree
        */
        Integer option;
        do{
            System.out.println("-------------------------------------");
            System.out.println("0: Quit Program");
            System.out.println("1: Add Partner to Ancestor");
            System.out.println("2: Add Child to Ancestor");
            System.out.println("3: Display Entire Family Tree");
            System.out.println("4: Display Select Family Tree");
            option = Input.getInteger("Input menu choice: ");
            while(option != 0 && option != 1 && option != 2 && option != 3 && option != 4){
                System.out.println("Invalid menu option...");
                option = Input.getInteger("Input menu choice: ");
            }
            System.out.println("-------------------------------------");
            
            switch(option){
                case 0:
                    // Case 0 will print a message, break and then quit the program
                    System.out.println("Quitting Program...");
                    break;
                
                case 1:
                    // Case 1 will prompt the user for a string input, asking for the ancestors name and partner to be added name
                    // Calls method from familyTree addPartner()
                    // Exceptions will be in place to check several inputs and check existing nodes including:
                        // Blank inputs that will go into a while loop until an input is added
                        // That the ancestor name exists within the class node
                        // That the ancestor does not already have a partner
                    name = Input.getString("Input name of the Ancestor: ");
                    while(name.isEmpty()){
                        System.out.println("Invalid blank input...");
                        name = Input.getString("Input name of the desired Family Member: ");
                    }
                    
                    String name2 = Input.getString("Input name of the Partner: ");
                    while(name.isEmpty()){
                        System.out.println("Invalid blank input...");
                        name2 = Input.getString("Input name of the Partner: ");
                    }
                    
                    try{
                        familyTree.addPartner(name, name2);
                        System.out.println("Partner added...");
                    } catch(FamilyTree.FamilyTakenException e) {
                        System.out.println("Ancestor already has a partner...");
                    } catch(FamilyTree.FamilyNotFoundException e) {
                        System.out.println("Ancestor does not exist...");
                    }
                    break;
                    
                case 2:
                    // Case 2 when interacted with will prompt an input for a child name to add to the ancestor
                    // Calls method from familyTree addChild()
                    // Exceptions will be in place to check for two conditions:
                        // That the ancestor has a partner
                        // The child name is unique
                    name = Input.getString("Input child's name: ");
                    while(name.isEmpty()){
                        System.out.println("Invalid blank input...");
                        name = Input.getString("Input child's name: ");
                    }
                    try{
                        familyTree.addChild(name);
                        System.out.println("Child added...");
                    } catch(FamilyTree.FamilyNoPartnerException e) {
                        System.out.println("Ancestor does not have a partner...");
                    } catch(FamilyTree.FamilyNotUniqueException e) {
                        System.out.println("Child name already exists...");
                    }
                    break;
                    
                case 3:
                    // Case 3 when interacted with will print out the full family tree
                    // Calls method from class familyTree toString()
                    System.out.println(familyTree);
                    break;
                    
                case 4:
                    // Case 4 when interacted with will print out the family tree of the specified family member input
                    // Calls method from class familyTree getFamilyMember()
                    name = Input.getString("Input family member branch to display: ");
                    while(name.isEmpty()){
                        System.out.println("Invalid blank input...");
                        name = Input.getString("Input family member branch to display: ");
                    }
                    try{
                        familyTree.getFamilyMember(name);
                        System.out.println("Family Member found...");
                        System.out.println(familyTree.getFamilyMember(name));
                    } catch(FamilyTree.FamilyNotFoundException e) {
                        System.out.println("Family Member does not exist...");
                    }
                    break;
            }
        } while (option != 0);
    }
}
