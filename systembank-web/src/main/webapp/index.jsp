<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/ExtJS/ext-all-debug.js"></script>
	<link rel="stylesheet" 
		type="text/css" href="js/ExtJS/ext-theme-neptune/ext-theme-neptune-all-debug.css">
		
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		Ext.onReady(function() {
			
			var welcome = new Ext.panel.Panel({
				title: 'Bienvenidos',
				layout: 'fit',
				contentEl: 'content'
			});
			
			Ext.getCmp('content-panel').removeAll();
			Ext.getCmp('content-panel').add(welcome);
		
		});
	</script>
</head>	
<body>
	<div id="content">
		<img width="500 px;" src="imagenes/logo.jpg">
	</div>

</body>
</html>