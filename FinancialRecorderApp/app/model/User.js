Ext.define('FinancialRecorderApp.model.User', {
	extend: 'Ext.data.Model',
	config: {
		fields: [
					{name: 'id', type: 'int'},
					{name: 'name', type: 'string'},
					{name: 'balance', type: 'int'}
				]
	}
});