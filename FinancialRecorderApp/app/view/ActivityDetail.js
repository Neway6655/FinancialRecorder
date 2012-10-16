Ext.define('FinancialRecorderApp.view.ActivityDetail', {
    extend: 'Ext.Panel',
    requires: "Ext.form.FieldSet",
    xtype: 'activitydetail',

    config:{
    	layout: 'fit',
        scrollable:'vertical',
    },

    formPanel: '',

	initialize: function() {
		console.log('init activity detail view.');

		this.formPanel = Ext.create('Ext.form.Panel',{
			items: [{
				xtype: 'fieldset',
				instructions: 'Please check the activity detail information.',
				defaults: {
					required: true,
					labelAlign: 'left',
					labelWidth: '20%'
				},
				items: [
				{
					xtype: 'textfield',
					name : 'name',
					label: 'Name',								
				},
				{
					xtype: 'numberfield',
					name : 'totalFee',
					label: 'Total Fee',
				}],
			}]
		});

		var topBar = {
    		xtype: 'titlebar',
    		docked: 'top',
    		title: 'Activity Detail',
    		items: [{
    			xtype: 'button',
    			ui: 'back',
    			align: 'left',
    			text: 'Back',
    			handler: this.back,
    			scope: this
    		},{
    			xtype: 'button',
    			ui: 'action',
    			align: 'right',
    			text: 'Save'
    		}]
    	};

		// var userSelector = {
		// 	xtype: 'userselector'
		// };

		var bottomBar = {
			xtype: 'titlebar',
			docked: 'bottom',
			items:[{
				xtype: "button",
		        iconCls: "trash",
		        iconMask: true,
		        scope: this
		    }]
		};

		this.add(topBar, this.formPanel, bottomBar);
	},

	loadRecord: function(record){
		this.formPanel.setRecord(record);
	},

	back: function() {
		console.log('back button tapped.');
		this.fireEvent('backToActivityListEvent', this);
	}
});