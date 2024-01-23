/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.view.panel.order;

import java.awt.Color;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
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
import me.khanh.clothingshopsalemanagement.util.NumberUtil;
import me.khanh.clothingshopsalemanagement.view.panel.OrderPanel;

/**
 *
 * @author ADMIN
 */
public class ListProductPanel extends javax.swing.JPanel {

    @Getter
    private final JDialog parent;
    @Getter
    private final ProductDAO productDAO;
    private final OrderPanel orderPanel;
    @Getter
    private final Map<Integer, Product> currentProducts = new HashMap<>();

    private boolean showingHint = true;

    /**
     * Creates new form AddOrderPruduct
     *
     * @param parent
     * @param orderPanel
     */
    public ListProductPanel(JDialog parent, OrderPanel orderPanel) {
        initComponents();
        this.parent = parent;
        this.productDAO = ClothingShopSaleManagement.getInstance().getDAOManager().getProductDAO();
        this.orderPanel = orderPanel;

        // Non zero and negative quantity
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1) {
            @Override
            public Object getNextValue() {
                Object nextValue = super.getNextValue();
                return (nextValue.equals(0)) ? super.getNextValue() : nextValue;
            }

            @Override
            public Object getPreviousValue() {
                Object previousValue = super.getPreviousValue();
                return (previousValue == null || previousValue.equals(0)) ? 1 : previousValue;
            }
        };
        quantity.setModel(spinnerModel);

        table.getColumn("ID").setPreferredWidth(34);
        table.getColumn("Name").setPreferredWidth(232);
        table.getColumn("Size").setPreferredWidth(40);
        table.getColumn("Color").setPreferredWidth(51);
        table.getColumn("Stock").setPreferredWidth(40);
        table.getColumn("Price").setPreferredWidth(82);

        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);

        table.setRowHeight(64);

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(rowSorter);
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

        loadProducts();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        searchBox = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        quantity = new javax.swing.JSpinner();

        jPanel1.setMaximumSize(new java.awt.Dimension(640, 440));
        jPanel1.setMinimumSize(new java.awt.Dimension(640, 440));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Image", "ID", "Name", "Size", "Color", "Stock", "Price"
            }
        ) {
            Class[] types = new Class [] {
                ImageIcon.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
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
        table.setColumnSelectionAllowed(true);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setHeaderValue("Image");
            table.getColumnModel().getColumn(1).setHeaderValue("ID");
            table.getColumnModel().getColumn(2).setHeaderValue("Name");
            table.getColumnModel().getColumn(3).setHeaderValue("Size");
            table.getColumnModel().getColumn(4).setHeaderValue("Color");
            table.getColumnModel().getColumn(5).setHeaderValue("Stock");
            table.getColumnModel().getColumn(6).setHeaderValue("Price");
        }

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

        addButton.setBackground(new java.awt.Color(75, 174, 79));
        addButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(250, 255, 248));
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 3, 0));
        jLabel9.setText("Price");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 3, 0));
        jLabel10.setText("Quantity");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadProducts() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        // Reset table
        tableModel.setRowCount(0);
        currentProducts.clear();

        ImageDAO imageDAO = productDAO.getManager().getImageDAO();

        productDAO.getAll().thenAccept((products) -> {
            Set<Integer> existedProducts = orderPanel.getExistedProductIDs();
            for (Product product : products) {
                if (existedProducts.contains(product.getId())) {
                    continue;
                }
                if (product.getStock() < 1) {
                    continue;
                }
                Optional<ImageIcon> optionalIcon = imageDAO.get(product.getImageId()).join();
                tableModel.addRow(new Object[]{ImageUtil.resize(optionalIcon.orElse(getImageHolder()), 64, 64), product.getId(), product.getName(), product.getSize(), product.getColor(), product.getStock(), product.getPrice()});
                currentProducts.put(product.getId(), product);
            }
        }).exceptionally((t) -> {
            t.printStackTrace();
            throw new RuntimeException(t);
        });
    }

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

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select product you want to add!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (price.getText() == null || price.getText().isBlank()) {
            price.setBorder(new LineBorder(Color.RED, 2));
            JOptionPane.showMessageDialog(
                    this,
                    "Product price cannot be blank!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double productPrice;
        if (NumberUtil.isDouble(price.getText())) {
            productPrice = Double.parseDouble(price.getText());
        } else {
            price.setBorder(new LineBorder(Color.RED, 2));
            JOptionPane.showMessageDialog(
                    this,
                    String.format("%s is invalid price!", price.getText()),
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Product product = currentProducts.get((int) table.getModel().getValueAt(table.convertRowIndexToModel(row), 1));
        assert product != null;

        if ((int) quantity.getValue() > product.getStock()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Out of stock!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        orderPanel.addProduct(product, productPrice, (int) quantity.getValue());
        parent.dispose();

    }//GEN-LAST:event_addButtonActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        if (row == -1) {
            return;
        }
        double productPrice = (double) table.getValueAt(row, 6);
        price.setText(new DecimalFormat("#.##").format(productPrice));
    }//GEN-LAST:event_tableMouseClicked

    /**
     * Returns a default image icon placeholder for products.
     *
     * @return An ImageIcon instance representing the default product image.
     */
    public ImageIcon getImageHolder() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/no-picture.png"));
        return new ImageIcon(icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    }

    private void filterRows() {
        String searchText = getSearchText();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + Pattern.quote(searchText), 2);
        ((TableRowSorter<DefaultTableModel>) table.getRowSorter()).setRowFilter(rowFilter);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField price;
    private javax.swing.JSpinner quantity;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
