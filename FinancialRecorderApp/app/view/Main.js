Ext.define('FinancialRecorderApp.view.Main', {
    extend: 'Ext.navigation.View',
	id: 'mainView',	
	xtype: 'mainpanel',
    
	requires: [
		'FinancialRecorderApp.view.ActivityList'		
    ],
	
	config: {
        items: [{
            xtype: 'activitylist'
        }]
    }
	
	/*requires: [
		'FinancialRecorderApp.view.ActivityNav'	
    ],
	
	config: {		
		tabBarPosition: 'bottom',
		layout: 'card',
		fullscreen: true,
		cardSwitchAnimation:'slide',
        items: [
		{
			title: 'Activity',
			iconCls: '',
            xtype: 'activitypanel'
        }
		]
    }*/
});