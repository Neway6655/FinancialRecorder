Ext.define('FinancialRecorderApp.view.UserSelector', {
    extend: 'Ext.List',
    alias: 'widget.userselector',

    config: {
        title: 'Select the participants',
        allowDeselect: true,
        mode: 'MULTI',
        itemTpl: '{name}',
        store: Ext.create("FinancialRecorderApp.store.User"),
        listeners: {
            select: 'userSelected',
        }
    },

    userSelected: function(list, record, item, a, b, c) {
        console.log("itemtap: " + record.data.name);
        // this.fireEvent('friendSelectedEvent', record.data.name, item);
    },
});