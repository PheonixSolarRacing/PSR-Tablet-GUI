package com.psrt.threads;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextComponent;
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

public class UIThread implements Runnable{
	
	BlockingQueue<Entity> entityQueue;
	World world;
	
	private Stage primaryStage;
    private TabPane tabOverview;
    private Scene mainScene;
    
    private Main main;
    
    ComponentMapper<TextComponent> tm;
    ComponentMapper<ProgressComponent> pm;
	
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
        this.primaryStage.setTitle("Tablet Telemetry GUI v1.7");
        try {
			this.primaryStage.getIcons().add(new Image(Main.class.getResource("res/images/other/logo.png").openStream()));
		} catch (IOException e) {
			System.out.println("Couldn't load icon.");
		}
        
        this.main = main;
        
        tm = world.getMapper(TextComponent.class);
        pm = world.getMapper(ProgressComponent.class);
        
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
        			AnchorPane ap = (AnchorPane)c;
        			
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
	
	public TabPane getRoot(){
		return this.tabOverview;
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
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	
	public synchronized void inject(Entity e) throws InterruptedException{
		entityQueue.put(e);
	}
	
	public synchronized Entity pull() throws InterruptedException{
		return entityQueue.poll();
	}
	
	
	//private Service<Void> backgroundThread;
	
	@Override
	public void run() {
		//temporary
		world.process(); //Runs all the data for the entity system. Should always be pretty fast.
		
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
}
