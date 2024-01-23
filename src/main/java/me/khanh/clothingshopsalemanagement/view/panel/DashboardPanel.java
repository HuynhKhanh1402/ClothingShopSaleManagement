/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.view.panel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import lombok.Getter;
import lombok.Setter;
import me.khanh.clothingshopsalemanagement.model.authentication.Permission;
import me.khanh.clothingshopsalemanagement.model.authentication.User;
import me.khanh.clothingshopsalemanagement.view.MainFrame;

/**
 * The DashboardPanel class represents the main dashboard interface for the
 * clothing shop sale management system. This panel provides navigation and
 * displays essential information about the logged-in user.
 *
 * @author ADMIN
 */
public class DashboardPanel extends javax.swing.JPanel {

    /**
     * The getter for the main frame of the application.
     */
    @Getter
    private final MainFrame mainFrame;
    /**
     * The getter for currently logged-in user.
     */
    @Getter
    @Setter
    private User user;
    /**
     * The getter for panel that holds the main content to be displayed within
     * the dashboard.
     */
    @Getter
    private JPanel contentPanel;

    private JButton currentSelectedButton = null;

    /**
     * Constructs a new DashboardPanel.
     *
     * @param mainFrame The main frame of the application.
     * @param user The currently logged-in user.
     */
    public DashboardPanel(MainFrame mainFrame, User user) {
        this.mainFrame = mainFrame;
        this.user = user;
        initComponents();

        usernameLabel.setText(user.getUsername());
        permissionLabel.setText(user.getPermission().toString());
        welcomeMessage.setText(welcomeMessage.getText().replace("{name}", user.getFullName()));

        EmptyPanel emptyPanel = new EmptyPanel();
        setContentPanel(emptyPanel);

        if (user.getPermission().equals(Permission.MODERATOR)) {
            manageUserButton.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        shopSaleManagementLabel = new javax.swing.JLabel();
        shopIcon = new javax.swing.JLabel();
        accountIcon = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        logoutIcon = new javax.swing.JLabel();
        logoutLabel = new javax.swing.JLabel();
        permissionLabel = new javax.swing.JLabel();
        NavPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        dashboardIconLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        revenueButton = new javax.swing.JButton();
        manageUserButton = new javax.swing.JButton();
        manageProductButton = new javax.swing.JButton();
        orderButton = new javax.swing.JButton();
        manageOrdersButton = new javax.swing.JButton();
        welcomeMessage = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(244, 244, 244));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        setMaximumSize(new java.awt.Dimension(848, 480));
        setMinimumSize(new java.awt.Dimension(848, 480));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(848, 480));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(62, 194, 151));

        shopSaleManagementLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        shopSaleManagementLabel.setForeground(new java.awt.Color(255, 255, 255));
        shopSaleManagementLabel.setText("Shop Sale Management");

        shopIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cashier.png"))); // NOI18N

        accountIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/account.png"))); // NOI18N

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("username");

        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png"))); // NOI18N
        logoutIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutIconMouseClicked(evt);
            }
        });

        logoutLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        logoutLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoutLabel.setText("Logout");
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutLabelMouseClicked(evt);
            }
        });

        permissionLabel.setForeground(new java.awt.Color(255, 255, 255));
        permissionLabel.setText("permission");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(shopIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shopSaleManagementLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251)
                .addComponent(accountIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(permissionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(logoutIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(accountIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shopIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(shopSaleManagementLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(permissionLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(logoutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(logoutIcon))
        );

        add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        NavPanel.setBackground(new java.awt.Color(41, 58, 72));

        jPanel3.setBackground(new java.awt.Color(102, 101, 247));

        dashboardIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard.png"))); // NOI18N
        dashboardIconLabel.setText("");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DASHBOARD");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(dashboardIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        revenueButton.setBackground(new java.awt.Color(41, 58, 72));
        revenueButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        revenueButton.setForeground(new java.awt.Color(220, 220, 220));
        revenueButton.setText("Revenue");
        revenueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revenueButtonActionPerformed(evt);
            }
        });

        manageUserButton.setBackground(new java.awt.Color(41, 58, 72));
        manageUserButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageUserButton.setForeground(new java.awt.Color(220, 220, 220));
        manageUserButton.setText("Manage Users");
        manageUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUserButtonActionPerformed(evt);
            }
        });

        manageProductButton.setBackground(new java.awt.Color(41, 58, 72));
        manageProductButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageProductButton.setForeground(new java.awt.Color(220, 220, 220));
        manageProductButton.setText("Manage Products");
        manageProductButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageProductButtonMouseClicked(evt);
            }
        });

        orderButton.setBackground(new java.awt.Color(41, 58, 72));
        orderButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        orderButton.setForeground(new java.awt.Color(220, 220, 220));
        orderButton.setText("Order");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        manageOrdersButton.setBackground(new java.awt.Color(41, 58, 72));
        manageOrdersButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageOrdersButton.setForeground(new java.awt.Color(220, 220, 220));
        manageOrdersButton.setText("Manage Orders");
        manageOrdersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageOrdersButtonActionPerformed(evt);
            }
        });

        welcomeMessage.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        welcomeMessage.setForeground(new java.awt.Color(255, 255, 255));
        welcomeMessage.setText("Welcome {name}");

        javax.swing.GroupLayout NavPanelLayout = new javax.swing.GroupLayout(NavPanel);
        NavPanel.setLayout(NavPanelLayout);
        NavPanelLayout.setHorizontalGroup(
            NavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(NavPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(revenueButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageProductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(manageOrdersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(NavPanelLayout.createSequentialGroup()
                        .addComponent(welcomeMessage)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        NavPanelLayout.setVerticalGroup(
            NavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavPanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(welcomeMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(manageUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(manageProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(manageOrdersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(revenueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        add(NavPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, -1, 470));
    }// </editor-fold>//GEN-END:initComponents

    private void logoutIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutIconMouseClicked
        handleLogout();
    }//GEN-LAST:event_logoutIconMouseClicked

    private void logoutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseClicked
        handleLogout();
    }//GEN-LAST:event_logoutLabelMouseClicked

    private void manageProductButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageProductButtonMouseClicked
        if (!handleButtonClick(manageProductButton)) {
            return;
        }
        setContentPanel(new ProductManagement(this));
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }//GEN-LAST:event_manageProductButtonMouseClicked

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        if (!handleButtonClick(orderButton)) {
            return;
        }
        mainFrame.hideLoading();
        setContentPanel(new OrderPanel(this));
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }//GEN-LAST:event_orderButtonActionPerformed

    private void manageUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUserButtonActionPerformed
        if (!handleButtonClick(manageUserButton)) {
            return;
        }
        setContentPanel(new UserManagementPanel(this));
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }//GEN-LAST:event_manageUserButtonActionPerformed

    private void manageOrdersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageOrdersButtonActionPerformed
        if (!handleButtonClick(manageOrdersButton)) {
            return;
        }
        setContentPanel(new OrderManagementPanel(this));
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }//GEN-LAST:event_manageOrdersButtonActionPerformed

    private void revenueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revenueButtonActionPerformed
        if (!handleButtonClick(revenueButton)) {
            return;
        }
        setContentPanel(new RevenuePanel(this));
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }//GEN-LAST:event_revenueButtonActionPerformed

    /**
     * Sets the content panel to display within the dashboard.
     *
     * @param panel The panel to be set as the content.
     */
    public final void setContentPanel(JPanel panel) {
        if (contentPanel != null) {
            remove(contentPanel);
        }
        contentPanel = panel;
        add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 640, 440));
    }

    /**
     * Handles the logout action by changing the main frame's content to the
     * login panel.
     */
    private void handleLogout() {
        LoginPanel panel = new LoginPanel(mainFrame);
        mainFrame.setContentPane(panel);
        mainFrame.pack();
    }

    /**
     * Updates the displayed permission label based on the user's current
     * permission level.
     */
    public void updateUserInfo() {
        permissionLabel.setText(user.getPermission().toString());
        if ((user.getPermission().equals(Permission.MODERATOR) && (manageUserButton == currentSelectedButton))) {
            manageUserButton.setEnabled(false);
            currentSelectedButton = null;
            EmptyPanel emptyPanel = new EmptyPanel();
            setContentPanel(emptyPanel);
        }
        welcomeMessage.setText("Welcome " + user.getFullName());
    }

    public void openOrderPanel() {
        mainFrame.hideLoading();
        setContentPanel(new OrderPanel(this));
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }

    public boolean handleButtonClick(JButton clickedButton) {
        if (clickedButton == currentSelectedButton) {
            return false;
        }
        currentSelectedButton = clickedButton;

        clickedButton.setBackground(new Color(60, 84, 104));

        Color defaultColor = new Color(41, 58, 72);

        if (manageUserButton != clickedButton) {
            manageUserButton.setBackground(defaultColor);
        }
        if (manageProductButton != clickedButton) {
            manageProductButton.setBackground(defaultColor);
        }
        if (orderButton != clickedButton) {
            orderButton.setBackground(defaultColor);
        }
        if (manageOrdersButton != clickedButton) {
            manageOrdersButton.setBackground(defaultColor);
        }
        if (revenueButton != clickedButton) {
            revenueButton.setBackground(defaultColor);
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NavPanel;
    private javax.swing.JLabel accountIcon;
    private javax.swing.JLabel dashboardIconLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JLabel logoutLabel;
    private javax.swing.JButton manageOrdersButton;
    private javax.swing.JButton manageProductButton;
    private javax.swing.JButton manageUserButton;
    private javax.swing.JButton orderButton;
    private javax.swing.JLabel permissionLabel;
    private javax.swing.JButton revenueButton;
    private javax.swing.JLabel shopIcon;
    private javax.swing.JLabel shopSaleManagementLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel welcomeMessage;
    // End of variables declaration//GEN-END:variables

}