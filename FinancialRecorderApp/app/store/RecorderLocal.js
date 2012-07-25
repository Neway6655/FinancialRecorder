Ext.define('FinancialRecorderApp.store.RecorderLocal', {
    extend: 'Ext.data.Store',
	requires: [
        'FinancialRecorderApp.model.Recorder'
    ],	
    
    config: {
        model: 'FinancialRecorderApp.model.Recorder',
        data: [
            { name: "TestActivityA", totalFee: 100, userNameList: ["Neway","Fred"] },
            { name: "TestActivityB", totalFee: 200, userNameList: ["Neway","Evan"] }
        ]		
    }
});
