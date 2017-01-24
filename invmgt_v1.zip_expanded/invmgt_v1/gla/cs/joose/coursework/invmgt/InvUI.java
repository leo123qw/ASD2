package gla.cs.joose.coursework.invmgt;

/**
 *
 * @author inah Omoronyia
 */

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import gla.cs.joose.coursework.invmgt.model.Item;
import gla.cs.joose.coursework.invmgt.model.ItemFactory;
import gla.cs.joose.coursework.invmgt.model.ItemType;
import gla.cs.joose.coursework.invmgt.util.Helper;

public class InvUI extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	
    private javax.swing.JButton addItem_b;
    private java.awt.Label barcode_label1;
    private java.awt.TextField barcode_tf;
    private javax.swing.JLabel item_desc_preview_l;
    private java.awt.Label desc_label;
    private javax.swing.JTextArea description_ta;
    private java.awt.Label itemName_label;
    private java.awt.TextField itemName_tf;
    private javax.swing.JComboBox<String> itemType_cb;
    private java.awt.Label itemtype_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> limit_cb;
    private java.awt.Label qty_label1;
    private java.awt.TextField qty_tf;
    private javax.swing.JButton search_b;
    private java.awt.TextField search_tf;
    private javax.swing.JComboBox<String> searchby_cb;
    private javax.swing.JLabel searchby_l;
    private java.awt.Label supplier_label;
    private java.awt.TextField supplier_tf;
    private static InvUI frame;
    
    private DefaultTableModel dtm;
    private Item [] results;
    private JPopupMenu popup;
	
    /**
     * Creates new form InvUI
     */
    public InvUI() {
        initComponents();
    }
                       
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        search_tf = new java.awt.TextField();
        searchby_cb = new javax.swing.JComboBox<>();
        searchby_l = new javax.swing.JLabel();
        search_b = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        item_desc_preview_l = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        limit_cb = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        itemName_label = new java.awt.Label();
        barcode_tf = new java.awt.TextField();
        barcode_label1 = new java.awt.Label();
        itemName_tf = new java.awt.TextField();
        qty_label1 = new java.awt.Label();
        qty_tf = new java.awt.TextField();
        supplier_label = new java.awt.Label();
        supplier_tf = new java.awt.TextField();
        desc_label = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        description_ta = new javax.swing.JTextArea();
        addItem_b = new javax.swing.JButton();
        itemtype_label = new java.awt.Label();
        itemType_cb = new javax.swing.JComboBox<>();
               
        dtm = new DefaultTableModel(0,0) {
			private static final long serialVersionUID = 1L;
			@Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));

        search_tf.setMinimumSize(new java.awt.Dimension(8, 18));
        search_tf.setPreferredSize(new java.awt.Dimension(8, 18));

        searchby_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Item Type", "Description", "Barcode", "Quantity", "Supplier", "Item Name" }));
        searchby_cb.setSelectedIndex(0);

        searchby_l.setText("Search By");
        searchby_l.setToolTipText("");

        search_b.setText("Search");
        search_b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                search_bMousePressed(evt);
            }
        });

        String tableheader[] = new String[] { "Barcode", "Name", "Type", "Qty", "Supplier"};
        dtm.setColumnIdentifiers(tableheader);
        jTable1.setModel(dtm);
        
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	if(event.getValueIsAdjusting()){
            		String barcode_selected = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                    updateItemDescription(new Long(barcode_selected));
            	}
            }
        });
        
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        
        popup = new JPopupMenu();
        JMenuItem deletemenu = new JMenuItem("Delete Item");
        deletemenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Component c = (Component)e.getSource();
		        JPopupMenu popup = (JPopupMenu)c.getParent();
		        JTable table = (JTable)popup.getInvoker();	
		        long barcode =(Long)dtm.getValueAt(table.getSelectedRow(), 0);
		        String itemname = (String)dtm.getValueAt(table.getSelectedRow(), 1);
		        ItemType itemtype = (ItemType)dtm.getValueAt(table.getSelectedRow(), 2);
		        int qty =(Integer)dtm.getValueAt(table.getSelectedRow(), 3);
		        String supplier =(String)dtm.getValueAt(table.getSelectedRow(), 4);
		        deleteItem(barcode, itemname, itemtype,qty, supplier);
			}     	
        });
        popup.add(deletemenu); 
        
        JMenuItem updatemenu = new JMenuItem("Update Item");
        updatemenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Component c = (Component)e.getSource();
		        JPopupMenu popup = (JPopupMenu)c.getParent();
		        JTable table = (JTable)popup.getInvoker();	
		        long barcode =(Long)dtm.getValueAt(table.getSelectedRow(), 0);
		        String itemname = (String)dtm.getValueAt(table.getSelectedRow(), 1);
		        ItemType itemtype = (ItemType)dtm.getValueAt(table.getSelectedRow(), 2);
		        int qty =(Integer)dtm.getValueAt(table.getSelectedRow(), 3);
		        String supplier =(String)dtm.getValueAt(table.getSelectedRow(), 4);
				updateItem(barcode, itemname, itemtype,qty, supplier);				
			}     	
        });
        popup.add(updatemenu); 
        
        jTable1.addMouseListener( new MouseAdapter(){
            public void mousePressed(MouseEvent e){}

            @SuppressWarnings("static-access")
			public void mouseReleased(MouseEvent e)
            {
            	if(e.getButton() ==e.BUTTON3){
                    int row = jTable1.rowAtPoint( e.getPoint() );
                    int column = jTable1.columnAtPoint( e.getPoint() );

                    if (! jTable1.isRowSelected(row))
                    	jTable1.changeSelection(row, column, false, false);

                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        jScrollPane1.setViewportView(jTable1);

        item_desc_preview_l.setText(" ");

        jLabel1.setText("Limit");

        limit_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "20", "25", "30", "35", "40", "50", "100" }));
        
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(item_desc_preview_l, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(searchby_l, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchby_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(limit_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_b, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchby_l)
                        .addComponent(searchby_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(limit_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(search_b)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(item_desc_preview_l, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Item"));
        jPanel1.setPreferredSize(new java.awt.Dimension(222, 288));

        itemName_label.setText("ItemName");

        barcode_label1.setText("Barcode");

        qty_label1.setText("Quantity");

        supplier_label.setText("Supplier");

        desc_label.setText("Description");

        description_ta.setColumns(20);
        description_ta.setLineWrap(true);
        description_ta.setRows(5);
        description_ta.setSize(new java.awt.Dimension(240, 70));
        jScrollPane2.setViewportView(description_ta);

        addItem_b.setText("Add");
        addItem_b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addItem_bMousePressed(evt);
            }
        });

        itemtype_label.setText("ItemType");

        itemType_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "","electronic", "outdoor", "health", "cloth", "book", "car", "garden", "others"}));
        itemType_cb.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(barcode_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(barcode_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(itemName_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(itemName_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(qty_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(qty_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(desc_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(supplier_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(itemtype_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supplier_tf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itemType_cb, 0, 128, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addItem_b)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcode_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barcode_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemName_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemName_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qty_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qty_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supplier_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplier_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemtype_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemType_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desc_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addItem_b)
                .addGap(2, 2, 2))
        );

        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }                       
        
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {                                   
        if(evt.getKeyCode() == KeyEvent.VK_DOWN ||evt.getKeyCode() == KeyEvent.VK_UP){
        	String barcode_selected = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            updateItemDescription(new Long(barcode_selected));            
        }
    } 

    private void search_bMousePressed(java.awt.event.MouseEvent evt) {                                      
    	String searchbydesc= (String)searchby_cb.getSelectedItem();
		String pattern = search_tf.getText();
		String limit_s = (String)limit_cb.getSelectedItem();
		if(!Helper.isNumeric(limit_s)){
			JOptionPane.showMessageDialog(frame,"limit error","error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int limit = new Integer(limit_s);
				
		results = ItemFactory.search(searchbydesc, pattern, limit);
		
		dtm.setRowCount(0);
		for (Item item:results) {
	        dtm.addRow(new Object[] { item.getBarcode(), item.getItemName(), item.getItemType(),item.getQuantity(), item.getSupplier()});
		}

    }                                     

    private void addItem_bMousePressed(java.awt.event.MouseEvent evt) {                                       
    	String barcode_s = barcode_tf.getText();
		String itemName =itemName_tf.getText();
		String itemDesc = description_ta.getText();
		String qty_s = qty_tf.getText();
		String supplier = supplier_tf.getText();
		String itemType_s = (String)itemType_cb.getSelectedItem();
		
		if(!Helper.isNumeric(barcode_s)){
			JOptionPane.showMessageDialog(frame,"invalid barcode","error",JOptionPane.ERROR_MESSAGE); 
			return;
		}
		if(itemName.trim().length() ==0){
			JOptionPane.showMessageDialog(frame,"invalid item name","error",JOptionPane.ERROR_MESSAGE); 
			return;
		}
		if(!Helper.isNumeric(qty_s)){
			JOptionPane.showMessageDialog(frame,"invalid quantity","error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(supplier.trim().length() ==0){
			JOptionPane.showMessageDialog(frame,"invalid supplier","error",JOptionPane.ERROR_MESSAGE); 
			return;
		}
		long barcode = new Long(barcode_s);
		int qty = new Integer(qty_s);
		
		boolean done = ItemFactory.addItem(barcode, itemName, itemType_s, qty, supplier, itemDesc);
		
		if(!done){
			JOptionPane.showMessageDialog(frame,"Item not added","error",JOptionPane.ERROR_MESSAGE);
		}
		else{
			barcode_tf.setText("");
			itemName_tf.setText("");
			description_ta.setText("");
			qty_tf.setText("");
			supplier_tf.setText("");
			itemType_cb.setSelectedIndex(0);
		}
		addItem_b.setText("Add");
    }                                                                            

    /**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
	    /* Create and display the form */
	    java.awt.EventQueue.invokeLater(new Runnable() {
	        public void run() {
	        	frame = new InvUI();
	        	frame.setTitle("InvManager");
	        	frame.setResizable(false);
	        	frame.setVisible(true);
	        }
	    });
	}
	
	private void updateItemDescription(long barcode){
		String desc = "";
		for(Item item: results){
			if(item.getBarcode() == barcode){
				desc = item.getDescription();
				break;
			}
		}
		String html1 = "<html><body style='width: ";
        String html2 = "px'>";
        
		item_desc_preview_l.setText(html1 + "465" + html2 + desc);
	}

	private void deleteItem(long barcode, String itemname, ItemType itemtype,int qty, String supplier) {
		Item  ditem	= null;
		for(Item item: results){
			if(item.getBarcode() == barcode && item.getItemName().equals(itemname) &&
			   item.getItemType() == itemtype && item.getQuantity() == qty && 
			   item.getSupplier().equals(supplier)){
				ditem = item;
				break;
			}
		}
		if(ditem !=null){
			boolean deleted = ItemFactory.delete(ditem);
			
			if(deleted){
				Item tempResults [] = new Item[results.length-1];
				int t=0;
				for(int i=0;i<results.length;i++){
					if(!(results[i].getBarcode() == barcode)){
						tempResults[t] = results[i];
						t = t+1;
					}
				}
				results = tempResults;
				dtm.setRowCount(0);
				for (Item item:results) {
			        dtm.addRow(new Object[] { item.getBarcode(), item.getItemName(), item.getItemType(),item.getQuantity(), item.getSupplier()});
				}
				item_desc_preview_l.setText("");
			}
			else{
				JOptionPane.showMessageDialog(frame,"Item not deleted","error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}  

	private void updateItem(long barcode, String itemname, ItemType itemtype,int qty, String supplier) {
		Item  ditem	= null;
		for(Item item: results){
			if(item.getBarcode() == barcode && item.getItemName().equals(itemname) &&
			   item.getItemType() == itemtype && item.getQuantity() == qty && 
			   item.getSupplier().equals(supplier)){
				ditem = item;
				break;
			}
		}
		if(ditem !=null){
			boolean deleted = ItemFactory.delete(ditem);
			
			if(deleted){
				Item tempResults [] = new Item[results.length-1];
				int t=0;
				for(int i=0;i<results.length;i++){
					if(!(results[i].getBarcode() == barcode)){
						tempResults[t] = results[i];
						t = t+1;
					}
				}
				results = tempResults;
				dtm.setRowCount(0);
				for (Item item:results) {
			        dtm.addRow(new Object[] { item.getBarcode(), item.getItemName(), item.getItemType(),item.getQuantity(), item.getSupplier()});
				}
				item_desc_preview_l.setText("");
				
				barcode_tf.setText(""+ditem.getBarcode());
				itemName_tf.setText(ditem.getItemName());
				description_ta.setText(ditem.getDescription());
				qty_tf.setText(""+ditem.getQuantity());
				supplier_tf.setText(ditem.getSupplier());
				itemType_cb.setSelectedItem(ItemType.getTypeDesc(ditem.getItemType()));
				addItem_b.setText("Update");
			}
			else{
				JOptionPane.showMessageDialog(frame,"Item not updated","error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}   
}
