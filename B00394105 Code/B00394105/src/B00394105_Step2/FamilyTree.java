package B00394105_Step2;

/**
 *
 * @author Lewis
 */
public class FamilyTree {
    
    public class FamilyNotUniqueException extends Exception{}
    public class FamilyNotFoundException extends Exception{}
    public class FamilyTakenException extends Exception{}
    public class FamilyNoPartnerException extends Exception{}
    
    // FamilyTreeNode class definition with attributes
    private class FamilyTreeNode{
        private String name; // Represents the string name of the family member node
        private String familyIdentifier; // Used to identify family members
        private FamilyTreeNode ancestor; // Represents the parent(Patriarch or Matriarch)node
        private FamilyTreeNode partner; // Represents the parents partner (husband or wife) node
        private FamilyTreeNode sibling; // Represents the shared relationship between the child nodes with the parent node
        private FamilyTreeNode child; // Represents the nodes connected to the parent node
    }
    
    private final FamilyTreeNode familyHead; // Represents the ancestor data for the constructor
    private FamilyTreeNode current; // Represents the current selected value/node data
    
    // Public class will act as a constructor, taking in a string input from FamilyTreeTest
    /* Algorithm
        familyHead equals new class
        familyHead using string name equals input string name from FamilyTreeTest
        current equals class familyHead
    */
    public FamilyTree(String familyAncestorName){
        this.familyHead = new FamilyTreeNode();
        this.familyHead.name = familyAncestorName;
        this.current = this.familyHead;
    }
    
    // Method will be used to add the name of the Partner to an ancestor node
    // Method will also throw an exception if an ancestor does not exist or already has a partner
    /* Algorithm
        New class member using FamilyTreeNode
        Class member string name equals input partnerName(name2)
        Class member string familyIdentifier equals input ancestorName(name)
        Class member node ancestor equals class current
        If input ancestor equals(ignoring ancestor) the familyHead name then:
            If the current partner is empty then:
                the current partner is equal to the input name
            Otherwise
                throw exception that ancestor already has partner
        Otherwise:
            If the familyHead child node is not empty then:
                If input ancestor equals(ignoring casing) the familyHead's child then:
                    if the familyHead child has no partner then:
                        the familyHead child's partner equals the input name
                    Otherwise:
                        throw exception that ancestor already has partner
                Otherwise:
                    New class next equals the current child node
                    While the next sibling is not empty:
                        If the input name equals(ignoring casing) the next siblings name then:
                            If the sibling partner is empty then:
                                sibling partner equals the input partner name
                            Otherwise:
                                throw exception that ancestor already has partner
                        Otherwise:
                            next class move onto the next sibling
            Otherwise:
                throw exception that the family member does not exist
    */
    public void addPartner(String ancestorName, String partnerName) throws FamilyNotFoundException, FamilyTakenException{
        FamilyTreeNode member = new FamilyTreeNode();

        member.name = partnerName;
        member.familyIdentifier = ancestorName;
        member.ancestor = this.current;
        if(member.familyIdentifier.equalsIgnoreCase(this.familyHead.name)){
            if(this.current.partner == null){
                this.current.partner = member;
            } else {
                throw new FamilyTakenException();
            }
        } else {
            if(this.familyHead.child != null){
                if(member.familyIdentifier.equalsIgnoreCase(this.familyHead.child.name)){
                    if(this.familyHead.child.partner == null){
                        this.familyHead.child.partner = member;
                    } else {
                        throw new FamilyTakenException();
                    }
                } else {
                    FamilyTreeNode next = this.current.child;
                    if(next.sibling != null){
                        if(member.familyIdentifier.equalsIgnoreCase(next.sibling.name)){
                            if(next.sibling.partner == null){
                                next.sibling.partner = member;
                            } else {
                                throw new FamilyTakenException();
                            }
                        } else {
                            next = next.sibling;
                        }
                    }
                }
            } else {
                throw new FamilyNotFoundException();
            }
        }
    }
    
    // Method will be used to add the name of the child to the family tree
    // Method will throw exceptions if child name is not unique
    /* Algorithm
        New class descendant using FamilyTreeNode
        descendant node name equals input name
        descendant node ancestor equals current class
        If current familyHead has no partner then:
            throw exception of no partner for ancestor to have children
        Otherwise:
            If the current child is empty then:
                the input name is now the current child
            Otherwise:
                If the input name equals (ignoring casing) the current childs name then:
                    throw exception that name is not unique
                Otherwise:
                    Create new class next equals the current child
                    While the next sibling is not empty:
                        If the input name equals(ignoring casing) the siblings name
                            throw exception that name is not unique
                        Otherwise:
                            next equals the next sibling and go through loop again
                    next sibling equals the name input
    */
    public void addChild(String name) throws FamilyNotUniqueException, FamilyNoPartnerException{
        FamilyTreeNode descendant = new FamilyTreeNode();
        descendant.name = name;
        descendant.ancestor = this.current;
        if(this.current.partner == null){
            throw new FamilyNoPartnerException();
        } else {
            if(this.current.child == null){
                this.current.child = descendant;
            } else {
                if(descendant.name.equalsIgnoreCase(this.current.child.name)){
                    throw new FamilyNotUniqueException();
                } else {
                    FamilyTreeNode next = this.current.child;
                    while(next.sibling != null){
                        if(descendant.name.equalsIgnoreCase(next.sibling.name)){
                            throw new FamilyNotUniqueException();
                        } else {
                            next = next.sibling;
                        }
                    }
                    next.sibling = descendant;
                }
            }
        }
    }
    
    // Method will be used to print full details when called
    /* Algorithm
        New familyDetails string class
        New attribute partner using class FamilyTreeNode equals class familyHead partner node
        If partner attribute is empty then:
            familyDetails plus equals: familyHead name + text indicating no parter + new line
            return the familyDetails class to print
        Otherwise:
            familyDetails plus equals: familyHead name + connecting text + familyHead partner name + new line
    
        New attribute descendant using class FamilyTreeNode equals class familyHead child node
        If descendant attribute is empty then:
            familyDetails plus equals: text indicating ancestor has no children
        Otherwise:
            While descendant attribute is not empty then:
                If descendant partner is empty then:
                    familyDetails plus equals: space + descendant name + text referencing having no partner + new line
            Otherwise:
                    familyDetails plus equals: space + descendant name + connecting text + descendant partner name + new line
                    If descendent child node is empty then:
                        familyDetails += space + text stating they have no children + new line
            familyDetails plus equals: calling method getChild using attributes descendent and partner
            descendent attribute equals descendant sibling
        return familyDetails class to print
    */
    @Override
    public String toString(){
        String familyDetails = new String();
        FamilyTreeNode partner = this.familyHead.partner;
        if(partner == null){
            familyDetails += this.familyHead.name + ", has no partner \n";
            return familyDetails;
        } else {
            familyDetails += this.familyHead.name + ", partner is " + this.familyHead.partner.name + "\n";
        }
        FamilyTreeNode descendant = this.familyHead.child;
        
        if(descendant == null){
            familyDetails += " has no descendants/children \n";
        } else {
            while(descendant != null){
                if(descendant.partner == null){
                    familyDetails += " " + descendant.name + ", has no partner \n";
                } else {
                    familyDetails += " " + descendant.name + ", partner is " + descendant.partner.name + "\n";
                    if(descendant.child == null){
                        familyDetails += "  has no children/descendants \n";
                    }
                }
                familyDetails += this.getChild(descendant);
                descendant = descendant.sibling;
            }
        }
        return familyDetails;
    }
        
    
    // Method will be used to print selected details when called
    // Method will also throw an exception input family member does not exist
    /* Algorithm
        
    */
    public String getFamilyMember(String name) throws FamilyNotFoundException{
        String selectDetails = new String();
        
        FamilyTreeNode member = new FamilyTreeNode();
        member.familyIdentifier = name;
        
        if(member.familyIdentifier.equalsIgnoreCase(this.familyHead.name)){
            FamilyTreeNode partner = this.familyHead.partner;
            if(partner == null){
                selectDetails += this.familyHead.name + ", has no partner \n";
                return selectDetails;
            } else {
                selectDetails += this.familyHead.name + ", partner is " + this.familyHead.partner.name + "\n";
            }
            FamilyTreeNode descendant = this.familyHead.child;
            
            if(descendant == null){
                selectDetails += " has no descendants/children \n";
            } else {
                while(descendant != null){
                    if(descendant.partner == null){
                        selectDetails += " " + descendant.name + ", has no partner \n";
                    } else {
                        selectDetails += " " + descendant.name + ", partner is " + descendant.partner.name + "\n";
                        if(descendant.child == null){
                            selectDetails += "  has no children/descendants \n";
                        }
                    }
                    selectDetails += this.getChild(descendant);
                    descendant = descendant.sibling;
                }
            }
            return selectDetails;
        } else if(member.familyIdentifier.equalsIgnoreCase(this.familyHead.partner.name)){
            selectDetails += this.familyHead.partner.name + ", partner is " + this.familyHead.name + "\n";
            
            FamilyTreeNode descendant = this.familyHead.child;
            
            if(descendant == null){
                selectDetails += " has no descendants/children \n";
            } else {
                while(descendant != null){
                    if(descendant.partner == null){
                        selectDetails += " " + descendant.name + ", has no partner \n";
                    } else {
                        selectDetails += " " + descendant.name + ", partner is " + descendant.partner.name + "\n";
                        if(descendant.child == null){
                            selectDetails += "  has no children/descendants \n";
                        }
                    }
                    selectDetails += this.getChild(descendant);
                    descendant = descendant.sibling;
                }
            }
            return selectDetails;
        } else {
            if(this.familyHead.child == null){
                throw new FamilyNotFoundException();
            } else {
                if(member.familyIdentifier.equalsIgnoreCase(this.familyHead.child.name)){
                    if(this.familyHead.child.partner == null){
                        selectDetails += this.familyHead.child.name + ", has no partner \n";
                        return selectDetails;
                    } else {
                        selectDetails += this.familyHead.child.name + ", partner is " + this.familyHead.child.partner.name + "\n";
                    }
                } else if(this.familyHead.child.partner != null){
                    if(member.familyIdentifier.equalsIgnoreCase(this.familyHead.child.partner.name)){
                        selectDetails += this.familyHead.child.partner.name + ", partner is " + this.familyHead.child.name + "\n";
                    } else {
                        throw new FamilyNotFoundException();
                    }
                } else {
                    FamilyTreeNode next = this.familyHead.child;
                    
                    while(next.sibling != null){
                        if(member.familyIdentifier.equalsIgnoreCase(next.sibling.name)){
                            if(next.sibling.partner == null){
                                selectDetails += next.sibling.name + ", has no partner \n";
                                return selectDetails;
                            } else {
                                selectDetails += next.sibling.name + ", partner is " + next.sibling.partner.name + "\n";
                            }
                        } else if(next.sibling.partner != null){
                            if(member.familyIdentifier.equalsIgnoreCase(next.sibling.partner.name)){
                                selectDetails += next.sibling.partner.name + ", partner is " + next.sibling.name + "\n";
                            } else {
                                throw new FamilyNotFoundException();
                            }
                        } else {
                            throw new FamilyNotFoundException();
                        }
                        FamilyTreeNode descendant = next.sibling.child;
            
                        if(descendant == null){
                            selectDetails += " has no descendants/children \n";
                        } else {
                            while(descendant != null){
                                if(descendant.partner == null){
                                    selectDetails += " " + descendant.name + ", has no partner \n";
                                } else {
                                    selectDetails += " " + descendant.name + ", partner is " + descendant.partner.name + "\n";
                                    if(descendant.child == null){
                                        selectDetails += "  has no children/descendants \n";
                                    }
                                }
                                selectDetails += this.getChild(descendant);
                                descendant = descendant.sibling;
                            }
                        }
                        return selectDetails;
                    }
                }
                FamilyTreeNode descendant = this.familyHead.child.child;
            
                if(descendant == null){
                    selectDetails += " has no descendants/children \n";
                } else {
                    while(descendant != null){
                        if(descendant.partner == null){
                            selectDetails += " " + descendant.name + ", has no partner \n";
                        } else {
                            selectDetails += " " + descendant.name + ", partner is " + descendant.partner.name + "\n";
                            if(descendant.child == null){
                                selectDetails += "  has no children/descendants \n";
                            }
                        }
                        selectDetails += this.getChild(descendant);
                        descendant = descendant.sibling;
                    }
                }
                return selectDetails;
            }
        }
    }
    
    // Method will get names of the child node for use in toString and selectDetails if any exist
    /* Algorithm
        New string class childDetails
        attribute descendent equals descendant child node
        if descendent is empty then:
            childDetails plus equals blank? Probably left over from Step One
        Otherwise:
            While descendent is not empty
                childDetails plus equals: space + descendant name + new line
                descendant equals sibling until empty
        Return childDetails
    */
    private String getChild(FamilyTreeNode descendant){
        String childDetails = new String();
        descendant = descendant.child;
        if(descendant == null) {
            childDetails += "";
        } else {
            while(descendant != null) {
                childDetails += " " + descendant.name + "\n";
                descendant = descendant.sibling;
            }
        }
        return childDetails;
    }
    
}
