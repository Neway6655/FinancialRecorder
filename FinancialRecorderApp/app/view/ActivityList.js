Ext.define('FinancialRecorderApp.view.ActivityList', {
    extend: 'Ext.List',
	requires: ['FinancialRecorderApp.store.Recorder'],
    xtype: 'activitylist',    
    
    config: {
        title: 'Activities',		
        itemTpl:
				'{name}---' + 
				'<small>TotalFee: {totalFee}</small></br>' + 
				'<small>({userNameList})</small>',
        store: Ext.create("FinancialRecorderApp.store.Recorder")		
    }
});