<html>
<head>
	<script type="text/javascript" src="js/ExtJS/ext-all-debug.js"></script>
	<link rel="stylesheet" 
		type="text/css" href="js/ExtJS/ext-theme-neptune/ext-theme-neptune-all-debug.css">
		
	<script type="text/javascript">
		Ext.onReady(function() {
			Ext.create('Ext.Viewport', {
				id: 'main-container',
				layout: 'border',
				height: '100%',
				items: [{
					xtype: 'box',
		            id: 'header',
		            region: 'north',	            
		            html: '<h1>Systembank</h1>',
		            style: {
		            	color: '#FFF'
		            },
		            height: 60
		        }, {
	                title: 'Menú Principal',
	                width: 150,
	                collapsible: true,
	                resizable: false,
	                region: 'west',                               
	                items: {
	                    xtype: 'menu',
		                autoWidth: true,			
					    floating: false, // true: posicion abosoluta, false: para comportarse como compinente hijo
					    border: 0,
					    items: [{
					        text: 'Clientes',
					        href: '#'
					    },{
					        text: 'Cuentas',
					        href: '#'
					    },{
					        text: 'Movimientos',
					        href: '#'
					    }]
	                }
	            }, {
					id: 'content-panel',
					region: 'center',				
					layout: 'fit', 
					title: 'Bienvenidos',
					contentEl: 'content'
				}],
				renderTo: Ext.getBody()
			});
	
		});

	</script>
</head>	
<body>
	<div id="content">
		<img width="500 px;" src="imagenes/logo.jpg">
	</div>

</body>
</html>
