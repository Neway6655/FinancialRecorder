/**
 * @author eortwyz
 */
app.view.recorderListTitle = new Ext.Toolbar({
	dock : 'top',
	title: 'My Recorders',
	layout: 'hbox',
	items: [{
		text: 'new',
		ui: 'action',
		handler: function(){
			Ext.dispatch({
				controller: app.controllers.recorder,
                action: 'newRecorder',
                animation: {type:'slide', direction:'left'}
        	});
		}
	}]
});

recorder.store = Ext.data.Store({
  	model: app.model.recorder,
  	autoLoad: true
});

app.view.recorderList = new Ext.List({
	store: 'Recorder',
	itemTpl: '<div class="list-item-title">{name}</div>' +
        '<div class="list-item-narrative">{price}</div>',                
    listeners: {
    	itemtap: function(thisCompoment, index, item, e){
    		var recorder = thisCompoment.store.getAt(index);
    		Ext.dispatch({
    			controller: app.controllers.recorder,
                action: 'edit',
                record: recorder,
                animation: {type:'slide', direction:'left'}
    		});
    	}
    }
});
 
app.view.recorderListView = new Ext.Panel({
	dockedItems: [app.view.recorderListTitle, app.view.recorderList],
	layout: 'fit'
});