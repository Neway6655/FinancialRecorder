Ext.define('FinancialRecorderApp.controller.Activity', {
    extend: 'Ext.app.Controller',

    launch: function () {
       this.callParent();
       console.log("financial record launch");
    },

    init: function () {
       this.callParent();
       console.log("financial record init");
    },

    config: {
        refs: {
          main: 'mainpanel',
    			activityList: 'activitylist',
    			activityDetail: 'activitydetail',
          userSelector: 'userselector',
  			},
        control: {
          activityList: {
            activityRecordTapEvent: 'onActivityRecordTap',
          },
          activityDetail: {
            backToActivityListEvent: 'onBackToActivityList',
          }
        }
    },

    slideLeftTransition: { type: 'slide', direction: 'left' },

    slideRightTransition: { type: 'slide', direction: 'right' },

    onActivityRecordTap: function(list, record) {
        if (!this.getActivityDetail()){
          this.activityDetail = Ext.create('FinancialRecorderApp.view.ActivityDetail');
        }
    		this.getActivityDetail().loadRecord(record);
        // this.getUserSelector().setData('{name: "Neway", name: "Fred"}');
        // this.getActivityDetail().setRecord(record);
        Ext.Viewport.animateActiveItem(this.getActivityDetail(), this.slideLeftTransition);
    },

    onBackToActivityList: function() {
      Ext.Viewport.animateActiveItem(this.getMain(), this.slideRightTransition);
    },

});
