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
import com.psrt.guitabs.BMSTab;
import com.psrt.guitabs.MainTab;
import com.psrt.guitabs.PDBTab;
import com.psrt.main.Main;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIThread implements Runnable{
	
	BlockingQueue<Entity> entityQueue;
	World world;
	
	private Stage primaryStage;
    private BorderPane rootLayout;
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
	public UIThread(Stage primaryStage, World world){
		System.out.println("Initializing UIThread");
		System.out.println("Launching UI");
		entityQueue = new ArrayBlockingQueue<Entity>(1024);
		this.world = world;
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tablet Telemetry GUI v1.4");
        this.primaryStage.getIcons().add(new Image("file:../main/res/images/other/logo.png"));
   
        
        this.main = Main.getMain();
        
        tm = world.getMapper(TextComponent.class);
        pm = world.getMapper(ProgressComponent.class);
        
        initRootLayout();
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
        				if_tree_of_doom(n);  //Oh the if hierarchies... This should only run once
        			}
        		}
        	}
        }
	}

	
	 private void if_tree_of_doom(Node n) {
			if(n.getId() != null){ //Example of retrieving all elements automatically... Could be easier? Hmm
				switch (n.getId()){
					case "battery_1_voltage":
						PDBTab.battery_1_voltage(world, n);
						break;
					case "SOC_Label":
						BMSTab.SOC_Label(world, n);
						break;
					case "speed_display":
						MainTab.speed_display(world, n);
						break;
					case "battery_2_voltage":
						PDBTab.battery_2_voltage(world, n);
						break;
					case "soc_indicator":
						BMSTab.soc_indicator(world, n);
						break;
					case "SOC_label":
						BMSTab.SOC_Label(world, n);
				}
			}
	}
	 
	 
	public Node getItem(String name){
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
	        				if(n.getId() != null && n.getId().equals(name)){
	        					return n;
	        				}
	        			}
	        		}
	        	}
	        	
	        }
	        System.out.println("UIThread getItem(): no item of that name found");
	        return null;
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
            primaryStage.centerOnScreen();
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
					int num = Math.min(10, entityQueue.size());
					for(int i = 0; i < num; i++){
						Entity e = null;
		        		try {
		        			e = pull();
		        		} catch (InterruptedException e1) {
		        			e1.printStackTrace();
		        		}
		        		TextComponent tc = tm.getSafe(e);
		        		ProgressComponent pc = (tc == null) ? pm.getSafe(e) : null;
		        		
		        		@SuppressWarnings("rawtypes")
		        		ValueComponent v = null;
		        		
		        		//if tc isn't null set it to v, if tc is null then set pc to v, if pc is null then set v to null
		        		v = ((tc != null) ? tc : ((pc != null) ? pc : null));
		        		v.update();
					}
	            }
	        });	
		}
	}
}
