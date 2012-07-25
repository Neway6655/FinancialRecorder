Ext.define('FinancialRecorderApp.view.ActivityDetail', {
    extend: 'Ext.form.FormPanel',
    xtype: 'activitydetail',    
    
    scroll: 'vertical',
	
	items: [{
			xtype: 'fieldset',
			title: 'Activity',
			instructions: 'Please check the activity detail information.',
			defaults: {
				required: true,
				labelAlign: 'left',
				labelWidth: '40%'
			},
			items: [
			{
				xtype: 'textfield',
				name : 'name',
				label: 'Name',								
			},
			{
				xtype: 'numberfield',
				name : 'total fee',
				label: 'totalFee',				
			}]
	}]	
});