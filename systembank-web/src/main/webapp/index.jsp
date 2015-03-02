<html>
<head>
	<script type="text/javascript" src="js/ExtJS/ext-all-debug.js"></script>	
	<link rel="stylesheet" 
		type="text/css" href="js/ExtJS/ext-theme-neptune/ext-theme-neptune-all-debug.css">
	
	<script type="text/javascript" src="js/main.js"></script>
		
	<script type="text/javascript">
	
	Ext.onReady(function(){
		
		var welcome = new Ext.panel.Panel({
			title: 'Bienvenidos',
			layout: 'fit',
			contentEl: 'content-div' 		
		});
		
		Ext.getCmp('content-panel').removeAll();
		Ext.getCmp('content-panel').add(welcome);
	});
	
	</script>
</head>
<body>

<div id="content-div">
	<img alt="logo" src="imagenes/logo.jpg" width="500px">
</div>

</body>
</html>