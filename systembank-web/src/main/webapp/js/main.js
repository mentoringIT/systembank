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
            title: 'Menu Principal',
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
			        href: 'clientes.do'
			    },{
			        text: 'Cuentas',
			        href: 'cuentas.do'
			    },{
			        text: 'Movimientos',
			        href: '#'
			    }]
            }
        }, {
			id: 'content-panel',
			region: 'center',				
			layout: 'fit'								
		}],
		renderTo: Ext.getBody()
	});
});