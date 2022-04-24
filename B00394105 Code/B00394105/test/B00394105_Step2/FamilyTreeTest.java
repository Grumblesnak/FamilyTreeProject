/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B00394105_Step2;

import B00394105_Step2.FamilyTree.FamilyNoPartnerException;
import B00394105_Step2.FamilyTree.FamilyNotFoundException;
import B00394105_Step2.FamilyTree.FamilyNotUniqueException;
import B00394105_Step2.FamilyTree.FamilyTakenException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lewis
 */
public class FamilyTreeTest {
    
    public FamilyTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPartner method, of class FamilyTree.
     * Method will take in an input of an ancestor identifier name and intended partner name
     * Method will then run the ancestor input through the code and compare it to values kept within the family tree
     * If the input does not match either the ancestor name, partner name, or child name then an exception will be thrown
     */
    @Test (expected = FamilyNotFoundException.class)
    public void testAddPartnerFamilyNotFound() throws Exception {
        System.out.println("addPartner");
        String ancestorName = "AncestorName";
        String partnerName = "PartnerName";
        String actualAncestorName = "ActualAncestorName";
        FamilyTree instance = new FamilyTree(actualAncestorName);
        instance.addPartner(ancestorName, partnerName);
    }
    
    /**
     * Test of addPartner method, of class FamilyTree.
     * Method will take in an input of an ancestor identifier name and intended partner name
     * Method will then run the ancestor input through the code and compare it to values kept within the family tree
     * If the input finds a match it will check the value of the matches partner value
     * If the value is null then the input partner will become the matching ancestors new partner value
     * Otherwise if the value is not null then an exception will be thrown
     */
    @Test (expected = FamilyTakenException.class)
    public void testAddPartnerFamilyTaken() throws Exception {
        System.out.println("addPartner");
        String ancestorName = "AncestorName";
        String partnerName = "PartnerName";
        FamilyTree instance = new FamilyTree(ancestorName);
        //Add a correct partner
        instance.addPartner(ancestorName, partnerName);
        String otherPartnerName = "OtherPartnerName";
        //Try to add another partner to an existing family tree with a partner
        instance.addPartner(ancestorName, otherPartnerName);
    }

    /**
     * Test of addChild method, of class FamilyTree.
     * Method will take an input of a child name
     * Method will check the ancestor value and if it has a partner value
     * If the ancestors partner value is null then an exception will be thrown, preventing the child name from being added
     */
    @Test (expected = FamilyNoPartnerException.class)
    public void testAddChildNoPartner() throws Exception {
        System.out.println("addChild");
        String ancestorName = "AncestorName";
        FamilyTree instance = new FamilyTree(ancestorName);
        String childName = "child";
        instance.addChild(childName);
    }
    
     /**
     * Test of addChild method, of class FamilyTree.
     * Method will take an input of a child name
     * Method will check the ancestor value and if it has a partner value
     * If the ancestor value is not null then the code will compare the child name input to the child value of the ancestor
     * If the child name input matches the current child value then an exception will be thrown, preventing the child from being added
     */
    @Test (expected = FamilyNotUniqueException.class)
    public void testAddChildNotUnique() throws Exception {
        System.out.println("addChild");
        String ancestorName = "AncestorName";
        FamilyTree instance = new FamilyTree(ancestorName);
        String partnerName = "PartnerName";
        instance.addPartner(ancestorName, partnerName);
        String childName = "child";
        instance.addChild(childName);
        instance.addChild(childName);
    }
    
     /**
     * Test of addChild method, of class FamilyTree.
     * Method will take an input of a child name
     * Method will check the ancestor value and if it has a partner value
     * If the ancestor value is not null then the code will compare the child name input to the child value of the ancestor
     * If the child name input does not match the child value then it will move onto the sibling values
     * IF the child name input matches the sibling values then it will throw an exception
     */
    @Test (expected = FamilyNotUniqueException.class)
    public void testAddChildWithSiblingNotUnique() throws Exception {
        System.out.println("addChild");
        String ancestorName = "AncestorName";
        FamilyTree instance = new FamilyTree(ancestorName);
        String partnerName = "PartnerName";
        instance.addPartner(ancestorName, partnerName);
        String childName = "child1";
        String siblingName = "child2";
        instance.addChild(childName);
        instance.addChild(siblingName);
        instance.addChild(childName);
    }

    /**
     * Test of toString method, of class FamilyTree.
     * Method will return a toString statement to be printed
     * Method will check if the ancestor has a partner
     * If they do not then it will only print out their name with a text message mentioning the lack of partner
     */
    @Test
    public void testAncestorOnlyToString() {
        System.out.println("toString");
        String ancestorName = "AncestorName";
        FamilyTree instance = new FamilyTree(ancestorName);
        String expResult = "AncestorName, has no partner \n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of toString method, of class FamilyTree.
     * Method will return a toString statement to be printed
     * Method will check if the ancestor has a partner
     * If they do have a partner and no children, then a text message will print
     * The message will have their name, connecting text, and their partner name, then a new line and another line of text
     * This time mentioning the lack of children/descendants
     */
    @Test
    public void testAncestorPartnerOnlyToString() throws Exception {
        System.out.println("toString");
        String ancestorName = "AncestorName";
        String partnerName = "PartnerName";
        FamilyTree instance = new FamilyTree(ancestorName);
        instance.addPartner(ancestorName, partnerName);
        String expResult = "AncestorName, partner is PartnerName\n" + " has no descendants/children \n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class FamilyTree.
     * Method will return a toString statement to be printed
     * Method will check if the ancestor has a partner
     * If they do have a partner and one child, then a text message will print
     * The message will have their name, connecting text, and their partner name, then a new line and another line of text
     * This time with their child's name and that they have no partner
     */
    @Test
    public void testAncestorPartnerOneChildToString() throws Exception {
        System.out.println("toString");
        String ancestorName = "AncestorName";
        String partnerName = "PartnerName";
        FamilyTree instance = new FamilyTree(ancestorName);
        instance.addPartner(ancestorName, partnerName);
        String childName = "ChildName";
        instance.addChild(childName);
        String expResult = "AncestorName, partner is PartnerName\n" + " ChildName, has no partner \n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class FamilyTree.
     * Method will return a toString statement to be printed
     * Method will check if the ancestor has a partner
     * If they do have a partner and one child, then a text message will print
     * The message will have their name, connecting text, and their partner name, then a new line and another line of text
     * This time with their child's name, this time with a partner and text stating no children
     */
    @Test
    public void testAncestorPartnerOneChildPartnerToString() throws Exception {
        System.out.println("toString");
        String ancestorName = "AncestorName";
        String partnerName = "PartnerName";
        FamilyTree instance = new FamilyTree(ancestorName);
        instance.addPartner(ancestorName, partnerName);
        String childName = "ChildName";
        instance.addChild(childName);
        String childPartner = "ChildPartnerName";
        instance.addPartner(childName, childPartner);
        String expResult = "AncestorName, partner is PartnerName\n" + " ChildName, partner is ChildPartnerName\n" + "  has no children/descendants \n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class FamilyTree.
     * Method will return a toString statement to be printed
     * Method will check if the ancestor has a partner
     * If they do have a partner and one child, then a text message will print
     * The message will have their name, connecting text, and their partner name, then a new line and another line of text
     * This time with their child's name and that they have no partner, the code will also include any siblings the child may have
     * The sibling name will be included on a new line with the same text if they also have no partner
     */
    @Test
    public void testAncestorPartnerMultipleChildToString() throws Exception {
        System.out.println("toString");
        String ancestorName = "AncestorName";
        String partnerName = "PartnerName";
        FamilyTree instance = new FamilyTree(ancestorName);
        instance.addPartner(ancestorName, partnerName);
        String childName = "ChildName";
        String siblingName = "SiblingName";
        instance.addChild(childName);
        instance.addChild(siblingName);
        String expResult = "AncestorName, partner is PartnerName\n" + " ChildName, has no partner \n" + " SiblingName, has no partner \n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class FamilyTree.
     * Method will return a toString statement to be printed
     * Method will check if the ancestor has a partner
     * If they do have a partner and one child, then a text message will print
     * The message will have their name, connecting text, and their partner name, then a new line and another line of text
     * This time with their child's name, this time with a partner and text stating no children
     * Also the same will be done with the sibling and their partner
     */
    @Test
    public void testAncestorPartnerMultipleChildPartnerToString() throws Exception {
        System.out.println("toString");
        String ancestorName = "AncestorName";
        String partnerName = "PartnerName";
        FamilyTree instance = new FamilyTree(ancestorName);
        instance.addPartner(ancestorName, partnerName);
        String childName = "ChildName";
        String siblingName = "SiblingName";
        instance.addChild(childName);
        instance.addChild(siblingName);
        String childPartner = "ChildPartnerName";
        String siblingPartner = "SiblingPartnerName";
        instance.addPartner(childName, childPartner);
        instance.addPartner(siblingName, siblingPartner);
        String expResult = "AncestorName, partner is PartnerName\n" + " ChildName, partner is ChildPartnerName\n" + "  has no children/descendants \n" + " SiblingName, partner is SiblingPartnerName\n" + "  has no children/descendants \n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    

    /**
     * Test of getFamilyMember method, of class FamilyTree.
     */
    @Test
    public void testGetFamilyMember() throws Exception {
        System.out.println("getFamilyMember");
        String name = "Name";
        FamilyTree instance = new FamilyTree(name);
        String expResult = "Name, has no partner \n";
        String result = instance.getFamilyMember(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
