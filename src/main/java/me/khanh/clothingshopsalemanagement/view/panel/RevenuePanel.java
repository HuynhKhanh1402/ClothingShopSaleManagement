/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.view.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.ClothingShopSaleManagement;
import me.khanh.clothingshopsalemanagement.model.order.OrderDAO;
import me.khanh.clothingshopsalemanagement.model.order.ViewMode;
import me.khanh.clothingshopsalemanagement.util.NumberUtil;
import me.khanh.clothingshopsalemanagement.view.chart.RevenueChartData;
import me.khanh.clothingshopsalemanagement.view.chart.RevenueMonthy;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author ADMIN
 */
public class RevenuePanel extends javax.swing.JPanel {

    @Getter
    private final DashboardPanel dashboard;
    @Getter
    private final OrderDAO orderDAO;

    /**
     * Creates new form RevenuePanel
     *
     * @param dashboard
     */
    public RevenuePanel(DashboardPanel dashboard) {
        initComponents();
        this.dashboard = dashboard;
        this.orderDAO = ClothingShopSaleManagement.getInstance().getDAOManager().getOrderDAO();

        dashboard.getMainFrame().hideLoading();

        revenuePanel.setBorder(new RoundedBorder());
        orderedProductPanel.setBorder(new RoundedBorder());

        revenueViewType.addItemListener((ItemEvent e) -> {
            updateRevenueValue();
        });

        orderedProductViewType.addItemListener((ItemEvent e) -> {
            updateOrderedProductValue();
        });

        updateRevenueValue();
        updateOrderedProductValue();

        orderDAO.getRevenueChartData().thenAccept((t) -> {
            showChart(t);
        }).exceptionally((t) -> {
            t.printStackTrace();
            throw new RuntimeException(t);
        });
    }

    private void updateRevenueValue() {
        ViewMode mode = ViewMode.valueOf(((String) revenueViewType.getSelectedItem()).toUpperCase());
        orderDAO.getRevenue(mode).thenAccept((t) -> {
            revenueValue.setText(NumberUtil.format(t));
        });
    }

    private void updateOrderedProductValue() {
        ViewMode mode = ViewMode.valueOf(((String) orderedProductViewType.getSelectedItem()).toUpperCase());
        orderDAO.getOrderedProducts(mode).thenAccept((t) -> {
            orderedProductValue.setText(NumberUtil.format(t));
        });
    }

    private void showChart(RevenueChartData data) {
        CategoryDataset dataset = createDataset(data);
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Revenue over the past 12 months",
                "",
                "Revenue(M₫)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );
        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRange(true);
        rangeAxis.setAutoRangeIncludesZero(false);

        CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        plot.getRenderer().setBaseItemLabelGenerator(generator);
        plot.getRenderer().setBaseItemLabelsVisible(true);
        plot.getRenderer().setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER
        ));
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(580, 255));

        try {
            pane.remove(chart);
            pane.add(chartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 162, 585, 230));
            SwingUtilities.invokeLater(() -> {
                SwingUtilities.updateComponentTreeUI(this);
            });
        } catch (Exception e) {
        }

    }

    private CategoryDataset createDataset(RevenueChartData data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = data.getData().size() - 1; i >= 0; i--) {
            RevenueMonthy monthy = data.getData().get(i);
            dataset.addValue(monthy.getRevenue() / 1000000, "Data", monthy.getMonthOfYear() + "/" + monthy.getYear());
        }
        return dataset;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pane = new javax.swing.JPanel();
        revenuePanel = new javax.swing.JPanel();
        revenueLabel = new javax.swing.JLabel();
        revenueIcon = new javax.swing.JLabel();
        revenueValue = new javax.swing.JLabel();
        revenueUnit = new javax.swing.JLabel();
        revenueViewType = new javax.swing.JComboBox<>();
        orderedProductPanel = new javax.swing.JPanel();
        orderedProductLabel = new javax.swing.JLabel();
        orderProductIcon = new javax.swing.JLabel();
        orderedProductValue = new javax.swing.JLabel();
        orderedProductUnit = new javax.swing.JLabel();
        orderedProductViewType = new javax.swing.JComboBox<>();
        chart = new javax.swing.JPanel();

        pane.setBackground(new java.awt.Color(235, 239, 242));
        pane.setMaximumSize(new java.awt.Dimension(640, 440));
        pane.setMinimumSize(new java.awt.Dimension(640, 440));
        pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        revenuePanel.setBackground(new java.awt.Color(255, 255, 255));

        revenueLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        revenueLabel.setText("Revenue");

        revenueIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/profits.png"))); // NOI18N

        revenueValue.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        revenueValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        revenueValue.setText("1000000000");

        revenueUnit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        revenueUnit.setText(" ₫");

        revenueViewType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Total", "Month", "Day" }));

        javax.swing.GroupLayout revenuePanelLayout = new javax.swing.GroupLayout(revenuePanel);
        revenuePanel.setLayout(revenuePanelLayout);
        revenuePanelLayout.setHorizontalGroup(
            revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(revenueLabel)
                    .addGroup(revenuePanelLayout.createSequentialGroup()
                        .addComponent(revenueIcon)
                        .addGroup(revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(revenuePanelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(revenueUnit)
                                    .addComponent(revenueViewType, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, revenuePanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(revenueValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        revenuePanelLayout.setVerticalGroup(
            revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(revenueLabel)
                .addGroup(revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(revenuePanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(revenueIcon))
                    .addGroup(revenuePanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(revenueViewType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revenueValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revenueUnit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pane.add(revenuePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 27, -1, 114));

        orderedProductPanel.setBackground(new java.awt.Color(255, 255, 255));

        orderedProductLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        orderedProductLabel.setText("Ordered Product");

        orderProductIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trolley.png"))); // NOI18N

        orderedProductValue.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        orderedProductValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        orderedProductValue.setText("10000");

        orderedProductUnit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        orderedProductUnit.setText("product(s)");

        orderedProductViewType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Total", "Month", "Day" }));

        javax.swing.GroupLayout orderedProductPanelLayout = new javax.swing.GroupLayout(orderedProductPanel);
        orderedProductPanel.setLayout(orderedProductPanelLayout);
        orderedProductPanelLayout.setHorizontalGroup(
            orderedProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderedProductPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderedProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderedProductPanelLayout.createSequentialGroup()
                        .addComponent(orderedProductLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(orderedProductPanelLayout.createSequentialGroup()
                        .addComponent(orderProductIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addGroup(orderedProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orderedProductValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderedProductViewType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderedProductUnit, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        orderedProductPanelLayout.setVerticalGroup(
            orderedProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderedProductPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderedProductLabel)
                .addGroup(orderedProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(orderedProductPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(orderProductIcon))
                    .addGroup(orderedProductPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(orderedProductViewType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderedProductValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(orderedProductUnit)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pane.add(orderedProductPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 27, -1, -1));

        chart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chartLayout = new javax.swing.GroupLayout(chart);
        chart.setLayout(chartLayout);
        chartLayout.setHorizontalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        chartLayout.setVerticalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );

        pane.add(chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 162, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chart;
    private javax.swing.JLabel orderProductIcon;
    private javax.swing.JLabel orderedProductLabel;
    private javax.swing.JPanel orderedProductPanel;
    private javax.swing.JLabel orderedProductUnit;
    private javax.swing.JLabel orderedProductValue;
    private javax.swing.JComboBox<String> orderedProductViewType;
    private javax.swing.JPanel pane;
    private javax.swing.JLabel revenueIcon;
    private javax.swing.JLabel revenueLabel;
    private javax.swing.JPanel revenuePanel;
    private javax.swing.JLabel revenueUnit;
    private javax.swing.JLabel revenueValue;
    private javax.swing.JComboBox<String> revenueViewType;
    // End of variables declaration//GEN-END:variables

    static class RoundedBorder extends AbstractBorder {

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(UIManager.getColor("controlShadow"));
            g.drawRoundRect(x, y, width - 1, height - 1, 10, 10);
        }

        @Override
        public Insets getBorderInsets(Component component) {
            return new Insets(2, 2, 2, 2);
        }
    }
}
