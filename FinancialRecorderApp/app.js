Ext.application({
    name: 'Sencha',

	requires: [
        'Ext.List',
        'Ext.data.proxy.JsonP',
        'Ext.tab.Panel',
		'Ext.TitleBar',
		'Ext.data.Store'
    ],
	
    launch: function() {
		this.data = {};
		
		this.data.recorder = Ext.define('Recorder', {
			extend: 'Ext.data.Model',
			config: {
				fields: [
							{name: 'name', type: 'string'},
							{name: 'totalFee', type: 'int'},
							{name: 'userNameList', type: 'auto'}
						]
			}
		});
		
		Ext.create('Ext.data.Store',{
				autoLoad: true,
			    model: this.data.recorder,
			    proxy: {
			        type: 'jsonp',
			        url : 'http://financialrecorder.cloudfoundry.com/api/jsonp/finance/list',			        
					reader: {
						type: 'json',
						rootProperty: 'recordList'
					}
				},
				storeId: 'recordStore'
			});
			
		this.recorderList = Ext.create('Ext.List',{
			title: 'Activity List',
			store: 'recordStore',
            itemTpl:
                '{name}---' + 
                '<small>TotalFee: {totalFee}</small></br>' + 
				'<small>({userNameList})</small>'
            /*listeners: {
            	itemtap: function(thisCompoment, index, item, e){
            		var recorder = thisCompoment.store.getAt(index);
            		nv.form.load(recorder);
            		nv.viewport.setActiveItem(1);
            	}
            }*/
		  });

		  this.recorderListActions = new Ext.Toolbar({
			title:'',
			docked: 'bottom',
			items: [{
				text: 'Add'
				/*handler: function(){
					var recorder = Ext.ModelMgr.create({name:'',totalFee:''},'Recorder');
					nv.form.load(recorder);
					nv.viewport.setActiveItem(1);
				}*/
			}]
		  });
		  
		this.recorderListView = Ext.create("Ext.tab.Panel",{
			items: [
				{
					html: this.recorderList,
					docked: 'top'
				},
				{
					html: this.recorderListActions,
					docked: 'bottom'
				}],
		  });
	
        Ext.create("Ext.tab.Panel", {
            fullscreen: true,
            tabBarPosition: 'top',

            //items: [this.recorderListTitle,this.recorderList,this.recorderListActions]
			items: [
				{
					html: this.recorderList,
					docked: 'top'
				},
				{
					html: this.recorderListActions,
					docked: 'bottom'
				}]
        });
    }
});