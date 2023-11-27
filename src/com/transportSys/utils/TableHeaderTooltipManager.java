package com.transportSys.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 * Manages tooltips for columns within a JTable's header. Associates tooltips
 * with specific columns based on the column's name when the mouse is over the
 * header.
 * 
 * @author Zafar Ahmad
 */
public class TableHeaderTooltipManager {

	/**
	 * Default constructor
	 */
	public TableHeaderTooltipManager() {
		// Default constructor - no additional functionality
	}

	/**
	 * Sets up tooltips for table header columns. Associates tooltips with specific
	 * columns based on the column's name when the mouse is over the header.
	 * 
	 * @param table The JTable for which tooltips need to be set up in the header.
	 */
	public static void setupTableHeaderTooltips(JTable table) {
		JTableHeader header = table.getTableHeader(); // Gets the table header
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int col = header.columnAtPoint(e.getPoint()); // Retrieves the column index under the mouse pointer
				if (col != -1) {
					String tooltip = table.getColumnName(col); // Gets the column name for the tooltip
					header.setToolTipText(tooltip); // Sets the tooltip for the header
				} else {
					header.setToolTipText(null); // Clears the tooltip if not over a column
				}
			}
		});
	}
}
