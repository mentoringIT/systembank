Ext.require([    
    'Ext.toolbar.Paging'
]);

Ext.onReady(function(){
	
	Ext.define('Cliente', {
		extend: 'Ext.data.Model',
		fields: ['id', 
	         'nombre', 
	         'apellidoPaterno', 
	         'apellidoMaterno',
	         'edad',
	         'banco',
	         'cuentas'
         ]
	});
	
	var storeClientes = new Ext.data.JsonStore({
		storeId:'clientes-store',
		model: 'Cliente',
		proxy: {
			type: 'ajax',
			url: 'clientes/listarClientes.do',
			reader: {
				type: 'json',
				root: 'clientes'				
			}
		},
		autoLoad: true,
        pageSize: 1
	});
	
	var storeBancos = new Ext.data.Store({
		storeId:'bancos-store',
		proxy: {
			type: 'ajax',
			url: 'bancos.do',
			reader: {
				type: 'json',
				root: 'bancos'				
			}
		},
		autoLoad: true,
        fields: ["banco"]
	});
	
	var columnModel = [
	      {
	    	  text: 'Nombre',
	    	  dataIndex: 'nombre'
	      }, {
	    	  text: 'A. Paterno',
	    	  dataIndex: 'apellidoPaterno'
	      }, {
	    	  text: 'A. Materno',
	    	  dataIndex: 'apellidoMaterno'
	      }, {
	    	  text: 'Edad',
	    	  dataIndex: 'edad'
	      }, {
	    	  text: 'Banco',
	    	  dataIndex: 'banco'
	      }            
	];
	
	var window = new Ext.window.Window({
		title: 'Nuevo Cliente',
		closable: false,
		width: 300,
		height: 500,
		layout: 'fit',
		closeAction: 'hide',
		items: {
			xtype: 'form',
			id: 'cliente-form',			
			items: [			    
			    {
			    	xtype: 'textfield',
			    	name: 'apellidoPaterno',
			    	fieldLabel: 'A. Paterno',
			    	allowBlank: false
			    },
			    {
			    	xtype: 'textfield',
			    	name: 'apellidoMaterno',
			    	fieldLabel: 'A. Materno' 
			    },
			    {
			    	xtype: 'textfield',
			    	name: 'nombre',
			    	fieldLabel: 'Nombre',
			    	allowBlank: false
			    },
			    {
			    	xtype: 'numberfield',
			    	name: 'edad',
			    	fieldLabel: 'Edad',
			    	minValue: 18,
			    	allowDecimals: false,
			    	allowBlank: false
			    },
			    {
			    	xtype: 'combo',
			    	name: 'bancoId',
			    	fieldLabel: 'Banco',
			    	store: storeBancos,
			    	displayField: 'banco',
			    	valueField: 'id',
			    	allowBlank: false
			    }
			]
		},
		buttons: [
		    {
		    	text: 'Guardar',
		    	handler: function(){
		    		var form = Ext.getCmp('cliente-form').getForm();
		    		if(form.isValid()){		    		
		    			form.submit({
		    				url: 'clientes/nuevoCliente.do',
		    				method: 'POST',
		    				success: function(form, action) {
		    					Ext.MessageBox.show({
				    				title: 'Nuevo Cliente',		    				
				    				icon: Ext.Msg.info, 
				    				buttons: Ext.Msg.OK,
				    				msg: 'Se agrego el Nuevo Cliente'
				    			})
		    					window.close();
		    					storeClientes.reload();
		    				},
		    				failure: function(form, action) {
		    					Ext.Msg.alert(action.result.error);
		    				}
		    			});
		    		} else {
		    			Ext.MessageBox.show({
		    				title: 'Nuevo Cliente',		    				
		    				icon: Ext.Msg.WARNING, 
		    				buttons: Ext.Msg.OK,
		    				msg: 'Compruebe los Datos Faltantes'
		    			})
		    		}
		    	}
		    },
		    {
		    	text: 'Cancelar',
		    	handler: function(){
		    		Ext.getCmp('cliente-form').getForm().reset();
		    		window.close();
		    	}
		    }
		]
	});
	
	var clientes = new Ext.grid.Panel({
		title: 'Clientes',
		layout: 'fit',
		store: storeClientes,
		columns: columnModel,
		emptyText: 'Sin informacion', 
		bbar: Ext.create('Ext.PagingToolbar', {
			store: storeClientes,
			displayInfo: true,
			displayMsg: 'Mostroando <b>{0}</b> - {1} of {2} Clientes',
            emptyMsg: 'No hay Clientes que mostrar'
		}),
		tbar: [
		   {
			   text: 'Nuevo',
			   handler: function(){
				   window.show();
			   }
		   },
		   {
			   text: 'Editar',
			   handler: function(){
				   alert('Boton Editar');					  
			   }
		   },
		   {
			   text: 'Eliminar',
			   handler: function(){
				   alert('Boton Eliminar');					  
			   }
		   }
		       
        ]
	});
	
	Ext.getCmp('content-panel').removeAll();
	Ext.getCmp('content-panel').add(clientes);
	
});