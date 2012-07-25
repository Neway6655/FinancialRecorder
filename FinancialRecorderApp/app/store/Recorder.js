Ext.define('FinancialRecorderApp.store.Recorder',{	

	extend: 'Ext.data.Store',
	requires: [
        'Ext.data.proxy.JsonP',
		'FinancialRecorderApp.model.Recorder'
    ],
	
	config: {
		autoLoad: true,
		model: 'FinancialRecorderApp.model.Recorder',
		proxy: {
			type: 'jsonp',
			url : 'http://financialrecorder.cloudfoundry.com/api/jsonp/finance/list',
			reader: {
				type: 'json',
				rootProperty: 'recordList'
			}
		},
	}
});