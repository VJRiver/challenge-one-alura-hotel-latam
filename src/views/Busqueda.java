package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdbc.controller.HuespedesController;
import jdbc.controller.ReservasController;
import jdbc.modelo.HuespedesModelo;
import jdbc.modelo.ReservasModelo;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController;
	private HuespedesController huespedesController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Busqueda() {
	    try {
            this.reservasController = new ReservasController();
            this.huespedesController = new HuespedesController();
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		establecerColumnasReserva();
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    resetTablaHuespedes();
			    resetTablaReservas();
			    if(txtBuscar.getText().isEmpty() && panel.getSelectedIndex() == 0) {
			        llenarTablaReservas();
			        
			    }else if((!txtBuscar.getText().isEmpty()) && panel.getSelectedIndex() == 0){
			        int idIngresado;
                    try {
                        idIngresado = Integer.parseInt(txtBuscar.getText());
                        llenarTablaReservasId(idIngresado);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showInternalMessageDialog(null, "Entrada no válida, favor de ingresar únicamente números", "Error", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(e1);
                    }
			    }else if(txtBuscar.getText().isEmpty() && panel.getSelectedIndex() == 1) {
			        llenarTablaHuespedes();
			    }else if((!txtBuscar.getText().isEmpty()) && panel.getSelectedIndex() == 1) {
	                 String apellidoIngresado;
	                    try {
	                        apellidoIngresado = txtBuscar.getText();
	                        llenarTablaHuespedesApellido(apellidoIngresado);
	                    } catch (NumberFormatException e1) {
	                        JOptionPane.showInternalMessageDialog(null, "Entrada no válida, favor de ingresar sólo texto", "Error", JOptionPane.ERROR_MESSAGE);
	                        throw new RuntimeException(e1);
	                    }                    
                }
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	System.out.println("Panel seleccionado: " + panel.getSelectedIndex());
		    	if(panel.getSelectedIndex() == 0) {
		    		
	                modificarReservas();
	                resetTablaReservas();
	                llenarTablaReservas();
		    	}
                if(panel.getSelectedIndex() == 1) {
    				modificarHuespedes();
    				resetTablaHuespedes();
    				llenarTablaHuespedes();
                }

		    }
        });
		
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		btnEliminar.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(panel.getSelectedIndex() == 0) {
						if(noTieneReservaElegida()) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para eliminar");
							return;
						}
						int id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						System.out.println("ID-> " + id);
						int renglonesAfectados = reservasController.eliminar(id);
		                resetTablaReservas();
		                llenarTablaReservas();
		                JOptionPane.showMessageDialog(panel, renglonesAfectados + " renglón eliminado con id " + id);
					}
					if(panel.getSelectedIndex() == 1) {
						if(noTieneHuespedElegido()) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para eliminar");
							return;
						}
						int id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						System.out.println("ID-> " + id);
						int renglonesAfectados = huespedesController.eliminar(id);
		                resetTablaHuespedes();
		                llenarTablaHuespedes();
		                JOptionPane.showMessageDialog(panel, renglonesAfectados + " usuario eliminado con id " + id);
					}
					}
				
			});
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}	
	

    private void establecerColumnasReserva() {
        modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
    }
	
	private List<ReservasModelo> buscarReservas(){
	    return this.reservasController.buscar();	   
	}
	
	private List<ReservasModelo> buscarId(int id){
	    return this.reservasController.buscarId(id);
	}
	
	private List<HuespedesModelo> buscarHuespedes(){
	    return this.huespedesController.buscarHuespedes();
	}
	
	   private List<HuespedesModelo> buscarHuespedesApellido(String apellido){
	        return this.huespedesController.buscarHuespedesApellido(apellido);
	    }
	
	private void llenarTablaReservas() {

	    List<ReservasModelo> reserva = buscarReservas();
	        try {
	            for(ReservasModelo r: reserva) {	                
	                modelo.addRow(new Object[] {r.getId(), r.getFechaEntrada(), r.getFechaSalida(), r.getValor(), r.getFormaDePago()});
	            }
	        }catch(Exception e) {
	            throw new RuntimeException(e);
	        }    
	}
	
	   private void llenarTablaReservasId(int id) {    

	        List<ReservasModelo> reserva = buscarId(id);  
	            try {
	                for(ReservasModelo r: reserva) {                    
	                    modelo.addRow(new Object[] {r.getId(), r.getFechaEntrada(), r.getFechaSalida(), r.getValor(), r.getFormaDePago()});
	                }
	            }catch(Exception e) {
	                throw new RuntimeException(e);
	            }    
	    }
	   
	   private void llenarTablaHuespedes() {
	        List<HuespedesModelo> huespedes = buscarHuespedes();
            try {
                for(HuespedesModelo h: huespedes) {                    
                    modeloHuesped.addRow(new Object[] {h.getId(), h.getNombre(), h.getApellido(), h.getFechaNacimiento(), h.getNacionalidad(), h.getTelefono(), h.getIdReserva()});
                }
            }catch(Exception e) {
                throw new RuntimeException(e);
            }    
	   }
	   
        private void resetTablaReservas() {
            int numeroDeFilas = modelo.getRowCount();

            if (numeroDeFilas > 0) {
//                System.out.println("Tamaño de modelo reservas antes del for: " + modelo.getRowCount());
                for (int i = numeroDeFilas; i >= 1; i--) {
//                    System.out.println("Iteracion: " + i + ", filas totales: " + modelo.getRowCount());
                    modelo.removeRow(i - 1);

                }
                modelo.fireTableStructureChanged();
//                System.out.println("Tamaño de modelo reservas despues del for: " + modelo.getRowCount());
            }
        }
        
        private void llenarTablaHuespedesApellido(String apellido) {    

            List<HuespedesModelo> huesped = buscarHuespedesApellido(apellido);  
                try {
                    for(HuespedesModelo h: huesped) {                    
                        modeloHuesped.addRow(new Object[] {h.getId(), h.getNombre(), h.getApellido(), h.getFechaNacimiento(), h.getNacionalidad(), h.getTelefono(), h.getIdReserva()});
                    }
                }catch(Exception e) {
                    throw new RuntimeException(e);
                }    
        }
        
        private void resetTablaHuespedes() {
            int numeroDeFilas = modeloHuesped.getRowCount();

            if (numeroDeFilas > 0) {
//                System.out.println("Tamaño de modelo huesped antes del for: " + modeloHuesped.getRowCount());
                for (int i = numeroDeFilas; i >= 1; i--) {
//                    System.out.println("Iteracion: " + i + ", filas totales: " + modeloHuesped.getRowCount());
                    modeloHuesped.removeRow(i - 1);

                }
                modeloHuesped.fireTableStructureChanged();
//                System.out.println("Tamaño de modelo huesped despues del for: " + modeloHuesped.getRowCount());
            }
        }
        
        private void modificarReservas() {
        	System.out.println("Valor de noTieneReservaElegida() -> " + noTieneReservaElegida());
            if (noTieneReservaElegida()) {
                JOptionPane.showMessageDialog(this, "Por favor, elige un item");
                return;
            }
            Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                    .ifPresentOrElse(fila -> {
                        Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                        Date fechaEntrada = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
                        Date fechaSalida = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
                        String valor = modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
                        String formaDePago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
                        var filasModificadas = this.reservasController.modificar(id, fechaEntrada, fechaSalida, valor, formaDePago);
                        System.out.println("modificar() en búsqueda");
                        System.out.println("Variable valor -> "+valor);
                        System.out.println("Variable formaDePago -> "+formaDePago);

                        JOptionPane.showMessageDialog(this, filasModificadas + " Reserva " + String.valueOf(id) + " modificada");
                    }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        }
        
        private void modificarHuespedes() {
        	System.out.println("Valor de noTieneHuespedElegido() -> " + noTieneHuespedElegido());
            if (noTieneHuespedElegido()) {
                JOptionPane.showMessageDialog(this, "Por favor, elige un item");
                return;
            }
            Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                    .ifPresentOrElse(fila -> {
                        Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                        String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
                        String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
                        Date fechaNacimiento = (Date) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
                        String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
                        String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
                        Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
                        var filasModificadas = this.huespedesController.modificarHuesped(id, nombre, apellido, fechaNacimiento,  nacionalidad, telefono, idReserva);
                        System.out.println("modificar() en búsqueda");
                        System.out.println("Variable nombre -> "+ nombre);
                        System.out.println("Variable apellido -> "+ apellido);

                        JOptionPane.showMessageDialog(this, filasModificadas + " Reserva " + String.valueOf(id) + " modificada");
                    }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        }

        private boolean noTieneReservaElegida() {
        	
            return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;

        }
        
        private boolean noTieneHuespedElegido() {
        	
        	System.out.println("Numero de renglones elegidos: " + tbHuespedes.getSelectedRowCount());
        	System.out.println("Numero de columnas elegidas: " + tbHuespedes.getSelectedColumnCount());
            return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;

        }
        
        private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}









