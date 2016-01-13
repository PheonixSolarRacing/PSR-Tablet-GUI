package com.psrt.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.utils.IntBag;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.systems.TimingSystem;
import com.psrt.entities.systems.ValueSystem;
import com.psrt.model.threads.UIThread;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static Main main;
	
    private static com.artemis.World world;
    
    private UIThread uiThread;
    
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(6);
   
    public Main(){
    	
    }
    
    private void initAll(Stage primaryStage){
    	initConfig();
    	initUIThread(primaryStage);
    	
    	startThreads();
    }

	public synchronized static Main getMain(){
    	return main;
    }
	
	public synchronized static World getWorld(){
		return world;
	}
    
    public synchronized void sendToUI(Entity e){
    	try {
			uiThread.inject(e);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    }
    
    private void initConfig() {
    	WorldConfiguration config = new WorldConfiguration();
    	config.setSystem(new ValueSystem(this));
    	config.setSystem(new TimingSystem());
    	//add systems here...
    	
    	world = new com.artemis.World(config);
		
		
	}
    
    private void initUIThread(Stage primaryStage){
    	uiThread = new UIThread(primaryStage, world);
    }
    
    private void startThreads() {
		scheduler.scheduleAtFixedRate(uiThread, 0, 15, TimeUnit.MILLISECONDS);
		scheduler.scheduleAtFixedRate(new Thread("Test data"){
			long ticks = 0;
			ComponentMapper<TextComponent> tm;
			
			@Override
			public void run(){
				//System.out.println("Run");
				ValueSystem v = world.getSystem(ValueSystem.class);
				EntitySubscription sub = v.getSubscription();
				if(ticks == 0){
					tm = world.getMapper(TextComponent.class);
				}
				else{
					IntBag b = sub.getEntities();
					for(int i = 0; i < b.size(); i++){
						int id = b.get(i);
						TextComponent tc = tm.getSafe(id);
						if(tc.getReference().equals("speed_display")){
							//System.out.println("True");
							tc.setValue(""+ticks);
						}if(tc.getReference().equals("battery_1_voltage")){
							//tc.setValue(""+ticks);
						}if(tc.getReference().equals("battery_2_voltage")){
							tc.setValue(""+ticks);
						}
					}
				}
				
				ticks++;
			}
		}, 0, 100, TimeUnit.MILLISECONDS);
    	//scheduler.scheduleAtFixedRate(entityThread, 30, 30, TimeUnit.MILLISECONDS);
	}

	/**
     * Called by the JavaFX API after it's done with its magic.
     * All I've got here is a call to the "Main" constructor and, after that, a call to a method which starts
     * initializing the UI.
     */
    @Override
    public void start(Stage primaryStage) {
        Main.main = new Main();
        this.initAll(primaryStage);
    }
    
    /**
     * Called when the GUI is closed.  Right now it is used to close the ThreadPoolExecutor which is running different threads. 
     * Better solutions exist, so this could change.
     */
    @Override
    public void stop(){
    	this.scheduler.shutdown();
    	try {
			this.scheduler.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // wait for 10s in this case
    	this.scheduler.shutdownNow();
    }

    /**
     * As in all Java programs, the code starts here.
     * "launch(args);" Calls the JavaFX API and starts that magic.
     * @param args
     */
    public static void main(String[] args) {
    	System.out.println("Starting");
        launch(args);
        System.out.println("Clean exit");
    }
}
