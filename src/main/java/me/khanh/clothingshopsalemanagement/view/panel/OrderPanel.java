/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.view.panel;

import java.awt.Color;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.ClothingShopSaleManagement;
import me.khanh.clothingshopsalemanagement.model.order.OrderDAO;
import me.khanh.clothingshopsalemanagement.model.order.OrderedProduct;
import me.khanh.clothingshopsalemanagement.model.product.Product;
import me.khanh.clothingshopsalemanagement.util.NumberUtil;
import me.khanh.clothingshopsalemanagement.view.panel.order.EditSelectedProductPanel;
import me.khanh.clothingshopsalemanagement.view.panel.order.ListProductPanel;

/**
 *
 * @author ADMIN
 */
public class OrderPanel extends javax.swing.JPanel {

    @Getter
    private final DashboardPanel dashboard;
    @Getter
    private final HashMap<Integer, Product> currentProducts = new HashMap<>();
    @Getter
    private final OrderDAO orderDAO;

    private JTextField lastInvalidField;
    private Border lastInvalidFiledBorder;
    private final LineBorder errorBorder = new LineBorder(Color.RED, 2);

    /**
     * Creates new form OrderOanel
     *
     * @param dashboard
     */
    public OrderPanel(DashboardPanel dashboard) {
        initComponents();
        this.dashboard = dashboard;
        this.orderDAO = ClothingShopSaleManagement.getInstance().getDAOManager().getOrderDAO();

        table.getColumn("ID").setPreferredWidth(34);
        table.getColumn("Name").setPreferredWidth(232);
        table.getColumn("Size").setPreferredWidth(40);
        table.getColumn("Color").setPreferredWidth(51);

        calculateTotal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        phoneLabel = new javax.swing.JLabel();
        customerPhone = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        customerName = new javax.swing.JTextField();
        addProductButton = new javax.swing.JButton();
        orderButton = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        totalTextLabel = new javax.swing.JLabel();

        jPanel1.setMaximumSize(new java.awt.Dimension(640, 440));
        jPanel1.setMinimumSize(new java.awt.Dimension(640, 440));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Size", "Color", "QTY", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(table);

        phoneLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        phoneLabel.setText("Customer Phone");

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nameLabel.setText("Customer Name");

        customerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameActionPerformed(evt);
            }
        });

        addProductButton.setBackground(new java.awt.Color(75, 174, 79));
        addProductButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        addProductButton.setForeground(new java.awt.Color(250, 255, 248));
        addProductButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus.png"))); // NOI18N
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        orderButton.setBackground(new java.awt.Color(38, 161, 244));
        orderButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        orderButton.setForeground(new java.awt.Color(255, 255, 255));
        orderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cart.png"))); // NOI18N
        orderButton.setText("ORDER");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        totalLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalLabel.setText("100000000đ");
        totalLabel.setToolTipText("");

        editButton.setBackground(new java.awt.Color(38, 161, 244));
        editButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        editButton.setForeground(new java.awt.Color(250, 255, 248));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pen.png"))); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        removeButton.setBackground(new java.awt.Color(244, 67, 54));
        removeButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        removeButton.setForeground(new java.awt.Color(250, 255, 248));
        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        totalTextLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalTextLabel.setText("Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(totalTextLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(orderButton)
                        .addGap(261, 261, 261))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addProductButton)
                        .addGap(18, 18, 18)
                        .addComponent(editButton)
                        .addGap(18, 18, 18)
                        .addComponent(removeButton)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void customerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameActionPerformed

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed
        JDialog dialog = new JDialog(dashboard.getMainFrame(), "Add product", true);
        ListProductPanel listProductPanel = new ListProductPanel(dialog, this);
        dialog.setContentPane(listProductPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(dashboard.getMainFrame());
        dialog.setResizable(false);
        dialog.setVisible(true);
    }//GEN-LAST:event_addProductButtonActionPerformed

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        if (lastInvalidField != null && lastInvalidFiledBorder != null) {
            lastInvalidField.setBorder(lastInvalidFiledBorder);
        }

        if (isBlank(customerName, "Customer name")) {
            return;
        }

        if (isBlank(customerPhone, "Customer phone")) {
            return;
        }

        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Product list is empty!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        dashboard.getMainFrame().showLoading();

        orderDAO.save(customerName.getText(), customerPhone.getText()).thenApply((orderID) -> {
            List<OrderedProduct> products = new ArrayList<>();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            for (int i = 0; i < table.getRowCount(); i++) {

                Product product = currentProducts.get((int) model.getValueAt(i, 0));
                assert product != null;

                OrderedProduct orderedProduct = new OrderedProduct(orderID, product, (int) model.getValueAt(i, 4), (double) model.getValueAt(i, 5));
                products.add(orderedProduct);
            }
            return new AbstractMap.SimpleEntry<Integer, List<OrderedProduct>>(orderID, products);
        }).thenComposeAsync((entry) -> {
            return orderDAO.saveOrderDetails(entry.getKey(), entry.getValue());
        }).exceptionally((t) -> {
            dashboard.getMainFrame().hideLoading();
            t.printStackTrace();
            throw new RuntimeException(t);
        }).thenRun(() -> {
            dashboard.getMainFrame().hideLoading();
            JOptionPane.showMessageDialog(
                    this,
                    "Order successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            customerName.setText("");
            customerPhone.setText("");
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            calculateTotal();
        });

    }//GEN-LAST:event_orderButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select product you want to edit!",
                    "",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int quantity = (int) table.getModel().getValueAt(row, 4);
        double price = (double) table.getModel().getValueAt(row, 5);
        Product product = currentProducts.get((int) table.getModel().getValueAt(row, 0));
        assert product != null;

        JDialog dialog = new JDialog(dashboard.getMainFrame(), "Add product", true);
        EditSelectedProductPanel espp = new EditSelectedProductPanel(dialog, this, product, price, quantity);
        dialog.setContentPane(espp);
        dialog.pack();
        dialog.setLocationRelativeTo(dashboard.getMainFrame());
        dialog.setResizable(false);
        dialog.setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select product you want to edit!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(row);

        calculateTotal();
    }//GEN-LAST:event_removeButtonActionPerformed

    public void addProduct(Product product, double price, int quantity) {
        if (getExistedProductIDs().contains(product.getId())) {
            throw new RuntimeException(String.format("Product %s already exists!", product.getId()));
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        double total = price * quantity;
        model.addRow(new Object[]{product.getId(), product.getName(), product.getSize(), product.getColor(), quantity, price, total});

        currentProducts.put(product.getId(), product);

        calculateTotal();
    }

    public void editProduct(Product product, double price, int quantity) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (product.getId() == (int) table.getModel().getValueAt(i, 0)) {
                table.getModel().setValueAt(quantity, i, 4);
                table.getModel().setValueAt(price, i, 5);
                table.getModel().setValueAt(price * quantity, i, 6);
                calculateTotal();
                return;
            }
        }
        throw new RuntimeException("Couldn't find product " + product.getId() + " in order list.");
    }

    public Set<Integer> getExistedProductIDs() {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            result.add((Integer) table.getValueAt(i, 0));
        }
        return result;
    }

    private void calculateTotal() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        double total = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            total += (double) model.getValueAt(i, 6);
        }
        totalLabel.setText(NumberUtil.format(total) + "₫");
    }

    private boolean isBlank(JTextField textField, String field) {
        if (textField.getText() == null || textField.getText().isBlank()) {
            lastInvalidField = textField;
            lastInvalidFiledBorder = textField.getBorder();
            textField.setBorder(errorBorder);
            JOptionPane.showMessageDialog(
                    this,
                    String.format("%s cannot be blank!", field),
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductButton;
    private javax.swing.JTextField customerName;
    private javax.swing.JTextField customerPhone;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton orderButton;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JButton removeButton;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalTextLabel;
    // End of variables declaration//GEN-END:variables
}
