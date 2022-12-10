/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrirarchivos;
import Alertas.AlertInformation;
import Alertas.AlertSucess;
import Alertas.AlertWarning;
import Alertas.AlertWarningSave;
import componentes.FadeEffect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 24DD018LA
 */
public class frm_abrirarchivos extends javax.swing.JFrame {
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    private int x, y;
    private boolean minimiza = false;
    private int contadorNuevo = 0;
    public static boolean crearNuevo = false;
    public static boolean abrirArchivo = false;
    public static boolean actualizar = false;
    List<identificador> tokenslist;

    /**
     * Creates new form frm_abrirarchivos
     */
    public frm_abrirarchivos() {
        initComponents();
        
    }
  
  
    
    public String AbrirArchivo(File archivo){
        String documento ="";
        try{
        entrada = new FileInputStream(archivo);    
        int ascci;
        while((ascci=entrada.read())!=-1){
            char caracter =(char)ascci;
            documento+=caracter;
        }
        }catch(Exception e){
        }
        return documento;
    }
     private frm_abrirarchivos frmA;
    public static String ruta = "";
    
     public void LeerTxt(frm_abrirarchivos frmA) {
     this.frmA = frmA;
        seleccionar = new JFileChooser();
        seleccionar.setFileSelectionMode(0);
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("TXT", "txt");
        seleccionar.setFileFilter(filtroImagen);
        seleccionar.setDialogTitle("Abrir archivo");
        if (seleccionar.showOpenDialog(frmA) == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            frmA.lbl_nombre.setText(archivo.getName());
            ruta = seleccionar.getSelectedFile().toString();
            try {
                if (archivo.exists()) {
                    BufferedReader leeArchivo = new BufferedReader(new FileReader(archivo));
                    String Slinea, datos = "";
                    while ((Slinea = leeArchivo.readLine()) != null) {
                        datos = datos + Slinea + "\n";
                    }
                    frmA.txt_area.setText(datos);
                    frmA.txt_area.setEditable(true);
                    frmA.txt_area.requestFocus();
                    leeArchivo.close();
                    this.frmA.abrirArchivo = true;
                    this.frmA.crearNuevo = false;
                } else {
                    System.out.println("Fichero No Existe");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
     public void CrearTxt(frm_abrirarchivos frmA, String SCadena, String nombre) {
        this.frmA = frmA;
    
        seleccionar = new JFileChooser();
        seleccionar.setFileSelectionMode(0);
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("TXT", "txt");
        seleccionar.setFileFilter(filtroImagen);
        seleccionar.setDialogTitle("Guardar archivo " + nombre);
        seleccionar.setSelectedFile(new File(nombre));
        if (seleccionar.showSaveDialog(frmA) == JFileChooser.APPROVE_OPTION) {
            ruta = seleccionar.getSelectedFile().toString();
            archivo = new File(ruta);
            frmA.lbl_nombre.setText(archivo.getName());
            try {
                //Si Existe el fichero lo borra
                if (archivo.exists()) {
                    archivo.delete();
                }
                BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
                FileWriter escribirArchivo = new FileWriter(archivo, true);
                BufferedWriter buffer = new BufferedWriter(escribirArchivo);
                buffer.write(SCadena);
                buffer.newLine();
                buffer.close();
                wr.close();
                escribirArchivo.close();

                this.frmA.abrirArchivo = true;
                this.frmA.crearNuevo = false;
            } catch (Exception ex) {
            }
        }
    }
    public void GuardarTxt(String SCadena, String nombre) {

        System.out.println(ruta);
        archivo = new File(ruta);
        try {
            if (archivo.exists()) {
                archivo.delete();
            }
            BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
            FileWriter escribirArchivo = new FileWriter(archivo, true);
            BufferedWriter buffer = new BufferedWriter(escribirArchivo);
            buffer.write(SCadena);
            buffer.newLine();
            buffer.close();
            wr.close();
            escribirArchivo.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void Guardar2Txt(String SCadena, String nombre) {
        System.out.println(ruta);
        archivo = new File(ruta);
        try {
            if (archivo.exists()) {
                archivo.delete();
            }
            BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
            FileWriter escribirArchivo = new FileWriter(archivo, true);
            BufferedWriter buffer = new BufferedWriter(escribirArchivo);
            buffer.write(SCadena);
            buffer.newLine();
            buffer.close();
            wr.close();
            escribirArchivo.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
     
      public void probarLexerFile() throws IOException{
        int contIDs=0;
        tokenslist = new LinkedList<identificador>();
        File fichero = new File ("fichero.txt");
        if (abrirArchivo) {
            System.out.println("Existente");
            new frm_abrirarchivos().GuardarTxt(this.txt_area.getText(), this.lbl_nombre.getText());
        }
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(txt_area.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="\n";
        while (true){
            Token token =lexer.yylex();
            if (token == null){
                for(int i=0;i<tokenslist.size();i++){
                    System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                }
                txt_area.setText(resultado);
                return;
            }
            switch (token){
                
             
                case ERROR:
                    resultado=resultado+ "Error, simbolo no reconocido ";
                    break;
              
                case CADENA : {
                    contIDs++;
                    identificador tokenitem=new identificador();
                    tokenitem.nombre=lexer.lexeme;
                    tokenitem.ID=contIDs;
                    tokenslist.add(tokenitem);
                    if(resultado.contains(".")){
                    resultado= "<TOKEN: IDENTIFICADOR" + contIDs + " " ;
                    }else{
                    resultado= " ERROR" + contIDs + " " ;
                    }
                    break;
                }
                case NUM:
                    resultado="<TOKEN: DIGITO " + lexer.lexeme + " ";
                    break;
                default:
                    resultado=resultado+ "TOKEN <"+ lexer.lexeme + ">\n ";
            }
    }
 }
      
      public void probarNumFile() throws IOException{
        int contIDs = 0;
        tokenslist = new LinkedList<identificador>();
        File fichero = new File ("fichero.txt");
        if (abrirArchivo) {
            System.out.println("Existente");
            new frm_abrirarchivos().GuardarTxt(this.txt_area.getText(), this.lbl_nombre.getText());
        }
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(txt_area.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="\n";
        while (true){
            Token token =lexer.yylex();
            if (token == null){
                for(int i=0;i<tokenslist.size();i++){
                    System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                }
                txt_area.setText(resultado);
                return;
            }
            switch (token){
                
                case NUM:
                     contIDs++;
                     int Estado = 0;
                    identificador tokenitem=new identificador();
                    tokenitem.nombre=lexer.lexeme;
                    tokenitem.ID=contIDs;
                    tokenslist.add(tokenitem);
                    if(resultado.contains(".")){
                    resultado= "<TOKEN: IDENTIFICADOR" + contIDs + " " ;
                    }else{
                    resultado= " NO CONTIENE PUNTO" + contIDs + " " ;
                    }
                    
                    break;
                    
                case MENOSMAS:
                    resultado="<TOKEN: DIGITO" + lexer.lexeme + "";
                    System.out.println("EN ESTADO 3");
                    break;
                
                case ERROR:
                    resultado=resultado+ "Error, simbolo no reconocido ";
                    break;
                default:
                    resultado=resultado+ "TOKEN <"+ lexer.lexeme + ">\n ";
            }
    }
 }
      
      
public void tablaResultado(){
        Object[][] matriz = new Object [tokenslist.size()][2];
        for(int i =0; i<tokenslist.size();i++){
            matriz[i][0] = tokenslist.get(i).nombre;
            matriz[i][1] = tokenslist.get(i).ID;
        }
      
    }
   
    
    
     public void BorrarTxt(frm_abrirarchivos frmA) {
        this.frmA = frmA;
        archivo = new File(ruta);
        try {
           
            if (archivo.exists()) {
           
                archivo.delete();
                Alertas.AlertSucess a = new AlertSucess(this.frmA, true);
                a.lblMensaje1.setText("Archivo eliminado.");
                a.lblMensaje2.setText("");
                a.setVisible(true);
            }
        } catch (Exception ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
    }
     
        
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_area = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        btn_minimizar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        btn_cerrar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        btn_guardar_como = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        btn_abrirArchivo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        btn_guardar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        btn_eliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        btn_guardar1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_areaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txt_area);

        jScrollPane8.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minimize.png"))); // NOI18N
        btn_minimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimizarActionPerformed(evt);
            }
        });
        jScrollPane7.setViewportView(btn_minimizar);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dialog_close.png"))); // NOI18N
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        jScrollPane6.setViewportView(btn_cerrar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Yu Gothic Medium", 3, 12)); // NOI18N
        jLabel6.setText("Editor de Texto ");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/text.png"))); // NOI18N

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_guardar_como.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document_save_as.png"))); // NOI18N
        btn_guardar_como.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_comoActionPerformed(evt);
            }
        });
        jScrollPane3.setViewportView(btn_guardar_como);

        btn_abrirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder_open.png"))); // NOI18N
        btn_abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrirArchivoActionPerformed(evt);
            }
        });
        jScrollPane2.setViewportView(btn_abrirArchivo);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document_save.png"))); // NOI18N
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jScrollPane4.setViewportView(btn_guardar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jScrollPane5.setViewportView(btn_eliminar);

        jLabel2.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel2.setText("Abir archivo");

        jLabel5.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel5.setText("Guardar como");

        jLabel3.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel3.setText("Crear");

        jLabel4.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel4.setText("Automata");

        btn_guardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btn_guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar1ActionPerformed(evt);
            }
        });
        jScrollPane9.setViewportView(btn_guardar1);

        jLabel8.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel8.setText("Guardar ");

        jLabel9.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel9.setText("Eliminar");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/automata.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/number.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jScrollPane10.setViewportView(jButton2);

        jLabel10.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel10.setText("Numero");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane9)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        jLabel1.setText("Nombre del Archivo:");

        lbl_nombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lbl_nombre.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
         if (abrirArchivo) {
            System.out.println("Existente");
            new frm_abrirarchivos().GuardarTxt(this.txt_area.getText(), this.lbl_nombre.getText());
        }
        if (crearNuevo) {
            System.out.println("Nuevo");
            new frm_abrirarchivos().CrearTxt(this, this.txt_area.getText(), this.lbl_nombre.getText());
        }
        if (!crearNuevo && !abrirArchivo) {
            Alertas.AlertInformation a = new AlertInformation(this, true);
            a.lblMensaje1.setText("Debes crear o abrir un archvo");
            a.lblMensaje2.setText("para poder guardarlo.");
            a.setVisible(true);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abrirArchivoActionPerformed
        // TODO add your handling code here:
         new frm_abrirarchivos().LeerTxt(this);
    }//GEN-LAST:event_btn_abrirArchivoActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        Alertas.AlertWarning a = new AlertWarning(this, true);
        Alertas.AlertWarningSave a1 = new AlertWarningSave(this, true);
        if (crearNuevo && !abrirArchivo) {
            a1.lblMensaje1.setText("Aun no guardas el archivo nuevo.");
            a1.lblMensaje2.setText("¿Deseas guardarlo?.");
            a1.setVisible(true);
            if (AlertWarningSave.guardar) {
                new frm_abrirarchivos().CrearTxt(this, this.txt_area.getText(), this.lbl_nombre.getText());
                FadeEffect.fadeOutFrame(this, 50, 0.1f);
            }
            if (AlertWarningSave.noGuardar) {
                FadeEffect.fadeOutFrame(this, 50, 0.1f);
            }
        } else {
            a.lblMensaje1.setText("La aplicación terminara.");
            a.lblMensaje2.setText("");
            a.setVisible(true);
            if (AlertWarning.ok) {
                FadeEffect.fadeOutFrame(this, 50, 0.1f);
            }
        }
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        // TODO add your handling code here:
         this.setExtendedState(ICONIFIED);
        if (!minimiza) {
            minimiza = false;
        } else {
            minimiza = true;
        }
    }//GEN-LAST:event_btn_minimizarActionPerformed

    private void btn_guardar_comoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_comoActionPerformed
        // TODO add your handling code here:
        contadorNuevo++;
        this.lbl_nombre.setText("Archivo" + contadorNuevo + ".txt");
        this.txt_area.setText("");
        this.txt_area.setEditable(true);
        this.txt_area.requestFocus();
        crearNuevo = true;
        abrirArchivo = false;
    }//GEN-LAST:event_btn_guardar_comoActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (abrirArchivo) {
            new frm_abrirarchivos().BorrarTxt(this);
            this.lbl_nombre.setText("");
            this.txt_area.setText("");
        }else{
            Alertas.AlertInformation a = new AlertInformation(this, true);
            a.lblMensaje1.setText("Debes abrir un archvo");
            a.lblMensaje2.setText("para poder eliminarlo.");
            a.setVisible(true);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_areaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_areaMousePressed
        // TODO add your handling code here:
         if (!this.txt_area.isEditable()) {
            Alertas.AlertInformation a = new AlertInformation(this, true);
            a.lblMensaje1.setText("Para poder escribir debes");
            a.lblMensaje2.setText("crear un nuevo archivo.");
            a.setVisible(true);
        }
          try{
             probarLexerFile();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        tablaResultado();
    }//GEN-LAST:event_txt_areaMousePressed

    private void btn_guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar1ActionPerformed
        // TODO add your handling code here:
        if (abrirArchivo) {
            System.out.println("Existente");
            new frm_abrirarchivos().GuardarTxt(this.txt_area.getText(), this.lbl_nombre.getText());
        }
        if (crearNuevo) {
            System.out.println("Nuevo");
            new frm_abrirarchivos().CrearTxt(this, this.txt_area.getText(), this.lbl_nombre.getText());
        }
       
    }//GEN-LAST:event_btn_guardar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new frm_abrirarchivos().setVisible(true);
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_abrirArchivo;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_guardar1;
    private javax.swing.JButton btn_guardar_como;
    private javax.swing.JButton btn_minimizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public static javax.swing.JLabel lbl_nombre;
    private javax.swing.JTextPane txt_area;
    // End of variables declaration//GEN-END:variables
}
