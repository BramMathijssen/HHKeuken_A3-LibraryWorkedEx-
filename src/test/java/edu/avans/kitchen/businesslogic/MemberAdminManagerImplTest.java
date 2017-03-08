/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.businesslogic;

import edu.avans.kitchen.businesslogic.MemberAdminManager;
import edu.avans.kitchen.businesslogic.MemberAdminManagerImpl;
import edu.avans.kitchen.domain.Member;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This test relies on the usage of stubs. The reason is twofold: 1. the test is
 * independent of the state of the database and can therefore easily be added to
 * a regression test. In fact the test does not need a database to be present in
 * order to succeed. 2. to simulate error situations that cannot be created
 * using the production code.
 *
 * To have the test use the stubs instead of the production code: - go to
 * "project properties" - go to "libraries" - go to tab "Run Tests" - move
 * "Compiled Tests" to the top
 *
 * @author Erco
 */
public class MemberAdminManagerImplTest {

    private MemberAdminManager memberAdminManager;
    private Member removableMember;
    private Member nonRemovableMember;
    private Member problemMember;

    public MemberAdminManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        memberAdminManager = new MemberAdminManagerImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void removeMember_path_1_2_3() {
        // test preparation
        // fill members array
        removableMember = memberAdminManager.findMember(1001);
        assertTrue(removableMember != null);

        // test execution
        boolean result = memberAdminManager.removeMember(removableMember);

        // test verification
        assertTrue("removableMember has been removed", result);
        assertEquals(null, memberAdminManager.findMember(1001));
    }

    @Test
    public void removeMember_path_1_4() {
        // test preparation
        // fill members array
        nonRemovableMember = memberAdminManager.findMember(1000);
        assertTrue(nonRemovableMember != null);

        // test execution
        boolean result = memberAdminManager.removeMember(nonRemovableMember);

        // test verification
        assertFalse("nonRemovableMember has not been removed", result);
        Member member = memberAdminManager.findMember(1000);
        assertEquals("Pascal", member.getFirstName());
    }

    @Test
    public void removeMember_path_1_2_5() {
        // test preparation
        // fill members array
        problemMember = memberAdminManager.findMember(1002);
        assertTrue(problemMember != null);

        // test execution
        boolean result = memberAdminManager.removeMember(problemMember);

        // test verification
        assertFalse("problemMember has not been removed", result);
        Member member = memberAdminManager.findMember(1002);
        assertEquals("Marice", member.getFirstName());
    }
}
