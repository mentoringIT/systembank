Ext.require([    
    'Ext.toolbar.Paging'
]);


	Ext.define('Cliente', {
		extend: 'Ext.data.Model',
		fields: ['id', 
	         'nombre', 
	         'apellidoPaterno', 
	         'apellidoMaterno',
	         'edad',
	         'banco',
	         'cuentas',
	         {
				name: 'nombreCompleto',
				convert: function( value , record ) {
			       return record.get( 'nombre' ) + ' ' + record.get( 'apellidoPaterno' )
			    }
	         }
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
		autoLoad: true
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
	
	var formClientes = {
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
			    }, 
			    {
			    	xtype: 'hidden',
			    	name: 'id'
			    }
			]
		};
	
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
			   handler: function() {
					var window = new Ext.window.Window({
						title: 'Nuevo Cliente',
						closable: false,
						width: 300,
						height: 500,
						layout: 'fit',
						items: formClientes,
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
					}).show();
			   }
		   },
		   {
			   text: 'Editar',
			   handler: function(){
				   var selModel = clientes.getSelectionModel();
				   if(Ext.isEmpty(selModel.getLastSelected())) {
					   Ext.MessageBox.show({
		    				title: 'Editar Cliente',		    				
		    				icon: Ext.Msg.WARNING, 
		    				buttons: Ext.Msg.OK,
		    				msg: 'Debe seleccionar un registro'
		    			});
				   } else {
					   var record = selModel.getLastSelected();
					   var window = new Ext.window.Window({
							title: 'Editar Cliente',
							closable: false,
							width: 300,
							height: 500,
							layout: 'fit',
							items: formClientes,
							buttons: [
							    {
							    	text: 'Guardar',
							    	handler: function(){		
							    		var form = Ext.getCmp('cliente-form').getForm();
							    		if(form.isValid()){		    		
							    			form.submit({
							    				url: 'clientes/actualizaCliente.do',
							    				method: 'POST',
							    				success: function(form, action) {
							    					Ext.MessageBox.show({
									    				title: 'Editar Cliente',		    				
									    				icon: Ext.Msg.info, 
									    				buttons: Ext.Msg.OK,
									    				msg: 'Se actualizo la informacion Cliente'
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
							    				title: 'Editar Cliente',		    				
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
						}).show();
					   Ext.getCmp('cliente-form').getForm().loadRecord(record);
				   }
				   
			   }
		   },
		   {
			   text: 'Eliminar',
			   handler: function(){
				   var selModel = clientes.getSelectionModel();
				   if(Ext.isEmpty(selModel.getLastSelected())) {
					   Ext.MessageBox.show({
		    				title: 'Eliminar Cliente',		    				
		    				icon: Ext.Msg.WARNING, 
		    				buttons: Ext.Msg.OK,
		    				msg: 'Debe seleccionar un registro'
		    			});
				   } else {
					   Ext.MessageBox.show({
		    				title: 'Eliminar Cliente',		    				
		    				icon: Ext.Msg.QUESTION, 
		    				buttons: Ext.Msg.YESNO,
		    				msg: 'Confirma que desea eliminar este registro?',
		    				fn: function(btn){
		    				    if (btn == 'yes'){
		    				    	var record = selModel.getLastSelected();
		    				    	Ext.Ajax.request({
		    				    	    url: 'clientes/eliminarCliente.do',
		    				    	    params: {
		    				    	        id: record.data.id
		    				    	    },
		    				    	    success: function(response){
		    				    	    	Ext.MessageBox.show({
							    				title: 'Eliminar Cliente',		    				
							    				icon: Ext.Msg.info, 
							    				buttons: Ext.Msg.OK,
							    				msg: 'Se elimino el Cliente'
							    			});
					    					storeClientes.reload();
		    				    	    },
		    				    	    failure: function() {
		    				    	    	Ext.MessageBox.show({
							    				title: 'Eliminar Cliente',		    				
							    				icon: Ext.Msg.info, 
							    				buttons: Ext.Msg.OK,
							    				msg: 'No se pudo eliminar el registro'
							    			});
		    				    	    }
		    				    	});
		    				    }
		    				}
		    			});
				   }
			   }
		   }
		       
        ]
	});
	
