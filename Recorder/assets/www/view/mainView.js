/**
 * @author eortwyz
 */
app = new Ext.Application({
	launch: function () {
    	this.views.viewport = new this.views.Viewport();
    }
});

app.views.Viewport = Ext.extend(Ext.Panel,{
	fullscreen : true,
	layout: 'card',
	cardSwitchAnimation: 'slide',
	initComponent : function(){
		Ext.apply(app.views,{
			recorderList: new app.views.recorderListView(),			
			recorderDetail: new app.views.recorderDetailView()
		}),
		Ext.apply(this, {
			items :[app.views.recorderList,app.views.recorderDetail]
		})
		
		app.views.Viewport.superclass.initComponent.apply(this, arguments);
	}
	
});