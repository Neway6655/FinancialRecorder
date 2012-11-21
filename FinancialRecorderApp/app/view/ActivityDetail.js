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
					labelWidth: '50%'
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
    			text: 'Save',
    			handler: this.save,
    			scope: this
    		}]
    	};

    	userSelectorPanel = Ext.create('Ext.Panel', {
            width: '100%',
            height: '100%',
            top: 120,
            layout: {
                type: 'fit',
            },
            items: [{
                xtype: 'userselector',
            }],
        });

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

		this.add(topBar, this.formPanel, userSelectorPanel, bottomBar);
	},

	loadRecord: function(record){
		this.formPanel.setRecord(record);
	},

	back: function() {
		console.log('back button tapped.');
		this.fireEvent('backToActivityListEvent', this);
	},

	save: function(){
		console.log('save activity.');
		this.fireEvent('saveActivityEvent', this);
	},

	getForm: function(){
		return this.formPanel;
	}

});