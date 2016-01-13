package com.psrt.model.threads;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.psrt.entities.components.TextComponent;
import com.psrt.guitabs.*;
import com.psrt.main.Main;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIThread implements Runnable{
	
	BlockingQueue<Entity> entityQueue;
	World entityWorld;
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    private TabPane tabOverview;
    private Scene mainScene;
    
    private Main main;
    
    ComponentMapper<TextComponent> tm;
	
	public UIThread(Stage primaryStage, World world){
		System.out.println("Initializing UIThread");
		System.out.println("Launching UI");
		entityQueue = new ArrayBlockingQueue<Entity>(1024);
		this.entityWorld = world;
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tablet Telemetry GUI v1.3");
        
   
        
        this.main = Main.getMain();
        
        tm = world.getMapper(TextComponent.class);
        
        initRootLayout();
        loadGUI();
        
        System.out.println("UIThread initialized.");
        
        /*
         * Once the number of GUI elements gets out of hand (which will be soon) this process will hold the key to extracting and automating the 
         * transition from GUI element to Java Object. It goes down the tree of nodes by getting the TabPane, then the tabs, then the anchorpane from 
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
        				if_tree_of_doom(world, n);  //Oh the if heirarchies...
        			}
        		}
        	}
        	
        }
	}

	
	 private void if_tree_of_doom(com.artemis.World world, Node n) {
			if(n.getId() != null){ //Example of retreiving all elements automatically... Could be easier? Hmm
				if(n.getId().equals("battery_1_voltage")){
					PDBTab.battery_1_voltage(world, n);
				}else if(n.getId().equals("SOC_Label")){
					BMSTab.SOC_Label(world, n);
				}
			}
	}

	private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("res/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            mainScene = new Scene(rootLayout);
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
            rootLayout.setCenter(tabOverview);
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
	
	
	private Service<Void> backgroundThread;
	
	@Override
	public void run() {
		//temporary
		
		entityWorld.process();
		
		
		if(entityQueue.size() > 0){
			
			/*
			Entity e = null;
    		try {
    			e = pull();
    		} catch (InterruptedException e1) {
    			e1.printStackTrace();
    		}
    		TextComponent tc = tm.getSafe(e);
    		
			
			backgroundThread = new Service<Void>(){
				@Override
				protected Task<Void> createTask(){
					return new Task<Void>(){

						@Override
						protected Void call() throws Exception {
							
							System.out.println("Setting value of: " + tc.getValue());
				            //tc.getLabel().setText(tc.getValue());
							updateMessage(tc.getValue());
							return null;
						}
						
					};
				}
			};
			
    		
			backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
				@Override
				public void handle(WorkerStateEvent arg0) {
					System.out.println("Done");
					tc.getLabel().textProperty().unbind();
				}
			});
			
			tc.getLabel().textProperty().bind(backgroundThread.messageProperty());
			
			backgroundThread.restart();
			*/
		}
		
		
		if(entityQueue.size() > 0){
			Platform.runLater(new Runnable(){
				@Override
	            public void run() {
					int num = Math.min(10, entityQueue.size());
					for(int i = 0; i < num; i++){
						Entity e = null;
		        		try {
		        			e = pull();
		        		} catch (InterruptedException e1) {
		        			e1.printStackTrace();
		        		}
		        		TextComponent tc = tm.getSafe(e);
		        		//System.out.println("Setting value of: " + tc.getValue());
		                tc.getLabel().setText(tc.getValue());
					}
	            }
	        });	
		}
		
		
	}
}
