/**
 * @author eortwyz
 */
app.view.recorderDetailTitle = new Ext.Toolbar({
	dock : 'top',
	title: 'Recorder',
	layout: 'hbox',
	items: [{
		text: 'Save',
		ui: 'confirm',
        handler: function() {
        	Ext.dispatch({
        		controller: app.controllers.recorder,
                action: 'save',
                record: app.view.recorderDetailForm.getRecord(),
                animation: {type:'slide', direction:'left'}
        	});
//          	  var currentRecord = app.form.getRecord();
//          	  app.form.updateRecord(currentRecord);
//          	  var id = currentRecord.data.id;
//          	  if(app.recorderList.store.findRecord('id',id)==null){
//          	  	  app.recorderList.store.add(currentRecord);
//          	  }
//          	  app.recorderList.store.sync();
          }
        },
		{
		text: 'Back',
		handler: function(){
			app.viewport.setActiveItem(0);
		}
	}]
});

app.view.recorderDetailForm = new Ext.form.FormPanel({
	scroll: 'vertical',
	standardSubmit : false,
	items: [{
		xtype: 'fieldset',
     	title: 'Recorder Info',
     	instructions: 'Please enter the information above.',
     	defaults: {
         	required: true,
         	labelAlign: 'left',
         	labelWidth: '40%'
     	},
		items: [
		{
			xtype: 'textfield',
			name : 'id',
			hidden: true
		},
		{
			xtype: 'textfield',
	        name : 'name',
	        label: 'Name',
	        useClearIcon: true,
	        autoCapitalize : false
		},
	    {
			xtype: 'numberfield',
			name : 'price',
			label: 'Money',
			useClearIcon: true,
	        autoCapitalize : false
	    },
		{
			xtype: 'textfield',
			name : 'description',
			label : 'Description',
			useClearIcon: true,
	        required : false
		}]
	}]
});			 
 
app.view.recorderDetailView = new Ext.Panel({
	dockedItems: [app.view.recorderDetailTitle, app.view.recorderForm],
	layout: 'fit'
});
