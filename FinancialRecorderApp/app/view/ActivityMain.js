Ext.define('FinancialRecorderApp.view.ActivityMain', {
    extend: 'Ext.Panel',
	xtype: 'mainpanel',

	requires: [
		'FinancialRecorderApp.view.ActivityList'
    ],
	
	config: {
		layout: 'fit',
        items: [{
        	xtype: 'titlebar',
        	title: 'Activity List',
        	docked: 'top',
        	items: [{
        		xtype: 'button',
        		ui: 'action',
        		text: 'New',
        		align: 'right',
        	}]
        },{
            xtype: 'activitylist'
        }]
    }
});