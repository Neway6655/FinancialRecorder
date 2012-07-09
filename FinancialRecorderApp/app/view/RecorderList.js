Ext.define('FinancialRecorderApp.view.RecorderList', {
    extend: 'Ext.List',
    xtype: 'recorderlist',
	
    requires: ['FinancialRecorderApp.store.Recorder'],
    
    config: {
        title: 'Activities',
        itemTpl:
				'{name}---' + 
				'<small>TotalFee: {totalFee}</small></br>' + 
				'<small>({userNameList})</small>',
        store: 'Recorder',
    }
});