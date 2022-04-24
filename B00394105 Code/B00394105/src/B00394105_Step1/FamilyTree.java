package B00394105_Step1;

/**
 *
 * @author Lewis
 */
public class FamilyTree {
    
    public class FamilyFoundException extends Exception{}
    
    // FamilyTreeNode class definition with its classes
    private class FamilyTreeNode {
        private String name; // Represents the string name of the family member
        private FamilyTreeNode ancestor; // Represents the parent(Patriarch or Matriarch)node
        private FamilyTreeNode partner; // Represents the parents partner (husband or wife) node
        private FamilyTreeNode sibling; // Represents the shared relationship between the child nodes with the parent node
        private FamilyTreeNode child; // Represents the nodes connected to the parent node
    }
    
    private final FamilyTreeNode familyHead; // Represents the ancestor data for the constructor
    private final FamilyTreeNode familyPartner; // Represents the partner data for the constructor
    private FamilyTreeNode current; // Represents the current selected value/node data
    
    // public FamilyTree class will take in two string inputs from the FamilyTreeTest class
    // -------------------------------------------------------------------------------------------------------------------------
    // familyHead equals new FamilyTreeNode class
    // familyPartner equals new FamilyTreeNode class
    // familyHead with node name equals string input familyHeadName
    // familyPartner with node name equals string input familyPartnerName
    // current equals familyHead class node
    public FamilyTree(String familyHeadName, String familyPartnerName){
        this.familyHead = new FamilyTreeNode();
        this.familyPartner = new FamilyTreeNode();
        this.familyHead.name = familyHeadName;
        this.familyPartner.name = familyPartnerName;
        this.current = this.familyHead;
    }
    
    // String toString() is called when case 2 in the main program is pinged at
    // Will gather details of the family tree, return the information and print it back in the main code
    // -------------------------------------------------------------------------------------------------------------------------
    /* Algorithm
    String familyDetails equals new String class for the current method
    familyDetails adds and equals: name node of the familyHead class + a connecting sentence + name node of the familyPartner class then take a new line
    class FamilyTreeNode node descendant equals the top child node of familyHead class
    If the node descendant is empty then familyDetails adds and equals: Sentence referencing empty node + a new line
    Otherwise while the node descendant is not empty:
        familyDetails adds and equals: a gap + the descendents current name node + a new line
        familyDetails adds and equals: access the getChild class using descendant
        descendant variable will now equal the sibling node, looping the code section until a sibling node is empty
    At end of class, return full familyDetails with message to be printed
    */
    @Override
    public String toString(){
        String familyDetails = new String();
        familyDetails += this.familyHead.name + ", partner is " + this.familyPartner.name + "\n";
        FamilyTreeNode descendant = this.familyHead.child;
        
        if(descendant == null){
            familyDetails += " has no descendants/children \n";
        } else {
            while(descendant != null){
                familyDetails += " " + descendant.name + "\n";
                familyDetails += this.getChild(descendant);
                descendant = descendant.sibling;
            }
        }
        return familyDetails;
    }
    
    // String getFlippedDetails() is called when case 2 in the main program is pinged at
    // Behaves almost exactly to toString apart from:
        // familyDetails is changed to flippedDetails
        // The first sentence is changed, flipping the roles of the familyHead and familyPartner node names
    // -------------------------------------------------------------------------------------------------------------------------
    public String flippedDetails(){
        String flippedDetails = new String();
        flippedDetails += this.familyPartner.name + ", partner is " + this.familyHead.name + "\n";
        FamilyTreeNode descendant = this.familyHead.child;
        
        if(descendant == null){
            flippedDetails += " has no descendants/children \n";
        } else {
            while(descendant != null){
                flippedDetails += " " + descendant.name + "\n";
                flippedDetails += this.getChild(descendant);
                descendant = descendant.sibling;
            }
        }
        return flippedDetails;
    }
    
    // This function get the names/id's of the descendant/children nodes to be used in toString
    // -------------------------------------------------------------------------------------------------------------------------
    /* Algorithm
    string childDetails equals new String class for the current method
    descendant from toString equals the child node
    If descendant is empty then message would have displayed that child had no descendants
    Otherwise while descendant was not empty:
        ChildDetails adds and equals a gap + the descendents current name node + a new line
        descendant variable will now equal the sibling node, looping the code section until a sibling node is empty
    childDetails string class will be returned to toString to be put into familyDetails string class
    */
    private String getChild(FamilyTreeNode descendant){
        String childDetails = new String();
        descendant = descendant.child;
        if(descendant == null) {
            childDetails += ""; // This originally displayed a message indicating the child had no descendants but was left blank for step one
        } else {
            while(descendant != null) {
                childDetails += " " + descendant.name + "\n";
                descendant = descendant.sibling;
            }
        }
        return childDetails;
    }
    
    // This function will be used in the main code to add new children names to the FamilyTree node
    // -------------------------------------------------------------------------------------------------------------------------
    /* Algorithm
    Using FamilyTreeNode descendant equals new FamilyTreeNode class
    descendant with class node name equals the name passed from FamilyTreeTest java code
    descendant with class node ancestor equals current, which equals the familyHead value
    If current familyHead's child node is empty:
        familyHead's child node equals descendant, which equals input name
    Otherwise:
        If descendant using input name equals (ignoring casing) the current child name then:
            Throw exception that name is found and not unique
        Otherwise:   
            Using FamilyTreeNode next equals the empty current child node
            While next using node sibling is not blank:
                If descendant using input name equals (ignoring casing) the next sibling name then:
                    Throw exception that name is found and not unique
                Otherwise:
                    next equals next with node sibling
            next using node sibling equals the descendant
    */
    public void addChild(String name) throws FamilyFoundException{
        FamilyTreeNode descendant = new FamilyTreeNode();
        descendant.name = name;
        descendant.ancestor = this.current;
        if(this.current.child == null){
            this.current.child = descendant;
        } else {
            if(descendant.name.equalsIgnoreCase(this.current.child.name)){
                throw new FamilyFoundException();
            } else {
                FamilyTreeNode next = this.current.child;
                while(next.sibling != null){
                    if(descendant.name.equalsIgnoreCase(next.sibling.name)){
                        throw new FamilyFoundException();
                    } else {
                        next = next.sibling;
                    }
                }
                next.sibling = descendant;
            }
        }
    }
    
    
    
}
