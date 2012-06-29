  nv = new Ext.Application({

      launch: function () {
			this.data = {};

			this.data.Recorder = Ext.regModel('Recorder', {
				fields: ['id','name','price','description'],
				proxy: {
					type: 'localstorage',
					id  : 'recorder'
				}
			});

		  this.recorderListTitle = new Ext.Toolbar({
			dock : 'top',
			title: 'My Recorders'
		  });

		  this.recorderListActions = new Ext.Toolbar({
			dock: 'bottom',
			items: [{
				text: 'Add',
				handler: function(){
					var recorder = Ext.ModelMgr.create({id:'',name:'',price:'',description:''},'Recorder');
					nv.form.load(recorder);
					nv.viewport.setActiveItem(1);
				}
			}]
		  });

		  this.data.recorders = new Ext.data.Store({
            model: this.data.Recorder,
            autoLoad: true
          });

		  this.recorderList = new Ext.List({
			store: this.data.recorders,
            itemTpl:                
                '{name}---' +                
                'Price: <small>{price}</small>',
            listeners: {
            	itemtap: function(thisCompoment, index, item, e){
            		var recorder = thisCompoment.store.getAt(index);
            		nv.form.load(recorder);
            		nv.viewport.setActiveItem(1);
            	}
            }
		  });

		  this.recorderListView = new Ext.Panel({
			dockedItems: [this.recorderListTitle, this.recorderList, this.recorderListActions],
			layout: 'fit'
		  });

		  this.recorderAddTitle = new Ext.Toolbar({
			dock : 'top',
			title: 'Add Recorders'
		  });
		  
		  this.form = new Ext.form.FormPanel({
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
		   }],
			listeners : {
                submit : function(form, result){
                    console.log('success', Ext.toArray(arguments));
                },
                exception : function(form, result){
                    console.log('failure', Ext.toArray(arguments));
                }
            }			 
		  });
		  
		  this.recorderAddActions = new Ext.Toolbar({
			dock : 'bottom',
			items: [
				{
                  text: 'Save',
                  ui: 'confirm',
                  handler: function() {
                  	  var currentRecord = nv.form.getRecord();
                  	  nv.form.updateRecord(currentRecord);
                  	  var id = currentRecord.data.id;
                  	  if(nv.recorderList.store.findRecord('id',id)==null){
                  	  	  nv.recorderList.store.add(currentRecord);
                  	  }
                  	  nv.recorderList.store.sync();
//                  	  	  // update
//                  	  	  formRecorder = nv.data.recorders.findRecord('id',id);
//                  	  	  
//						  formRecorder.name = nv.form.getValues(true).name;
//				          formRecorder.price = nv.form.getValues(true).price;
//				          formRecorder.description = nv.form.getValues(true).description;
//				          nv.data.recorders.add(formRecorder);
//						  nv.data.recorders.sync();
//                  	  }else{
//						  formRecorder = Ext.ModelMgr.create({
//	                          'name'    : nv.form.getValues(true).name,
//	                          'price': nv.form.getValues(true).price,
//	                          'description' : nv.form.getValues(true).description
//	                      }, 'Recorder');
//						  formRecorder.save();
//	                  }
					  nv.viewport.setActiveItem(this.recorderListView);
                  }
                },
				{
				text: 'Back',
				handler: function(){
					nv.viewport.setActiveItem(this.recorderListView);
				}
			}]
		  });

		  this.addRecorderView = new Ext.Panel({
			dockedItems: [this.recorderAddTitle,this.form, this.recorderAddActions],
			layout: 'fit'
		  });


          nv.viewport = new Ext.Panel({
			layout:'card',
			fullscreen: true,
			cardSwitchAnimation:'slide',
			items:[this.recorderListView,this.addRecorderView]
		  });
      }
  });