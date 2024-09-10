package com.mycompany.view;

import com.mycompany.dao.ProductoDAO;
import com.mycompany.entity.Producto;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JJAB
 */
public class IfrmActivos extends javax.swing.JInternalFrame {

    ProductoDAO productoDAO = new ProductoDAO();

    /**
     * Creates new form IfrmEntregar
     */
    public IfrmActivos() {
        initComponents();
        creartabla();
    }

    public void creartabla() {

        DefaultTableModel tableModel = (DefaultTableModel) tblProducto.getModel();

        tblProducto.setModel(tableModel);

        List<Producto> productos = productoDAO.mostrarProductosPorEstado('v');

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Producto p : productos) {

            tableModel.addRow(new Object[]{p.getIdproducto(), p.getNombre(),
                formato.format(p.getFecharecibo()), p.getDescripcion(), p.getMarcaIdmarca().getMarca(),
                p.getPropietarioIdpropietario().getNombres()});
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Productos por Entregar");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblProducto.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "FechaInfreso", "Descripcion", "Marca", "Propietrio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProducto);
        if (tblProducto.getColumnModel().getColumnCount() > 0) {
            tblProducto.getColumnModel().getColumn(0).setResizable(false);
            tblProducto.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblProducto.getColumnModel().getColumn(1).setResizable(false);
            tblProducto.getColumnModel().getColumn(1).setPreferredWidth(95);
            tblProducto.getColumnModel().getColumn(2).setResizable(false);
            tblProducto.getColumnModel().getColumn(3).setResizable(false);
            tblProducto.getColumnModel().getColumn(3).setPreferredWidth(300);
            tblProducto.getColumnModel().getColumn(4).setResizable(false);
            tblProducto.getColumnModel().getColumn(4).setPreferredWidth(75);
            tblProducto.getColumnModel().getColumn(5).setResizable(false);
            tblProducto.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setText("Buscar");

        btnSeleccionar.setBackground(new java.awt.Color(0, 0, 204));
        btnSeleccionar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeleccionar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        try {

            int indice = (int) tblProducto.getModel().getValueAt(tblProducto.getSelectedRow(), 0);

            productoDAO.actualizarEstado(indice);

            Principal.panel.removeAll();
            Principal.panel.repaint();

            IfrmEntregar entregar = new IfrmEntregar();
            entregar.setVisible(true);

            entregar.obtenerIdProducto(indice);

            Principal.panel.add(entregar);

        } catch (Exception e) {
            System.out.println("error tabla " + e);
            JOptionPane.showMessageDialog(this, "Seleccione una fila");

        }


    }//GEN-LAST:event_btnSeleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblProducto;
    // End of variables declaration//GEN-END:variables
}
