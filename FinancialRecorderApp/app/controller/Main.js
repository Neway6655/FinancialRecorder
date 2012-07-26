Ext.define('FinancialRecorderApp.controller.Main', {
    extend: 'Ext.app.Controller',
	
	requires: [		
		'FinancialRecorderApp.view.ActivityList',
		'FinancialRecorderApp.view.ActivityDetail'
    ],

    config: {
        refs: {           
			activityList: {
				xtype: 'activitylist'
			},
			activityDetail: {
				xtype: 'activitydetail'
			}
        },
        control: {
            activitylist: {
                itemtap: 'showDetail'
            }
        }
    },

    showDetail: function(list, record) {
		alert(record);
		//this.getActivityDetail().load(record.getData());
        this.getMain().push({
            xtype: 'activitydetail',
            title: 'Detail',
            data: record.getData()
        })
    }
});
