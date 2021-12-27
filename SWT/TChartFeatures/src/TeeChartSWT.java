import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
/*
 * Javax Sources
 */
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

//import TeeChartSWT.Navigate;

import features.Parameters;
import features.WidgetFactory;

/*
 * TeeChartDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

public class TeeChartSWT {
		
	public TeeChartSWT() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("TeeChart for Java Examples");
			
		final Image IconImage = new Image(display, getClass()
				.getResourceAsStream(IMAGE_SHELLICON_URL));
		shell.setImage(IconImage);
		Monitor monitor = display.getPrimaryMonitor();
		if (monitor != null) {
		/*	Rectangle dim = monitor.getClientArea();
			shell.setSize(dim.width - (dim.width / 3),
                    dim.height - (dim.height / 3));
		}*/
		
			// Here we change executable size
			shell.setSize(872, 741);

		}
		Color tabColor = new Color(display, 215, 215, 215);
		shell.setForeground(tabColor);
		initUI();
		//shell.pack();
	}
	
	public void run() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();		
	}
	
	private Display display;
	private Shell shell;
	// We create the interface buttons
	private Button leftButton, rightButton, firstButton, lastButton, Search,
			checkButton, Clean;
	private org.eclipse.swt.widgets.Text t1;
	private Composite sampleContainer;	
	private Combo comboSearch, comboResults;
	private Vector<TeeFeature> a_t = new Vector<TeeFeature>();
	
	static class HelperTree  {
		
		public static TreeItem getNextSibling(Tree tree, TreeItem item,
				boolean expandChilds) {

			TreeItem sibling = null;
			TreeItem parent = item.getParentItem();
			int index = -1;
			if (expandChilds & item.getItemCount()>0) {
				sibling = item.getItem(0);
			} else {
				if (parent == null) {
					index = tree.indexOf(item);
					if (index != tree.getItemCount()-1) {
						sibling = tree.getItem(++index);
					}
				} else {
					if (item == parent.getItem(parent.getItemCount()-1)) {
						sibling = getNextSibling(tree, parent, false); 
					} else {
						index = parent.indexOf(item);
						sibling = parent.getItem(++index);
					}
				}
			}
			return sibling;
		}

		public static TreeItem getPreviousSibling(Tree tree, TreeItem item,
				boolean expandChilds) {
			TreeItem sibling = null;
			TreeItem parent = item.getParentItem();
			int index = -1;
			if (parent == null) {
				index = tree.indexOf(item);
				if (index >= 0 ) {
					sibling = tree.getItem(--index);
					if (expandChilds) {
						sibling = getLastChild(sibling);
					}
				}
			} else {
				if (item == parent.getItem(0)) {
					sibling = getPreviousSibling(tree, parent, false); 
				} else {
					index = parent.indexOf(item);
					sibling = parent.getItem(--index);
					if (expandChilds) {
						sibling = getLastChild(sibling);
					}
				}
			}
			return sibling;
		}
		
		public static TreeItem getLastChild(TreeItem item) {
			int childs=item.getItemCount();
			if (childs>0) {
				item = getLastChild(item.getItem(childs-1));
			} 
			return item;
		}
		
		public static TreeItem getParent(Tree item) {
			TreeItem i = item.getItem(0);
			return i;
		}
	}
	
	class Close extends SelectionAdapter {
		public void widgetSelected(SelectionEvent se) {
			shell.close();
		}
	};
	
	class About extends SelectionAdapter {
		public void widgetSelected(SelectionEvent se) {
			com.steema.teechart.editors.AboutDialog dialog = new com.steema.teechart.editors.AboutDialog(
					shell);
			dialog.open();
		}
	};  
	
	class Navigate extends SelectionAdapter {
		public void widgetSelected(SelectionEvent se) {

			String[] pathCounter;

			pathCounter = new String[300];
			TeeFeature tee;

			if (se.widget == Search) {

				String SearchValue = comboSearch.getText();
				boolean b = false;
				String[] auxSearch = new String[300];
				int i = 0;
				for (i = 0; i < 300; i++) {
					auxSearch[i] = "";
					auxSearch[i].toCharArray();
				}

				int counterWord = 0, forInt = 0;
				SearchValue = SearchValue + "*";
				while (forInt < SearchValue.length() - 1) {
					if (SearchValue.charAt(forInt) != ' ') {

						while (SearchValue.charAt(forInt) != ' '
								&& forInt < SearchValue.length() - 1) {

							auxSearch[counterWord] = auxSearch[counterWord]
									+ SearchValue.charAt(forInt);
							forInt++;
						}
					} else {
						forInt++;
						counterWord++;
					}

				}
				comboResults.setVisible(true);
				
				String curDir = System.getProperty("user.dir");
				
				File dir = new File(curDir);
				if (dir.isDirectory()) {
					File ResultsFile = new File(dir + "\\fileResult.txt");
					if (ResultsFile.exists()) {
						ResultsFile.delete();
						ResultsFile = new File(dir + "\\fileResult.txt");
					}
					Vector<File> ficherosJava = new Vector<File>();
					b = checkButton.getSelection();
					Search(curDir, dameRegex("*.java"), ficherosJava, true,
							curDir, pathCounter, auxSearch, counterWord, b);
					i = 0;

					while (pathCounter[i] != null) {
						pathCounter[i] = pathCounter[i].replace(".java", "");
						i++;
					}
					for (i = 0; i < ficherosJava.size(); i++) {
						tee = new TeeFeature(ficherosJava.get(i).getName(),
								pathCounter[i], "", true, false);
						a_t.add(tee);
						comboResults.add(a_t.lastElement().name.toString());

					}
					
					t1.setText(ficherosJava.size() + " Items");
					t1.setVisible(true);
				}
			
			} else if (se.widget == comboResults) {
				boolean found = false;
				int i = 0;
				while (i < a_t.size() && !found) {
					found = a_t.get(i).name.equals(comboResults.getText());
					if (!found)
						i++;
				}
				if (found)
					showSamplePane(a_t.get(i));
			} else if (se.widget == Clean) {
				a_t.removeAllElements();
				comboResults.removeAll();
				t1.setVisible(false);
			} else {
				try {
			if (activeTree.getSelectionCount()>0) {
				TreeItem selection = activeTree.getSelection()[0];				
				TreeItem sibling = null;
				if (se.widget==leftButton) {				
							sibling = HelperTree.getPreviousSibling(activeTree,
									selection, true);
				} else if (se.widget==rightButton) {
							sibling = HelperTree.getNextSibling(activeTree,
									selection, true);
						} else if (se.widget == lastButton) {
							sibling = HelperTree.getLastChild(activeTree
									.getTopItem());

						} else if (se.widget == firstButton) {
							sibling = HelperTree.getParent(activeTree);

				}

				if (sibling != null) {
					activeTree.setSelection(sibling);
				}				

				activeTree.getSelection()[0].setExpanded(true);
						showSamplePane((TeeFeature) activeTree.getSelection()[0]
								.getData());
					}
				} catch (NullPointerException e) {

				}
			}

		}
	};  
	   
	private Label statusLabel;
	
    private Composite createBottomUI(Composite parent) {
    	Composite pane = new Composite(parent, SWT.BORDER);
    	pane.setLayout(new FillLayout());    	
    	statusLabel = new Label(pane, SWT.CENTER);    	
    	return pane;
    }

	private Composite addTabPage(TabFolder folder, String label) {
		TabItem tab = new TabItem(folder, SWT.NONE);
		tab.setText(label);
		Composite page = new Composite(folder, SWT.NONE);
		page.setLayout(new FillLayout());
		tab.setControl(page);

		return page;
	}
	
    protected final class TeeFeature {
        private String name;
        private String classpath;
        private String issues;
        private boolean visible;
        private boolean leaf;

        public TeeFeature(String name, String classpath, String issues,
                              boolean visible, boolean leaf) {
            this.name = name;
            this.classpath = classpath;
            this.issues = issues;
            this.visible = visible;
        }

        public TeeFeature(String name, String classpath) {
            this(name, classpath, "", true, true);
        }

        public String getIssues() {
            return issues;
        }

        public boolean isFinished() {
            return ((issues.length() == 0) || issues.equals("0"));
        }

        public boolean isLeaf() {

            return leaf;
        }

        public boolean isSample() {
            return !this.classpath.equalsIgnoreCase("");
        }

        public boolean isVisible() {
            return visible;
        }

        public String toString() {
            return name;
        }
    }
    
    public TeeFeature createFeatureNodeOf(Node childElement) {
        NamedNodeMap elementAttributes = childElement.getAttributes();

        TeeFeature tmpNode = null;
		if (elementAttributes != null && elementAttributes.getLength() > 0) {

            boolean hasChilds = (childElement.getChildNodes().getLength() > 0);

            Node attribute = elementAttributes.getNamedItem("caption");
			String tmpCaption = (attribute != null) ? attribute.getNodeValue()
					: "";
            attribute = elementAttributes.getNamedItem("classpath");
			String tmpClassPath = (attribute != null) ? attribute
					.getNodeValue() : "";
            attribute = elementAttributes.getNamedItem("visible");
			boolean tmpVisible = (attribute != null) ? attribute.getNodeValue()
					.equals("1") : true;
            attribute = elementAttributes.getNamedItem("issues");
			String tmpIssues = (attribute != null) ? attribute.getNodeValue()
					: "";

            if (!fullTree) {
                if (tmpIssues.equals("") || tmpIssues.equals("0")) {
                } else {
                    tmpVisible = hasChilds;
                    if (hasChilds) {
                        tmpClassPath = "";
					}
					;
                    tmpIssues = "";
                }
            } else {
                tmpVisible = true;
            }

			tmpNode = new TeeFeature(tmpCaption, tmpClassPath, tmpIssues,
					tmpVisible, !hasChilds);
        }
        return tmpNode;
    }	
    
	private void addChildren(Tree tree, TreeItem parentItem,
            Node parentXMLElement) {

		NodeList childElements = parentXMLElement.getChildNodes();

		for (int i = 0; i < childElements.getLength(); i++) {
			Node childElement = childElements.item(i);

			if (!(childElement instanceof Text || childElement instanceof Comment)) {

				boolean hasChilds = (childElement.getChildNodes().getLength() > 0);

				TeeFeature feature = createFeatureNodeOf(childElement);

				if (feature.isVisible() || Parameters.getShowAllFeatures()
						|| hasChilds) {

					if (feature.isSample()) {
						treeNodeCount++;
					}
					;

					TreeItem childItem;
					if (parentItem==null) { 
						childItem = new TreeItem(tree, SWT.NONE);

					} else {
						childItem = new TreeItem(parentItem, SWT.NONE);

					}
					childItem.setText(feature.toString());
					childItem.setData(feature);
					//Image image = new Image(display, FOLDER_ICON_URL);
					//childItem.setImage(image);
					
					addChildren(tree, childItem, childElement);
				}
			}
		}
	}

	private int treeNodeCount = 0;		
	
    protected Tree createFeaturesTree(Composite parent, String section) {
    	Tree allTree = new Tree(parent, SWT.SINGLE);    	    	        	
    	
        try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document document = builder.parse(getClass().getResource(
					FEATURES_URL).toString()); // TeeChartDemo.class

            document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();

            NodeList tmpList = document.getElementsByTagName(section);
            
            treeNodeCount = 0;

            if (tmpList != null) {
                rootElement = (Element) tmpList.item(0);
                addChildren(allTree, null, rootElement);
            }

        } catch (Exception e) {
            String errorMessage = "Error creating feature tree: " + e;
            System.err.println(errorMessage);
            e.printStackTrace();
        }
        return allTree;
    }

	protected Tree createNewTree(Composite parent, String section) {
		Tree newTree = new Tree(parent, SWT.SINGLE);

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document document = builder.parse(getClass().getResource(NEWS_URL)
					.toString()); // TeeChartDemo.class

			document.getDocumentElement().normalize();
			Element rootElement = document.getDocumentElement();

			NodeList tmpList = document.getElementsByTagName(section);

			treeNodeCount = 0;
	
			if (tmpList != null) {
				rootElement = (Element) tmpList.item(0);
				addChildren(newTree, null, rootElement);
			}

		} catch (Exception e) {
			String errorMessage = "Error creating what's new tree: " + e;
			System.err.println(errorMessage);
			e.printStackTrace();
		}

		return newTree;
	}

	private Composite allPage;
	private Composite neww;
	private Tree allTree;
	private Tree newTree;

    private Composite createNavigationUI(Composite parent) {
    	final Color bgColor = new Color(display, 234, 238, 255);
    	TabFolder folder = new TabFolder(parent, SWT.NONE);   	
		folder.moveAbove(rightButton);
    	folder.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {				
				bgColor.dispose();
			}
    	});    	    	
    	
		SelectionAdapter featureSelection = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

    			//System.out.println("Selection:"+ e.item.toString());
				if (e.item.getData() != null) {
					showSamplePane((TeeFeature) e.item.getData());
					activeTree = allTree;

				}

			}
		};

		SelectionAdapter newSelection = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

    			if (e.item.getData() != null) {

    				showSamplePane((TeeFeature)e.item.getData());
					activeTree = newTree;

    			}

    		}
    	};    			
    	
    	/* New Features */
		neww = addTabPage(folder, "What's new?");
		newTree = createNewTree(neww, "new");
		newTree.addSelectionListener(newSelection);
    	
    	/* All Features */

		allPage = addTabPage(folder, "All Features");
		allTree = createFeaturesTree(allPage, "all");
		// allTree.setBackground(tabColor);
    	allTree.setBackground(bgColor);
		newTree.setBackground(bgColor);
    	allTree.addSelectionListener(featureSelection); 
    	
		/* Search Features */
		Composite Search1;
		Search1 = addTabPage(folder, "Search");
    	
		createlittleUI(Search1);

    	return folder;
    }
    
    private Composite createTopUI(Composite parent) {
    	Composite pane = new Composite(parent, SWT.NONE);
    	final Color bgColor = new Color(display, 241, 243, 243);
    	final Image tcImage = new Image(display, getClass().getResourceAsStream(IMAGE_TEECHARTHEADER_URL));
    	final Image logoImage = new Image(display, getClass().getResourceAsStream(IMAGE_TEECHARTLOGO_URL));
    	final Font font = new org.eclipse.swt.graphics.Font(display, "Arial", 14, SWT.BOLD);

    	GridLayout gridLayout = new GridLayout();
    	gridLayout.numColumns = 10;    	
    	pane.setLayout(gridLayout);
    	pane.setBackground(bgColor);

    	final Navigate navigateListener = new Navigate();
    	leftButton = WidgetFactory.createPushButton(
    			pane,
    			"<",
    			"Previous",
    			navigateListener
    	);        
    	rightButton = WidgetFactory.createPushButton(
    			pane, 
    			">",
    			"Next", 
    			navigateListener
    	);                   

    	pane.setBackgroundImage(tcImage);

    	shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
      
    	Label spacer1 = new Label(pane, SWT.NONE);
    	spacer1.setLayoutData(new GridData(SWT.TRANSPARENCY_ALPHA, SWT.TOP, true, false));
    	Label spacer2 = new Label(pane, SWT.NONE);
    	spacer2.setLayoutData(new GridData(SWT.TRANSPARENCY_ALPHA, SWT.TOP, true, false));
    	{
    		Label spacer3 = new Label(pane, SWT.LEFT);
    		spacer3.setForeground(new Color(display,225,225,225));
    	spacer3.setFont(font);
    	spacer3.setText("SWT Examples");
    	}
    	
    	Label spacer4 = new Label(pane, SWT.NONE);
    	spacer4.setLayoutData(new GridData(SWT.TRANSPARENCY_ALPHA, SWT.TOP, true, false));

    	Label spacer4b = new Label(pane, SWT.NONE);
    	spacer4b.setLayoutData(new GridData(SWT.TRANSPARENCY_ALPHA, SWT.TOP, true, false));

    	Label lblHeader = new Label(pane, SWT.CENTER);
    	if (logoImage != null) {
    		lblHeader.setImage(logoImage);
    		lblHeader.setBounds(logoImage.getBounds());
    	}
    	lblHeader.setLayoutData(new GridData(SWT.TRANSPARENCY_ALPHA, SWT.NONE, true, false));

    	WidgetFactory.createPushButton(
    			pane, 
    			"About...",
    			"About TeeChart for Java",
    			new About()
    	);

    	WidgetFactory.createPushButton(
    			pane, 
    			"Close", 
    			"Close TeeChart Demo Application",
    			new Close()
    	);            	

    	pane.addDisposeListener(new DisposeListener() {
    		public void widgetDisposed(DisposeEvent e) {
    			tcImage.dispose();
    			logoImage.dispose();
    			bgColor.dispose();
    			font.dispose();
    		}
    	});

    	return pane;
    }
    
    private void initUI() {
    	GridLayout gl = new GridLayout();
    	gl.numColumns = 1;    	
    	shell.setLayout(gl);
    	Composite topPane = createTopUI(shell);
    	topPane.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
    	    	
    	SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL);
    	sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));    	    	
    	
    	createNavigationUI(sashForm);    	
    	sampleContainer = new Composite(sashForm, SWT.NONE);
    	sampleContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    	
    	sashForm.setWeights(new int[] {30,70});
    	
    	Composite bottomPane = createBottomUI(shell);
    	bottomPane.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
    
    private void disposeComposite(Composite c) {
    	while (c.getChildren().length > 0) {
    		if (c.getChildren()[0] instanceof Composite) {
    			disposeComposite(((Composite)c.getChildren()[0]));
    		} else {	
    			while (c.getChildren().length > 0) {
    				c.getChildren()[0].dispose();
    			}
    		}
    	}
    	if (c != sampleContainer) { c.dispose(); };     	
    }
    
    private Composite createlittleUI(Composite parent) {

		Composite pane = new Composite(parent, SWT.CENTER);
		
		//Image image = new Image(display, System.getProperty("user.dir")
		//		+ "/features/images/Search-icon.png");
				
		pane.setSize(20, 20);
		final Color bgColor = new Color(display, 255, 255, 255);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		pane.setLayout(gridLayout);
		pane.setBackground(bgColor);

		final Navigate navigateListener = new Navigate();

		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		org.eclipse.swt.widgets.Text t = WidgetFactory.createText(pane, 0,
				"Search");
		t.setBackground(bgColor);
		new Label(pane, SWT.NONE);
		comboSearch = WidgetFactory.createCombo(pane, 0, navigateListener);

		comboSearch.setText("                                            ");

		Search = WidgetFactory.createPushButton(pane, "Search",
				"Click for Search", navigateListener);

		//Image SearchIcon = new Image(display, SEARCH_ICON_URL);
		//Search.setImage(SearchIcon);
	
		new Label(pane, SWT.NONE);
		Label lblHeader = new Label(pane, SWT.CENTER);
		checkButton = WidgetFactory.createCheckButton(pane,
				"Find all words (AND)", "", navigateListener);
		checkButton.setBackground(new Color(pane.getDisplay(),255,255,255));

		lblHeader.setLayoutData(new GridData(SWT.TRANSPARENCY_ALPHA, SWT.NONE,
				true, false));
		new Label(pane, SWT.NONE);
		new Label(pane, SWT.NONE);
		new Label(pane, SWT.NONE);
		 t1 = WidgetFactory.createText(pane, 0,
				"");
		t1.setVisible(false);
		new Label(pane, SWT.NONE);
		comboResults = WidgetFactory.createCombo(pane, 1, navigateListener);
		comboResults.setVisible(true);
		comboResults.setText("                                            ");
		
		Clean = WidgetFactory.createPushButton(pane, "   Clear",
				"Click for Clean results found", navigateListener);
		//Image clean = new Image(display, CLEAR_ICON_URL);
		//Clean.setImage(clean);
		pane.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				// logoImage.dispose();
				bgColor.dispose();
				// font.dispose();
			}
		});
		return pane;

	}

//	private void initUI() {
//		GridLayout gl = new GridLayout();
//		gl.numColumns = 1;
//		shell.setLayout(gl);
//		Composite topPane = createTopUI(shell);
//		Composite topPane2 = createMiddleUI(shell);
//		topPane.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
//		topPane2.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
//
//		SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL);
//		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
//
//		createNavigationUI(sashForm);
//
//		sampleContainer = new Composite(sashForm, SWT.NONE);
//		sampleContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
//				true));
//
//		Image image = new Image(display, INITIAL_BACKGROUND_URL);
//		sampleContainer.setBackgroundImage(image);
//		sashForm.setWeights(new int[] { 30, 70 });
//
//		Composite bottomPane = createBottomUI(shell);
//		bottomPane.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
//
//	}
    
    private void showSamplePane(TeeFeature feature) {
    	//disposeComposite(sampleContainer);
    	while (sampleContainer.getChildren().length > 0) {
    		sampleContainer.getChildren()[0].dispose();
    	}
    	
    	if (feature.classpath.equals("")) { return; }

        try {
        	String className = Parameters.featuresName + feature.classpath;
            Class cls = Class.forName(className);
          	Constructor constructor = cls.getConstructor(new Class[]{Composite.class});
           	Composite tmp = (Composite)constructor.newInstance(new Object[]{sampleContainer});
           	tmp.setSize(sampleContainer.getSize());
           	statusLabel.setText(feature.name + "  (" + className + ".java)");
        } catch (InvocationTargetException e) {
            System.out.println(e);
            e.printStackTrace();                                
        } catch (NoSuchMethodException e) {
            System.out.println(e);
            e.printStackTrace();                                
        } catch (InstantiationException e) {
            System.out.println(e);
            e.printStackTrace();            
        } catch (IllegalAccessException e) {
            System.out.println(e);
            e.printStackTrace();           
        } catch (ClassNotFoundException e) {        	
            System.out.println(e);
            e.printStackTrace();
            statusLabel.setText("");
        }
    }
    
    public void Search(String pathInicial, String mascara,
			Vector<File> listaFicheros, boolean busquedaRecursiva,
			String pathInicial2, String[] classpaths, String[] searchValue,
			int counterWords, boolean b) {
		int cnt = 0;
		String j;
		File directorioInicial = new File(pathInicial);

		if (directorioInicial.isDirectory()) {

			File[] ficheros = directorioInicial.listFiles();

			for (int i = 0; i < ficheros.length; i++) {

				if (ficheros[i].isDirectory() && busquedaRecursiva) {

					Search(ficheros[i].getAbsolutePath(), mascara,
							listaFicheros, busquedaRecursiva, pathInicial2,
							classpaths, searchValue, counterWords, b);

				}

				else if (Pattern.matches(mascara, ficheros[i].getName())) {

					if (!b) {
						for (int k = 0; k <= counterWords; k++) {
							if (ficheros[i].getName().toLowerCase()
									.contains(searchValue[k].toLowerCase())) {
								j = ficheros[i].getAbsolutePath().replace(
										pathInicial2, "");
								while (classpaths[cnt] != null) {
									cnt++;
								}

								j = j.replace("\\features\\", "");
								j = j.replace("\\", ".");

								classpaths[cnt] = j;
								listaFicheros.add(ficheros[i]);
							}
						}
					} else {
						boolean parameters = true;
						for (int k = 0; k <= counterWords && parameters; k++) {
							if (ficheros[i].getName().toLowerCase()
									.contains(searchValue[k].toLowerCase())) {
								j = ficheros[i].getAbsolutePath().replace(
										pathInicial2, "");
								while (classpaths[cnt] != null) {
									cnt++;
								}

								j = j.replace("\\features\\", "");
								j = j.replace("\\", ".");

								classpaths[cnt] = j;
								if(!listaFicheros.contains(ficheros[i]))
								listaFicheros.add(ficheros[i]);
							}
							else
							{	parameters = false;
								for(int counter=0;counter<cnt;counter++)
									classpaths[counter]="";
																	
							}
							}
					}
				}

			}
		}

	}
    
	public static String dameRegex(String mascara) {
		mascara = mascara.replace(".", "\\.");
		mascara = mascara.replace("*", ".*");
		mascara = mascara.replace("?", ".");
		return mascara;
	}
    
    private boolean fullTree = false;
    private Tree activeTree;
       
	public static final String FEATURES_URL = "/features/features.xml";
	public static final String IMAGE_TEECHARTHEADER_URL = "/features/images/header.jpg";
	public static final String IMAGE_TEECHARTLOGO_URL = "/features/images/TeeChartLogo.gif";
	public static final String IMAGE_SHELLICON_URL = "/features/images/TChart.ico";
	public static final String IMAGE_JAVA_URL = "/features/images/java.png";
	public static final String NEWS_URL = "/features/new.xml";
	public static final String SEARCH_URL = "/features/index.xml";
	//public static final String SEARCH_ICON_URL = System.getProperty("user.dir")
	//		+ "/features/images/Search-icon.png";
	public static final String SEARCH_ICON_URL = "/features/images/Search-icon.png";
	public static final String NEXT_ICON_URL = "/features/images/Button-Next-icon.png";
	public static final String PREVIOUS_ICON_URL = "/features/images/Button-Previous-icon.png";
//	public static final String FOLDER_ICON_URL = System.getProperty("user.dir")
//			+ "/features/images/Folder-Blank-11-icon.png";
	public static final String FOLDER_ICON_URL = "/features/images/Folder-Blank-11-icon.png";
	public static final String FIRST_ICON_URL = "/features/images/Button-First-icon.png";
	public static final String LAST_ICON_URL = "/features/images/Button-Last-icon.png";
	public static final String ABOUT_ICON_URL = "/features/images/about-icon.png";
	public static final String CLOSE_ICON_URL = "/features/images/Button-Close-icon.png";
//	public static final String CLEAR_ICON_URL = System.getProperty("user.dir")
//			+ "/features/images/Symbols-Delete-icon.png";
//	public static final String GREY_BACKGROUND_URL = System
//			.getProperty("user.dir") + "/features/images/greyBackGround.jpg";
//	public static final String INITIAL_BACKGROUND_URL = System
//			.getProperty("user.dir") + "/features/images/initialBackground.jpg";
	public static final String CLEAR_ICON_URL = "/features/images/Symbols-Delete-icon.png";
	public static final String GREY_BACKGROUND_URL = "/features/images/greyBackGround.jpg";
	public static final String INITIAL_BACKGROUND_URL = "/features/images/initialBackground.jpg";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Parameters.loadProperties();
		TeeChartSWT instance = new TeeChartSWT();
		instance.run();
		Parameters.saveProperties();
	}
}
