Ext.define('FinancialRecorderApp.view.ActivityMain', {
    extend: 'Ext.Panel',
	xtype: 'mainpanel',

	requires: [
		'FinancialRecorderApp.view.ActivityList'
    ],
	
	config: {
		layout: 'fit',
    },

    initialize: function() {
        var topBar = {
            xtype: 'titlebar',
            title: 'Activity List',
            docked: 'top',
            items: [{
                xtype: 'button',
                ui: 'action',
                text: 'New',
                align: 'right',
                handler: this.create,
                scope: this
            }],
        };

        var activityList = {
            xtype: 'activitylist',
        };

        this.add(topBar, activityList);
    },

    create: function(){
        console.log('create an new record');
        this.fireEvent('showNewFinancialRecordEvent', this);
    },
});