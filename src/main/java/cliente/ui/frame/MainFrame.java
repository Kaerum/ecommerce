package cliente.ui.frame;

import interfaces.ContextoAutenticado;
import interfaces.RespostaServidor;
import interfaces.usuario.TipoUsuario;
import servidor.ServidorImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainFrame {
    ServidorImpl servidor = new ServidorImpl();

    private final JFrame frame = new JFrame();
    private final JPanel logInPanel = new JPanel();
    private final JPanel signInPanel = new JPanel();
    private final JPanel productsPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final Container contentPane = frame.getContentPane();
    private final JButton logInButton = new JButton("Logar");
    private final JButton signInButton = new JButton("Cadastrar");
    private final JButton editProductButton1 = new JButton("Editar");
    private final JButton removeProductButton1 = new JButton("Remover");
    private final JButton editProductButton2 = new JButton("Editar");
    private final JButton removeProductButton2 = new JButton("Remover");
    private final JButton editProductButton3 = new JButton("Editar");
    private final JButton removeProductButton3 = new JButton("Remover");
    private final JButton editProductButton4 = new JButton("Editar");
    private final JButton removeProductButton4 = new JButton("Remover");
    private final JButton editProductButton5 = new JButton("Editar");
    private final JButton removeProductButton5 = new JButton("Remover");
    private final JTextField logInTextField = new JTextField("Nome de Usuário");
    private final JTextField signInTextField = new JTextField("Nome de Usuário");
    private final JLabel filterByLabel = new JLabel("Filtrar por");
    private final JLabel ordedByLabel = new JLabel("Ordenar por");
    private final JLabel numberOfEntriesLabel = new JLabel("Número de Produtos por Página");
    private final JComboBox<TipoUsuario> signInComboBox = new JComboBox<>();
    private final JComboBox filterByComboBox = new JComboBox();
    private final JComboBox orderByComboBox = new JComboBox();
    private final JSpinner numberOfEntriesSpinner = new JSpinner();
    private final JScrollPane scrollPane = new JScrollPane();

    public MainFrame() {
        init();
        cardLayout.show(contentPane,IdentificadorDePainel.LOGIN.toString());
    }

    private void init() {
        logInPanelSetup();
        signInPanelSetup();
        productsPanelSetup();
        contentPaneSetup();
        frameSetup();
    }
    public JFrame getFrame() {
        return frame;
    }
    public void goToPanel(IdentificadorDePainel identificador) {
        cardLayout.show(frame,identificador.toString());
    }
    private void frameSetup() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);

    }
    private void contentPaneSetup() {
        contentPane.setLayout(cardLayout);
        contentPane.setPreferredSize(new Dimension(800,800));
        contentPane.add(logInPanel,IdentificadorDePainel.LOGIN.toString());
        contentPane.add(signInPanel,IdentificadorDePainel.SIGNIN.toString());
        contentPane.add(productsPanel,IdentificadorDePainel.PRODUTOS.toString());
    }
    javax.swing.GroupLayout logInPanelLayout = new javax.swing.GroupLayout(logInPanel);
    private void logInPanelSetup() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                logInAction();
            }
        });
        logInTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                logInAction();
            }
        });
        logInPanel.setLayout(logInPanelLayout);
        logInPanelLayout.setHorizontalGroup(
                logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(logInPanelLayout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(logInButton)
                                        .addComponent(logInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        logInPanelLayout.setVerticalGroup(
                logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(logInPanelLayout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(logInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(logInButton)
                                .addContainerGap(25, Short.MAX_VALUE))
        );
    }
    private void logInAction() {
        String userInput = logInTextField.getText();
        var respostaServidor = servidor.logar(userInput);
//        if (false) {
        if(respostaServidor.valor().isPresent()) {
            cardLayout.show(contentPane, IdentificadorDePainel.PRODUTOS.toString());
        } else {
            Object[] options = {"Cadastrar","Retornar ao Login"};
            int optionPaneResponse = JOptionPane.showOptionDialog(frame,
                    "Usuário não encontrado. Deseja se cadastrar?",
                    "Usuário não encontrado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            switch (optionPaneResponse) {
                case javax.swing.JOptionPane.YES_OPTION:
                    cardLayout.show(contentPane, IdentificadorDePainel.SIGNIN.toString());
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        }
    }
    javax.swing.GroupLayout signInPanelLayout = new javax.swing.GroupLayout(signInPanel);
    private void signInPanelSetup() {
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signInAction();
            }
        });
        signInTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signInAction();
            }
        });
        signInComboBox.addItem(TipoUsuario.Cliente);
        signInComboBox.addItem(TipoUsuario.Administrador);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
                signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signInPanelLayout.createSequentialGroup()
                                .addContainerGap(19, Short.MAX_VALUE)
                                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(signInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(signInComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(signInButton))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        signInPanelLayout.setVerticalGroup(
                signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signInPanelLayout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(signInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(signInComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(signInButton)
                                .addContainerGap(38, Short.MAX_VALUE))
        );
    }
    private void signInAction() {
        String userInput = signInTextField.getText();
        if (false) {
            JOptionPane.showMessageDialog(contentPane, "Cadastro efetuado com sucesso. Confirme para retornar a página de login.");
            cardLayout.show(contentPane, IdentificadorDePainel.LOGIN.toString());
        } else {
            Object[] options = {"Tentar novamente","Abortar"};
            int optionPaneResponse = JOptionPane.showOptionDialog(frame,
                    "Falha no cadastro. Deseja tentar novamente?",
                    "Falha no cadastro",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            switch (optionPaneResponse) {
                case JOptionPane.YES_OPTION -> cardLayout.show(contentPane, IdentificadorDePainel.SIGNIN.toString());
                case JOptionPane.NO_OPTION -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
    javax.swing.GroupLayout productsPanelLayout = new javax.swing.GroupLayout(productsPanel);
    private void productsPanelSetup() {
        productsPanel.setLayout(productsPanelLayout);
        productsPanelLayout.setHorizontalGroup(
                productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(productsPanelLayout.createSequentialGroup()
                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(editProductButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(removeProductButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(editProductButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(removeProductButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(editProductButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(removeProductButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(editProductButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(removeProductButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(editProductButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(removeProductButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(ordedByLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsPanelLayout.createSequentialGroup()
                                                                .addComponent(filterByLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(filterByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(numberOfEntriesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(numberOfEntriesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        productsPanelLayout.setVerticalGroup(
                productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(productsPanelLayout.createSequentialGroup()
                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(numberOfEntriesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(numberOfEntriesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(filterByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(filterByLabel)))
                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ordedByLabel))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(productsPanelLayout.createSequentialGroup()
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(editProductButton1)
                                                        .addComponent(removeProductButton1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(editProductButton2)
                                                        .addComponent(removeProductButton2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(editProductButton3)
                                                        .addComponent(removeProductButton3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(editProductButton4)
                                                        .addComponent(removeProductButton4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(editProductButton5)
                                                        .addComponent(removeProductButton5)))
                                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}