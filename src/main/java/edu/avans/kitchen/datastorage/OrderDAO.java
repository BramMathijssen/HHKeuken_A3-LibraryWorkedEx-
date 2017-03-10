package edu.avans.kitchen.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.avans.kitchen.domain.Order;

public class OrderDAO {

    public OrderDAO() {
        // Nothing to be initialized. This is a stateless class. Constructor
        // has been added to explicitely make this clear.
    }

    /**
     * Tries to find the member identified by the given membership number in the
     * persistent data store, in this case a MySQL database. All loans and
     * reservations for the member are loaded as well. In this POC, the reserved
     * books and/or lend copies of the books are not loaded - it is out of scope
     * for now.
     *
     * @param membershipNumber identifies the member to be loaded from the
     * database
     *
     * @return the Member object to be found. In case member could not be found,
     * null is returned.
     */
    public List<Order> findAcceptedOrders() {
        Order order = null;

        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                    "SELECT * FROM member WHERE MembershipNumber = " + membershipNumber + ";");

            if (resultset != null) {
                try {
                    // The membershipnumber for a member is unique, so in case the
                    // resultset does contain data, we need its first entry.
                    if (resultset.next()) {
                        int membershipNumberFromDb = resultset.getInt("MembershipNumber");
                        String firstNameFromDb = resultset.getString("FirstName");
                        String lastNameFromDb = resultset.getString("LastName");

                        member = new Member(
                                membershipNumberFromDb,
                                firstNameFromDb,
                                lastNameFromDb);

                        member.setStreet(resultset.getString("Street"));
                        member.setHouseNumber(resultset.getString("HouseNumber"));
                        member.setCity(resultset.getString("City"));
                        member.setFine(resultset.getDouble("Fine"));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    member = null;
                }
            }
            // else an error occurred leave 'member' to null.

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }

        return member;
    }

    /**
     * Removes the given member from the database.
     *
     * @param memberToBeRemoved an object of the Member class representing the
     * member to be removed.
     *
     * @return true if execution of the SQL-statement was successful, false
     * otherwise.
     */
    public boolean removeMember(Member memberToBeRemoved) {
        boolean result = false;

        if (memberToBeRemoved != null) {
            // First open the database connection.
            DatabaseConnection connection = new DatabaseConnection();
            if (connection.openConnection()) {
                // Execute the delete statement using the membership number to
                // identify the member row.
                result = connection.executeSqlDmlStatement(
                        "DELETE FROM member WHERE MembershipNumber = " + memberToBeRemoved.getMembershipNumber() + ";");

                // Finished with the connection, so close it.
                connection.closeConnection();
            }
            // else an error occurred leave 'member' to null.
        }

        return result;
    }
}