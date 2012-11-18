Ext.define('FinancialRecorderApp.store.User',{	

	extend: 'Ext.data.Store',
	requires: [
        'Ext.data.proxy.JsonP',
		'FinancialRecorderApp.model.User'
    ],
	
	config: {
		autoLoad: true,
		model: 'FinancialRecorderApp.model.User',
		proxy: {
			type: 'jsonp',
			url : 'http://financialrecorder.cloudfoundry.com/api/jsonp/user/list',
			reader: {
				type: 'json',
				rootProperty: 'userList'
			}
		},
	}
});