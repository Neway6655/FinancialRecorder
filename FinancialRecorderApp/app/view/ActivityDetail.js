Ext.define('FinancialRecorderApp.view.ActivityDetail', {
    extend: 'Ext.Panel',
    requires: "Ext.form.FieldSet",
    xtype: 'activitydetail',

    config:{
    	layout: 'fit',
        scrollable:'vertical',
    },

    formPanel: '',

    nameField: '',

    totalFeeField: '',

	initialize: function() {
		console.log('init activity detail view.');

		this.nameField = Ext.create('Ext.field.Text',{
			xtype: 'textfield',
			name : 'name',
			label: 'Name',
		});

		this.totalFeeField =  Ext.create('Ext.field.Number',{
			xtype: 'numberfield',
			name : 'totalFee',
			label: 'Total Fee',
		});

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
					this.nameField,this.totalFeeField
				],
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
		this.nameField.setReadOnly(true);
		this.totalFeeField.setReadOnly(true);
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