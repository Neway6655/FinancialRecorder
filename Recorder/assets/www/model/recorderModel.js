/**
 * @author eortwyz
 */
app.model.recorder = Ext.regModel('Recorder', {
	fields: ['id','name','price','description'],
	proxy: {
		type: 'localstorage',
		id  : 'recorder'
	}
});