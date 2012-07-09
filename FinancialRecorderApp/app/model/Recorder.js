Ext.define('FinancialRecorderApp.model.Recorder', {
	extend: 'Ext.data.Model',
	config: {
		fields: [
					{name: 'name', type: 'string'},
					{name: 'totalFee', type: 'int'},
					{name: 'userNameList', type: 'auto'}
				]
	}
});