package com.transportSys.actiontable;

import com.transportSys.connection.dbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Represents an action for adding new records to a JTable and a database table.
 * This class extends JButton to act as a button triggering the addition
 * process.
 * 
 * The AddAction class provides functionalities like: - Validating and
 * restricting input in specific JTextField components - Refreshing the
 * associated JTable after data insertion - Handling ActionListener for adding
 * new records - Inserting data into the specified database table - Displaying
 * UI components for data entry and verification
 * 
 * @author Joakim Evensen, Zafar Ahmad
 */

public class AddAction extends JButton {
	/**
	 * The serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The table where new data will be added
	 */
	private JTable table;

	/**
	 * The name of the table where data will be inserted
	 */
	private String tableName;

	/**
	 * The primary key column name in the table
	 */
	private String primaryKey;

	/**
	 * Constructs an instance of AddAction with specified parameters.
	 *
	 * @param text       The text label for the button.
	 * @param table      The JTable where new data will be added.
	 * @param primaryKey The primary key column name in the table.
	 * @param tableName  The name of the table where data will be inserted.
	 * 
	 */
	public AddAction(String text, JTable table, String primaryKey, String tableName) {
		super(text);
		this.table = table;
		this.tableName = tableName;
		this.primaryKey = primaryKey;
		addActionListener(new AddRecordActionListener());
	}

	// Restricts input to allow only numeric characters in the provided JTextField.
	private void restrictInputToNumbers(JTextField textField) {
		// DocumentFilter is employed to validate and modify document content.
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				// Validates whether the input string contains only numeric characters.
				if (string.matches("[0-9]*")) {
					super.insertString(fb, offset, string, attr);
				} else {
					// Notifies the user of the error when non-numeric characters are entered.
					JOptionPane.showMessageDialog(null, "This field accepts only numbers");
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				// Validates whether the replacement text contains only numeric characters.
				if (text.matches("[0-9]*")) {
					super.replace(fb, offset, length, text, attrs);
				} else {
					// Notifies the user of the error when non-numeric characters are entered during
					// replacement.
					JOptionPane.showMessageDialog(null, "This field accepts only numbers");
				}
			}
		});
	}

	// Restricts input to allow only decimal characters in the provided JTextField.
	private void restrictInputToDecimal(JTextField textField) {
		// DocumentFilter is utilized to validate and modify document content.
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				// Validates whether the input string contains only numeric and decimal
				// characters.
				if (string.matches("[0-9.]*")) {
					super.insertString(fb, offset, string, attr);
				} else {
					// Notifies the user of the error when non-numeric or non-decimal characters are
					// entered.
					JOptionPane.showMessageDialog(null, "This field accepts only numbers and a decimal point");
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				// Validates whether the replacement text contains only numeric and decimal
				// characters.
				if (text.matches("[0-9.]*")) {
					super.replace(fb, offset, length, text, attrs);
				} else {
					// Notifies the user of the error when non-numeric or non-decimal characters are
					// entered during replacement.
					JOptionPane.showMessageDialog(null, "This field accepts only numbers and a decimal point");
				}
			}
		});
	}

	// Refreshes the JTable by fetching data from the associated database table.
	private void refreshTable() throws SQLException {
		// This method refreshes the table by retrieving and updating data from the
		// database.
		int[] columnWidths = new int[table.getColumnCount()];
		for (int i = 0; i < table.getColumnCount(); i++) {
			columnWidths[i] = table.getColumnModel().getColumn(i).getWidth();
		}

		Connection myConn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			myConn = dbConnection.getConnection();
			pst = myConn.prepareStatement("SELECT * FROM " + tableName + " ORDER BY " + primaryKey + " DESC");

			rs = pst.executeQuery();

			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getObject(i));
				}
				data.add(row);
			}

			Vector<String> columns = new Vector<String>();
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				columns.add(rs.getMetaData().getColumnName(i));
			}

			DefaultTableModel model = new DefaultTableModel(data, columns);
			table.setModel(model);

		} finally {
			dbConnection.closeConnection(myConn);
			if (pst != null) {
				pst.close();
			}
			if (rs != null) {
				rs.close();
			}
		}

		for (int i = 0; i < table.getColumnCount() && i < columnWidths.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
	}

	// Retrieves the foreign key associated with the provided table.
	private String getForeignKey() {
		// This method retrieves the foreign key linked with the provided table.
		Connection myConn = null;
		ResultSet rs = null;
		try {
			myConn = dbConnection.getConnection();
			ResultSet foreignKeys = myConn.getMetaData().getImportedKeys(myConn.getCatalog(), null, tableName);
			while (foreignKeys.next()) {
				String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
				return fkColumnName; // Return the first foreign key found
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection(myConn);
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null; // Return null if no foreign key is found
	}

	// Inserts data into the specified database table using the provided column
	// data.
	private void insertData(String tableName, Map<String, Object> columnData) throws SQLException {
		// This method inserts data into the specified database table using the provided
		// column data.
		Connection myConn = null;
		PreparedStatement pst = null;

		try {
			myConn = dbConnection.getConnection();

			StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
			queryBuilder.append(tableName).append(" (");

			for (String columnName : columnData.keySet()) {
				queryBuilder.append(columnName).append(", ");
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(") VALUES (");

			for (int i = 0; i < columnData.size(); i++) {
				queryBuilder.append("?, ");
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(")");

			pst = myConn.prepareStatement(queryBuilder.toString());

			int parameterIndex = 1;
			for (Object columnValue : columnData.values()) {
				pst.setObject(parameterIndex++, columnValue);
			}

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected <= 0) {
				JOptionPane.showMessageDialog(null, "No data added. Please check your inputs.");
			}
		} finally {
			dbConnection.closeConnection(myConn);
			if (pst != null) {
				pst.close();
			}
		}
	}

	// ActionListener responsible for adding a new record to the database.
	private class AddRecordActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// This ActionListener handles the addition of a new record to the database.
			Map<String, Object> rowData = new HashMap<>();

			// Construct the input fields outside the loop
			Map<String, JComponent> columnTextFields = new HashMap<>();
			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;

			Connection myConn = null;
			try {
				myConn = dbConnection.getConnection();
				String catalog = myConn.getCatalog(); // Get the current catalog
				ResultSet rs = myConn.getMetaData().getColumns(catalog, null, tableName, null);

				panel.removeAll(); // Clear the panel before adding components

				while (rs.next()) {
					String columnName = rs.getString("COLUMN_NAME");
					if (columnTextFields.containsKey(columnName)) {
						continue; // Skip if we've already processed this column
					}
					String columnType = rs.getString("TYPE_NAME");
					JComponent inputComponent;

					if (columnName.contains("Description")) {
						JTextArea textArea = new JTextArea(3, 20);
						textArea.setWrapStyleWord(true);
						textArea.setLineWrap(true);
						JScrollPane scrollPane = new JScrollPane(textArea);
						scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						inputComponent = textArea;
						panel.add(new JLabel(columnName), gbc);
						panel.add(scrollPane, gbc);
					} else {
						JTextField textField = new JTextField(20);
						inputComponent = textField;
						panel.add(new JLabel(columnName), gbc);
						panel.add(textField, gbc);

						// Check column type and apply appropriate restriction
						if (columnType.equalsIgnoreCase("INT") || columnType.equalsIgnoreCase("TINYINT")
								|| columnType.equalsIgnoreCase("SMALLINT") || columnType.equalsIgnoreCase("MEDIUMINT")
								|| columnType.equalsIgnoreCase("BIGINT")) {
							restrictInputToNumbers(textField);
						} else if (columnType.equalsIgnoreCase("DECIMAL") || columnType.equalsIgnoreCase("FLOAT")
								|| columnType.equalsIgnoreCase("DOUBLE") || columnType.equalsIgnoreCase("REAL")) {
							restrictInputToDecimal(textField);
						}
					}

					columnTextFields.put(columnName, inputComponent);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				dbConnection.closeConnection(myConn);
			}

			boolean validInput = false;
			while (!validInput) {
				int result = JOptionPane.showConfirmDialog(null, panel, "Add New Record", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					boolean allFieldsEmpty = true;
					StringBuilder dataSummary = new StringBuilder("<html>Data to be saved:<br>");

					for (Map.Entry<String, JComponent> entry : columnTextFields.entrySet()) {
						String value = "";

						if (entry.getValue() instanceof JTextArea) {
							value = ((JTextArea) entry.getValue()).getText();
						} else {
							value = ((JTextField) entry.getValue()).getText();
						}

						dataSummary.append("<b>").append(entry.getKey()).append("</b>: ").append(value).append("<br>");

						if (!value.trim().isEmpty()) {
							allFieldsEmpty = false;
						}
					}

					if (allFieldsEmpty) {
						JOptionPane.showMessageDialog(null, "No data detected.");
						continue;
					}

					JEditorPane editorPane = new JEditorPane("text/html", dataSummary.toString());
					editorPane.setEditable(false);
					editorPane.setBackground(UIManager.getColor("Panel.background"));

					int confirmation = JOptionPane.showOptionDialog(null, editorPane, "Confirmation",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "Save", "Go Back" }, "Save");

					if (confirmation == 1) { // "Go Back" option
						continue;
					}

					// Populate rowData for insertion
					for (Map.Entry<String, JComponent> entry : columnTextFields.entrySet()) {
						String value = "";

						if (entry.getValue() instanceof JTextArea) {
							value = ((JTextArea) entry.getValue()).getText();
						} else {
							value = ((JTextField) entry.getValue()).getText();
						}

						rowData.put(entry.getKey(), value);
					}

					try {
						insertData(tableName, rowData);
						JOptionPane.showMessageDialog(null, "Data added successfully!");
						refreshTable();
						validInput = true;
					} catch (SQLException ex) {
						if (ex.getMessage().contains("Duplicate entry")) {
							JOptionPane.showMessageDialog(null, "The primary key already exists, write a new one.");
						} else if (ex.getMessage().contains("foreign key constraint fails")) {
							String foreignKey = getForeignKey();
							JOptionPane.showMessageDialog(null, "Check if " + foreignKey + " already exists.");
						} else if (ex.getMessage().contains("Incorrect integer value")) {
							JOptionPane.showMessageDialog(null,
									"Please ensure all number fields are filled correctly.");
						} else {
							JOptionPane.showMessageDialog(null, "Error inserting data: " + ex.getMessage());
						}
					}
				} else {
					validInput = true; // Exit the loop if the user clicks 'Cancel'
				}
			}
		}
	}
}
