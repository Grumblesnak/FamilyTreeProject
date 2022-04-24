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
     * Test of getFamilyMember method, of class FamilyTree.
     */
    @Test
    public void testGetFamilyMember() throws Exception {
        System.out.println("getFamilyMember");
        String name = "";
        FamilyTree instance = null;
        String expResult = "";
        String result = instance.getFamilyMember(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
