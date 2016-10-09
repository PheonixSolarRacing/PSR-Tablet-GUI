package com.psrt.threads;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class WebcamView {
	
	private class WebCamInfo {
		private String webCamName;
		private int webCamIndex;
		//private Dimension[] dimensions;

		public String getWebCamName() {
			return webCamName;
		}

		public void setWebCamName(String webCamName) {
			this.webCamName = webCamName;
		}

		public int getWebCamIndex() {
			return webCamIndex;
		}

		public void setWebCamIndex(int webCamIndex) {
			this.webCamIndex = webCamIndex;
		}
		//public void setDimensions(Dimension[] dimensions){ this.dimensions = dimensions;}
		
		//public Dimension[] getDimensions(){return this.dimensions;}

		@Override
		public String toString() {
			return webCamName;
		}
	}
	
	private class DimensionInfo{
		private Dimension d;
		private String name = null;
		public DimensionInfo setDimension(int width, int height){
			this.d = new Dimension(width, height);
			return this;
		}
		public DimensionInfo setName(String n){
			name = n;
			return this;
		}
		public String getName(){return this.name;}
		public Dimension getDimension(){return this.d;}
		
		@Override
		public String toString(){
			if(name == null)
				{name = "Res: " + d.getWidth() + " x " + d.getHeight();}
			
			return name;
		}
	}
	
	ToggleButton btnToggleCamera;

	ComboBox<WebCamInfo> cbCameraOptions;
	
	ComboBox<DimensionInfo> cbResolutionOptions;

	BorderPane bpWebCamPaneHolder;

	ImageView imgWebCamCapturedImage;
	
	AnchorPane ap;
	
	FXMLLoader loader;
	TabPane tabOverview;
	com.artemis.World world;
	
	private BufferedImage grabbedImage;
	private Webcam selWebCam = null;
	private boolean stopCamera = false;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
	private String cameraListPromptText = "Choose Foto taker";
	private Dimension maxRes = null;
	
	public WebcamView(FXMLLoader loader, TabPane tabpane, com.artemis.World world){
		this.loader = loader;
		this.tabOverview = tabpane;
		this.world = world;
		
		getNodes();
		cbCameraOptions.setDisable(true);
		
		ObservableList<DimensionInfo> optionsRes = FXCollections.observableArrayList();
		optionsRes.add(new DimensionInfo().setName("Default"));
		optionsRes.add(new DimensionInfo().setDimension(176, 144).setName("176 x 144"));
		optionsRes.add(new DimensionInfo().setDimension(320, 240).setName("320 x 240"));
		optionsRes.add(new DimensionInfo().setDimension(640, 480).setName("640 x 480"));
		optionsRes.add(new DimensionInfo().setDimension(1280, 720).setName("1280 x 720 (Careful)"));
		optionsRes.add(new DimensionInfo().setDimension(1920, 1080).setName("1920 x 1080 (Careful)"));
		
		cbResolutionOptions.setItems(optionsRes);
		cbResolutionOptions.setPromptText("Select a resolution.");
		cbResolutionOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DimensionInfo>() {

			@Override
			public void changed(ObservableValue<? extends DimensionInfo> arg0, DimensionInfo arg1, DimensionInfo arg2) {
				if (arg2 != null) {					
					if(!arg2.getName().equals("Default")){
						System.out.println("Selected max resolution: " + arg2.getDimension().getWidth() + " x " + arg2.getDimension().getHeight());
						maxRes = arg2.getDimension();
					}
					for(Webcam cam : Webcam.getWebcams()){
						Dimension chosen = cam.getViewSizes()[cam.getViewSizes().length - 1];
						cam.setCustomViewSizes(new Dimension[]{new Dimension(640, 480), new Dimension(1280, 720), new Dimension(1920, 1080)});
						if(maxRes == null){ 
							System.out.println("maxres == null");
							cam.setViewSize(chosen);
						}else {
							System.out.println("using maxres");
							cam.setViewSize(maxRes);
						}
					}
					cbCameraOptions.setDisable(false);
				}
			}
		});
		
		
		ObservableList<WebCamInfo> options = FXCollections.observableArrayList();
		int webCamCounter = 0;
		for (Webcam webcam : Webcam.getWebcams()) {
			WebCamInfo webCamInfo = new WebCamInfo();
			System.out.println("Cams: " + webCamInfo.webCamName);
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			//webCamInfo.setDimensions(webcam.getViewSizes());
			options.add(webCamInfo);
			webCamCounter++;
		}
		
		cbCameraOptions.setItems(options);
		cbCameraOptions.setPromptText(cameraListPromptText);
		cbCameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {

			@Override
			public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
				if (arg2 != null) {
					System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
					initializeWebCam(arg2.getWebCamIndex());
					cbResolutionOptions.setDisable(true);
				}
			}
		});
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				setImageViewSize();
			}
		});
	}
	
	private void getNodes() {
		ObservableList<Tab> tabs = tabOverview.getTabs();
        for(int i = 0; i < tabs.size(); i++){
        	Tab t = tabs.get(i);
        	String id = t.getId();
        	if(id.contains("tab")){
        		Node c = t.getContent();
        		if(c.getId().contains("anchor")){
        			ap = (AnchorPane)c;
        			ObservableList<Node> children = ap.getChildren();
        			for(int j = 0; j < children.size(); j++){
        				Node n = children.get(j);
        				identityCheck(n);
        			}
        		}
        	}
        }
	}

	@SuppressWarnings("unchecked")
	private void identityCheck(Node n) {
		if(n.getId() != null){
			switch (n.getId()){
			
			case "btnToggleCamera":
				btnToggleCamera = (ToggleButton) n;
				break;
			case "cbCameraOptions":
				cbCameraOptions = (ComboBox<WebCamInfo>) n;
				break;
			case "cbResolutionOptions":
				cbResolutionOptions = (ComboBox<DimensionInfo>) n;
				break;
			case "bpWebCamPaneHolder":
				bpWebCamPaneHolder = (BorderPane) n;
				for(Node bpn: bpWebCamPaneHolder.getChildren()){
					if(bpn.getId() != null && bpn.getId().equals("imgWebCamCapturedImage")){
						imgWebCamCapturedImage = (ImageView) bpn;
					}
				}
				break;
			case "imgWebCamCapturedImage":
				System.out.println("Imageview detected");
				imgWebCamCapturedImage = (ImageView) n;
				break;
			default:
				//System.out.println("WebcamView: Didn't match.");
				return;
			
			}
		}
		
	}

	protected void setImageViewSize() {
		
		double height = bpWebCamPaneHolder.getHeight();
		double width = bpWebCamPaneHolder.getWidth();
		
		if(imgWebCamCapturedImage == null) System.out.println("Imageview null");
		imgWebCamCapturedImage.setFitHeight(height);
		imgWebCamCapturedImage.setFitWidth(width);
		imgWebCamCapturedImage.prefHeight(height);
		imgWebCamCapturedImage.prefWidth(width);
		//Make the view mirrored
		imgWebCamCapturedImage.setScaleX(-1);
		imgWebCamCapturedImage.setPreserveRatio(true);

	}
	
	protected void initializeWebCam(final int webCamIndex) {

		Task<Void> webCamIntilizer = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				if (selWebCam == null) {
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				} else {
					closeCamera();
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				}
				int cams = selWebCam.getViewSizes().length;
				for(int i = 0; i < cams; i++){
					System.out.println("WebcamView: Resolution[" + i + "] - W: " + selWebCam.getViewSizes()[i].getWidth() + ", H: " + selWebCam.getViewSizes()[i].getHeight());
				}
				
				System.out.println("WebcamView: Current Resolution - W:" + selWebCam.getViewSize().getWidth() + ", H: " + selWebCam.getViewSize().getHeight());
				startWebCamStream();
				return null;
			}
		};

		new Thread(webCamIntilizer).start();
		ap.setDisable(false);
		tabOverview.setDisable(false);
		bpWebCamPaneHolder.setDisable(false);
	}
	

	protected void startWebCamStream() {

		stopCamera = false;
		Task<Void> task = new Task<Void>() {
			
			//long ticks = 0;

			@Override
			protected Void call() throws Exception {
				
				while (!stopCamera) {
					try {
						if ((grabbedImage = selWebCam.getImage()) != null) {
							
							//grabbedImage.
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									final Image mainiamge = SwingFXUtils
										.toFXImage(grabbedImage, null);
									imageProperty.set(mainiamge);
								}
							});

							grabbedImage.flush();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return null;
			}

		};
		
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		imgWebCamCapturedImage.imageProperty().bind(imageProperty);

	}
	

	public void stopCamera(ActionEvent event) {
		stopCamera = true;
	}

	public void startCamera(ActionEvent event) {
		stopCamera = false;
		startWebCamStream();
	}
	
	private void closeCamera() {
		if (selWebCam != null) {
			selWebCam.close();
		}
	}

}
