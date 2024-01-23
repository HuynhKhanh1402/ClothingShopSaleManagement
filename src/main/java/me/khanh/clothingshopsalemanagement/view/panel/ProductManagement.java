/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.view.panel;

import me.khanh.clothingshopsalemanagement.view.panel.product.EditProductPanel;
import me.khanh.clothingshopsalemanagement.view.panel.product.AddProductPanel;
import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.image.ImageDAO;
import me.khanh.clothingshopsalemanagement.model.ClothingShopSaleManagement;
import me.khanh.clothingshopsalemanagement.model.product.Product;
import me.khanh.clothingshopsalemanagement.model.product.ProductDAO;
import me.khanh.clothingshopsalemanagement.util.ImageUtil;

/**
 * The ProductManagement class is responsible for displaying and managing the
 * list of products in a table format within the dashboard panel.
 *
 *
 * @author ADMIN
 */
public class ProductManagement extends javax.swing.JPanel {

    @Getter
    private final DashboardPanel panel;
    @Getter
    private final ProductDAO productDAO;
    @Getter
    private final Map<Integer, Product> currentProducts = new HashMap<>();

    private boolean showingHint = true;

    /**
     * Constructs a new ProductManagement panel.
     *
     * @param panel The parent dashboard panel to which this product management
     * panel belongs.
     */
    public ProductManagement(DashboardPanel panel) {
        this.panel = panel;
        this.productDAO = ClothingShopSaleManagement.getInstance().getDAOManager().getProductDAO();

        initComponents();

        searchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    filterRows();
                });

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    filterRows();
                });
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        updateProducts();
    }

    /**
     * Fetches the list of products from the database and updates the table with
     * the product information.
     */
    public final void updateProducts() {
        panel.getMainFrame().showLoading();

        productDAO.getAll().thenAcceptAsync((products) -> {
            DefaultTableModel model = new DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Image", "ID", "Name", "Size", "Color", "Stock", "Price"
                    }
            ) {
                Class[] types = new Class[]{
                    ImageIcon.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, Double.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };

            table.setModel(model);

            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
            table.setRowSorter(rowSorter);

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            table.getColumn("ID").setPreferredWidth(34);
            table.getColumn("Name").setPreferredWidth(232);
            table.getColumn("Size").setPreferredWidth(40);
            table.getColumn("Color").setPreferredWidth(51);
            table.getColumn("Stock").setPreferredWidth(40);
            table.getColumn("Price").setPreferredWidth(82);

            table.setCellSelectionEnabled(false);
            table.setRowSelectionAllowed(true);

            table.setRowHeight(64);

            ImageDAO imageDAO = productDAO.getManager().getImageDAO();

            currentProducts.clear();

            for (Product product : products) {
                Optional<ImageIcon> optionalIcon = imageDAO.get(product.getImageId()).join();
                model.addRow(new Object[]{ImageUtil.resize(optionalIcon.orElse(getImageHolder()), 64, 64), product.getId(), product.getName(), product.getSize(), product.getColor(), product.getStock(), product.getPrice()});
                currentProducts.put(product.getId(), product);
            }

            filterRows();

        }).thenRun(panel.getMainFrame()::hideLoading);

    }

    /**
     * Returns a default image icon placeholder for products.
     *
     * @return An ImageIcon instance representing the default product image.
     */
    public ImageIcon getImageHolder() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/no-picture.png"));
        return new ImageIcon(icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addProductBtn = new javax.swing.JButton();
        searchBox = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(640, 440));
        setMinimumSize(new java.awt.Dimension(640, 440));
        setPreferredSize(new java.awt.Dimension(640, 440));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Image", "ID", "Name", "Size", "Color", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
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

        editButton.setBackground(new java.awt.Color(38, 161, 244));
        editButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pen.png"))); // NOI18N
        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(244, 67, 54));
        deleteButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addProductBtn.setBackground(new java.awt.Color(75, 174, 79));
        addProductBtn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        addProductBtn.setForeground(new java.awt.Color(250, 255, 248));
        addProductBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus.png"))); // NOI18N
        addProductBtn.setText("ADD PRODUCT");
        addProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductBtnActionPerformed(evt);
            }
        });

        searchBox.setForeground(java.awt.Color.gray);
        searchBox.setText("Search");
        searchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addGap(134, 134, 134))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addProductBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBox))
                .addGap(12, 12, 12)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private String getSearchText() {
        return showingHint ? "" : searchBox.getText();
    }

    private void searchBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusGained
        if (getSearchText().isEmpty()) {
            searchBox.setText("");
            searchBox.setForeground(Color.BLACK);
            showingHint = false;
        }
    }//GEN-LAST:event_searchBoxFocusGained

    private void searchBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusLost
        if (getSearchText().isEmpty()) {
            searchBox.setText("Search");
            searchBox.setForeground(Color.GRAY);
            showingHint = true;
        }
    }//GEN-LAST:event_searchBoxFocusLost

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select product you want to delete!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int productID = (int) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 1);
        int result = JOptionPane.showConfirmDialog(this, "Do you want to delete product " + productID + "?", "Confirm product deletion", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            productDAO.delete(productID).thenRun(() -> {
                JOptionPane.showMessageDialog(this, "Deleted product: " + productID, "Deleted successfully", JOptionPane.INFORMATION_MESSAGE);
                updateProducts();
            }).handle((r, ex) -> {
                if (ex == null) {
                    return r;
                }
                if (ex.getMessage().contains("a foreign key constraint fails (`clothing_shop`.`orderdetails`, CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`))")) {
                    int confirmResult = JOptionPane.showConfirmDialog(
                            this,
                            "This product is associated with your sales data. Deleting this product will result in a reduction of your revenue data. \nNote: I advise against deleting the product; instead, consider adding a new product to avoid impacting income data.",
                            "Confirm product deletion",
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        productDAO.getManager().getOrderDAO().deleteAllOrderOfProduct(productID).thenCompose((t) -> {
                            return productDAO.delete(productID);
                        }).thenRun(() -> {
                            JOptionPane.showMessageDialog(this, "Deleted product: " + productID, "Deleted successfully", JOptionPane.INFORMATION_MESSAGE);
                            updateProducts();
                        }).exceptionally((t) -> {
                            t.printStackTrace();
                            throw new RuntimeException(t);
                        });
                    }
                    return r;
                } else {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }

            });
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select product you want to edit!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int productID = (int) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 1);
        Product product = currentProducts.get(productID);
        assert product != null;

        JDialog dialog = new JDialog(panel.getMainFrame(), "Edit product: " + productID, true);
        EditProductPanel editProductPanel = new EditProductPanel(dialog, this, product);
        dialog.setContentPane(editProductPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(panel.getMainFrame());
        dialog.setResizable(false);
        dialog.setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void addProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductBtnActionPerformed
        JDialog dialog = new JDialog(panel.getMainFrame(), "Add product", true);
        AddProductPanel addProductPanel = new AddProductPanel(dialog, this);
        dialog.setContentPane(addProductPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(panel.getMainFrame());
        dialog.setResizable(false);
        dialog.setVisible(true);
    }//GEN-LAST:event_addProductBtnActionPerformed

    private void filterRows() {
        String searchText = getSearchText();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + Pattern.quote(searchText), 2);
        ((TableRowSorter<DefaultTableModel>) table.getRowSorter()).setRowFilter(rowFilter);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductBtn;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
