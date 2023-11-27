package com.transportSys.actiontable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.transportSys.connection.dbConnection;

/**
 * Utility class to check for foreign key references in the database tables.
 * Helps in verifying if a specific primary key value is referenced in other tables.
 */
public class ForeignKeyChecker {

    /**
     * Default constructor for ForeignKeyChecker class.
     * Constructs a ForeignKeyChecker instance.
     */
    public ForeignKeyChecker() {
        // Default constructor
    }
	
    /**
     * Checks if the provided primary key value is referenced in other tables.
     *
     * @param tableName            The name of the table containing the foreign key references.
     * @param primaryKeyColumnName The column name of the primary key in the referenced table.
     * @param primaryKeyValue      The value of the primary key to check for references.
     * @return True if the primary key value has references in other tables, false otherwise.
     */
    public boolean hasForeignKeyReferences(String tableName, String primaryKeyColumnName, Object primaryKeyValue) {
        Connection myConn = null;
        ResultSet resultSet = null;
        PreparedStatement pst = null;

        try {
            myConn = dbConnection.getConnection();

            // Query to retrieve tables referencing the provided foreign key
            String query = "SELECT TABLE_NAME " +
                    "FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " +
                    "WHERE REFERENCED_TABLE_NAME = ? AND REFERENCED_COLUMN_NAME = ?";

            pst = myConn.prepareStatement(query);
            pst.setString(1, tableName);
            pst.setString(2, primaryKeyColumnName);

            resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String referencingTable = resultSet.getString("TABLE_NAME");
                // Check if the referencing table contains the foreign key value
                if (checkForeignKeyValueExists(referencingTable, primaryKeyColumnName, primaryKeyValue)) {
                    return true; // Reference found, can't delete
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exceptions
        } finally {
            // Close resources and connections
            dbConnection.closeConnection(myConn);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // No references found, can proceed with deletion
        return false;
    }

    /**
     * Checks if the foreign key value exists in the referencing table.
     *
     * @param referencingTable     The table name referencing the primary key.
     * @param primaryKeyColumnName The column name of the primary key.
     * @param primaryKeyValue      The value of the primary key to check in the referencing table.
     * @return True if the primary key value exists in the referencing table, false otherwise.
     */
    private boolean checkForeignKeyValueExists(String referencingTable, String primaryKeyColumnName, Object primaryKeyValue) {
        Connection myConn = null;
        ResultSet resultSet = null;
        PreparedStatement pst = null;

        try {
            myConn = dbConnection.getConnection();

            // Query to check if the primary key value exists in the referencing table
            String query = "SELECT * FROM " + referencingTable + " WHERE " + primaryKeyColumnName + " = ?";

            pst = myConn.prepareStatement(query);
            pst.setObject(1, primaryKeyValue);

            resultSet = pst.executeQuery();

            return resultSet.next(); // If result set has any rows, the value exists
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exceptions
        } finally {
            dbConnection.closeConnection(myConn);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
