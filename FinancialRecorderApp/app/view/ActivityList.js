Ext.define('FinancialRecorderApp.view.ActivityList', {
    extend: 'Ext.List',
	requires: ['FinancialRecorderApp.store.Recorder'],
    xtype: 'activitylist',    
    
    config: {
        title: 'Activities',
        loadingText: "Loading Activity List",
        emptyText: '<div><p>No Activity Found.</div>',
        itemTpl:
				'{name}---' + 
				'<small>TotalFee: {totalFee}</small></br>' + 
				'<small>({userNameList})</small>',
        store: Ext.create("FinancialRecorderApp.store.Recorder"),
        listeners: {
            itemtap: 'activityRecordTap'
        }
    },

    activityRecordTap: function (list, index, item, record) {
        console.log('activity record tapped.' + record.data.name);
        this.fireEvent('activityRecordTapEvent', this, record);
    }
});