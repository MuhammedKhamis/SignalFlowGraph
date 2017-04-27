package Application;



public class ApplicationInitializer {
	
	//01_ATTRIBUTES
	//*************************************************************
	private MAIN application;
	private ElementInitializer elements;
	private LayoutInitializer layout;
	private ActionListenerInitializer actionListeners;
	//private CanvasControlsHandler canvasControls;
	//private HotKeysHandler hotKeys;
	
	
	//02_CONSTRUCTOR
	//*************************************************************
	public ApplicationInitializer(MAIN application){
		this.application=application;
		elements=new ElementInitializer();
		layout=new LayoutInitializer(elements);
		actionListeners=new ActionListenerInitializer(elements, layout);
		//canvasControls=new CanvasControlsHandler(elements);
		//hotKeys=new HotKeysHandler(elements);
		
	}
	
	//03_METHODS
	//*************************************************************
	public void initialize(){
		elements.initialize();
		actionListeners.initialize();
		//canvasControls.initialize();
		//hotKeys.initialize();
		layout.initialize();
		application.setLayout(elements.getLayout());
	}
	
	
}
