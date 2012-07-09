Ext.define("FinancialRecorderApp.view.Main", {
    extend: 'Ext.tab.Panel',
    
	requires: [
		'FinancialRecorderApp.view.RecorderList'		
    ],
	
	config: {
        items: [{
            xtype: 'recorderlist'
        }]
    }
});