/**
 * @author eortwyz
 */
app.controllers.recorder = new Ext.Controller({
	newRecorder: function(options){
		var record = Ext.ModelMgr.create({id:'',name:'',price:'',description:''},'Recorder');
		app.view.recorderDetailForm.load(record);
		app.viewport.setActiveItem(1);
	},
	
	edit: function(options){
		// load form data
		app.view.recorderDetailForm.load(options.record);
		app.viewport.setActiveItem(1);
	},
	
	save: function(options){
		app.view.recorderDetailForm.updateRecord(options.record);
        var id = options.record.data.id;
        if(app.view.recorderList.store.findRecord('id',id)==null){
        	  app.view.recorderList.store.add(currentRecord);
        }
        app.view.recorderList.store.sync();
		app.viewport.setActiveItem(0);
	}
});