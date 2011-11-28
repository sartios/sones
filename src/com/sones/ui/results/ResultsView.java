package com.sones.ui.results;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.print.DocFlavor.READER;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;

import com.sones.businessLogic.Searcher.Results.IResult;
import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.Searcher.Results.ViewModelResults;
import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.controllers.results.IResultsViewController;
import com.sones.controllers.results.ResultsViewController;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ResultsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollPane1 = null;
	private JTable ResultsTable = null;
	private	DefaultTableModel defaultTableModel = null;
	private	IResultsViewController	_controller;  //  @jve:decl-index=0:
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private	IViewModelResults _results;
	/**
	 * This is the default constructor
	 */
	public ResultsView() {
		super();
		initialize();
		_controller	=	new	ResultsViewController();
		_results	=	new	ViewModelResults();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		defaultTableModel	=	new	DefaultTableModel();
		defaultTableModel.setRowCount(0);
		defaultTableModel.setColumnCount(4);
		this.setSize(1008, 569);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("View Searching Results");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.X_AXIS));
			jContentPane.add(getJScrollPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJScrollPane1());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getResultsTable());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes ResultsTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getResultsTable() {
		if (ResultsTable == null) {
			TableColumn tableColumn3 = new TableColumn();
			tableColumn3.setHeaderValue("Creator");
			tableColumn3.setModelIndex(3);
			TableColumn tableColumn2 = new TableColumn();
			tableColumn2.setHeaderValue("Creation Date");
			tableColumn2.setModelIndex(2);
			TableColumn tableColumn1 = new TableColumn();
			tableColumn1.setHeaderValue("Contents");
			tableColumn1.setModelIndex(1);
			TableColumn tableColumn = new TableColumn();
			tableColumn.setHeaderValue("Keyowrds");
			tableColumn.setModelIndex(0);
			ResultsTable = new JTable();
			ResultsTable.setAutoCreateColumnsFromModel(false);
			ResultsTable.setName("TableOfResults");
			ResultsTable.setShowGrid(true);
			ResultsTable.setColumnSelectionAllowed(true);
			ResultsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			ResultsTable.addColumn(tableColumn);
			ResultsTable.addColumn(tableColumn1);
			ResultsTable.addColumn(tableColumn2);
			ResultsTable.addColumn(tableColumn3);
			ResultsTable.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					System.out.println("keyReleased()"); // TODO Auto-generated Event stub keyReleased()
					OpenResult();
				}
			});
		}
		return ResultsTable;
	}
	

	private void OpenResult() 
	{
		int	rowNum = ResultsTable.getSelectedRow();
		IResultViewModel	model	=	_results.GetResultAt(rowNum);
		_controller.ShowFeed(model.GetFeedID(),model.GetType());
	}

	private	void	SetResults( List<IResultViewModel> results )
	{
		for( IResultViewModel result : results )
		{
			Vector<Object>	data	=	new	Vector<Object>();
			data.add(0,result.GetKeywords());
			data.add(1,result.GetContent());
			System.out.println(result.GetContent());
			data.add(2,result.GetCreator());
			data.add(3,result.GetCreationDate());
			defaultTableModel.addRow(data);
			this.getResultsTable().setModel(defaultTableModel);
		}
	}
	
	private	void	SetResults( IViewModelResults results )
	{
		_results	=	results;
		Iterator<IResultViewModel>	iterator	=	results.GetIterator();
		while( iterator.hasNext() )
		{
			IResultViewModel	result	=	iterator.next();
			Vector<Object>	data	=	new	Vector<Object>();
			data.add(0,result.GetKeywords());
			data.add(1,result.GetContent());
			System.out.println(result.GetContent());
			data.add(2,result.GetCreator());
			data.add(3,result.GetCreationDate());
			defaultTableModel.addRow(data);
			this.getResultsTable().setModel(defaultTableModel);
		}
		
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Results");
			jMenu.add(getJMenuItem());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Load");
			jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					System.out.println("mouseReleased()"); // TODO Auto-generated Event stub mouseReleased()
					//AddResultsFromListToTable();
					AddResultsToTable();
				}
			});
		}
		return jMenuItem;
	}
	
	private	void	AddResultsFromListToTable()
	{
		List	results	=	_controller.GetKeywordSearchingResultList(new ApplicationUserID("1"));
		SetResults(results);
	}
	
	private	void	AddResultsToTable()
	{
		IViewModelResults	results	=	_controller.GetKeywordSearchingResults(new ApplicationUserID("1"));
		SetResults(results);
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
