Ext.Loader.setPath({
	'Ext': 'sdk/src'
});

Ext.application({
    name: 'FinancialRecorderApp',
		
	requires: [
		'FinancialRecorderApp.view.ActivityMain'
    ],

	models: ['Recorder'],
	stores: ['RecorderLocal'],
	controllers: ['Activity'],
	views: ['ActivityMain', 'ActivityList', 'ActivityDetail', 'UserSelector'],
	
	launch: function() {
        // Destroy the #appLoadingIndicator element
        Ext.fly('appLoadingIndicator').destroy();

        // Initialize the main view
        var mainView = Ext.create('FinancialRecorderApp.view.ActivityMain');
        var activityDetailView = Ext.create('FinancialRecorderApp.view.ActivityDetail');
        Ext.Viewport.add(mainView, activityDetailView);
    }
});