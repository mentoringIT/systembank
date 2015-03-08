Ext.require([    
    'Ext.toolbar.Paging'
]);

	Ext.define('Cuenta', {
		extend: 'Ext.data.Model',
		fields: ['id', 
	         'numeroCuenta', 
	         'tipoCuenta', 
	         'saldo',
	         'idCliente',
	         'idTipoCuenta'
         ]
	});
	
	var storeCuentas = new Ext.data.JsonStore({
		storeId:'cuentas-store',
		model: 'Cuenta',
		proxy: {
			type: 'ajax',			
			url: 'cuentas/listarCuentas.do',
			reader: {
				type: 'json',
				root: 'cuentas'				
			}
		},
		autoLoad: false
	});
	
	var storeTipoCuenta = new Ext.data.Store({
		storeId:'tipoCuenta-store',
		proxy: {
			type: 'ajax',
			url: 'tipoCuenta.do',
			reader: {
				type: 'json',
				root: 'tipos'				
			}
		},
		autoLoad: true,
        fields: ['id', 'tipoCuenta']
	});
	
	var columnModel = [
	      {
	    	  text: 'Numero de Cuenta',
	    	  dataIndex: 'numeroCuenta',
	    	  editor: {
			    	xtype: 'textfield',			    	
			    	allowBlank: false
			    }
	      }, {
	    	  text: 'Tipo Cuenta',
	    	  dataIndex: 'tipoCuenta',
	    	  editor: {
			    	xtype: 'combo',
			    	name:'idTipoCuenta',
			    	store: storeTipoCuenta,
			    	displayField: 'tipoCuenta',
			    	valueField: 'id',
			    	allowBlank: false
			    }
	      }, {
	    	  text: 'Saldo',
	    	  dataIndex: 'saldo', 
	    	  editor: {
		    	xtype: 'numberfield',
		    	minValue: 0,
		    	allowBlank: false
		      }
	      }           
	];
	
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToEdit: 2
    });
	
    
    var grafica = Ext.create('Ext.chart.Chart', {
        style: 'background:#fff',
        animate: true,
        shadow: true,
        store: storeCuentas,
        axes: [{
            type: 'Numeric',
            position: 'bottom',
            fields: ['saldo'],
            label: {
                renderer: Ext.util.Format.numberRenderer('0,0')
            },
            title: 'Saldo',
            grid: true
        }, {
            type: 'Category',
            position: 'left',
            fields: ['numeroCuenta'],
            title: 'Cuenta'
        }],
        series: [{
            type: 'bar',
            axis: 'left',
            highlight: true,
            tips: {
              trackMouse: true,
              width: 140,
              height: 28,
              renderer: function(storeItem, item) {
                this.setTitle('Tipo: ' + storeItem.get('tipoCuenta'));
              }
            },
            label: {
              display: 'insideEnd',
              'text-anchor': 'middle',
                field: 'saldo',
                renderer: Ext.util.Format.numberRenderer('0'),
                orientation: 'vertical',
                color: '#333'
            },
            xField: 'numeroCuenta',
            yField: 'saldo'
        }]
    });
    
	var cuentas = new Ext.grid.Panel({
		title: 'Cuentas',
		layout: 'fit',
		store: storeCuentas,
		columns: columnModel,
		emptyText: 'Sin informacion', 
		bbar: Ext.create('Ext.PagingToolbar', {
			store: storeCuentas,
			displayInfo: true,
			displayMsg: 'Mostroando <b>{0}</b> - {1} of {2} cuentas',
            emptyMsg: 'No hay Cuentas que mostrar'
		}),
		tbar: [
			 {
			    	xtype: 'combo',
			    	id: 'combo-cliente',
			    	name: 'idCliente',
			    	fieldLabel: 'Cliente',
			    	store: storeClientes,
			    	displayField: 'nombreCompleto',
			    	valueField: 'id',
			    	listeners: {
			    		select: function(combo, records){	
			    			storeCuentas.load({	
			    				params: {			    					
			    					clienteId: combo.getValue()
			    				}
			    			});
			    			Ext.getCmp('boton-nueva').enable();
			    			Ext.getCmp('boton-grafica').enable();
			    		}
			 			
			    	}
			    },
		   {
			   text: 'Nueva',
			   id: 'boton-nueva',
			   disabled: true,
			   handler: function(){
				   var combo = Ext.getCmp('combo-cliente');
				   
				   var cta = Ext.create('Cuenta', {
					   id: 0,
					   idCliente:combo.getValue()
				   });
				   
				   rowEditing.cancelEdit();
	               storeCuentas.insert(0, cta);
                   rowEditing.startEdit(0, 0);

			   }
		   },
		   {
			   text: 'Grafica',
			   id: 'boton-grafica',
			   disabled: true,
			   handler: function(){
					var window = new Ext.window.Window({
						title: 'Cuentas Cliente',
						closable: false,
						width: 500,
						height: 300,
						layout: 'fit',
						items: grafica,
						buttons: [						    
						    {
						    	text: 'Cerrar',
						    	handler: function(){
						    		window.close();
						    	}
						    }
						]
					}).show();
			   }
		   }
		       
        ],
        plugins: [rowEditing],
        listeners: {
        	edit: function(editor, params) {
        		var cta = params.record.data;
        		if (cta.id == 0 ) {
        			Ext.Ajax.request({
    		    	    url: 'cuentas/nuevaCuenta.do',
    		    	    params: cta,
    		    	    success: function(response){
    		    	    	Ext.MessageBox.show({
    		    				title: 'Nueva Cuenta',		    				
    		    				icon: Ext.Msg.info, 
    		    				buttons: Ext.Msg.OK,
    		    				msg: 'Se agrego la nueva Cuenta'
    		    			});
    		    	    	storeCuentas.load({	
    		    				params: {			    					
    		    					clienteId: cta.idCliente
    		    				}
    		    			});
    		    	    },
    		    	    failure: function() {
    		    	    	Ext.MessageBox.show({
    		    				title: 'Nueva Cuenta',		    				
    		    				icon: Ext.Msg.info, 
    		    				buttons: Ext.Msg.OK,
    		    				msg: 'No se pudo agregar la Cuenta'
    		    			});
    		    	    }
    		    	});
        		}         	
        		storeCuentas.load({	
    				params: {			    					
    					clienteId: cta.idCliente
    				}
    			});
        	},
        	canceledit: function(editor, params){		
        		var cta = params.record.data;
        		if(cta.id == 0){
        			storeCuentas.removeAt(0);
        		}
        		
            }
        }
        
	});
