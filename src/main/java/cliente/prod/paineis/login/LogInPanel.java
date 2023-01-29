package cliente.prod.paineis.login;

import cliente.prod.Cliente;
import cliente.prod.paineis.CustomPanel;
import javax.swing.*;


public class LogInPanel extends CustomPanel {
    private final String textoCampoUsuario = "Nome de Usuário";
    @SuppressWarnings("FieldCanBeLocal")
    private final String textoBotao = "Logar";

    public LogInPanel() {
        configuraComponenteDeBoato();
        configuraComponenteDeTexto();
    }

    @Override
    protected void addComponents() {

    }
    protected void configuraComponenteDeTexto() {
        campoNomeUsuario.setText(textoCampoUsuario);
        campoNomeUsuario.addActionListener(this::respostaAcaoDeTexto);
    }
    protected void configuraComponenteDeBoato() {
        botaoLogIn.setText(textoBotao);
        botaoLogIn.setActionCommand(campoNomeUsuario.getText());
        botaoLogIn.addActionListener(this::respostaAcaoDoBotao);
    }

    private void respostaAcaoDeTexto(java.awt.event.ActionEvent evt) {
        String textoInserido = evt.getActionCommand();
        if (textoInserido.equals(textoCampoUsuario)) {
            // nada foi inserido, deve mostrar um popup
        } else {
            if (Cliente.instance.logar(textoInserido)) {
                // vai pra janela do catalogo
            } else {
                // vai pra janela de cadastro
            }
        }
    }
    private void respostaAcaoDoBotao(java.awt.event.ActionEvent evt) {
        respostaAcaoDeTexto(evt);
    }
    private final JTextField campoNomeUsuario = new JTextField();
    private final JButton botaoLogIn = new JButton();

}
