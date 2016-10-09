package com.psrt.threads;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.artemis.Entity;
import com.artemis.World;
import com.psrt.entities.components.ValueComponent;
import com.psrt.guitabs.factories.ValueFactory;
import com.psrt.main.Main;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class for handling updating the GUI interface.  Other classes place entities in queue for pushing
 * to the UI thread.
 * @author Austin Dibble
 */
public class UIThread implements Runnable{
	
	
	/*************************************
                PRIVATE FIELDS
	**************************************/		
	private BlockingQueue<Entity> entityQueue;
	private World world;
	
	private Stage primaryStage;
    private TabPane tabOverview;
    private Scene mainScene;
    
    private Main main;
    
	/*************************************
				PUBLIC FIELDS
	**************************************/	
    
    
    
    
	
    /**
     * UIThread runs all the updating GUI elements. It receives entities from around the program and puts them to the UI.
     * @param primaryStage - javaFX main stage
     * @param world - entity system's main controller, world
     */
	public UIThread(Stage primaryStage, World world, Main main){
		System.out.println("Initializing UIThread");
		System.out.println("Launching UI");
		entityQueue = new ArrayBlockingQueue<Entity>(1024);
		this.world = world;
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tablet Telemetry GUI v1.7.3");
        try {
			this.primaryStage.getIcons().add(new Image(Main.class.getResource("res/images/other/logo.png").openStream()));
		} catch (IOException e) {
			System.out.println("Couldn't load icon.");
		}
        
        this.main = main;
        
        loadGUI();
        
        System.out.println("UIThread initialized.");
        
        /*
         * Once the number of GUI elements gets out of hand (which will be soon) this process will hold the key to extracting and automating the 
         * transition from GUI element to Java Objects. It goes down the tree of nodes by getting the TabPane, then the tabs, then the anchorpane from 
         * each tab, then the elements from each anchorpane, and so on. Only issue is converting from Node objects to the correct scene objects (ie. Label, ImageView, etc.)
         * But that could be done with a little naming magic, perhaps.
         */
        ObservableList<Tab> tabs = tabOverview.getTabs();
        System.out.println("Tabs: " + tabs.size());
        for(int i = 0; i < tabs.size(); i++){
        	Tab t = tabs.get(i);
        	String id = t.getId();
        	System.out.println(id);
        	if(id.contains("tab")){
        		Node c = t.getContent();
        		if(c.getId().contains("anchor")){
        			AnchorPane ap = (AnchorPane) c;
        			
        			ObservableList<Node> children = ap.getChildren();
        			for(int j = 0; j < children.size(); j++){
        				Node n = children.get(j);
        				System.out.println("\tAnchorPane[" + i + "]: ID: " + n.getId());
        				ValueFactory.if_tree_of_doom(n, main, this);  //Oh the if hierarchies... This should only run once
        			}
        		}
        	}
        }
	}	

    /**
     * Shows the tab overview inside the root layout.
     */
    private void loadGUI() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("res/GUI.fxml"));
            tabOverview = (TabPane) loader.load();
            
            // Set person overview into the center of root layout.
//            rootLayout.setCenter(tabOverview);
            mainScene = new Scene(tabOverview);
            primaryStage.setScene(mainScene);
//          //primaryStage.setFullScreen(true);
          	primaryStage.centerOnScreen();
          	primaryStage.show();
            
            new WebcamView(loader, tabOverview, world);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Overriden from {@link Thread#run()}.  With each call to run() on the thread, this function
	 * updates all entities on the update queue.
	 */
	@Override
	public void run() {
		//temporary
		world.process(); //Runs all the data for the entity system. Should always be pretty fast.
		
		//A better way to do this with JavaFX Service's exists. But this works for now.
		if(entityQueue.size() > 0){ //sends entity data to GUI!
			Platform.runLater(new Runnable(){
				@Override
	            public void run() {
					int num = Math.min(30, entityQueue.size());
					
					for(int i = 0; i < num; i++){
						Entity e = null;
		        		try {
		        			e = pull();
		        		} catch (InterruptedException e1) {
		        			e1.printStackTrace();
		        		}
		        		
		        		@SuppressWarnings("rawtypes")
		        		ValueComponent v = null;
		        		if(e != null) v = main.getValueFactory().getValue(e);
						
		        		if(v != null) {
		        			//System.out.println(v.getReference());
		        			v.update();
		        		}
					}
	            }
	        });	
		}
	}
	
	/**************************************************************************
                                  UTILITIES (Queue Accessors)
	***************************************************************************/


    /**
     * Puts entity into update queue for GUI retrieval.  This method is synchronized. 
     * See {@link BlockingQueue#poll()}
     * @param e - {@link Entity} to push onto queue.
     * @throws InterruptedException
     */
	public synchronized void inject(Entity e) throws InterruptedException{
		entityQueue.put(e);
	}
	
	/**
	 * Retrieves and removes the head of the queue of entities. See {@link BlockingQueue#poll()}
	 * @return {@link Entity} 
	 * @throws InterruptedException
	 */
	public synchronized Entity pull() throws InterruptedException{
		return entityQueue.poll();
	}
	
	
    
	
    /***************************************************************************
                                       GETTERS
     ***************************************************************************/
	
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public TabPane getRoot(){
		return this.tabOverview;
	}
	
}
