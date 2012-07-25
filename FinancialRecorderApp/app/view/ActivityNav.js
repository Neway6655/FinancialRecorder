Ext.define('FinancialRecorderApp.view.ActivityNav', {
    extend: 'Ext.navigation.View',
    xtype: 'activitypanel',
    requires: [
        'FinancialRecorderApp.view.ActivityList'        
    ],

    config: {
        items: [{
            xtype: 'activitylist'
        }]
    }
});