package com.transportSys.dashboard;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.transportSys.customers.CustomersWindow;
import com.transportSys.employees.EmployeesWindow;
import com.transportSys.offices.OfficesWindow;
import com.transportSys.orderdetails.OrderDetailsWindow;
import com.transportSys.orders.OrdersWindow;
import com.transportSys.payments.PaymentsWindow;
import com.transportSys.productlines.ProductLinesWindow;
import com.transportSys.products.ProductsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * The main Dashboard frame for the application.
 */
public class Dashboard  extends JFrame {
    /**
	 * The serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	 /**
     * Constructs the Dashboard frame.
     * Initializes and sets up the main user interface components,
     * including buttons, panels, and window properties like title and icons.
     */
    public Dashboard () {
        setForeground(new Color(10, 21, 38));
        setBackground(new Color(10, 21, 38));
        getContentPane().setBackground(new Color(10, 21, 38));

        // Set the title
        setTitle("Dashboard");

     // Load the image icon
        ImageIcon icon = new ImageIcon("images/TranzitTransparentLogo.png");
        setIconImage(icon.getImage());

        // Create a panel for the logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));

        // Create a top section with dark blue background
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(10, 21, 38));
        topPanel.setPreferredSize(new Dimension(1549, 400)); // Adjust height as needed
        logoPanel.add(topPanel);

        // Create the label for the logo
        JLabel logoLabel = new JLabel(icon);
        logoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        logoLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        logoPanel.add(logoLabel);

             
        // Create buttons
     // Create btnOffice button
        JButton btnOffice = new JButton("Offices");
        btnOffice.setBackground(new Color(10, 21, 38));
        btnOffice.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnOffice.setForeground(Color.WHITE);
        btnOffice.setFocusPainted(false);
        // Add action listener
        btnOffice.addActionListener(e -> openOfficesWindow());

        JButton btnEmployees = new JButton("Employees");
        btnEmployees.setBackground(new Color(10, 21, 38));
        btnEmployees.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnEmployees.setForeground(Color.WHITE);
        btnEmployees.setFocusPainted(false);
        // Add action listener
        btnEmployees.addActionListener(e -> openEmployeesWindow());

        JButton btnProductLines = new JButton("Product Lines");
        btnProductLines.setBackground(new Color(10, 21, 38));
        btnProductLines.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnProductLines.setForeground(Color.WHITE);
        btnProductLines.setFocusPainted(false);
        // Add action listener
        btnProductLines.addActionListener(e -> openProductLinesWindow());
        
        JButton btnOrderdetails = new JButton("Order Details");
        btnOrderdetails.setBackground(new Color(10, 21, 38));
        btnOrderdetails.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnOrderdetails.setForeground(Color.WHITE);
        btnOrderdetails.setFocusPainted(false);
        // Add action listener
        btnOrderdetails.addActionListener(e -> openOrderDetailsWindow());


        JButton btnPayments = new JButton("Payments");
        btnPayments.setBackground(new Color(10, 21, 38));
        btnPayments.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnPayments.setForeground(Color.WHITE);
        btnPayments.setFocusPainted(false);
        // Add action listener
        btnPayments.addActionListener(e -> openPaymentsWindow());

        JButton btnOrders = new JButton("Orders");
        btnOrders.setBackground(new Color(10, 21, 38));
        btnOrders.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnOrders.setForeground(Color.WHITE);
        btnOrders.setFocusPainted(false);
        // Add action listener
        btnOrders.addActionListener(e -> openOrdersWindow());
        
        JButton btnCustomers = new JButton("Customers");
        btnCustomers.setBackground(new Color(10, 21, 38));
        btnCustomers.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCustomers.setForeground(Color.WHITE);
        btnCustomers.setFocusPainted(false);
        // Add action listener
        btnCustomers.addActionListener(e -> openCustomersWindow());
        
        JButton btnProducts = new JButton("Products");
        btnProducts.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnProducts.setForeground(Color.WHITE);
        btnProducts.setBackground(new Color(10, 21, 38));
        btnProducts.setFocusPainted(false);
        // Add action listener
        btnProducts.addActionListener(e -> openProductsWindow());


        // Set preferred size for each button
        btnOffice.setPreferredSize(new Dimension(150, 60));
        btnEmployees.setPreferredSize(new Dimension(150, 60));
        btnProductLines.setPreferredSize(new Dimension(150, 60));
        btnOrderdetails.setPreferredSize(new Dimension(150, 60));
        btnPayments.setPreferredSize(new Dimension(150, 60));
        btnOrders.setPreferredSize(new Dimension(150, 60));
        btnCustomers.setPreferredSize(new Dimension(150, 60));
        btnProducts.setPreferredSize(new Dimension(150, 60));
     // Existing code for creating buttonPanel
        JPanel buttonPanel = new JPanel(); // Use FlowLayout
        buttonPanel.setBackground(new Color(10, 21, 38));

        // Add padding to the bottom of the buttonPanel
        int bottomPadding = 50; // Set the desired bottom padding
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, bottomPadding, 0));



        // Create a layout for the frame
        getContentPane().setLayout(new BorderLayout());

        // Add the logo and button panels to the frame
        getContentPane().add(logoPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        JLabel lblOrdersIcon = new JLabel();
        lblOrdersIcon.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblCustPayIcon = new JLabel();
        lblCustPayIcon.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblcompanyIcon = new JLabel();
        lblcompanyIcon.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblProductsIcon = new JLabel();
        lblProductsIcon.setHorizontalAlignment(SwingConstants.CENTER);
        
     // Load the icons for the labels
        ImageIcon icon1 = new ImageIcon("images/orders.png");
        ImageIcon icon2 = new ImageIcon("images/customers.png");
        ImageIcon icon3 = new ImageIcon("images/office.png");
        ImageIcon icon4 = new ImageIcon("images/product lines.png");

        // Icon for Order and Orderdetails
        lblOrdersIcon.setIcon(icon1);

        // Icon for Customers and Payments
        lblCustPayIcon.setIcon(icon2);

        // Icon for Offices and Employees
        lblcompanyIcon.setIcon(icon3);

        // Icon for Products and Productlines
        lblProductsIcon.setIcon(icon4);

        
        GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
        gl_buttonPanel.setHorizontalGroup(
        	gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_buttonPanel.createSequentialGroup()
        			.addGap(446)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_buttonPanel.createSequentialGroup()
        					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnOrders, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnOrderdetails, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        					.addGap(19))
        				.addGroup(gl_buttonPanel.createSequentialGroup()
        					.addComponent(lblOrdersIcon, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        					.addGap(28)))
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnPayments, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCustomers, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_buttonPanel.createSequentialGroup()
        					.addGap(9)
        					.addComponent(lblCustPayIcon, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
        			.addGap(10)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        					.addComponent(lblcompanyIcon, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        					.addComponent(btnEmployees, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        				.addComponent(btnOffice, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(btnProducts, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblProductsIcon, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        				.addComponent(btnProductLines, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        			.addGap(340))
        );
        gl_buttonPanel.setVerticalGroup(
        	gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_buttonPanel.createSequentialGroup()
        			.addGap(36)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblProductsIcon, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblcompanyIcon, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        				.addComponent(lblOrdersIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblCustPayIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnOrders, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnProducts, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        				.addComponent(btnCustomers, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        				.addComponent(btnOffice, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_buttonPanel.createParallelGroup(Alignment.BASELINE)
        					.addComponent(btnEmployees, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        					.addComponent(btnProductLines, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        				.addGroup(gl_buttonPanel.createParallelGroup(Alignment.BASELINE)
        					.addComponent(btnOrderdetails, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        					.addComponent(btnPayments, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
        			.addGap(77))
        );
        buttonPanel.setLayout(gl_buttonPanel);

        // Set frame properties
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a minimum size for the frame
        setMinimumSize(new Dimension(1420, 750));

        // Add a WindowListener to handle window events
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                // Check if the window is iconified
                if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    // When the window is minimized, restore it to the center
                    setExtendedState(JFrame.NORMAL);
                    setLocationRelativeTo(null);
                }
            }
        });

        setVisible(true);
    }
    
    
    // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openOfficesWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                OfficesWindow officesWindow = new OfficesWindow();
                officesWindow.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
 // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openEmployeesWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                EmployeesWindow employeesWindow = new EmployeesWindow();
                employeesWindow.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
    
    // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openProductLinesWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                ProductLinesWindow productLines = new ProductLinesWindow();
                productLines.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
 // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openProductsWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                ProductsWindow productsWindow = new ProductsWindow();
                productsWindow.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
    // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openOrdersWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                OrdersWindow ordersWindow = new OrdersWindow();
                ordersWindow.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
 // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openOrderDetailsWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                OrderDetailsWindow ordersDetailsWindow = new OrderDetailsWindow();
                ordersDetailsWindow.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
    
    // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openCustomersWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                CustomersWindow customersWindow = new CustomersWindow();
                customersWindow.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
 // Method to open OfficesWindow
    @SuppressWarnings("deprecation")
	private void openPaymentsWindow() {
        EventQueue.invokeLater(() -> {
            try {
                // Set the FlatLaf light theme
                FlatLightLaf.install();

                // Create OfficesWindow instance after setting the theme
                PaymentsWindow paymentsWindow = new PaymentsWindow();
                paymentsWindow.frame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
    /**
     * The main method to start the application.
     * Sets the look and feel to FlatDarkLaf and invokes the Dashboard GUI.
     *
     * @param args Command line arguments (not used in this context).
     */
    public static void main(String[] args) {
        try {
            // Preserve existing colors and set the look and feel to FlatDarkLaf
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create and display the Dashboard GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new Dashboard());
    }

}