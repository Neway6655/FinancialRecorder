Ext.Loader.setPath({
	'Ext': 'sdk/src'
});

Ext.application({
    name: 'FinancialRecorderApp',
		
	requires: [
		'FinancialRecorderApp.view.Main'		
    ],
	config: {
		models: ['Recorder'],
		views: ['Main'],
		stores: ['Recorder'],
		controllers: ['Main'],
	},		
	
	launch: function() {
        // Destroy the #appLoadingIndicator element
        Ext.fly('appLoadingIndicator').destroy();

        // Initialize the main view
        Ext.Viewport.add(Ext.create('FinancialRecorderApp.view.Main'));
    }  
});